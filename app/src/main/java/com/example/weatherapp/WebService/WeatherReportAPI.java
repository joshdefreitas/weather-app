package com.example.weatherapp.WebService;

import com.example.weatherapp.Models.WeatherReport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeatherReportAPI {

    @GET("data/2.5/weather")
    Call<WeatherReport> getReport(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String APIKey,
            @Query("units") String unit
    );
}
