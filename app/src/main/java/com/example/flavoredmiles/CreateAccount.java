package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
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
    ImageView view_or_not;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser != null){
            //Intent intent = new Intent(context, )
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        coupon = findViewById(R.id.createCoupon);
        credit = findViewById(R.id.createCredits);
        Terms = findViewById(R.id.createTOS);
        Privacy = findViewById(R.id.createPrivacy);
        Logo = findViewById(R.id.createLogo);

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

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        Signin = findViewById(R.id.Sign_In);

        /**
         * @SpannableString
         * Makes it so that text is underlined
         * https://stackoverflow.com/questions/5645789/how-to-set-underline-text-on-textview
         */
        SpannableString Sign_in  = new SpannableString("Sign In");
        Sign_in.setSpan(new UnderlineSpan(), 0, Sign_in.length(), 0);
        Signin.setText(Sign_in);

        /**
         * @Intent
         * Intents to LogInScreen class
         */
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                startActivity(intent);
            }
        });

        male = findViewById(R.id.Male);
        female = findViewById(R.id.Female);

        /**
         * @RadioGroup
         * Essentially an icon that shows whether you chose male or female as your gender
         */
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackgroundResource(R.drawable.circle_button2);

                female.setBackgroundResource(R.drawable.circle_button1);
            }
        });

        /**
         * @RadioGroup
         * Essentially an icon that shows whether you chose male or female as your gender
         */
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackgroundResource(R.drawable.circle_button2);

                male.setBackgroundResource(R.drawable.circle_button1);
            }
        });

        final int[] clicks = {0};
        Password = findViewById(R.id.CreatePassword);

        view_or_not = findViewById(R.id.view_or_not);

        /**
         * When clicked on, sets the password to being unhidden.
         */
        view_or_not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                if(clicks[0] % 2 != 0)
                {
                    view_or_not.setImageResource(R.drawable.noview);
                    Password.setTransformationMethod(new PasswordTransformationMethod());
                }
                else
                {
                    view_or_not.setImageResource(R.drawable.view);
                    Password.setTransformationMethod(null);
                }
            }
        });

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        Day = findViewById(R.id.Months);
        Month = findViewById(R.id.Days);
        Year = findViewById(R.id.Years);
        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Email = findViewById(R.id.CreateEmail);

        SignUpButton = findViewById(R.id.SignUpButton);
        SignUpText = findViewById(R.id.SignUpText);


        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, day, month, year, firstName, lastname;
                /**
                 * @Edittext
                 * Gets the String values from the various EditTexts
                 */
                email = String.valueOf(Email.getText().toString().trim());
                password = String.valueOf(Password.getText().toString().trim());
                day = String.valueOf(Day.getText().toString().trim());
                month = String.valueOf(Month.getText().toString().trim());
                year = String.valueOf(Year.getText().toString().trim());
                firstName = String.valueOf(FirstName.getText().toString().trim());
                lastname = String.valueOf(LastName.getText().toString().trim());

                boolean hasNumbersorSymbols = false;
                for(char c : password.toCharArray())
                {
                    if (Character.isDigit(c))
                    {
                        hasNumbersorSymbols = true;
                        break;
                    }
                }

                /**
                 * Checks if any of the Required EditTexts are empty.
                 */
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(day) || TextUtils.isEmpty(month) || TextUtils.isEmpty(year) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastname))
                {
                    Toast.makeText(getApplicationContext(), "Fill out the required information", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6 || !hasNumbersorSymbols)
                {
                    Toast.makeText(getApplicationContext(), "Password length must be > 6 characters, have a number, and symbol", Toast.LENGTH_LONG).show();
                    return;
                }

                if(Integer.valueOf(month) == 1 || Integer.valueOf(month) == 3 || Integer.valueOf(month) == 5 || Integer.valueOf(month) == 7 || Integer.valueOf(month) == 8 || Integer.valueOf(month) == 10 || Integer.valueOf(month) == 12)
                {
                    if (Integer.valueOf(day) >= 31 || Integer.valueOf(day) < 1)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid day", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else if(Integer.valueOf(month) == 4 || Integer.valueOf(month) == 6 || Integer.valueOf(month) == 9 || Integer.valueOf(month) == 11)
                {
                    if (Integer.valueOf(day) > 30 || Integer.valueOf(day) < 1)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid day", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else if (Integer.valueOf(month) == 2)
                {
                    if (Integer.valueOf(day) > 29 || Integer.valueOf(day) < 1)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid day", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else if (Integer.valueOf(month) > 12)
                {
                    Toast.makeText(getApplicationContext(), "Invalid month", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.valueOf(year) < 1867  || Integer.valueOf(year) > 2024)
                {
                    Toast.makeText(getApplicationContext(), "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }

                /**
                 * @FirebaseAuth
                 * https://firebase.google.com/docs/auth/android/password-auth
                 * Links an email & password to the Firebase servers
                 */
                fAuth.createUserWithEmailAndPassword(email, password)//, day, month, year, firstName, lastname)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Log.d("RianButtala", day + month + year + firstName + lastname);

                                if (task.isSuccessful()) {

                                    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String userId = fAuth.getCurrentUser().getUid();

                                    /*FirebaseDatabase db = FirebaseDatabase.getInstance();
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    DatabaseReference node = db.getReference("MealUsers");//.child(userId);

                                    FireBaseUser newUser = new FireBaseUser(email, day, month, year, firstName, lastname);
                                    node.child(userId).setValue(newUser);*/

                                    /**
                                     * @Hashmap - Creates a hashmap that will store information from the EditTexts into the documentReference
                                     */
                                    DocumentReference documentReference = fStore.collection("MealUsers").document(userId);
                                    Map<String,Object> MealUser = new HashMap<>();
                                    MealUser.put("email",email);
                                    MealUser.put("day",day);
                                    MealUser.put("month",month);
                                    MealUser.put("year",year);
                                    MealUser.put("firstName",firstName);
                                    MealUser.put("lastName",lastname);
                                    MealUser.put("password", password);

                                    documentReference.set(MealUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(), "Successful Account Created for: " + firstName, Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Account Creation Failure.", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                    Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "Unauthorized email (must have @)", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

    }
}