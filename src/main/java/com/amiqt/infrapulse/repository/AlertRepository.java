package com.amiqt.infrapulse.repository;

import com.amiqt.infrapulse.model.entity.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for AlertEntity.
 * Provides standard CRUD operations out of the box.
 */
@Repository
public interface AlertRepository extends JpaRepository<AlertEntity, String> {
}
