package com.amiqt.infrapulse.controller;

import com.amiqt.infrapulse.model.dto.AlertDTO;
import com.amiqt.infrapulse.model.dto.NodeDTO;
import com.amiqt.infrapulse.service.InfraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for infrastructure health endpoints.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Infrastructure Health", description = "Endpoints for monitoring system health and alerts")
public class InfraController {

    private final InfraService infraService;

    public InfraController(InfraService infraService) {
        this.infraService = infraService;
    }

    /**
     * Endpoint for overall system health.
     * @return Map containing status
     */
    @GetMapping("/health")
    @Operation(summary = "Get overall system health", description = "Returns the aggregate health status of all monitored infrastructure components")
    public ResponseEntity<Map<String, String>> getHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", infraService.getOverallHealth());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to list all node statuses.
     * @return List of nodes
     */
    @GetMapping("/nodes")
    @Operation(summary = "List all nodes", description = "Retrieves a detailed status list of all Kubernetes nodes including CPU and Memory usage")
    public ResponseEntity<List<NodeDTO>> getNodes() {
        return ResponseEntity.ok(infraService.getAllNodes());
    }

    /**
     * Endpoint to list active alerts.
     * @return List of alerts
     */
    @GetMapping("/alerts")
    @Operation(summary = "List active alerts", description = "Retrieves all currently active (unresolved) infrastructure alerts")
    public ResponseEntity<List<AlertDTO>> getAlerts() {
        return ResponseEntity.ok(infraService.getActiveAlerts());
    }
}
