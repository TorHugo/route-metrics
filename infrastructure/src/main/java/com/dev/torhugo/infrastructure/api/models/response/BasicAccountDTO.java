package com.dev.torhugo.infrastructure.api.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BasicAccountDTO(
        @JsonProperty("account_id") UUID accountId,
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("in_active") boolean active,
        @JsonProperty("in_admin") boolean admin,
        @JsonProperty("last_access")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        LocalDateTime lastAccess,
        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime updatedAt
) {
}
