package io.pipecrafts.commons.tools.error;

public class BhResourceNotFoundException extends RuntimeException {

  private static final String ID_KEY = "id";

  public <T> BhResourceNotFoundException(Class<T> clazz, String key, String value) {
    super(formatMessage(clazz, key, value));
  }

  public static <T> BhResourceNotFoundException ofId(Class<T> clazz, Long value) {
    return new BhResourceNotFoundException(clazz, ID_KEY, String.valueOf(value));
  }

  private static <T> String formatMessage(Class<T> clazz, String key, String value) {
    return String.format("Resource of type %s with [<%s>: <%s>] not found", clazz.getSimpleName(), key, value);
  }
}
