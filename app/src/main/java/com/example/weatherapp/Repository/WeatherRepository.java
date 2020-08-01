package com.example.weatherapp.Repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.Models.Cloud;
import com.example.weatherapp.Models.Coord;
import com.example.weatherapp.Models.Main;
import com.example.weatherapp.Models.Sys;
import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Models.WeatherReport;
import com.example.weatherapp.WebService.WeatherReportAPI;

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

    private String URL = "https://api.openweathermap.org/";

    public static WeatherRepository getInstance(){
        if(instance == null){
            instance = new WeatherRepository();
        }
        return instance;

    }

    public MutableLiveData<WeatherReport> getWeatherReport(){

        retrieveWeatherReport();
//        MutableLiveData<WeatherReport> data = new MutableLiveData<>();
//        data.setValue(weatherReport);
//        return data;
        return weatherReport;
    }

    //retrieves report from api
    public void retrieveWeatherReport(){
        //weatherReport = new WeatherReport(800,"Clear","clear sky","01n");
        //TODO implement



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherReportAPI weatherReportAPI = retrofit.create(WeatherReportAPI.class);

        Call<WeatherReport> call = weatherReportAPI.getReport();

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


}
