package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.client.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String token;
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

            }
        }


        cardList = new ArrayList<>();
        picked_cards = new ArrayList<>();
        setCardInfo();
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
                for (int i = 0; i < 3; i++) {
                    set[i] = Integer.parseInt(picked_cards.get(i)) ;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {}
                for (int i = 0; i < 12; i++) {
                    cardList.get(i).setPicked(false);
                    picked_cards.clear();
                }
                is_set(set);
            }
            adapter.notifyDataSetChanged();
            //Todo latency before clearing
        };
    }

    private void is_set(int[] set) {
        //Todo проверяем сет или нет
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
    private void setCardInfo() {
        cardList.add(new Card(1,1,1,1,1,false));
        cardList.add(new Card(2,2,1,1,2,false));
        cardList.add(new Card(3,2,1,1,1,false));
        cardList.add(new Card(4,1,2,3,3,false));
        cardList.add(new Card(5,3,3,3,2,false));
        cardList.add(new Card(6,3,1,2,3,false));
        cardList.add(new Card(7,3,1,3,2,false));
        cardList.add(new Card(8,2,2,1,2,false));
        cardList.add(new Card(9,2,3,2,3,false));
        cardList.add(new Card(10,3,2,1,1,false));
        cardList.add(new Card(11,3,2,1,1,false));
        cardList.add(new Card(12,3,1,1,1,false));
    }

}