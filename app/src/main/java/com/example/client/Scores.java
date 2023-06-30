package com.example.client;

import java.util.ArrayList;

public class Scores extends ServResponse {

    private ArrayList<User> users;

    protected class User{

        private String nick;
        private int score;

        public String getNick() {
            return nick;
        }

        public int getScore() {
            return score;
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
