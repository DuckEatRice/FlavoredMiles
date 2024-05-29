package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.protobuf.MapEntryLite;

import java.util.ArrayList;

public class MealCompetionScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MealCompletionAdapter mealCompletionAdapter;
    Context context;
    TextView SubTotal;
    TextView TotalTax;
    TextView Total;
    ImageView MealCompletionBackArrow;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_competion_screen);

        Intent intent = getIntent();
        ArrayList<CartItem> cartItemArrayList =  intent.getParcelableArrayListExtra("CartItemList");
        double subtotal = intent.getDoubleExtra("TotalPrice",0);
        double totaltax = intent.getDoubleExtra("SubTotal",0);
        double total = intent.getDoubleExtra("TotalTax",0);

        // Settings up RecyclerView and MealCompletionAdapter
        recyclerView = findViewById(R.id.MealCompletionRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealCompetionScreen.this);
        recyclerView.setLayoutManager(layoutManager);
        mealCompletionAdapter = new MealCompletionAdapter(cartItemArrayList, context, MealCompetionScreen.this);
        recyclerView.setAdapter(mealCompletionAdapter);

        // Matching the IDs with the TextViews, ImageViews, and Views
        SubTotal = findViewById(R.id.MealCompletionSubTotal);
        TotalTax = findViewById(R.id.MealCompletionTotalTax);
        Total = findViewById(R.id.MealCompletionTotal);
        MealCompletionBackArrow = findViewById(R.id.MealCompletionBackArrow);

        SubTotal.setText("$" + String.format("%.2f", totaltax));
        TotalTax.setText("$" + String.format("%.2f", total));
        Total.setText("$" + String.format("%.2f", subtotal));

        /**
         * @Intent
         * Intents back to MainMenu class when clicked
         */
        MealCompletionBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent1);
            }
        });

    }
}