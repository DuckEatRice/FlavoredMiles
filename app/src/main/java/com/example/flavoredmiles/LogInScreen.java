package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class LogInScreen extends AppCompatActivity { // implements AdapterView.OnItemSelectedListener{

    /*private Spinner Year;
    private Spinner Month;
    private Spinner Day;*/

    TextView signUp;
    ImageView back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        signUp = findViewById(R.id.SignUp);
        back = findViewById(R.id.backButton);

        SpannableString SignUp  = new SpannableString("Sign In");
        SignUp.setSpan(new UnderlineSpan(), 0, SignUp.length(), 0);
        signUp.setText(SignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });






        /*Year = findViewById(R.id.Year);
        String[] Years = getResources().getStringArray(R.array.Years);

        ArrayAdapter yearAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,Years);

        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year.setAdapter(yearAdapter);

        Year.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

    }
}