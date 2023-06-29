package com.example.client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.client.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.btnGoReg.setOnClickListener(view -> {

        });

        binding.login.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            //Todo Вот сюда Тимофей
        });

        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}