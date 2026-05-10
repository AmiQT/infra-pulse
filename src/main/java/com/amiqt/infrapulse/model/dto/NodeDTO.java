package com.amiqt.infrapulse.model.dto;

import java.time.LocalDateTime;

/**
 * Represents the status of a Kubernetes node.
 * Uses Java 21 Record for immutable data representation.
 */
public record NodeDTO(
    String id,
    String hostname,
    double cpuUsage,
    double memoryUsage,
    String status,
    String uptime
) {}
