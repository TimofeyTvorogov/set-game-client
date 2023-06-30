package com.example.client;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClass {
    private MainActivity mainActivity;
    private static final String address = "http://51.250.45.188:8080/";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(address)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final Api api = retrofit.create(Api.class);



    public WebClass(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void pickSet(CheckSetData checkSetData) {

        Call<Pick> isSetCall = api.pickCards(checkSetData);
        isSetCall.enqueue(new Callback<Pick>() {
            @Override
            public void onResponse(Call<Pick> call, Response<Pick> response) {
                Pick pick = response.body();
                if (pick.isSet()) {
                    getScores(mainActivity.getToken());
                }
            }

            @Override
            public void onFailure(Call<Pick> call, Throwable t) {
                Log.d("pickSet",t.getMessage());
            }
        });

    }
    public void enterRoom(EntRoom entRoom){

        Call<EnterCreate> enterCall = api.enterRoom(entRoom);
        enterCall.enqueue(new Callback<EnterCreate>() {
            @Override
            public void onResponse(Call<EnterCreate> call, Response<EnterCreate> response) {

            }

            @Override
            public void onFailure(Call<EnterCreate> call, Throwable t) {

            }
        });
    }

    public int createRoom(String token){
        final int[] tempId = new int[1];
        Call<EnterCreate> enterCall = api.createRoom(token);
        enterCall.enqueue(new Callback<EnterCreate>() {
            @Override
            public void onResponse(Call<EnterCreate> call, Response<EnterCreate> response) {
                tempId[0] = response.body().getGameId();
            }

            @Override
            public void onFailure(Call<EnterCreate> call, Throwable t) {

            }
        });
        return tempId[0];
    }
    public String register(RegUser regUser) {
        final String[] returnToken = new String[1];
        Call<UserData> userDataCall = api.registerUser(regUser);
        userDataCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (!response.isSuccessful()) {
                    Log.d("registerSucc", response.message());
                    return;
                }
                UserData data = response.body();
                returnToken[0] = data.getAccesstoken();

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("registerFail", t.getMessage());
            }
        });
        return returnToken[0];
    }
    //TODO patterns for vibration
    public void setCardInfo(String token) {
        Call<FetchDeck> fetchDeckCall = api.getDeck(token);
        fetchDeckCall.enqueue(new Callback<FetchDeck>() {
            @Override
            public void onResponse(Call<FetchDeck> call, Response<FetchDeck> response) {
                if (!response.isSuccessful()) {
                    Log.d("setcard", response.message());
                    return;
                }
                FetchDeck fetchDeck = response.body();
                mainActivity.setCardList(fetchDeck.getCards());
                for (Card card:mainActivity.getCardList()) {
                    card.setPicked(false);
                }

            }

            @Override
            public void onFailure(Call<FetchDeck> call, Throwable t) {
                Log.d("setcardFail", t.getMessage());
            }
        });
    }
    public void addCards(String token) {
        Call<ServResponse> call = api.addCards(token);
        call.enqueue(new Callback<ServResponse>() {
            @Override
            public void onResponse(Call<ServResponse> call, Response<ServResponse> response) {

            }

            @Override
            public void onFailure(Call<ServResponse> call, Throwable t) {

            }
        });
    }
    public void getScores(String token) {
        Call<Scores> scoresCall = api.getScores(token);
        scoresCall.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                ArrayList<Scores.User> users = response.body().getUsers();
                int score1 = users.get(0).getScore();
                int score2 = users.get(1).getScore();
                mainActivity.binding.scoreUser1.setText(String.valueOf(score1));
                mainActivity.binding.scoreUser2.setText(String.valueOf(score2));
            }

            @Override
            public void onFailure(Call<Scores> call, Throwable t) {

            }
        });
    }
}
