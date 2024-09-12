package io.pipecrafts.commons.tools.spring.error;

import io.pipecrafts.commons.tools.error.BhErrorCode;
import io.pipecrafts.commons.tools.error.BhException;
import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import io.pipecrafts.commons.tools.error.ErrorResponse;
import io.pipecrafts.commons.tools.spring.request.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BhControllerAdvice {

  @ExceptionHandler(BhException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ErrorResponse processingErrorHandler(
    HttpServletRequest request,
    BhException ex) {
    return createErrResponse(request, BhErrorCode.PROCESSING_ERROR, ex.getMessage());
  }


  @ExceptionHandler(BhResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorResponse notFoundErrorHandler(
    HttpServletRequest request,
    BhResourceNotFoundException ex) {
    return createErrResponse(request, BhErrorCode.RESOURCE_NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ErrorResponse processGenericError(
    HttpServletRequest request,
    Exception ex) {
    return createErrResponse(request, BhErrorCode.SERVER_ERROR, ex.getMessage());
  }


  private ErrorResponse createErrResponse(HttpServletRequest request, @NonNull BhErrorCode errorCode, String errorMessage) {
    return ErrorResponse.builder()
      .errorTimestamp(LocalDateTime.now())
      .requestUuid(RequestContext.get().requestId())
      .path(request != null ? request.getRequestURI() : null)
      // todo with version
      .systemVersion("")
      .errorCode(errorCode.name())
      .errorMessage(errorMessage)
      .build();
  }
}
