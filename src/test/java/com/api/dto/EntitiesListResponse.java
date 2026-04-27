package com.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class EntitiesListResponse {
    @JsonProperty("entity")
    private List<EntityDataResponse> entity;

    public EntitiesListResponse() {}

    public List<EntityDataResponse> getEntity() {
        return entity;
    }

    public void setEntity(List<EntityDataResponse> entity) {
        this.entity = entity;
    }
}