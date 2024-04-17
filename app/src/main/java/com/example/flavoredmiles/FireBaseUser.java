package com.example.flavoredmiles;

public class FireBaseUser {

    String email;
    String day;
    String month;
    String year;
    String firstName;
    String lastName;

    public FireBaseUser(String email, String day, String month, String year, String firstName, String lastName) {
        this.email = email;
        this.day = day;
        this.month = month;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
