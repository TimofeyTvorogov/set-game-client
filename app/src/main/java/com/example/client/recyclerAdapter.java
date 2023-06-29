package com.example.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private ArrayList<Card> cardList;
    private Context context;
    private RecyclerViewClickListener clickListener;

    public recyclerAdapter(ArrayList<Card> cardList, Context context, RecyclerViewClickListener clickListener){
        this.cardList = cardList;
        this.context = context;
        this.clickListener = clickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView fig1, fig2, fig3;
        private ConstraintLayout card;

        public MyViewHolder(final View view){
            super(view);
            fig1 = view.findViewById(R.id.fig1);
            fig2 = view.findViewById(R.id.fig2);
            fig3 = view.findViewById(R.id.fig3);
            card = view.findViewById(R.id.card);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
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
        boolean picked = cardList.get(position).isPicked();

        //Todo clich effect

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

        switch (shape){
            case 1:
                switch (type){
                    case 1:
                        holder.fig1.setImageResource(R.drawable.rhomb_empty);
                        holder.fig2.setImageResource(R.drawable.rhomb_empty);
                        holder.fig3.setImageResource(R.drawable.rhomb_empty);
                        break;
                    case 2:
                        holder.fig1.setImageResource(R.drawable.rhomb_blured);
                        holder.fig2.setImageResource(R.drawable.rhomb_blured);
                        holder.fig3.setImageResource(R.drawable.rhomb_blured);
                        break;
                    case 3:
                        holder.fig1.setImageResource(R.drawable.rhomb_full);
                        holder.fig2.setImageResource(R.drawable.rhomb_full);
                        holder.fig3.setImageResource(R.drawable.rhomb_full);
                        break;
                }
                break;
            case 2:
                switch (type){
                    case 1:
                        holder.fig1.setImageResource(R.drawable.circle_empty);
                        holder.fig2.setImageResource(R.drawable.circle_empty);
                        holder.fig3.setImageResource(R.drawable.circle_empty);
                        break;
                    case 2:
                        holder.fig1.setImageResource(R.drawable.circle_blured);
                        holder.fig2.setImageResource(R.drawable.circle_blured);
                        holder.fig3.setImageResource(R.drawable.circle_blured);
                        break;
                    case 3:
                        holder.fig1.setImageResource(R.drawable.circle_full);
                        holder.fig2.setImageResource(R.drawable.circle_full);
                        holder.fig3.setImageResource(R.drawable.circle_full);
                        break;
                }
                break;
            case 3:
                switch (type){
                    case 1:
                        holder.fig1.setImageResource(R.drawable.triangle_empty);
                        holder.fig2.setImageResource(R.drawable.triangle_empty);
                        holder.fig3.setImageResource(R.drawable.triangle_empty);
                        break;
                    case 2:
                        holder.fig1.setImageResource(R.drawable.triangle_blured);
                        holder.fig2.setImageResource(R.drawable.triangle_blured);
                        holder.fig3.setImageResource(R.drawable.triangle_blured);
                        break;
                    case 3:
                        holder.fig1.setImageResource(R.drawable.triangle_full);
                        holder.fig2.setImageResource(R.drawable.triangle_full);
                        holder.fig3.setImageResource(R.drawable.triangle_full);
                        break;
                }
                break;
        }


    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View view,int position);
    }
}
