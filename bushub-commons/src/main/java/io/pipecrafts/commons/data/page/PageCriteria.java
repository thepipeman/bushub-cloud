package io.pipecrafts.commons.data.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageCriteria implements Serializable {

  @Min(0)
  @Builder.Default
  private Integer pageNumber = 0;

  @Min(1)
  @Builder.Default
  private Integer pageSize = 100;

  @JsonIgnore
  public int getOffset() {
    return pageSize * pageNumber;
  }
}
