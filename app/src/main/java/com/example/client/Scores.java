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

        public void setNick(String nick) {
            this.nick = nick;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}
