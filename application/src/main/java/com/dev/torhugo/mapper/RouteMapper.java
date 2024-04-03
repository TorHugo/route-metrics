package com.dev.torhugo.mapper;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.models.BasicRouteDTO;

public interface RouteMapper {
    BasicRouteDTO mappingToBasic(final Route existingRoute);
}
