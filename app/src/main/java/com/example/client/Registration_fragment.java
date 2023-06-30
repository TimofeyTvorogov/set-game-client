package com.example.client;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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

            Dialog dialog=new Dialog(getContext());
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            dialog.getWindow().setAttributes(lp);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_add_room);

            EditText editText = dialog.findViewById(R.id.codeRoom);
            Button button = dialog.findViewById(R.id.joinBtn);

            button.setOnClickListener(view1 -> {
                if (editText.getText().toString().trim().isEmpty()){
                    //create room
                }
                else {
                    //join room
                }
                startActivity(intent);
            });

            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.show();

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