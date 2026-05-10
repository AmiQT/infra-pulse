package com.amiqt.infrapulse.service;

import com.amiqt.infrapulse.model.dto.AlertDTO;
import com.amiqt.infrapulse.model.dto.NodeDTO;
import com.amiqt.infrapulse.repository.AlertRepository;
import com.amiqt.infrapulse.repository.NodeRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Gauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service handling infrastructure health logic and data simulation.
 */
@Service
public class InfraService {

    private static final Logger log = LoggerFactory.getLogger(InfraService.class);
    private final NodeRepository nodeRepository;
    private final AlertRepository alertRepository;
    private final AtomicInteger activeAlertsCount = new AtomicInteger(0);

    public InfraService(NodeRepository nodeRepository, AlertRepository alertRepository, MeterRegistry meterRegistry) {
        this.nodeRepository = nodeRepository;
        this.alertRepository = alertRepository;
        
        // Custom Metric: Gauge for active alerts count
        Gauge.builder("infrapulse.alerts.active", activeAlertsCount, AtomicInteger::get)
            .description("Number of currently active infrastructure alerts")
            .register(meterRegistry);
    }

    /**
     * Retrieves node statuses from the database and maps them to DTOs.
     * @return List of NodeDTO (Records)
     */
    public List<NodeDTO> getAllNodes() {
        log.info("Fetching all node statuses from database on thread: {}", Thread.currentThread());
        return nodeRepository.findAll().stream()
            .map(entity -> new NodeDTO(
                entity.getId(),
                entity.getHostname(),
                entity.getCpuUsage(),
                entity.getMemoryUsage(),
                entity.getStatus(),
                entity.getUptime()
            ))
            .toList();
    }

    /**
     * Retrieves active infrastructure alerts from the database.
     * @return List of AlertDTO (Records)
     */
    public List<AlertDTO> getActiveAlerts() {
        log.info("Retrieving active alerts from database...");
        List<AlertDTO> alerts = alertRepository.findAll().stream()
            .map(entity -> new AlertDTO(
                entity.getId(),
                entity.getSeverity(),
                entity.getMessage(),
                entity.getNodeId(),
                entity.getTimestamp(),
                entity.isResolved()
            ))
            .toList();
        
        activeAlertsCount.set(alerts.size());
        return alerts;
    }

    /**
     * Gets overall system health summary based on node statuses in the database.
     * @return Health status string
     */
    public String getOverallHealth() {
        boolean hasWarning = nodeRepository.findAll().stream()
            .anyMatch(node -> "WARNING".equals(node.getStatus()));
        return hasWarning ? "DEGRADED" : "HEALTHY";
    }
}
