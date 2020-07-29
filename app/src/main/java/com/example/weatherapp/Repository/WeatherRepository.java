package com.example.weatherapp.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.Models.Weather;

public class WeatherRepository {
    private static WeatherRepository instance;
    private Weather weather;

    public static WeatherRepository getInstance(){
        if(instance == null){
            instance = new WeatherRepository();
        }
        return instance;

    }

    public MutableLiveData<Weather> getWeather(){
        //TODO implement API retrieval

        setWeather();
        MutableLiveData<Weather> data = new MutableLiveData<>();
        data.setValue(weather);
        return data;
    }

    //test method
    public void setWeather(){
        weather = new Weather(800,"Clear","clear sky","01n");
    }

}
