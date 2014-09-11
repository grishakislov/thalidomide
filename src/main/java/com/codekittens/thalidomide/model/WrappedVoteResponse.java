package com.codekittens.thalidomide.model;

public class WrappedVoteResponse {

    private String commentId;
    private VoteResponse voteResponse;

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
