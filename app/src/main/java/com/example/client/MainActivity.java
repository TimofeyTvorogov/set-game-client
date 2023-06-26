package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
        recyclerAdapter adapter = new recyclerAdapter(cardList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter(adapter);
    }

    private void setCardInfo() {
        cardList.add(new Card(1,1,1,1,1));
        cardList.add(new Card(1,1,1,1,1));
        cardList.add(new Card(1,1,1,1,1));

    }

    /*private void setLogFragment() {
        LogFragment logFragment = new LogFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.constraint_layout, logFragment);

    }*/

}