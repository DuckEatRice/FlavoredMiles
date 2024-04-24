package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    ArrayList<CartItem> cartItemsArrayList = new ArrayList<>();
    ImageView cartBackArrow;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartBackArrow = findViewById(R.id.cartBackArrowhi);
        cartRecyclerView = findViewById(R.id.cartRecyclerView); // Replace with your RecyclerView ID
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
        cartRecyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        ArrayList<CartItem> cartItems = intent.getParcelableArrayListExtra("cartList"); //line 27 issue
        ArrayList<CartItem> cartItemsfromRecyclerAdapter = intent.getParcelableArrayListExtra("cartListfromRecyclerAdapter");


        if ((cartItems == null || cartItems.isEmpty())) {
            //Toast.makeText(getApplicationContext(), "Your cart is empty.", Toast.LENGTH_SHORT).show();
            // You can also show a different layout here
        } else
        {
            Log.d("DEBUGGINGRAHH", "cartItems size: " + cartItems.size());
            for (int j = 0; j < cartItems.size(); j++) {
                cartItemsArrayList.add(new CartItem(cartItems.get(j).getMealName(), cartItems.get(j).getImageName(), cartItems.get(j).getMealPrice(), cartItems.get(j).getQuantity()));
                Log.d("DEBUGGINGRAHH", "MealName: " + cartItems.get(j).getMealName());
                Log.d("DEBUGGINGRAHH", "ImageName: " + cartItems.get(j).getImageName());
                Log.d("DEBUGGINGRAHH", "MealPrice: " + cartItems.get(j).getMealPrice());
                Log.d("DEBUGGINGRAHH", "Quantity:" + cartItems.get(j).getQuantity());
            }
            //Toast.makeText(getApplicationContext(), "Working?", Toast.LENGTH_SHORT).show();
            cartAdapter = new CartAdapter(cartItemsArrayList, this, CartActivity.this);
            cartRecyclerView.setAdapter(cartAdapter);
        }
        if ((cartItemsfromRecyclerAdapter == null || cartItemsfromRecyclerAdapter.isEmpty()))
        {
            //Toast.makeText(getApplicationContext(), "Your cart is empty.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Log.d("DEBUGGINGRAHH", "cartItems size: " + cartItems.size());
            for (int j = 0; j < cartItemsfromRecyclerAdapter.size(); j++) {
                cartItemsArrayList.add(new CartItem(cartItemsfromRecyclerAdapter.get(j).getMealName(), cartItemsfromRecyclerAdapter.get(j).getImageName(), cartItemsfromRecyclerAdapter.get(j).getMealPrice(), cartItemsfromRecyclerAdapter.get(j).getQuantity()));
                Log.d("DEBUGGINGRAHH", "MealName: " + cartItemsfromRecyclerAdapter.get(j).getMealName());
                Log.d("DEBUGGINGRAHH", "ImageName: " + cartItemsfromRecyclerAdapter.get(j).getImageName());
                Log.d("DEBUGGINGRAHH", "MealPrice: " + cartItemsfromRecyclerAdapter.get(j).getMealPrice());
                Log.d("DEBUGGINGRAHH", "Quantity:" + cartItemsfromRecyclerAdapter.get(j).getQuantity());
            }
            //Toast.makeText(getApplicationContext(), "Working?", Toast.LENGTH_SHORT).show();
            cartAdapter = new CartAdapter(cartItemsArrayList, this, CartActivity.this);
            cartRecyclerView.setAdapter(cartAdapter);
        }


        cartBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent1);
            }
        });




    }
}