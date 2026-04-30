package com.api.dto;

import com.api.dto.AdditionDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class EntityDataResponse {
    @JsonProperty("id")
    int id;
    @JsonProperty("title")
    String title;
    @JsonProperty("verified")
    boolean verified;
    @JsonProperty("addition")
    AdditionDataResponse addition;
    @JsonProperty("important_numbers")
    List<Integer> importantNumbers;

    public EntityDataResponse() {
    }

    public EntityDataResponse(int id, String title, boolean verified, AdditionDataResponse addition, List<Integer> importantNumbers) {
        this.id = id;
        this.title = title;
        this.verified = verified;
        this.addition = addition;
        this.importantNumbers = importantNumbers;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVerified() {
        return verified;
    }

    public AdditionDataResponse getAddition() {
        return addition;
    }

    public List<Integer> getImportantNumbers() {
        return importantNumbers;
    }
}