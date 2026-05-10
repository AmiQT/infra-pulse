package com.amiqt.infrapulse.model.dto;

import java.time.OffsetDateTime;

/**
 * Represents an active infrastructure alert.
 * Uses Java 21 Record for immutable data representation.
 */
public record AlertDTO(
    String id,
    String severity,
    String message,
    String nodeId,
    OffsetDateTime timestamp,
    boolean resolved
) {}
