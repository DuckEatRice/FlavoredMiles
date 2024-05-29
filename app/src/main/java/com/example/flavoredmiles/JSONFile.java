package com.example.flavoredmiles;

import android.graphics.drawable.Drawable;
import android.provider.Telephony;

public class JSONFile {

    //Required strings for ArrayList using RecyclerView
    String MealName;
    String MealPicturePath;
    String MealDescription;
    String MealType;
    String Rating;
    String Price;
    String Time;
    String Calories;
    String Ingredients;

    //Constructor for required strings
    public JSONFile(String mealName, String mealPicture, String mealDescription, String mealType, String rating, String price, String time, String calories, String ingredients) {
        MealName = mealName;
        MealPicturePath = mealPicture;
        MealDescription = mealDescription;
        MealType = mealType;
        Rating = rating;
        Price = price;
        Time = time;
        Calories = calories;
        Ingredients = ingredients;
    }

    //All the methods for the strings

    public String getMealName() {
        return MealName;
    }

    public String getMealPicture() {
        return MealPicturePath;
    }

    public String getMealDescription() {
        return MealDescription;
    }

    public String getMealType() {
        return MealType;
    }

    public String getRating() {
        return Rating;
    }

    public String getPrice() {
        return Price;
    }

    public String getTime() {
        return Time;
    }

    public String getCalories() {
        return Calories;
    }

    public String getIngredients() {
        return Ingredients;
    }
}
