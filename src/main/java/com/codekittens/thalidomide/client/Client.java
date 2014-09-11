package com.codekittens.thalidomide.client;

import com.codekittens.thalidomide.model.KarmaState;
import com.codekittens.thalidomide.model.ServerResponse;
import com.codekittens.thalidomide.model.VoteState;

import java.util.List;

public interface Client {

    void init() throws ClientException;

    void login() throws ClientException;

    KarmaState listKarma(int limit, int offset) throws ClientException;

    ServerResponse uploadMedia(String media) throws ClientException;

    ServerResponse editControls() throws ClientException;

    ServerResponse listComments() throws ClientException;

    VoteState listCommentVotes(String commentId) throws ClientException;
    List<VoteState> listCommentVotes(List<String> commentId) throws ClientException;

    VoteState listPostVotes(String postId) throws ClientException;
    List<VoteState> listPostVotes(List<String> postId) throws ClientException;

    List<String> listCommentsIds() throws ClientException;


}
