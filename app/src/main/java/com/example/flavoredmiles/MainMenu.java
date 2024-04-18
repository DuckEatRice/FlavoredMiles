package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends AppCompatActivity {

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private ArrayList<JSONFile> FoodsList = new ArrayList<>();
    FirebaseAuth auth;
    Button sampleButton;
    TextView sample;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        sample = findViewById(R.id.sampleText);
        sampleButton = findViewById(R.id.SignOut);

        if (user == null)
        {
            Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
            startActivity(intent);
            finish();
        }
        else
        {
            sample.setText(user.getEmail());
        }

        sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                startActivity(intent);
                finish();
            }
        });

        String userId = user.getUid();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(userId);

        /*mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HashMap<String, Object> userData = (HashMap<String, Object>) snapshot.getValue();
                    String email = (String) userData.get("email");
                    String firstName = (String) userData.get("firstName");
                    String lastName = (String) userData.get("lastName");
                    String birthday = (String) userData.get("birthday");

                    sample.setText("Email: " + email + "\nName: " + firstName + " " + lastName + "\nBirthday: " + birthday);
                } else {
                    // Handle case where user data is not found
                    Toast.makeText(MainMenu.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainMenu.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
            }
        });*/

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        String temp = loadJSONFromAsset();

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(this, FoodsList, MainMenu.this);
        recyclerView.setAdapter(recyclerAdapter);

        try{
            JSONArray obj = new JSONArray(temp);
            setupJSONArray(obj);

        } catch (JSONException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        for(JSONFile foodList : FoodsList)
        {
            System.out.println("MealName" + foodList.getMealName());
            System.out.println("MealPicture" + foodList.getMealPicture());
            System.out.println("MealDescription" + foodList.getMealDescription());
            System.out.println("MealType" + foodList.getMealType());
            System.out.println("MealPrice" + foodList.getPrice());
            System.out.println("MealRating" + foodList.getRating());
            System.out.println("MealTime" + foodList.getTime());
            System.out.println("MealCalories" + foodList.getCalories());
            System.out.println("MealIngredients" + foodList.getIngredients());
        }

    }

    private void setupJSONArray(JSONArray obj) throws JSONException {

        Intent intent = getIntent();

        for (int i = 0; i<=obj.length() - 1; i++)
        {
            JSONObject entry = obj.getJSONObject(i);

            String MealName = entry.getString("MealName");
            String MealPicture = entry.getString("MealPicture");
            String MealDescription = entry.getString("MealDescription");
            String MealType = entry.getString("MealType");
            String Rating = entry.getString("MealRating");
            String Price = entry.getString("MealPrice");
            String Time = entry.getString("MealTime");
            String Calories = entry.getString("MealCalories");
            String Ingredients = entry.getString("MealIngredients");

            FoodsList.add(new JSONFile(MealName, MealPicture, MealDescription, MealType, Rating, Price, Time, Calories, Ingredients));
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
    }
}