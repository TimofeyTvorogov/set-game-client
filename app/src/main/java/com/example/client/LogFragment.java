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

import com.example.client.databinding.FragmentBlankBinding;

public class LogFragment extends Fragment {

    private FragmentBlankBinding binding;
    private IClickListener mClickListener;

    @Override

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mClickListener = (IClickListener) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(inflater, container, false);
        binding.btnGoReg.setOnClickListener(view -> {
            mClickListener.onClick();
        });
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}