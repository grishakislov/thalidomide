package com.codekittens.thalidomide.client;

import com.codekittens.thalidomide.model.KarmaResponse;
import com.codekittens.thalidomide.model.ServerResponse;
import com.codekittens.thalidomide.model.VoteResponse;
import com.codekittens.thalidomide.model.WrappedVoteResponse;

import java.util.List;

public interface Client {

    void init() throws ClientException;

    void login() throws ClientException;

    KarmaResponse listKarma(int limit, int offset) throws ClientException;

    ServerResponse uploadMedia(String media) throws ClientException;

    ServerResponse editControls() throws ClientException;

    ServerResponse listComments() throws ClientException;

    VoteResponse listCommentVotes(String commentId) throws ClientException;
    List<WrappedVoteResponse> listCommentVotes(List<String> commentId) throws ClientException;

    VoteResponse listPostVotes(String postId) throws ClientException;
    List<VoteResponse> listPostVotes(List<String> postId) throws ClientException;

    List<String> listCommentsIds() throws ClientException;


}
