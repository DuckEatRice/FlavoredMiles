package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MealDetails extends AppCompatActivity {

    ImageView foodImage;
    TextView mealName;
    TextView mealRating;
    TextView mealPrice;
    TextView mealCalories;
    TextView mealTime;
    TextView mealDetails;
    TextView mealIngredients;
    TextView mealTotalPrice;
    TextView Quantity;
    ImageView AddQuantity;
    ImageView SubtractQuantity;
    ImageView addCart;
    ImageView backarrow;

    ArrayList<CartItem> cartItems = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        foodImage = findViewById(R.id.mealdetailsImage);
        mealName = findViewById(R.id.mealdetailName);
        mealRating = findViewById(R.id.mealdetailsRating);
        mealPrice = findViewById(R.id.mealdetailsPrice);
        mealCalories = findViewById(R.id.mealdetailCalories);
        mealTime = findViewById(R.id.mealdetailsTime);
        mealDetails = findViewById(R.id.mealdetailDetails);
        mealIngredients = findViewById(R.id.mealdetailIngredients);
        mealTotalPrice = findViewById(R.id.mealdetailTotalPrice);
        Quantity = findViewById(R.id.quantityNumber);
        AddQuantity = findViewById(R.id.quantityAdd);
        SubtractQuantity = findViewById(R.id.quantitySubtract);
        addCart = findViewById(R.id.mealdetailsAdd);
        backarrow = findViewById(R.id.mealdetailsBackArrow);

        /**
         * @Intent
         * Intents to MainMenu class
         */
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });

        /**
         * @Intent
         * Receives intents sent from RecyclerAdapter
         */
        Intent intent = getIntent();
        String MealName = intent.getStringExtra("MealName");
        String MealPicture = intent.getStringExtra("MealPicture");
        String MealDescription = intent.getStringExtra("MealDescription");
        String MealType = intent.getStringExtra("MealType");
        String MealRating = intent.getStringExtra("MealRating");
        String MealPrice = intent.getStringExtra("MealPrice");
        String MealTime = intent.getStringExtra("MealTime");
        String MealCalories = intent.getStringExtra("MealCalories");
        String MealIngredients = intent.getStringExtra("MealIngredients");

        /**
         * resourceId just uses the getIdentifier required documentation to get picture information, and uses getResources() to receive said information from the R file.
         */
        int resourceId = getApplicationContext().getResources().getIdentifier(MealPicture, "drawable", getApplicationContext().getPackageName());
        if (resourceId != 0) {
            foodImage.setImageResource(resourceId); // Set the image to the ImageView
        } else {
            foodImage.setImageResource(R.drawable.imagenotfound);
        }

        /**
         * Setting the necessary TextViews to make things look good
         */
        mealName.setText(MealName);
        mealRating.setText(MealRating);
        mealPrice.setText("$" + MealPrice);
        mealCalories.setText("Calories:\n" + MealCalories + " cal");
        mealTime.setText(MealTime);
        mealDetails.setText(MealDescription);
        mealIngredients.setText("Ingredients:\n" + MealIngredients);

        final int[] quantity = {0};
        quantity[0]++;

        /**
         * Essentially, when quantity is increased, calories, price, and everything affected by quantity is increased.
         */
        AddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity[0]++;
                String quantityNumber = String.valueOf(quantity[0]);
                Quantity.setText(quantityNumber);

                int calories = Integer.valueOf(MealCalories);
                int totalCalories = quantity[0] * calories;
                String cals = String.valueOf(totalCalories);
                mealCalories.setText("Calories:\n" + cals + " cal");

                double price = Double.valueOf(MealPrice);
                double total = quantity[0] * price;
                String result = String.format("%.2f", total);
                String totalPrice = String.valueOf(result);
                mealTotalPrice.setText("Total Price:\n$" + totalPrice);
            }
        });

        /**
         * When quantity decreases, calories, price, and everything affected by quantity is decreased.
         */
        SubtractQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity[0] > 1)
                {
                    quantity[0]--;
                    String quantityNumber = String.valueOf(quantity[0]);
                    Quantity.setText(quantityNumber);

                    double price = Double.valueOf(MealPrice);
                    double total = quantity[0] * price;
                    String result = String.format("%.2f", total);
                    String totalPrice = String.valueOf(result);
                    mealTotalPrice.setText("Total Price:\n$" + totalPrice);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You cannot subtract any further.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * @Intent
         * When addCart is clicked on intents an ArrayList, "cartItems" over to CartActivity class
         */
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantityNumber = String.valueOf(quantity[0]);
                CartItem cartItem = new CartItem(MealName, MealPicture, MealPrice, quantityNumber);
                cartItems.add(cartItem);

                Intent intent1 = new Intent(getApplicationContext(), CartActivity.class);
                intent1.putParcelableArrayListExtra("cartList", cartItems); //issue
                startActivity(intent1);

                Toast.makeText(getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();


                //Toast.makeText(getApplicationContext(), "Doesn't work at the moment :)", Toast.LENGTH_SHORT).show();
            }
        });


    }
}