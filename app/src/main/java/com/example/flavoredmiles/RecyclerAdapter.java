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
import android.widget.Toast;

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
        private TextView name;
        private TextView time;
        private TextView price;
        private TextView rating;
        private ImageView foodView;
        private ImageView addButton;
        private CardView cardView;

        public FoodViewHolder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.foodname);
            time = itemView.findViewById(R.id.time);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            foodView = itemView.findViewById(R.id.foodimage);
            addButton = itemView.findViewById(R.id.addbutton);
            cardView = itemView.findViewById(R.id.cardview);

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

        holder.name.setText(javaList.get(position).getMealName());
        holder.price.setText(javaList.get(position).getPrice());
        holder.rating.setText(javaList.get(position).getRating());
        holder.time.setText(javaList.get(position).getTime());


        String imageName = item.getMealPicture();
        int resourceId = context.getResources().getIdentifier(javaList.get(position).getMealPicture(), "drawable", holder.itemView.getContext().getPackageName());

        if (resourceId != 0) {
            holder.foodView.setImageResource(resourceId); // Set the image to the ImageView
        } else {
            holder.foodView.setImageResource(R.drawable.imagenotfound);
        }

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "Doesn't work right now :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override

    public int getItemCount()
    {
        return javaList.size();
    }

}