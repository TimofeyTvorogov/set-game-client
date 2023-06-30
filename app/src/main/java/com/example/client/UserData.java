package com.example.client;

public class UserData extends ServResponse {
    private String nickname;
    private String password;

    @Override
    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
