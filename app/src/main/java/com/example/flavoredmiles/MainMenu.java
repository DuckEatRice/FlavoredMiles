package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;

public class MainMenu extends AppCompatActivity {

    FirebaseAuth auth;
    Button sampleButton;
    TextView sample;
    FirebaseUser user;

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

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
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
        });

    }
}