package io.pipecrafts.commons.tools.spring.error;

import io.pipecrafts.commons.tools.error.*;
import io.pipecrafts.commons.tools.spring.request.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorResponse handleValidationExceptions(
    HttpServletRequest request,
    MethodArgumentNotValidException ex
  ) {
    final Map<String, Object> errorAttrs = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(err -> {
      String fieldName = ((FieldError) err).getField();
      String message = err.getDefaultMessage();
      errorAttrs.put(fieldName, message);
    });
    return createErrResponse(request, BhErrorCode.INVALID_INPUT, ex.getMessage(), errorAttrs);
  }

  @ExceptionHandler(BhDuplicateResourceException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  ErrorResponse handleDuplicateResource(
    HttpServletRequest request,
    BhDuplicateResourceException exception
  ) {
    return createErrResponse(request, BhErrorCode.DUPLICATE_RESOURCE, exception.getMessage());
  }


  private ErrorResponse createErrResponse(HttpServletRequest request,
                                          @NonNull BhErrorCode errorCode,
                                          String errorMessage,
                                          Map<String, Object> attributes) {
    return ErrorResponse.builder()
      .errorTimestamp(LocalDateTime.now())
      .requestUuid(RequestContext.get().requestId())
      .path(request != null ? request.getRequestURI() : null)
      // todo with version
      .systemVersion("")
      .errorCode(errorCode.name())
      .errorMessage(errorMessage)
      .attributes(attributes)
      .build();
  }

  private ErrorResponse createErrResponse(HttpServletRequest request, @NonNull BhErrorCode errorCode, String errorMessage) {
    return createErrResponse(request, errorCode, errorMessage, null);
  }
}
