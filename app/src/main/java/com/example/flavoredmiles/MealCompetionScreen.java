package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MealCompetionScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MealCompletionAdapter mealCompletionAdapter;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_competion_screen);

        Intent intent = getIntent();
        ArrayList<CartItem> cartItemArrayList =  intent.getParcelableArrayListExtra("CartItemList");

        recyclerView = findViewById(R.id.MealCompletionRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealCompetionScreen.this);
        recyclerView.setLayoutManager(layoutManager);

        mealCompletionAdapter = new MealCompletionAdapter(cartItemArrayList, context, MealCompetionScreen.this);
        recyclerView.setAdapter(mealCompletionAdapter);

    }
}