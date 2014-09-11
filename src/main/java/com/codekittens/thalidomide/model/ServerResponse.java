package com.codekittens.thalidomide.model;


import org.codehaus.jackson.annotate.JsonProperty;

public class ServerResponse {

    @JsonProperty("status")
    String status;

    public String getStatus() {
        return status;
    }
}
