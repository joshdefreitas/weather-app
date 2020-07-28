package com.example.weatherapp.ViewModels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<Weather> currentWeather;
    private WeatherRepository wRepo;

    public void init(){
        if(currentWeather != null){
            return;
        }
        wRepo = WeatherRepository.getInstance();
        currentWeather = wRepo.getWeather();
    }

    public LiveData<Weather> getCurrentWeather(){
        return currentWeather;
    }
}
