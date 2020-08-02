package com.example.weatherapp.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.Models.Coord;
import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Models.WeatherReport;
import com.example.weatherapp.R;
import com.example.weatherapp.ViewModels.LocationViewModel;
import com.example.weatherapp.ViewModels.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class firstFragment extends Fragment {
    private TextView locText,wID,wMain,lLat,lLon;
    private Button locButton;
    private Button upButton;
    private double Lat,Lon;
    private Coord currentLoc = new Coord(0.000,0.000);
    private WeatherViewModel weatherViewModel;
    private LocationViewModel locationViewModel;
    private int LOCATION_PERMISSION_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;

    public firstFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
        locText = v.findViewById(R.id.locView);
        locButton = v.findViewById(R.id.locButton);
        upButton = v.findViewById(R.id.updateButton);
        wID = v.findViewById(R.id.wIDView);
        wMain = v.findViewById(R.id.wMainView);
        lLat = v.findViewById(R.id.latView);
        lLon = v.findViewById(R.id.lonView);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());


        locButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLoc != null){
                    locText.setText("Location Available");
                }else{
                    locText.setText("Location Unavailable");
                }
            }
        });



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        weatherViewModel = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);
        weatherViewModel.getCurrentWeather().observe(getViewLifecycleOwner(), new Observer<WeatherReport>() {
            @Override
            public void onChanged(@Nullable WeatherReport weatherReport) {
                if(weatherReport == null){
                    //TODO implement
                }
                else{
                    wID.setText(weatherReport.getmName());
                    wMain.setText(String.valueOf(weatherReport.getmMain().getTemp()));
                    locText.setText(weatherReport.getmName());
                }
            }
        });


        locationViewModel = new ViewModelProvider(getActivity()).get(LocationViewModel.class);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                    updateLocation();

                } else {
                    requestLocationPermission();
                }


            }
        });



    }



    public void updateWeather(){
        //TODO implement update weather
    }

    private void requestLocationPermission(){
        if (shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(
                                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            requestPermissions(
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    private void updateLocation(){
        locationViewModel.init(getActivity(),fusedLocationClient);
        locationViewModel.getLocation().observe(getViewLifecycleOwner(), new Observer<Coord>() {
            @Override
            public void onChanged(Coord coord) {
                lLat.setText(String.valueOf(coord.getLat()));
                lLon.setText(String.valueOf(coord.getLon()));
                firstFragment.this.currentLoc.setLon(coord.getLon());
                firstFragment.this.currentLoc.setLat(coord.getLat());
            }
        });

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        wMain.setText(String.valueOf(requestCode));
        if (requestCode == LOCATION_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateLocation();
                Toast.makeText(getActivity(), "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
