package com.example.weatherapp.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.Models.Coord;
import com.example.weatherapp.Repository.WeatherRepository;

public class LocationViewModel extends ViewModel {
    private MutableLiveData<Coord> location;
    private WeatherRepository cRepo;

    public void init() {
        if (location != null) {
            return;
        }

        cRepo = WeatherRepository.getInstance();
        location = cRepo.getLocation();

    }

    public LiveData<Coord> getLocation(){
        return location;
    }
}
