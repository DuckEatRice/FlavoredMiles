package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends AppCompatActivity {

    TextView coupon;
    TextView credit;
    TextView Terms;
    TextView Privacy;
    View Logo;
    TextView gettoknowus;

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private ArrayList<JSONFile> FoodsList = new ArrayList<>();
    Button sampleButton;
    ImageView backButton;
    ImageView cartIcon;
    TextView Welcome;
    FirebaseUser user;
    FirebaseFirestore fStore;
    FirebaseAuth auth;
    ImageView accountButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        coupon = findViewById(R.id.Coupon);
        credit = findViewById(R.id.Credits);
        Terms = findViewById(R.id.TOS);
        Privacy = findViewById(R.id.Privacy);
        Logo = findViewById(R.id.logo);

        /**
         * @SpannableString
         * Makes it so that text is underlined
         * https://stackoverflow.com/questions/5645789/how-to-set-underline-text-on-textview
         */
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

        /**
         * @Intent
         * Intents to Terms_Of_Service class
         */
        Terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tos = new Intent(getApplicationContext(), Terms_Of_Service.class);
                startActivity(tos);
            }
        });

        /**
         * @Intent
         * Intents to Privacy class
         */
        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent privat = new Intent(getApplicationContext(), Privacy.class);
                startActivity(privat);
            }
        });

        /**
         * @Intent
         * Intents to CreditsScreen class
         */
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent credits = new Intent(getApplicationContext(), CreditsScreen.class);
                startActivity(credits);
            }
        });

        /**
         * @Intent
         * Intents to MainActivity class
         */
        Logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(log);
            }
        });

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Line for separation

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();

        Welcome = findViewById(R.id.welcometitle);
        backButton = findViewById(R.id.backButton2);
        cartIcon = findViewById(R.id.cartIcon);
        //sampleButton = findViewById(R.id.SignOut);
        accountButton = findViewById(R.id.accountButton);
        gettoknowus = findViewById(R.id.GetToKnowUs);

        /**
         * Runs method if the user isn't null
         */
        if (user != null) {
            String userId = auth.getCurrentUser().getUid();
            gettoknowus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_SHORT).show();
                }
            });
            DocumentReference documentReference = fStore.collection("MealUsers").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                    /**
                     * Gets variables from documentReference which gets the information from FirebaseFirestore
                     */
                    String firstName = documentSnapshot.getString("firstName");
                    String lastName = documentSnapshot.getString("lastName");
                    String day = documentSnapshot.getString("day");
                    String month = documentSnapshot.getString("month");
                    String year = documentSnapshot.getString("year");
                    String email = documentSnapshot.getString("email");
                    Welcome.setText("Welcome, \n" + firstName + " " + lastName);

                    /**
                     * @Intent
                     * Intents information into AccountDetails class
                     */
                    accountButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), AccountDetails.class);
                            intent.putExtra("firstName", firstName);
                            intent.putExtra("lastName", lastName);
                            intent.putExtra("day", day);
                            intent.putExtra("month", month);
                            intent.putExtra("year", year);
                            intent.putExtra("email", email);
                            intent.putExtra("user", userId);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
        /**
         * If user is null, essentially if there is no user logged in
         * @Intent
         * Intents back to LogInScreen class
         */
        else
        {
            Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
            startActivity(intent);
            finish();
        }
        /**
         * @Intent
         * Intents to MainActivity class
         */
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * @Intent
         * Intents to CartActivity class
         */
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });


        /*if (user == null)
        {
            Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
            startActivity(intent);
            finish();
        }
        else
        {
            //sample.setText(user.getEmail());
        }*/

        /*sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                startActivity(intent);
                finish();
            }
        });*/

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        String temp = loadJSONFromAsset();

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainMenu.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(this, FoodsList, MainMenu.this, fStore, user, auth) ;
        //Log.d("Rian Rian", "FoodsList size: " + FoodsList.size());
        recyclerView.setAdapter(recyclerAdapter);

        try{
            JSONArray obj = new JSONArray(temp);
            setupJSONArray(obj);

        } catch (JSONException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        /*for(JSONFile foodList : FoodsList)
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
        }*/

    }

    private void setupJSONArray(JSONArray obj) throws JSONException
    {

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
//victor from 9th grade was here