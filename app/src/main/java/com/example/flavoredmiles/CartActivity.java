package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class CartActivity extends AppCompatActivity {

    //private ActivityCartBinding binding;
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    ArrayList<CartItem> cartItemsArrayList = new ArrayList<>();
    ImageView cartBackArrow;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedPreferences sharedPreferences = getSharedPreferences("cart_items", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        cartBackArrow = findViewById(R.id.cartBackArrowhi);
        cartRecyclerView = findViewById(R.id.cartRecyclerView); // Replace with your RecyclerView ID
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
        cartRecyclerView.setLayoutManager(layoutManager);

        /**
         * @Intent
         * Receives ArrayList from MealDetails
         */
        Intent intent = getIntent();
        ArrayList<CartItem> cartItems = intent.getParcelableArrayListExtra("cartList"); //line 27 issue
        ArrayList<CartItem> cartItemsfromRecyclerAdapter = intent.getParcelableArrayListExtra("cartListfromRecyclerAdapter");


        if ((cartItems == null || cartItems.isEmpty())) {
            //Toast.makeText(getApplicationContext(), "Your cart is empty.", Toast.LENGTH_SHORT).show();
            // You can also show a different layout here
        } else
        {
            Log.d("DEBUGGINGRAHH", "cartItems size: " + cartItems.size());
            /*for (int j = 0; j < cartItems.size(); j++) {
                cartItemsArrayList.add(new CartItem(cartItems.get(j).getMealName(), cartItems.get(j).getImageName(), cartItems.get(j).getMealPrice(), cartItems.get(j).getQuantity()));
            }*/
            //Toast.makeText(getApplicationContext(), "Working?", Toast.LENGTH_SHORT).show();
            /**
             * Depending on the if statement, the CartActivity will set CartAdapter ArrayList differently
             */
            cartAdapter = new CartAdapter(cartItems, this, CartActivity.this);
            cartRecyclerView.setAdapter(cartAdapter);
        }
        if ((cartItemsfromRecyclerAdapter == null || cartItemsfromRecyclerAdapter.isEmpty()))
        {
            //Toast.makeText(getApplicationContext(), "Your cart is empty.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Log.d("DEBUGGINGRAHH", "cartItems size: " + cartItems.size());
            /*for (int j = 0; j < cartItemsfromRecyclerAdapter.size(); j++) {
                cartItemsArrayList.add(new CartItem(cartItemsfromRecyclerAdapter.get(j).getMealName(), cartItemsfromRecyclerAdapter.get(j).getImageName(), cartItemsfromRecyclerAdapter.get(j).getMealPrice(), cartItemsfromRecyclerAdapter.get(j).getQuantity()));
            }*/
            //Toast.makeText(getApplicationContext(), "Working?", Toast.LENGTH_SHORT).show();

            /**
             * Depending on the if statement, the CartActivity will set CartAdapter ArrayList differently
             */
            cartAdapter = new CartAdapter(cartItemsfromRecyclerAdapter, this, CartActivity.this);
            cartRecyclerView.setAdapter(cartAdapter);
        }

        /**
         * @Intent
         * Intents back to MainMenu class
         */
        cartBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent1);
            }
        });
    }

    /*public void saveCartItems(SharedPreferences sharedPreferences, SharedPreferences.Editor editor, ArrayList<CartItem> list)
    {
         sharedPreferences = getSharedPreferences("cart_items", MODE_PRIVATE);
         editor = sharedPreferences.edit();
         editor.putString("cart_items_list", String.valueOf(list));
    }*/

}