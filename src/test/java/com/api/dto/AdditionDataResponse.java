package com.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionDataResponse {
    @JsonProperty("id")
    int id;
    @JsonProperty("additional_info")
    String additionalInfo;
    @JsonProperty("additional_number")
    int additionalNumber;

    public AdditionDataResponse(int id, String additionalInfo, int additionalNumber) {
        this.id = id;
        this.additionalInfo = additionalInfo;
        this.additionalNumber = additionalNumber;
    }

    public AdditionDataResponse() {
    }

    public int getId() {
        return id;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public int getAdditionalNumber() {
        return additionalNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setAdditionalNumber(int additionalNumber) {
        this.additionalNumber = additionalNumber;
    }

}
