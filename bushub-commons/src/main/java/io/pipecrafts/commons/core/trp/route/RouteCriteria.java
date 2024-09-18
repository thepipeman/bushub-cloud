package io.pipecrafts.commons.core.trp.route;

import io.pipecrafts.commons.data.page.PageCriteria;

public record RouteCriteria(

  String origin,
  String destination
) implements PageCriteria {
}
