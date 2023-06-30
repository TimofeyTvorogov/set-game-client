package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.client.databinding.ActivityPreGameBinding;

public class PreGameActivity extends AppCompatActivity {

    private ActivityPreGameBinding binding;
    private Registration_fragment registration_fragment = new Registration_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPreGameBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        replaceFragment(registration_fragment);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }
}