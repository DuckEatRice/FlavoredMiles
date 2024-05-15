package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AccountDetails extends AppCompatActivity {

    TextView accountName;
    TextView accountEmail;
    TextView accountPassword;
    TextView accountLanguage;
    TextView accountBirthday;
    TextView accountUserID;
    TextView LargeName;
    ImageView backarrow;
    View signoutView;
    TextView accSignOut;
    ImageView signoutIcon;

    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String day = intent.getStringExtra("day");
        String month = intent.getStringExtra("month");
        String year = intent.getStringExtra("year");
        String email = intent.getStringExtra("email");
        String userID = intent.getStringExtra("user");

        /**
         * Since we want to keep accounts secure and safe, the "fakeUser" is only half the length of the real userID.
         */
        int length = userID.length();
        String fakeUser = userID.substring(0, length/2);

        accountName = findViewById(R.id.accountName);
        accountEmail = findViewById(R.id.accEmail);
        accountPassword = findViewById(R.id.accPassword);
        accountLanguage = findViewById(R.id.accLanguage);
        accountBirthday  = findViewById(R.id.accBirthday);
        signoutView = findViewById(R.id.view25);
        accSignOut = findViewById(R.id.accSignOut);
        //accountUserID = findViewById(R.id.accountUserId);
        LargeName = findViewById(R.id.LargeName);
        signoutIcon = findViewById(R.id.LockedIcon);
        backarrow = findViewById(R.id.accountBackArrow);

        accountName.setText(" " + firstName + " " + lastName);
        accountEmail.setText(" " + email);
        accountPassword.setText(" " + "*********");
        accountLanguage.setText(" " + "English, US");
        accountBirthday.setText(" " + month + " / " + day + " / " + year);
        //accountUserID.setText(" " + fakeUser);
        LargeName.setText(" " + firstName + " " + lastName);

        /**
         * @Intent
         * Intents back to MainMenu class
         */
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent1);
            }
        });

        signoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                startActivity(intent);
                finish();
            }
        });

        signoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                startActivity(intent);
                finish();
            }
        });

        accSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}