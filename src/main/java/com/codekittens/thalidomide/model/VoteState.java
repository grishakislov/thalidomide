package com.codekittens.thalidomide.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class VoteState extends ServerResponse {

    @JsonProperty("rating")
    private int rating;

    @JsonProperty("cons")
    private List<Vote> cons;

    @JsonProperty("pros")
    private List<Vote> pros;

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("cons_count")
    private int consCount;

    @JsonProperty("pros_count")
    private int prosCount;

    @JsonProperty("offset")
    private int offset;

}
