package com.example.flavoredmiles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class CartActivity extends AppCompatActivity{

    //private ActivityCartBinding binding;
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    ArrayList<CartItem> cartItemsArrayList = new ArrayList<>();
    ImageView cartBackArrow;
    FirebaseUser user;
    FirebaseFirestore fireStore;
    FirebaseAuth auth;

    TextView SubTotal;
    TextView Total;
    TextView Tax;

    double totalPrice = 0.0;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        fireStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        SubTotal = findViewById(R.id.SubTotal);
        Total = findViewById(R.id.Total);
        Tax = findViewById(R.id.TotalTax);

        if (user != null)
        {
            CollectionReference cartRef = fireStore.collection("MealUsers").document(user.getUid()).collection("MealStoring");

            cartRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                {
                    cartItemsArrayList.clear();
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments())
                    {
                        String MealName = documentSnapshot.getString("MealName");
                        String MealPicture = documentSnapshot.getString("MealPicture");
                        String MealPrice = documentSnapshot.getString("MealPrice");
                        String quantity = documentSnapshot.getString("quantity");

                        Log.d("success", MealName + ", " + MealPicture + ", " + MealPrice + ", " + quantity + ".");

                        //CartItem cartItem = new CartItem(MealName, MealPicture, MealPrice, quantity);

                        //Log.d("Rian Rian", cartItem.getMealName());

                        cartItemsArrayList.add(new CartItem(MealName, MealPicture, MealPrice, quantity));

                        Log.d("Rian Rian haha", String.valueOf(cartItemsArrayList.size()));
                    }
                    cartAdapter.notifyDataSetChanged();

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("CartFailure", "Error Fetching Items", e);
                        }
                    });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Not Signed In bro", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
            startActivity(intent);
        }


        cartBackArrow = findViewById(R.id.cartBackArrowhi);

        cartRecyclerView = findViewById(R.id.cartRecyclerView); // Replace with your RecyclerView ID
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
        cartRecyclerView.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartItemsArrayList, CartActivity.this);
        //Log.d("Rian Rian", "cartItems size: " + cartItemsArrayList.size());
        cartRecyclerView.setAdapter(cartAdapter);

        Thread t = new Thread(()-> {

        });
        t.start();




        /**
         * @Intent
         * Intents back to MainMenu class
         */
        cartBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent1);
            }
        });
    }

    public interface CartItemQuantityListener
    {
        void onCartItemQuantityListener(int position, int newQuantity);
    }

    public void onCartItemQuantityChangeMinus(int position, int newQuantity)
    {
        updatePriceandSummary2(position, newQuantity);
    }

    private void updatePriceandSummary2(int position, int newQuantity)
    {
        CartItem cartItem = cartItemsArrayList.get(position);

        // Calculate price change for this item (old quantity * price - new quantity * price)
        double priceChange = (newQuantity - Integer.parseInt(cartItem.getQuantity())) * Double.parseDouble(cartItem.getMealPrice());


        totalPrice -= priceChange;

        Total.setText("$" + String.format("%.2f", totalPrice));
    }



    public void onCartItemQuantityChange(int position, int newQuantity)
    {
        updatePriceandSummary(position, newQuantity);
    }

    private void updatePriceandSummary(int position, int newQuantity)
    {
        CartItem cartItem = cartItemsArrayList.get(position);

        // Calculate price change for this item (old quantity * price - new quantity * price)
        double priceChange = (newQuantity - Integer.parseInt(cartItem.getQuantity())) * Double.parseDouble(cartItem.getMealPrice());


        totalPrice += priceChange;

        Total.setText("$" + String.format("%.2f", totalPrice));
    }

    private double setTotalPrice()
    {



        for (CartItem cartItem : cartItemsArrayList)
        {
            int quantity = Integer.parseInt(cartItem.getQuantity());

        }
        return totalPrice;
    }


}