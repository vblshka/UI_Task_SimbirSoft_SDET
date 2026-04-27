package com.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateEntityResponse (@JsonProperty("id") int id ) {}