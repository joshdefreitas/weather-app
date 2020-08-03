package com.example.weatherapp.ViewModels;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.Models.Coord;
import com.example.weatherapp.Repository.WeatherRepository;
import com.google.android.gms.location.FusedLocationProviderClient;

public class LocationViewModel extends ViewModel {
    private MutableLiveData<Coord> location;
    private WeatherRepository cRepo;

    public void init(Activity a, FusedLocationProviderClient f) {
//        if (location != null) {
//            return;
//        }

        cRepo = WeatherRepository.getInstance();
        location = cRepo.getLocation(a,f);

    }

    public LiveData<Coord> getLocation(){
        return location;
    }
}
