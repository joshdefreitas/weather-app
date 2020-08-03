package com.example.weatherapp.Repository;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.Models.Cloud;
import com.example.weatherapp.Models.Coord;
import com.example.weatherapp.Models.Main;
import com.example.weatherapp.Models.Sys;
import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Models.WeatherReport;
import com.example.weatherapp.WebService.WeatherReportAPI;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import javax.xml.namespace.QName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    private static WeatherRepository instance;
    MutableLiveData<WeatherReport> weatherReport = new MutableLiveData<>();
    MutableLiveData<Coord> locationR = new MutableLiveData<>();

    private String URL = "https://api.openweathermap.org/";
    private double mLat = 49.262817;
    private double mLon = -123.25385;
    private final String mAPIKey = "b808aed785ba7a00d0078526c4a21883";
    private final String UNITS = "metric";

    public static WeatherRepository getInstance(){
        if(instance == null){
            instance = new WeatherRepository();
        }
        return instance;

    }

    public MutableLiveData<WeatherReport> getWeatherReport(double mLat,double mLon){

        retrieveWeatherReport(mLat,mLon);
        return weatherReport;
    }

    public MutableLiveData<Coord> getLocation(Activity activity, FusedLocationProviderClient f){
        retrieveLocation(activity,f);
        return locationR;
    }

    //retrieves report from api
    private void retrieveWeatherReport(double mLat, double mLon){
        //weatherReport = new WeatherReport(800,"Clear","clear sky","01n");
        //TODO implement



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherReportAPI weatherReportAPI = retrofit.create(WeatherReportAPI.class);

        Call<WeatherReport> call = weatherReportAPI.getReport(mLat,mLon,mAPIKey,UNITS);

        call.enqueue(new Callback<WeatherReport>() {
            @Override
            public void onResponse(Call<WeatherReport> call, Response<WeatherReport> response) {
                if(!response.isSuccessful()) {
                    Log.d("Error", String.valueOf(response.code()));
                    return;
                }
               weatherReport.setValue(response.body());

            }

            @Override
            public void onFailure(Call<WeatherReport> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });



    }

    public void retrieveLocation(Activity activity, FusedLocationProviderClient fusedLocationClient) {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Coord coord = new Coord(location.getLongitude(),location.getLatitude());
                        WeatherRepository.instance.locationR.setValue(coord);
                    }
                });
    }


}
