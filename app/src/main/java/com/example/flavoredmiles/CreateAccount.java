package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

public class CreateAccount extends AppCompatActivity {

    int daysAmount = 0;
    int monthsAmount = 0;
    int yearsAmount = 0;
    TextView Signin;
    RadioGroup male;
    RadioGroup female;
    boolean selected = false;
    TextView coupon;
    TextView credit;
    TextView Terms;
    TextView Privacy;
    View Logo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

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

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        Signin = findViewById(R.id.Sign_In);

        SpannableString Sign_in  = new SpannableString("Sign In");
        Sign_in.setSpan(new UnderlineSpan(), 0, Sign_in.length(), 0);
        Signin.setText(Sign_in);

        male = findViewById(R.id.Male);
        female = findViewById(R.id.Female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackgroundResource(R.drawable.circle_button2);

                female.setBackgroundResource(R.drawable.circle_button1);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackgroundResource(R.drawable.circle_button2);

                male.setBackgroundResource(R.drawable.circle_button1);
            }
        });



        /*Day = findViewById(R.id.Day);
        String[] Days = getResources().getStringArray(R.array.Days);

        ArrayAdapter dayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,Days);

        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Day.setAdapter(dayAdapter);

        Day.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //int dayValue = (int) adapterView.getItemAtPosition(i);
                //daysAmount += dayValue;

                ((TextView) parent.getChildAt(0)).setTextSize(7);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Month = findViewById(R.id.Months);
        String[] Months = getResources().getStringArray(R.array.Months);

        ArrayAdapter monthAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,Months);

        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Month.setAdapter(monthAdapter);

        Month.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(7);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }
}