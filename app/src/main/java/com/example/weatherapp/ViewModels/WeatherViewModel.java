package com.example.weatherapp.ViewModels;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Models.WeatherReport;
import com.example.weatherapp.Repository.WeatherRepository;
import com.example.weatherapp.WebService.WeatherReportAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<WeatherReport> currentWeather;
    private WeatherRepository wRepo;
    private WeatherReport weatherReport;
    private String URL = "https://api.openweathermap.org/";

    public void init(double mLat,double mLon) {
//        if (currentWeather != null) {
//            return;
//        }

        wRepo = WeatherRepository.getInstance();
        currentWeather = wRepo.getWeatherReport(mLat,mLon);

    }

    public LiveData<WeatherReport> getCurrentWeather() {
        return currentWeather;
    }

}
