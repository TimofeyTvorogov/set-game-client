package com.example.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/set/field")
    Call<List<Card>> getDeck(String token);

    @GET("/set/pick")
    Call<Void> pickCards(String token, int[] cards);

    @GET("/set/scores")
    Call<Void> getScores(String token);

    //maybe post
    @GET("/user/register")
    Call<Void> registerUser(String nick,String pass);

    @GET("/set/room/enter")
    Call<Void> enterRoom(String token,int gameId);

    @GET("/set/add")
    Call<Void> addCards(String token);


//todo replace voids, add queries


}
