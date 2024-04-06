package com.dev.torhugo.infrastructure.repository;

import com.dev.torhugo.infrastructure.repository.models.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteJpaRepository extends JpaRepository<RouteEntity, UUID> {
}
