package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.client.databinding.ActivityForFragmentsBinding;
import com.example.client.databinding.FragmentBlankBinding;

public class ActivityForFragments extends AppCompatActivity {

    private ActivityForFragmentsBinding binding;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ActivityForFragmentsBinding.inflate(inflater, container, false);
        LogFragment logFragment = new LogFragment();
        RegFragment regFragment = new RegFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, logFragment);
        ft.addToBackStack(null);
        ft.commit();
        return binding.getRoot();
    }

//    @Override
//    public void onClick() {
//        startFragment(RegFragment);
//    }
//    private void startFragment(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    public void onDestroyView() {
        binding = null;
    }
}