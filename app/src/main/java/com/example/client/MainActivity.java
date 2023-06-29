package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.client.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Card> cardList;
    private recyclerAdapter.RecyclerViewClickListener clickListener;
    ActivityMainBinding binding;
    private static final String address = "http://84.201.155.174";
    private Retrofit retrofit;
    private Api api;
    private Button btn_FrLog;
    private ConstraintLayout constraintLayout;
    //change
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new Retrofit.Builder()
                .baseUrl(address)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);


        // Фрагмент входа
        /*btn_FrLog = findViewById(R.id.btn_Log);

        setLogFragment();*/

        cardList = new ArrayList<>();
        setCardInfo();
        setAdapter();
        /*btn_FrLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLogFragment();
            }
        });*/
    }

    private void setAdapter() {
        setOnClickListener();
        recyclerAdapter adapter = new recyclerAdapter(cardList, getApplicationContext(),clickListener);
        RecyclerView.LayoutManager layoutManager = new CustomGridLayoutManager(getApplicationContext(),4);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        clickListener = (view, position) -> {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            VibrationEffect effect = VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);
            cardList.get(position).setPicked(!cardList.get(position).isPicked());
        };
    }
    //TODO patterns for vibration
    private void setCardInfo() {
        cardList.add(new Card(1,1,1,1,1,true));
        cardList.add(new Card(1,2,1,1,1,false));
        cardList.add(new Card(1,2,1,1,1,false));
        cardList.add(new Card(1,1,2,1,1,false));
        cardList.add(new Card(1,3,3,1,1,false));
        cardList.add(new Card(1,3,1,2,1,false));
        cardList.add(new Card(1,3,1,3,1,false));
        cardList.add(new Card(1,2,1,1,2,false));
        cardList.add(new Card(1,2,1,1,3,false));
        cardList.add(new Card(1,3,1,1,1,false));
        cardList.add(new Card(1,3,1,1,1,false));
        cardList.add(new Card(1,3,1,1,1,false));
    }

    /*private void setLogFragment() {
        LogFragment logFragment = new LogFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.constraint_layout, logFragment);

    }*/

}