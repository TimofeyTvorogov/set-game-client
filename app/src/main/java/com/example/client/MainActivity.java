package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;


import com.example.client.databinding.ActivityMainBinding;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String token;
    private int gameId;
    private ArrayList<Card> cardList;
    private CountDownTimer countDownTimer;
    private recyclerAdapter adapter;
    private ArrayList<String> picked_cards;
    private recyclerAdapter.RecyclerViewClickListener clickListener;
    ActivityMainBinding binding;
    private static final String address = "http://84.201.155.174";
    private Retrofit retrofit;
    private Api api;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder()
                .baseUrl(address)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String login = extras.getString("Login");
                String password = extras.getString("Password");
                register("Obama","12345");

            }
        }
        createRoom(token);
        enterRoom(token,gameId);
        setCardInfo(token);
        picked_cards = new ArrayList<>();
        setOnClickListener();
        adapter = new recyclerAdapter(cardList, getApplicationContext(),clickListener);
        RecyclerView.LayoutManager layoutManager = new CustomGridLayoutManager(getApplicationContext(),4);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);
    }
    @SuppressLint("NotifyDataSetChanged")
    private void setOnClickListener() {
        clickListener = (view, position) -> {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            VibrationEffect effect = VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);

            if (!cardList.get(position).isPicked()){
                cardList.get(position).setPicked(true);
                picked_cards.add(String.valueOf(cardList.get(position).getId()));
            }
            else{
                cardList.get(position).setPicked(false);
                picked_cards.remove(String.valueOf(cardList.get(position).getId()));
            }

            if (picked_cards.size()==3){
                int [] set = new int[3];
                for (int i = 0; i < 3; i++) { set[i] = Integer.parseInt(picked_cards.get(i)) ;}

                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {}

                picked_cards.clear();
                for (int i = 0; i < 12; i++) {
                    cardList.get(i).setPicked(false);
                }
                pickSet(token,set);
            }
            adapter.notifyDataSetChanged();
            //Todo latency before clearing
        };
    }

    private boolean pickSet(String token, int[] cardIDs) {
        final boolean[] isSet = new boolean[1];
        Call<Pick> isSetCall = api.pickCards(token,cardIDs);
        isSetCall.enqueue(new Callback<Pick>() {
            @Override
            public void onResponse(Call<Pick> call, Response<Pick> response) {
                Pick pick = response.body();
                isSet[0] = pick.isSet();
                //todo обновление счётчиков очков
            }

            @Override
            public void onFailure(Call<Pick> call, Throwable t) {

            }
        });
        return isSet[0];
    }
    private void enterRoom(String token, int gameId){

        Call<EnterCreate> enterCall = api.enterRoom(token,gameId);
        enterCall.enqueue(new Callback<EnterCreate>() {
            @Override
            public void onResponse(Call<EnterCreate> call, Response<EnterCreate> response) {

            }

            @Override
            public void onFailure(Call<EnterCreate> call, Throwable t) {

            }
        });
    }
    private void createRoom(String token){

        Call<EnterCreate> enterCall = api.createRoom(token);
        enterCall.enqueue(new Callback<EnterCreate>() {
            @Override
            public void onResponse(Call<EnterCreate> call, Response<EnterCreate> response) {
                gameId = response.body().getGameId();
            }

            @Override
            public void onFailure(Call<EnterCreate> call, Throwable t) {

            }
        });
    }
    private void register(String nick, String pass) {
        Call<UserData> userDataCall = api.registerUser(nick, pass);
        userDataCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData data = response.body();
                token = data.getToken();

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });
    }
    //TODO patterns for vibration
    private void setCardInfo(String token) {
        Call<FetchDeck> fetchDeckCall = api.getDeck(token);
        fetchDeckCall.enqueue(new Callback<FetchDeck>() {
            @Override
            public void onResponse(Call<FetchDeck> call, Response<FetchDeck> response) {
                FetchDeck fetchDeck = response.body();
                cardList = fetchDeck.getCards();
                for (Card card:cardList) {
                    card.setPicked(false);
                }

            }

            @Override
            public void onFailure(Call<FetchDeck> call, Throwable t) {

            }
        });
    }

}