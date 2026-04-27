package com.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBodyAfterDelete {
    @JsonProperty("error")
    private String error;

    public String getError(){
        return error;
    }
}
