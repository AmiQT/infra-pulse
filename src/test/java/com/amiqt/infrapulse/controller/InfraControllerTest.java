package com.amiqt.infrapulse.controller;

import com.amiqt.infrapulse.model.dto.AlertDTO;
import com.amiqt.infrapulse.model.dto.NodeDTO;
import com.amiqt.infrapulse.service.InfraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfraController.class)
class InfraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfraService infraService;

    @Test
    void getHealth_ShouldReturnStatus() throws Exception {
        when(infraService.getOverallHealth()).thenReturn("HEALTHY");

        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("HEALTHY"));
    }

    @Test
    void getNodes_ShouldReturnNodeList() throws Exception {
        List<NodeDTO> nodes = List.of(
                new NodeDTO("node-01", "k8s-worker-01", 10.0, 20.0, "HEALTHY", "1d")
        );
        when(infraService.getAllNodes()).thenReturn(nodes);

        mockMvc.perform(get("/api/nodes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("node-01"))
                .andExpect(jsonPath("$[0].hostname").value("k8s-worker-01"));
    }

    @Test
    void getAlerts_ShouldReturnAlertList() throws Exception {
        List<AlertDTO> alerts = List.of(
                new AlertDTO("alert-001", "HIGH", "Disk full", "node-01", OffsetDateTime.now(), false)
        );
        when(infraService.getActiveAlerts()).thenReturn(alerts);

        mockMvc.perform(get("/api/alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].severity").value("HIGH"))
                .andExpect(jsonPath("$[0].message").value("Disk full"));
    }
}
