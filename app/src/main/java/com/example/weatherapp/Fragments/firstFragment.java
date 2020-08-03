package com.example.weatherapp.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class firstFragment extends Fragment {
    private TextView locText,wID,wMain,lLat,lLon,detView;
    private Button locButton;
    private ImageButton refreshButton;
    private SwipeRefreshLayout refreshLayout;
    private ImageView wImage;
    private double Lat,Lon;
    private Coord currentLoc = new Coord(0.000,0.000);
    private WeatherViewModel weatherViewModel;
    private LocationViewModel locationViewModel;
    private int LOCATION_PERMISSION_CODE = 1;
    private String URL;
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
        wID = v.findViewById(R.id.wIDView);
        wMain = v.findViewById(R.id.wMainView);
        lLat = v.findViewById(R.id.latView);
        lLon = v.findViewById(R.id.lonView);
        wImage = v.findViewById(R.id.weatherImage);
        detView = v.findViewById(R.id.detailView);
        refreshButton = v.findViewById(R.id.refButt);
        refreshLayout = v.findViewById(R.id.refreshLayout);



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMethod();
                Toast.makeText(getActivity(), "Weather Updated",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





        locationViewModel = new ViewModelProvider(getActivity()).get(LocationViewModel.class);



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateMethod();
                Toast.makeText(getActivity(), "Weather Updated",
                        Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });

        updateMethod();


    }



    public void updateWeather(){
        weatherViewModel = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);
        weatherViewModel.init(currentLoc.getLat(),currentLoc.getLon());
        weatherViewModel.getCurrentWeather().observe(getViewLifecycleOwner(), new Observer<WeatherReport>() {
            @Override
            public void onChanged(@Nullable WeatherReport weatherReport) {
                if(weatherReport != null){


                    wID.setText(weatherReport.getmWeather().get(0).getMain());
                    double rounded = Math.round(weatherReport.getmMain().getTemp() * 10) / 10.0;
                    wMain.setText(rounded + "\u00B0" + "C");
                    locText.setText(weatherReport.getmName());
                    detView.setText(
                            "wind: \t" + weatherReport.getmWind().getSpeed() +"m/s" + "\n"+
                            "max temp: \t" +weatherReport.getmMain().getTemp_max()+ "\u00B0" + "C" + "\n"+
                            "min temp: \t" +weatherReport.getmMain().getTemp_min()+ "\u00B0" + "C" + "\n"+
                            "humidity: \t" +weatherReport.getmMain().getHumidity()+ "\u0025" + "\n"+
                            "pressure: \t" +weatherReport.getmMain().getPressure()+ "hPa" + "\n"

                    );


                    firstFragment.this.URL = "http://openweathermap.org/img/wn/"+weatherReport.getmWeather().get(0).getIcon() + "@2x.png";

                    Glide.with(getContext())
                            .load(URL)
                            .fitCenter()
                            .into(wImage);

                }


            }
        });


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
                String latString = "Lat: " + coord.getLat();
                String lonString = "Lon: " + coord.getLon();
                lLat.setText(latString);
                lLon.setText(lonString);
                firstFragment.this.currentLoc.setLon(coord.getLon());
                firstFragment.this.currentLoc.setLat(coord.getLat());
                firstFragment.this.updateWeather();
            }
        });


    }

    private void updateMethod(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            updateLocation();

        } else {
            requestLocationPermission();
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

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
