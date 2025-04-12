package io.pipecrafts.commons.tools.error;

public class BhDuplicateResourceException extends RuntimeException {

  public <T, V> BhDuplicateResourceException(Class<T> clazz, String key, V value) {
    super(String.format("Duplicate resource of type %s with [<%s>: <%s>]", clazz.getSimpleName(), key, value));
  }

}
