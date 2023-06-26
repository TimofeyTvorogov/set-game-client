package com.example.client;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private ArrayList<Card> cardList;

    public recyclerAdapter(ArrayList<Card> cardList){
        this.cardList = cardList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(final View view){
            super(view);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
