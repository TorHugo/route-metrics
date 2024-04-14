package com.dev.torhugo.infrastructure.api.controller;

import com.dev.torhugo.application.dto.UcCoordinateDTO;
import com.dev.torhugo.application.dto.UcUpdatePositionDTO;
import com.dev.torhugo.application.usecase.UpdatePositionUseCase;
import com.dev.torhugo.infrastructure.api.PositionAPI;
import com.dev.torhugo.infrastructure.api.mappers.PositionMapper;
import com.dev.torhugo.infrastructure.api.models.request.CreatePositionDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicPositionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PositionController implements PositionAPI {
    private final UpdatePositionUseCase updatePositionUseCase;
    private final PositionMapper positionMapper;
    @Override
    public BasicPositionDTO updatePosition(final CreatePositionDTO request) {
        final var input = new UcUpdatePositionDTO(request.routeId(), new UcCoordinateDTO(request.coordinate().latitude(), request.coordinate().longitude(), null));
        return positionMapper.mapperToBasic(updatePositionUseCase.execute(input));
    }
}
