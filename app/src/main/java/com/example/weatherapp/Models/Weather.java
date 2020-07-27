package com.example.weatherapp.Models;

public class Weather {
    private int id;
    private String main,decription,icon;

    public Weather(int id, String main, String decription, String icon) {
        this.id = id;
        this.main = main;
        this.decription = decription;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
