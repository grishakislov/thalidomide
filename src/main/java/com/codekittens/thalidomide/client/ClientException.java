package com.codekittens.thalidomide.client;

public class ClientException extends Exception {

    private final int code;

    public ClientException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public ClientException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
