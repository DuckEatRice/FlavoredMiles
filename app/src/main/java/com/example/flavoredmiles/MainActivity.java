package com.example.flavoredmiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private ArrayList<JSONFile> FoodsList = new ArrayList<>();


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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