package com.example.client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("/set/field")
    Call<FetchDeck> getDeck(@Body String token);

    @POST("/set/pick")
    Call<Pick> pickCards(@Body CheckSetData checkSetData);
//мб всё таки int[] cards

    @POST("/set/scores")
    Call<Scores> getScores(@Body String token);

    //maybe post
    @POST("/user/register")
    Call<ServResponse> registerUser(@Body RegUser regUser);

    @POST("/set/room/enter")
    Call<EnterCreate> enterRoom(@Body EntRoom roomData);

    @POST("/set/room/create")
    Call<EnterCreate> createRoom(@Body String token);


    @POST("/set/add")
    Call<ServResponse> addCards(@Body String token);


}
