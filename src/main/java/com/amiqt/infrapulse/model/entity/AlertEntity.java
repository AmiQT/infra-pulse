package com.amiqt.infrapulse.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

/**
 * Database entity representing an infrastructure alert.
 * Demonstrates JPA entity mapping.
 */
@Entity
@Table(name = "alerts")
public class AlertEntity {

    @Id
    private String id;
    private String severity;
    private String message;
    private String nodeId;
    private OffsetDateTime timestamp;
    private boolean resolved;

    // Default constructor for JPA
    public AlertEntity() {}

    public AlertEntity(String id, String severity, String message, String nodeId, OffsetDateTime timestamp, boolean resolved) {
        this.id = id;
        this.severity = severity;
        this.message = message;
        this.nodeId = nodeId;
        this.timestamp = timestamp;
        this.resolved = resolved;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getNodeId() { return nodeId; }
    public void setNodeId(String nodeId) { this.nodeId = nodeId; }
    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
}
