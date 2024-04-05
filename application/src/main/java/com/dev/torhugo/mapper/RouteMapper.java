package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.models.UcBasicRouteDTO;

public interface RouteMapper {
    UcBasicRouteDTO mappingToBasic(final Route existingRoute);
}
