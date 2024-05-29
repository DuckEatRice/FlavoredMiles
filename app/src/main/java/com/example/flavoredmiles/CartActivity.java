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
    TextView PlaceOrder;


    double totalPrice;
    double subtotalPrice;
    double taxSubTotal;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize Firebase and User references
        fireStore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Find view references
        SubTotal = findViewById(R.id.SubTotal);
        Total = findViewById(R.id.Total);
        Tax = findViewById(R.id.TotalTax);
        PlaceOrder = findViewById(R.id.cartPlaceOrderButton);

        // Handle user signed in state
        if (user != null)
        {
            // Get cart items from Firestore
            CollectionReference cartRef = fireStore.collection("MealUsers").document(user.getUid()).collection("MealStoring");

            cartRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                {
                    cartItemsArrayList.clear();
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments())
                    {
                        // Extract item details from Firestore document
                        String MealName = documentSnapshot.getString("MealName");
                        String MealPicture = documentSnapshot.getString("MealPicture");
                        String MealPrice = documentSnapshot.getString("MealPrice");
                        String quantity = documentSnapshot.getString("quantity");

                        Log.d("success", MealName + ", " + MealPicture + ", " + MealPrice + ", " + quantity + ".");

                        // Add item to cart list
                        cartItemsArrayList.add(new CartItem(MealName, MealPicture, MealPrice, quantity));

                        Log.d("Rian Rian haha", String.valueOf(cartItemsArrayList.size()));
                    }
                    // Update cart adapter with new data
                    cartAdapter.notifyDataSetChanged();

                    // Calculate and display prices (if cart is not empty)
                    if (!cartItemsArrayList.isEmpty())
                    {
                        subtotalPrice = setSubTotalPrice();
                        SubTotal.setText("$" + String.format("%.2f", subtotalPrice));

                        taxSubTotal = setTaxPrice(subtotalPrice);
                        Tax.setText("$" + String.format("%.2f", taxSubTotal));

                        totalPrice = subtotalPrice + taxSubTotal;
                        Total.setText("$" + String.format("%.2f",totalPrice));
                    }

                }

            })
                    // Handle if cart fails to fetch item, which it won't :)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("CartFailure", "Error Fetching Items", e);
                        }
                    });
        }
        // User is not signed in, prompt them to sign in
        else
        {
            Toast.makeText(getApplicationContext(), "Not Signed In bro", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LogInScreen.class);
            startActivity(intent);
        }



        // Set up recycler view
        cartBackArrow = findViewById(R.id.cartBackArrowhi);

        cartRecyclerView = findViewById(R.id.cartRecyclerView); // Replace with your RecyclerView ID
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
        cartRecyclerView.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartItemsArrayList, CartActivity.this);

        cartRecyclerView.setAdapter(cartAdapter);



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

        if (PlaceOrder != null)
        {
            PlaceOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (user != null) {
                        CollectionReference cartRef = fireStore.collection("MealUsers").document(user.getUid()).collection("MealStoring");
                        // Fetches a collection, just a database

                        cartRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for (int i = 0; i < queryDocumentSnapshots.size(); i++) {
                                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(i);
                                    //Fetches specific database
                                    String mealName = documentSnapshot.getString("MealName");

                                    fireStore.collection("MealUsers").document(user.getUid()).collection("MealStoring").document(mealName)
                                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.wtf("Ethan C", "Succesfully ordered");
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getApplicationContext(), "Not Nice!", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                                    startActivity(intent);
                                }
                                Toast.makeText(getApplicationContext(), "Successfully Ordered!", Toast.LENGTH_LONG).show();

                                // Start MealCompletionScreen with cart details
                                Intent intent = new Intent(getApplicationContext(), MealCompetionScreen.class);
                                intent.putParcelableArrayListExtra("CartItemList", cartItemsArrayList);
                                intent.putExtra("TotalPrice", totalPrice);
                                intent.putExtra("SubTotal", subtotalPrice);
                                intent.putExtra("TotalTax", taxSubTotal);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed to remove", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Null Place Order", Toast.LENGTH_SHORT).show();
        }
    }

    // Listener interface for cart item quantity changes
    public interface CartItemQuantityListener
    {
        void onCartItemQuantityListener(int position, int newQuantity);
    }

    // Update prices when item quantity is decreased
    public void onCartItemQuantityChangeMinus(int position, int newQuantity)
    {
        updatePriceandSummary2(position, newQuantity);
    }

    // Update prices and summary for decrease in quantity
    private void updatePriceandSummary2(int position, int newQuantity)
    {
        for (CartItem item : cartItemsArrayList)
        {
            int quantity = Integer.parseInt(item.getQuantity());
            double price = Double.parseDouble(item.getMealPrice());
            totalPrice -= (quantity) * price;
        }

        if(totalPrice >= 0)
        {
            Total.setText("$" + String.format("%.2f", totalPrice));

            double tax = setTaxPrice(totalPrice);
            Tax.setText("$" + String.format("%.2f", tax));

            double subTotal = totalPrice - tax;
            SubTotal.setText("$" + String.format("%.2f", subTotal));
        }
        else
        {
            Total.setText("$0.00");
        }
    }


    // Update prices when item quantity is increased
    public void onCartItemQuantityChange(int position, int newQuantity)
    {
        updatePriceandSummary(position, newQuantity);
    }

    // Update prices and summary for increase in quantity
    private void updatePriceandSummary(int position, int newQuantity)
    {
        for (CartItem item : cartItemsArrayList)
        {
            int quantity = Integer.parseInt(item.getQuantity());
            double price = Double.parseDouble(item.getMealPrice());
            totalPrice += quantity * price;
        }

        Total.setText("$" + String.format("%.2f", totalPrice));

        double tax = setTaxPrice(totalPrice);
        Tax.setText("$" + String.format("%.2f", tax));

        double subTotal = totalPrice - tax;
        SubTotal.setText("$" + String.format("%.2f", subTotal));
    }

    // Calculate tax amount based on subtotal
    private double setTaxPrice(double SubTotal)
    {
        double tax = 0.0725;
        double SubTotaltax = SubTotal * tax;
        return SubTotaltax;
    }

    // Calculate subtotal price
    private double setSubTotalPrice()
    {
        double total = 0.0;
        for (CartItem cartItem : cartItemsArrayList)
        {
            int quantity = Integer.parseInt(cartItem.getQuantity());
            double price = Double.parseDouble(cartItem.getMealPrice());
            total += quantity * price;
            Log.d("rian Rian", "loser" + total);
        }
        return total;
    }
}