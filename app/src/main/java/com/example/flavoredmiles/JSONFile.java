package com.example.flavoredmiles;

import android.graphics.drawable.Drawable;
import android.provider.Telephony;

public class JSONFile {
    String MealName;
    Drawable MealPicture;
    String MealDescription;
    String MealType;
    String Rating;
    String Price;
    String Time;
    String Calories;
    String Ingredients;

    public JSONFile(String mealName, Drawable mealPicture, String mealDescription, String mealType, String rating, String price, String time, String calories, String ingredients) {
        MealName = mealName;
        MealPicture = mealPicture;
        MealDescription = mealDescription;
        MealType = mealType;
        Rating = rating;
        Price = price;
        Time = time;
        Calories = calories;
        Ingredients = ingredients;
    }

    public String getMealName() {
        return MealName;
    }

    public Drawable getMealPicture() {
        return MealPicture;
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

    public void setMealName(String mealName) {
        MealName = mealName;
    }

    public void setMealPicture(Drawable mealPicture) {
        MealPicture = mealPicture;
    }

    public void setMealDescription(String mealDescription) {
        MealDescription = mealDescription;
    }

    public void setMealType(String mealType) {
        MealType = mealType;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }
}
