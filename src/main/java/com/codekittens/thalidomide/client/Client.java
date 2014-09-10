package com.codekittens.thalidomide.client;

import com.codekittens.thalidomide.model.KarmaState;
import com.codekittens.thalidomide.model.ServerResponse;

import java.util.List;

public interface Client {

    void init() throws ClientException;

    void login() throws ClientException;

    KarmaState listKarma(int limit, int offset) throws ClientException;

    ServerResponse uploadMedia(String media) throws ClientException;

    ServerResponse editControls() throws ClientException;

    ServerResponse listComments() throws ClientException;

    List<String> listCommentsIds() throws ClientException;


}
