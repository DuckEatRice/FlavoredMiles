package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private ArrayList<JSONFile> FoodsList = new ArrayList<>();

    TextView coupon;
    TextView credit;
    TextView Terms;
    TextView Privacy;
    View Logo;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coupon = findViewById(R.id.Coupon);
        credit = findViewById(R.id.Credits);
        Terms = findViewById(R.id.TOS);
        Privacy = findViewById(R.id.Privacy);
        Logo = findViewById(R.id.logo);

        SpannableString couponCodes  = new SpannableString("Coupon Codes");
        couponCodes.setSpan(new UnderlineSpan(), 0, couponCodes.length(), 0);
        coupon.setText(couponCodes);

        SpannableString creditScreen = new SpannableString("Credits Screen");
        creditScreen.setSpan(new UnderlineSpan(), 0, creditScreen.length(), 0);
        credit.setText(creditScreen);

        SpannableString TOS  = new SpannableString("Terms of Service");
        TOS.setSpan(new UnderlineSpan(), 0, TOS.length(), 0);
        Terms.setText(TOS);

        SpannableString PrivacyDocument = new SpannableString("Privacy");
        PrivacyDocument.setSpan(new UnderlineSpan(), 0, PrivacyDocument.length(), 0);
        Privacy.setText(PrivacyDocument);

        Terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tos = new Intent(getApplicationContext(), Terms_Of_Service.class);
                startActivity(tos);
            }
        });

        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent privat = new Intent(getApplicationContext(), Privacy.class);
                startActivity(privat);
            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent credits = new Intent(getApplicationContext(), CreditsScreen.class);
                startActivity(credits);
            }
        });

        Logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(log);
            }
        });



        /*String temp = loadJSONFromAsset();

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(this, FoodsList, MainActivity.this);
        recyclerView.setAdapter(recyclerAdapter);

        try{

        } catch ()
        {

        }*/



    }

    /*private void setupJSONArray(JSONArray obj) throws JSONException {

        Intent intent = getIntent();

        for (int i = 0; i<=obj.length() - 1; i++)
        {
            JSONObject entry = obj.getJSONObject(i);

            String MealName = entry.getString("MealName");
            //Drawable MealPicture =
            String MealDescription = entry.getString("MealDescription");
            String MealType = entry.getString("MealType");
            String Rating = entry.getString("MealRating");
            String Price = entry.getString("MealPrice");
            String Time = entry.getString("MealTime");
            String Calories = entry.getString("MealCalories");
            String Ingredients = entry.getString("MealIngredients");

            FoodsList.add(new JSONFile(MealName, null, MealDescription, MealType, Rating, Price, Time, Calories, Ingredients));
        }

    }

    public String loadJSONFromAsset()
    {
        String json = null;
        try {
            InputStream is = getAssets().open("Foods.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            Log.d("Error", "didn't work");
            return null;
        }
        Log.d("Worked", "It worked");
        return json;
    }*/
}