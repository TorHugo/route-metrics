package com.dev.torhugo.repository;

import com.dev.torhugo.repository.models.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteJpaRepository extends JpaRepository<RouteEntity, UUID> {
}
