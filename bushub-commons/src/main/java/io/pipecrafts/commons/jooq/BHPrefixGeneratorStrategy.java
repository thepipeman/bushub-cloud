package io.pipecrafts.commons.jooq;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class BHPrefixGeneratorStrategy extends DefaultGeneratorStrategy {

  @Override
  public String getJavaClassName(final Definition definition, final Mode mode) {
    return "BH" + super.getJavaClassName(definition, mode);
  }
}
