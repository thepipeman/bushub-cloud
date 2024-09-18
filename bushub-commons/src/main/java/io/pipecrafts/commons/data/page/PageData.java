package io.pipecrafts.commons.data.page;

import java.util.List;

public record PagedData<T>(
  int pageNumber,
  List<T> data
) {
}
