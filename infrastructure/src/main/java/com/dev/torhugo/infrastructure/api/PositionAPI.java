package com.dev.torhugo.infrastructure.api;

import com.dev.torhugo.infrastructure.api.models.request.CreatePositionDTO;
import com.dev.torhugo.infrastructure.api.models.response.BasicPositionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(value = "/position")
public interface PositionAPI {


    /**
     * For the functionality to occur correctly, this endpoint <br/>
     * must be called by a third party every 2s. <br/>
     * For greater accuracy in distance and speed (km/h) travelled.
     */
    @PutMapping("/public/update-position")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    BasicPositionDTO updatePosition(final @RequestBody CreatePositionDTO request);
}
