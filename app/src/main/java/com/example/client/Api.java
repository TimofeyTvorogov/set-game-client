package com.example.client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/set/field")
    Call<FetchDeck> getDeck(@Query("accessToken") String token);

    @GET("/set/pick")
    Call<Pick> pickCards(@Query("accessToken") String token, @Query("cards") ArrayList<Integer> cards);
//мб всё таки int[] cards

    @GET("/set/scores")
    Call<Scores> getScores(@Query("accessToken") String token);

    //maybe post
    @GET("/user/register")
    Call<UserData> registerUser(@Query("nickname") String nick,@Query("password") String pass);

    @GET("/set/room/enter")
    Call<EnterCreate> enterRoom(@Query("accessToken") String token, @Query("gameId") int gameId);

    @GET("/set/room/create")
    Call<EnterCreate> createRoom(@Query("accessToken") String token);


    @GET("/set/add")
    Call<ServResponse> addCards(@Query("accessToken") String token);

}
