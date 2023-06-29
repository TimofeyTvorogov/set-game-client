package com.example.client;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private ArrayList<Card> cardList;
    private Context context;

    public recyclerAdapter(ArrayList<Card> cardList, Context context){
        this.cardList = cardList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView fig1, fig2, fig3;

        public MyViewHolder(final View view){
            super(view);
            fig1 = view.findViewById(R.id.fig1);
            fig2 = view.findViewById(R.id.fig2);
            fig3 = view.findViewById(R.id.fig3);
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
        int color = cardList.get(position).getColor();
        int shape = cardList.get(position).getShape();
        int type = cardList.get(position).getFill();
        int quantity = cardList.get(position).getCount();
        int id = cardList.get(position).getId();

        switch (color) {
            case 1:
                holder.fig1.setColorFilter(ContextCompat.getColor(context, R.color.green));
                holder.fig2.setColorFilter(ContextCompat.getColor(context, R.color.green));
                holder.fig3.setColorFilter(ContextCompat.getColor(context, R.color.green));
                break;
            case 2:
                holder.fig1.setColorFilter(ContextCompat.getColor(context, R.color.red));
                holder.fig2.setColorFilter(ContextCompat.getColor(context, R.color.red));
                holder.fig3.setColorFilter(ContextCompat.getColor(context, R.color.red));
                break;
            case 3:
                holder.fig1.setColorFilter(ContextCompat.getColor(context, R.color.purple));
                holder.fig2.setColorFilter(ContextCompat.getColor(context, R.color.purple));
                holder.fig3.setColorFilter(ContextCompat.getColor(context, R.color.purple));
                break;
        }

        switch (quantity) {
            case 1:
                holder.fig1.setVisibility(View.GONE);
                holder.fig2.setVisibility(View.VISIBLE);
                holder.fig3.setVisibility(View.GONE);
                break;
            case 2:
                holder.fig1.setVisibility(View.VISIBLE);
                holder.fig2.setVisibility(View.GONE);
                holder.fig3.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.fig1.setVisibility(View.VISIBLE);
                holder.fig2.setVisibility(View.VISIBLE);
                holder.fig3.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
