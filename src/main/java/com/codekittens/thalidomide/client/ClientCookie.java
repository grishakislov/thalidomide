package com.codekittens.thalidomide.client;


public class ClientCookie {

    private String uid;
    private String sid;
    private String ga;

    public static final String UID_NAME = "uid";
    public static final String SID_NAME = "sid";
    public static final String GA_NAME = "_ga";

    public ClientCookie(String uid, String sid, String ga) {
        this.uid = uid;
        this.sid = sid;
        this.ga = ga;
    }

    public String getUid() {
        return uid;
    }

    public String getSid() {
        return sid;
    }

    public String getGa() {
        return ga;
    }

    @Override
    public String toString() {
        return GA_NAME + "=" + getGa() + "; " +
                SID_NAME + "=" + getSid() + "; " +
                UID_NAME + "=" + getUid();
    }
}