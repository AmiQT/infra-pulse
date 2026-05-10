package com.amiqt.infrapulse.repository;

import com.amiqt.infrapulse.model.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for NodeEntity.
 * Provides standard CRUD operations out of the box.
 */
@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, String> {
}
