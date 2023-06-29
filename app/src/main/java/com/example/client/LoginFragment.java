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

import java.util.Objects;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.registration.setOnClickListener(view -> {

        });

        binding.login.setOnClickListener(view -> {
            binding.loginET.getText().toString();
            binding.passwordET.getText().toString();
            startActivity(new Intent(getContext(), MainActivity.class));
            requireActivity().finish();
            //Todo Вот сюда Тимофей fsdkf

        });
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}