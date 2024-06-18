package com.example.carapp;

public class MainModel {
    String model,brand,fueltype,curl;
    MainModel() {
    }
    public MainModel(String model, String brand, String fueltype, String curl) {
        this.model = model;
        this.brand = brand;
        this.fueltype = fueltype;
        this.curl = curl;
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }
}
