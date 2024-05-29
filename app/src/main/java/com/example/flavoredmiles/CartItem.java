package com.example.flavoredmiles;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CartItem implements Parcelable {

    //Strings for CartItem
    String mealName;
    String imageName;
    String mealPrice;
    String quantity;
    public CartItem(String mealName, String imageName, String mealPrice, String quantity) {
        this.mealName = mealName;
        this.imageName = imageName;
        this.mealPrice = mealPrice;
        this.quantity = quantity;
    }


    //A required method for parcing, which basically just allows ParceableArrayLists to be intented
    protected CartItem(Parcel in) {
        mealName = in.readString();
        imageName = in.readString();
        mealPrice = in.readString();
        quantity = in.readString();
    }

    //A required method for parcing, which basically just allows ParceableArrayLists to be intented
    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public String getMealName() {
        return mealName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @Parcel
     * Basically, this is some required method, and if I use "Show Context Actions", automatically adds this method.
     */
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(mealName);
        parcel.writeString(imageName);
        parcel.writeString(mealPrice);
        parcel.writeString(quantity);
    }
}
