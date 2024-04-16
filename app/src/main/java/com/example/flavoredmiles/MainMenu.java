package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kotlinx.coroutines.channels.ChannelSegment;

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

    }
}