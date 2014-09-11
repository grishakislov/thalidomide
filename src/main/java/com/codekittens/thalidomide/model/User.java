package com.codekittens.thalidomide.model;


import org.codehaus.jackson.annotate.JsonProperty;

public class User {
    @JsonProperty("city")
    private String city;

    @JsonProperty("ignored")
    private String ignored;

    @JsonProperty("bans")
    private String bans;

    @JsonProperty("subscribed")
    private String subscribed;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("deleted")
    private int deleted;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("subscribers_count")
    private String subscribersCount;

    @JsonProperty("few_words")
    private String fewWords;

    @JsonProperty("wiki_groups")
    private String wikiGroups;

    @JsonProperty("karma")
    private int karma;

    @JsonProperty("country")
    private String country;

    @JsonProperty("ban")
    private String ban;

    @JsonProperty("attributes")
    private String attributes;

    @JsonProperty("login")
    private String login;

    @JsonProperty("active")
    private int active;

    @JsonProperty("id")
    private int id;

    public String getCity() {
        return city;
    }

    public String getIgnored() {
        return ignored;
    }

    public String getBans() {
        return bans;
    }

    public String getSubscribed() {
        return subscribed;
    }

    public String getRating() {
        return rating;
    }

    public int getDeleted() {
        return deleted;
    }

    public String getGender() {
        return gender;
    }

    public String getSubscribersCount() {
        return subscribersCount;
    }

    public String getFewWords() {
        return fewWords;
    }

    public String getWikiGroups() {
        return wikiGroups;
    }

    public int getKarma() {
        return karma;
    }

    public String getCountry() {
        return country;
    }

    public String getBan() {
        return ban;
    }

    public String getAttributes() {
        return attributes;
    }

    public String getLogin() {
        return login;
    }

    public int getActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
