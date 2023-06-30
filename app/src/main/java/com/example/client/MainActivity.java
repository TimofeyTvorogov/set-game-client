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
import android.util.Log;


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
    private String nickname;

    private WebClass webClass;




    private ArrayList<Card> cardList;

    private recyclerAdapter adapter;
    private ArrayList<String> picked_cards;
    private recyclerAdapter.RecyclerViewClickListener clickListener;
    ActivityMainBinding binding;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                token = extras.getString("Token");
                nickname = extras.getString("Login");
                gameId = Integer.parseInt(extras.getString("GameId"));
                webClass = new WebClass(this);
                webClass.setCardInfo(token);


            }
        }

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
               webClass.pickSet(new CheckSetData(token,set));
            }
            adapter.notifyDataSetChanged();
            //Todo latency before clearing
        };
    }
//todo пересмотреть функцию
    public String getToken() {
        return token;
    }
    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }


    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}