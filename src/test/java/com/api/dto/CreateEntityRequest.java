package com.api.dto;

import com.api.dto.AdditionDataRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import java.util.List;

@Builder
@Value
public class CreateEntityRequest {
    @JsonProperty("addition")
    AdditionDataRequest addition;

    @JsonProperty("important_numbers")
    List<Integer> importantNumbers;

    @JsonProperty("title")
    String title;

    @JsonProperty("verified")
    boolean verified;
}