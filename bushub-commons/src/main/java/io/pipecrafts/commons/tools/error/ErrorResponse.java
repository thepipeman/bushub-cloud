package io.pipecrafts.commons.tools.error;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ErrorResponse(
  LocalDateTime errorTimestamp,
  String requestUuid,
  String path,
  String systemVersion,
  String errorCode,
  String errorMessage,
  Map<String, Object> attributes
) {
}
