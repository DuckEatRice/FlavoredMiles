package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AccountDetails extends AppCompatActivity {

    TextView accountName;
    TextView accountEmail;
    TextView accountPassword;
    TextView accountLanguage;
    TextView accountBirthday;
    TextView accountUserID;
    TextView LargeName;
    ImageView lockedicon;
    ImageView backarrow;

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

        int length = userID.length();
        String fakeUser = userID.substring(0, length/2);

        accountName = findViewById(R.id.accountName);
        accountEmail = findViewById(R.id.accountEmail);
        accountPassword = findViewById(R.id.accountPassword);
        accountLanguage = findViewById(R.id.accountLanguage);
        accountBirthday  = findViewById(R.id.accountBirthday);
        accountUserID = findViewById(R.id.accountUserId);
        LargeName = findViewById(R.id.LargeName);
        lockedicon = findViewById(R.id.LockedIcon);
        backarrow = findViewById(R.id.accountBackArrow);

        accountName.setText(" " + firstName + " " + lastName);
        accountEmail.setText(" " + email);
        accountPassword.setText(" " + "*********");
        accountLanguage.setText(" " + "English, US");
        accountBirthday.setText(" " + month + " / " + day + " / " + year);
        accountUserID.setText(" " + fakeUser);
        LargeName.setText(" " + firstName + " " + lastName);

        lockedicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), userID, Toast.LENGTH_SHORT).show();
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent1);
            }
        });

    }
}