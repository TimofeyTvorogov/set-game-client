package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String address = "http://84.201.155.174";
    private Retrofit retrofit;
    private Api api;
    private List<Card> cardList;
    private Button btn_FrLog;
    private ConstraintLayout constraintLayout;
    //change
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder()
                .baseUrl(address)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        // Фрагмент входа
        btn_FrLog = findViewById(R.id.btn_Log);
        constraintLayout = findViewById(R.id.constraint_layout);

        setLogFragment();

        btn_FrLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLogFragment();
            }
        });
    }

    private void setLogFragment() {
        LogFragment logFragment = new LogFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.constraint_layout, logFragment);
    }

}