package com.amiqt.infrapulse.service;

import com.amiqt.infrapulse.model.entity.AlertEntity;
import com.amiqt.infrapulse.model.entity.NodeEntity;
import com.amiqt.infrapulse.model.dto.AlertDTO;
import com.amiqt.infrapulse.model.dto.NodeDTO;
import com.amiqt.infrapulse.repository.AlertRepository;
import com.amiqt.infrapulse.repository.NodeRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InfraServiceTest {

    private InfraService infraService;
    private NodeRepository nodeRepository;
    private AlertRepository alertRepository;
    private MeterRegistry meterRegistry;

    @BeforeEach
    void setUp() {
        nodeRepository = Mockito.mock(NodeRepository.class);
        alertRepository = Mockito.mock(AlertRepository.class);
        meterRegistry = Mockito.mock(MeterRegistry.class);
        infraService = new InfraService(nodeRepository, alertRepository, meterRegistry);
    }

    @Test
    void getAllNodes_ShouldReturnMappedDTOs() {
        NodeEntity entity = new NodeEntity("node-01", "k8s-worker-01", 10.0, 20.0, "HEALTHY", "1d");
        when(nodeRepository.findAll()).thenReturn(List.of(entity));

        List<NodeDTO> nodes = infraService.getAllNodes();
        assertEquals(1, nodes.size());
        assertEquals("node-01", nodes.get(0).id());
    }

    @Test
    void getActiveAlerts_ShouldReturnMappedDTOs() {
        AlertEntity entity = new AlertEntity("alert-01", "HIGH", "Msg", "node-01", OffsetDateTime.now(), false);
        when(alertRepository.findAll()).thenReturn(List.of(entity));

        List<AlertDTO> alerts = infraService.getActiveAlerts();
        assertEquals(1, alerts.size());
        assertEquals("alert-01", alerts.get(0).id());
    }

    @Test
    void getOverallHealth_ShouldReturnDegradedWhenWarningExists() {
        NodeEntity warningNode = new NodeEntity("node-02", "host-02", 90, 90, "WARNING", "1d");
        when(nodeRepository.findAll()).thenReturn(List.of(warningNode));

        String health = infraService.getOverallHealth();
        assertEquals("DEGRADED", health);
    }
}
