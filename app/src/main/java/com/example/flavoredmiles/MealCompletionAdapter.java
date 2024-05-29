package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MealCompletionAdapter extends RecyclerView.Adapter<MealCompletionAdapter.MealCompletionViewHolder> {
    private ArrayList<CartItem> cartItems;
    private Context context;
    Activity activity;
    public MealCompletionAdapter(ArrayList<CartItem> cartItems, Context context, Activity activity)
    {
        this.cartItems = cartItems;
        this.context = context;
        this.activity = activity;
    }

    public class MealCompletionViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        ImageView mealPicture;

        public MealCompletionViewHolder(View itemView) {
            super(itemView);

            mealName = itemView.findViewById(R.id.MealCompletioncartItemName);
            mealPicture = itemView.findViewById(R.id.MealCompletionMealImage);
        }
    }

    @NonNull
    @Override
    public MealCompletionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_meal_completion_adapter_cardview, parent, false);

        return new MealCompletionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealCompletionViewHolder holder, int position) {
        /**
         * resourceId just uses the getIdentifier required documentation to get picture information, and uses getResources() to receive said information from the R file.
         */
        int resourceId = activity.getResources().getIdentifier(cartItems.get(position).getImageName(), "drawable", holder.itemView.getContext().getPackageName());
        if (resourceId != 0) {
            holder.mealPicture.setImageResource(resourceId); // Set the image to the ImageView
        } else {
            holder.mealPicture.setImageResource(R.drawable.imagenotfound);
        }

        // Sets mealName textView as the MealName
        holder.mealName.setText(cartItems.get(position).getMealName());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }


}