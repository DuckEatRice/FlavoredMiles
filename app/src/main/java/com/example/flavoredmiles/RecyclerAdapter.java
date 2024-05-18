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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.FoodViewHolder>
{
    private Context context;
    private  ArrayList<JSONFile> javaList = new ArrayList<>();
    Activity activity;
    FirebaseFirestore firestore;
    FirebaseUser user;
    FirebaseAuth auth;

    public RecyclerAdapter(Context context, ArrayList javaList, Activity activity, FirebaseFirestore firestore, FirebaseUser user, FirebaseAuth auth)
    {
        this.firestore = firestore;
        this.user = user;
        this.auth = auth;
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
        /**
         * resourceId just uses the getIdentifier required documentation to get picture information, and uses getResources() to receive said information from the R file.
         */
        int resourceId = context.getResources().getIdentifier(javaList.get(position).getMealPicture(), "drawable", holder.itemView.getContext().getPackageName());
        Log.d("HELP ME PLEASE RAHHH", holder.itemView.getContext().getPackageName());

        if (resourceId != 0) {
            holder.foodView.setImageResource(resourceId); // Set the image to the ImageView
        } else {
            holder.foodView.setImageResource(R.drawable.imagenotfound);
        }

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<CartItem> cartItems = new ArrayList<>();
                /*String quantityNumber = String.valueOf(quantity[0]);
                CartItem cartItem = new CartItem(MealName, MealPicture, MealPrice, quantityNumber);
                cartItems.add(cartItem);

                Intent intent1 = new Intent(getApplicationContext(), CartActivity.class);
                intent1.putParcelableArrayListExtra("cartList", cartItems); //issue
                startActivity(intent1);

                Toast.makeText(getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();*/

                if (user != null)
                {

                    /**
                     * @Hashmap - Creates a hashmap that will store information from the EditTexts into the documentReference
                     */

                    DocumentReference documentReference = firestore.collection("MealUsers").document(user.getUid()).collection("MealStoring").document(javaList.get(position).getMealName());
                    Map<String, Object> Meals = new HashMap<>();
                    Meals.put("MealName", javaList.get(position).getMealName());
                    Meals.put("MealPicture", javaList.get(position).getMealPicture());
                    Meals.put("MealPrice", javaList.get(position).getPrice());
                    Meals.put("quantity", "1");

                    documentReference.set(Meals).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context.getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context.getApplicationContext(), "Cart failure.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context.getApplicationContext(), "User not signed in.", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(context.getApplicationContext(), MainMenu.class);
                    context.startActivity(intent1);
                }

            }
        });

        holder.foodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MealDetails.class);
                intent.putExtra("MealName",javaList.get(position).getMealName());
                intent.putExtra("MealPicture",javaList.get(position).getMealPicture());
                intent.putExtra("MealDescription",javaList.get(position).getMealDescription());
                intent.putExtra("MealType",javaList.get(position).getMealType());
                intent.putExtra("MealRating",javaList.get(position).getRating());
                intent.putExtra("MealPrice",javaList.get(position).getPrice());
                intent.putExtra("MealTime",javaList.get(position).getTime());
                intent.putExtra("MealCalories",javaList.get(position).getCalories());
                intent.putExtra("MealIngredients",javaList.get(position).getIngredients());
                context.startActivity(intent);
            }
        });
    }

    @Override

    public int getItemCount()
    {
        return javaList.size();
    }

}