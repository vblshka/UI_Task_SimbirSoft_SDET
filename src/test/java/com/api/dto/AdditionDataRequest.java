package com.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AdditionDataRequest {
    @JsonProperty("additional_info")
    String additionalInfo;

    @JsonProperty("additional_number")
    int additionalNumber;
}
