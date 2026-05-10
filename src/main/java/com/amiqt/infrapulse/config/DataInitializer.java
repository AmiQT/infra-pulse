package com.amiqt.infrapulse.config;

import com.amiqt.infrapulse.model.entity.AlertEntity;
import com.amiqt.infrapulse.model.entity.NodeEntity;
import com.amiqt.infrapulse.repository.AlertRepository;
import com.amiqt.infrapulse.repository.NodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Seeds the in-memory database with initial infrastructure data on startup.
 * Ensures the portfolio app is ready for demonstration immediately.
 */
@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner initDatabase(NodeRepository nodeRepo, AlertRepository alertRepo) {
        return args -> {
            log.info("Seeding database with initial infrastructure data...");

            nodeRepo.saveAll(List.of(
                new NodeEntity("node-01", "k8s-worker-01", 42.5, 67.3, "HEALTHY", "5d 3h 12m"),
                new NodeEntity("node-02", "k8s-worker-02", 89.1, 91.0, "WARNING", "2d 14h 5m"),
                new NodeEntity("node-03", "k8s-worker-03", 15.2, 34.8, "HEALTHY", "12d 8h 45m")
            ));

            alertRepo.save(new AlertEntity(
                "alert-001",
                "HIGH",
                "Node k8s-worker-02 CPU usage exceeded 85%",
                "node-02",
                OffsetDateTime.parse("2026-05-10T10:30:00Z"),
                false
            ));

            log.info("Database seeding complete.");
        };
    }
}
