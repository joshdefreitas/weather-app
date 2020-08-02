package com.example.weatherapp.WebService;

import com.example.weatherapp.Models.WeatherReport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WeatherReportAPI {

    @GET("data/2.5/weather?q=London&appid=b808aed785ba7a00d0078526c4a21883")
    Call<WeatherReport> getReport();
}
