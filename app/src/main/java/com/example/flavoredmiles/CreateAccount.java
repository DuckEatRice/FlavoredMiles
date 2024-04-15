package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    FirebaseAuth mAuth;
    Context context;
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
    EditText Day;
    EditText Month;
    EditText Year;
    EditText FirstName;
    EditText LastName;
    EditText Email;
    EditText Password;
    View SignUpButton;
    TextView SignUpText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();

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

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                startActivity(intent);
            }
        });

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

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        Day = findViewById(R.id.Days);
        Month = findViewById(R.id.Months);
        Year = findViewById(R.id.Years);
        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Email = findViewById(R.id.CreateEmail);
        Password = findViewById(R.id.CreatePassword);
        SignUpButton = findViewById(R.id.SignUpButton);
        SignUpText = findViewById(R.id.SignUpText);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, day, month, year, firstName, lastname;
                email = String.valueOf(Email.getText().toString());
                password = String.valueOf(Password.getText().toString());
                day = String.valueOf(Day.getText().toString());
                month = String.valueOf(Month.getText().toString());
                year = String.valueOf(Year.getText().toString());
                firstName = String.valueOf(FirstName.getText().toString());
                lastname = String.valueOf(LastName.getText().toString());

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(context, "Enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(context, "Enter a valid password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(day))
                {
                    Toast.makeText(context, "Enter a valid day", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(month))
                {
                    Toast.makeText(context, "Enter a valid month", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(year))
                {
                    Toast.makeText(context, "Enter a valid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(firstName))
                {
                    Toast.makeText(context, "Enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(lastname))
                {
                    Toast.makeText(context, "Enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(CreateAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


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