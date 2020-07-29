package com.example.weatherapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.R;
import com.example.weatherapp.ViewModels.WeatherViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class firstFragment extends Fragment {
    private TextView locText,wID,wMain;
    private Button locButton;
    private String currentLoc = "London";
    WeatherViewModel weatherViewModel;

    public firstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
        locText = v.findViewById(R.id.locView);
        locButton = v.findViewById(R.id.locButton);
        wID = v.findViewById(R.id.wIDView);
        wMain = v.findViewById(R.id.wMainView);

        locButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locText.setText(currentLoc);
            }
        });
        return v;
    }

    public void setLocation(){
        //TODO implement location display
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity() != null){
            weatherViewModel = new ViewModelProvider(getActivity()).get(WeatherViewModel.class);
            weatherViewModel.getCurrentWeather().observe(getViewLifecycleOwner(), new Observer<Weather>() {
                @Override
                public void onChanged(Weather weather) {
                    wID.setText(String.valueOf(weather.getId()));
                    wMain.setText(weather.getMain());
                }
            });
        }
    }
}
