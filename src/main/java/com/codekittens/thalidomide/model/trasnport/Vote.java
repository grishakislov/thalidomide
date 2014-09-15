package com.codekittens.thalidomide.model.trasnport;


import org.codehaus.jackson.annotate.JsonProperty;

public class Vote {
    @JsonProperty("vote")
    private int vote;

    @JsonProperty("user")
    private User user;

    public int getVote() {
        return vote;
    }

    public User getUser() {
        return user;
    }
}
