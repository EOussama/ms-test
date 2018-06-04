package com.test.sm.mstest;

public class User {
    public String username;
    public String password;
    public String[] favAnime;

    User(String username, String password, String[] favAnime) {
        this.username = username;
        this.password = password;
        this.favAnime = favAnime;
    }
}