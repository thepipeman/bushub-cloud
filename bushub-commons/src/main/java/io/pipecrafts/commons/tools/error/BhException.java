package io.pipecrafts.commons.tools.error;

public class BhException extends RuntimeException{

  public BhException(String message, Exception cause){
    super(message, cause);
  }
}