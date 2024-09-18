package io.pipecrafts.commons.data.page;

import java.util.List;

public record PageData<T>(
  int pageNumber,
  List<T> data
) {

  public static <T> PageData<T> of(int pageNumber, List<T> data) {
    return new PageData<>(pageNumber, data);
  }
}
