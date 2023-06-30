package com.example.client;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.client.databinding.FragmentRegistrationBinding;

public class Registration_fragment extends Fragment {

    private FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater,container,false);


        binding.loginET.addTextChangedListener(loginTextWatcher);
        binding.passwordET.addTextChangedListener(loginTextWatcher);

        binding.login.setOnClickListener(view -> {
            String logIn = binding.loginET.getText().toString();
            String password = binding.passwordET.getText().toString();
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("Login", logIn);
            intent.putExtra("Password", password);

            PreGameActivity preGameActivity = new PreGameActivity();
        });

        return binding.getRoot();
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String login = binding.loginET.getText().toString().trim();
            String password = binding.passwordET.getText().toString().trim();
            binding.login.setEnabled(!login.isEmpty() && !password.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable editable) {}
    };

}