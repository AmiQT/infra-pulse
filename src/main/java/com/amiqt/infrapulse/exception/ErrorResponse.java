package com.amiqt.infrapulse.exception;

import java.time.LocalDateTime;

/**
 * Standard error response for the API.
 */
public record ErrorResponse(
    int status,
    String message,
    LocalDateTime timestamp
) {}
