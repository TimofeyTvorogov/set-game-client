package com.example.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/set/field")
    Call<List<Card>> getDeck(@Query("accessToken") String token);

    @GET("/set/pick")
    Call<Void> pickCards(@Query("accessToken") String token, @Query("cards") int[] cards);

    @GET("/set/scores")
    Call<Void> getScores(@Query("accessToken") String token);

    //maybe post
    @GET("/user/register")
    Call<Void> registerUser(@Query("nickname") String nick,@Query("password") String pass);

    @GET("/set/room/enter")
    Call<Void> enterRoom(@Query("accessToken") String token, @Query("gameId") int gameId);

    @GET("/set/add")
    Call<Void> addCards(@Query("accessToken") String token);


//todo replace voids, add queries


}
