package com.example.client;

public class CheckSetData{
    private String accessToken;
    private int[] cards;

    public CheckSetData(String accessToken, int[] cards) {
        this.accessToken = accessToken;
        this.cards = cards;
    }
}
