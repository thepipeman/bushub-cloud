package io.pipecrafts.commons.data.page;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


public interface PageCriteria extends Serializable {

  default Integer pageNumber() {
    return 0;
  }

  default Integer pageSize() {
    return 100;
  }

  @JsonIgnore
  default int offSet() {
    return pageNumber() * pageSize();
  }
}
