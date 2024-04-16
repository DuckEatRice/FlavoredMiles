package com.example.flavoredmiles;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.util.*;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavoredmiles.JSONFile;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.FoodViewHolder>
{
    private Context context;
    private  ArrayList<JSONFile> javaList = new ArrayList<>();
    Activity activity;

    public RecyclerAdapter(Context context, ArrayList javaList, Activity activity)
    {
        this.context = context;
        this.javaList = javaList;
        this.activity = activity;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;
        private ImageView imageView;
        private CardView cardView;

        public FoodViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.blackStar);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override

    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_adapter, parent, false);
        return new FoodViewHolder((view));
    }

    @Override

    public void onBindViewHolder(@NonNull FoodViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        JSONFile item = javaList.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("name:",javaList.get(position).getMealName());
                intent.putExtra("price",javaList.get(position).getPrice());

            }
        });
    }

    @Override

    public int getItemCount()
    {
        return javaList.size();
    }

}