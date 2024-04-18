package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInScreen extends AppCompatActivity { // implements AdapterView.OnItemSelectedListener{

    /*private Spinner Year;
    private Spinner Month;
    private Spinner Day;*/

    Context context;
    FirebaseAuth mAuth;
    TextView signUp;
    ImageView back;
    Button login;
    EditText emailLogIn;
    EditText passwordLogIn;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(intent);
        }
    }



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
        mAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.SignUp);
        back = findViewById(R.id.backButton);
        login = findViewById(R.id.LogIn);
        emailLogIn = findViewById(R.id.Email);
        passwordLogIn = findViewById(R.id.Password);

        SpannableString SignUp  = new SpannableString("Sign Up");
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

        passwordLogIn.setTransformationMethod(new PasswordTransformationMethod());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(emailLogIn.getText().toString());
                password = String.valueOf(passwordLogIn.getText().toString());

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

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(), "Successful Login.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                                    startActivity(intent);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

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