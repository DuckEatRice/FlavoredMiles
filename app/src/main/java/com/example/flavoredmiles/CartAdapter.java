package com.example.flavoredmiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements CartActivity.CartItemQuantityListener {

    private ArrayList<CartItem> cartItems;
    private Context context;
    Activity activity;


    public CartAdapter(ArrayList<CartItem> cartItems, Activity activity) {
        this.cartItems = cartItems;
        //this.context = context;
        this.activity = activity;

        //Log.d("Ethan", cartItems.get(0).getMealName());
    }

    @Override
    public void onCartItemQuantityListener(int position, int newQuantity) {
        cartItems.get(position).setQuantity(String.valueOf(newQuantity));
        ((CartActivity) activity).onCartItemQuantityChange(position, newQuantity);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        ImageView mealPicture;
        TextView mealPrice;
        TextView quantity;
        TextView cartRealPrice;
        ImageView cartAdd;
        ImageView cartSubtract;
        ImageView RedCross;

        public CartViewHolder(View itemView) {
            super(itemView);

            mealName = itemView.findViewById(R.id.cartItemName); // Replace with your TextView IDs
            mealPicture = itemView.findViewById(R.id.cartMealImage);
            mealPrice = itemView.findViewById(R.id.cartItemPrice);
            quantity = itemView.findViewById(R.id.cartItemQuantity);
            cartRealPrice = itemView.findViewById(R.id.cartItemRealPrice);
            cartAdd = itemView.findViewById(R.id.cartQuantityAdd);
            cartSubtract = itemView.findViewById(R.id.cartQuantitySubtract);
            RedCross = itemView.findViewById(R.id.redCross);
        }
    }

    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each cart item (e.g., cart_item.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_cardview, parent, false);
        System.out.println("bound2");
        return new CartViewHolder(view);
    }

    public void onBindViewHolder(CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        System.out.println("bound");
        Log.d("Rian Rian bubi", "Binding item: " + cartItems.get(position).getMealName());
        Log.d("Rian Rian boo bo", String.valueOf(cartItems.size()));
        //CartItem cartItem = cartItems.get(position);

        /**
         * Sets the necessary TextView statements using the information from the ArrayLists.
         */
        holder.mealName.setText(cartItems.get(position).getMealName());
        holder.mealPrice.setText("$" + cartItems.get(position).getQuantity() + "*" + cartItems.get(position).getMealPrice());
        holder.quantity.setText(cartItems.get(position).getQuantity());

        double intQuantity = Double.valueOf(cartItems.get(position).getQuantity());
        double intPrice = Double.valueOf(cartItems.get(position).getMealPrice());
        double RealPrice = intPrice * intQuantity;
        String result = String.format("%.2f", RealPrice);
        holder.cartRealPrice.setText("$" + result);

        int currentQuantity = Integer.parseInt(cartItems.get(position).getQuantity());
        holder.quantity.setText(String.valueOf(currentQuantity));

        /**
         * resourceId just uses the getIdentifier required documentation to get picture information, and uses getResources() to receive said information from the R file.
         */
        int resourceId = activity.getResources().getIdentifier(cartItems.get(position).getImageName(), "drawable", holder.itemView.getContext().getPackageName());

        if (resourceId != 0) {
            holder.mealPicture.setImageResource(resourceId); // Set the image to the ImageView
        } else {
            holder.mealPicture.setImageResource(R.drawable.imagenotfound);
        }

        final int[] quantityQuantity = {0};

        quantityQuantity[0] += intQuantity;

        /**
         * When quantity is increased, calories, price, and everything affected by quantity is increased.
         */
        holder.cartAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityQuantity[0]++;
                String quantityNumber = String.valueOf(quantityQuantity[0]);
                holder.quantity.setText(quantityNumber);

                holder.mealPrice.setText("$" + quantityNumber + "*" + cartItems.get(position).getMealPrice());
                double total = quantityQuantity[0] * intPrice;
                String totalTotal = String.format("%.2f", total);
                holder.cartRealPrice.setText("$" + totalTotal);

                ((CartActivity) activity).onCartItemQuantityChange(position, quantityQuantity[0]);
            }
        });

        /**
         * When quantity is decreased, calories, price, and everything affected by quantity is decreased.
         */
        holder.cartSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantityQuantity[0] > 1)
                {
                    quantityQuantity[0]--;
                    String quantityNumber = String.valueOf(quantityQuantity[0]);
                    holder.quantity.setText(quantityNumber);

                    holder.mealPrice.setText("$" + quantityNumber + "*" + cartItems.get(position).getMealPrice());
                    double total = quantityQuantity[0] * intPrice;
                    String totalTotal = String.format("%.2f", total);
                    holder.cartRealPrice.setText("$" + totalTotal);

                    ((CartActivity) activity).onCartItemQuantityChangeMinus(position, quantityQuantity[0]);
                }
                else
                {
                    Toast.makeText(activity.getApplicationContext(), "You cannot subtract any further.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*holder.RedCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = (int) view.getTag();
                removeItem(itemPosition);
            }
        });*/

        // Set the image if you included it in CartItem (optional)
    }

    public int getItemCount() {
        return cartItems.size();
    }

    /*public int getPrice()
    {
        double subTotal;
        double Total;
    }*/




}