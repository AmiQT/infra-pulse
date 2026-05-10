package com.amiqt.infrapulse.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Database entity representing a Kubernetes node.
 * Demonstrates JPA entity mapping.
 */
@Entity
@Table(name = "nodes")
public class NodeEntity {

    @Id
    private String id;
    private String hostname;
    private double cpuUsage;
    private double memoryUsage;
    private String status;
    private String uptime;

    // Default constructor for JPA
    public NodeEntity() {}

    public NodeEntity(String id, String hostname, double cpuUsage, double memoryUsage, String status, String uptime) {
        this.id = id;
        this.hostname = hostname;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.status = status;
        this.uptime = uptime;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getHostname() { return hostname; }
    public void setHostname(String hostname) { this.hostname = hostname; }
    public double getCpuUsage() { return cpuUsage; }
    public void setCpuUsage(double cpuUsage) { this.cpuUsage = cpuUsage; }
    public double getMemoryUsage() { return memoryUsage; }
    public void setMemoryUsage(double memoryUsage) { this.memoryUsage = memoryUsage; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getUptime() { return uptime; }
    public void setUptime(String uptime) { this.uptime = uptime; }
}
