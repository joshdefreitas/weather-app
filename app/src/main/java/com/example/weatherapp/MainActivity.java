package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Models.WeatherReport;
import com.example.weatherapp.ViewModels.WeatherViewModel;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private WeatherViewModel weatherViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
//        weatherViewModel.init();

//        weatherViewModel.getCurrentWeather().observe(this, new Observer<WeatherReport>() {
//            @Override
//            public void onChanged(WeatherReport weatherReport) {
//                if (weatherReport != null){
//                    int duration = Toast.LENGTH_SHORT;
//                    CharSequence msg = "Weather Report Received";
//                    Toast toast = Toast.makeText(getApplicationContext(),msg,duration);
//                    toast.show();
//                }
//
//
//            }
//        });


    }



}
