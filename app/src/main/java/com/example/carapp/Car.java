package com.example.carapp;
import com.google.gson.annotations.SerializedName;

public class Car {
    @SerializedName("Name")
    private String name;

    @SerializedName("Year")
    private String year;

    @SerializedName("Origin")
    private String origin;

    @SerializedName("Acceleration")
    private double acceleration;

    // Constructor, getters, and setters
    // Example constructor for initialization
    public Car(String name, String year, String origin, double acceleration) {
        this.name = name;
        this.year = year;
        this.origin = origin;
        this.acceleration = acceleration;
    }

    // Getters and setters for all fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
}
