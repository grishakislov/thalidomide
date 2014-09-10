package com.codekittens.thalidomide.client;

public class Settings {

    private String siteUrl;
    private String login;
    private String csrfToken;
    private String password;
    private int uid;

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public static Settings createClientSettings() {
        Settings settings = new Settings();
        settings.setSiteUrl("https://leprosorium.ru");
        settings.setLogin("grishakislov");
        settings.setPassword("");
        settings.setUid(19201);
        return settings;
    }
}
