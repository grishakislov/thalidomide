package com.codekittens.thalidomide.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class KarmaState extends ServerResponse {
    @JsonProperty("cons")
    private List<Vote> cons;

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("pros")
    private List<Vote> pros;

    @JsonProperty("cons_count")
    private int consCount;

    @JsonProperty("pros_count")
    private int prosCount;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("karma")
    private int karma;

    public List<Vote> getCons() {
        return cons;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Vote> getPros() {
        return pros;
    }

    public int getConsCount() {
        return consCount;
    }

    public int getProsCount() {
        return prosCount;
    }

    public int getOffset() {
        return offset;
    }

    public int getKarma() {
        return karma;
    }
}
