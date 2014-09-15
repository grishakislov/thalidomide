package com.codekittens.thalidomide.model;

import com.codekittens.thalidomide.model.trasnport.VoteResponse;
import org.codehaus.jackson.annotate.JsonProperty;

public class WrappedVoteResponse {

    @JsonProperty("commentId")
    private String commentId;

    @JsonProperty("voteResponse")
    private VoteResponse voteResponse;

    public WrappedVoteResponse() {
    }

    public WrappedVoteResponse(String commentId, VoteResponse voteResponse) {
        this.commentId = commentId;
        this.voteResponse = voteResponse;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public VoteResponse getVoteResponse() {
        return voteResponse;
    }

    public void setVoteResponse(VoteResponse voteResponse) {
        this.voteResponse = voteResponse;
    }
}
