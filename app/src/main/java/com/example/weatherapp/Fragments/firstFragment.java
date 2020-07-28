package com.example.weatherapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import com.example.weatherapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class firstFragment extends Fragment {
    private TextView locText;
    private Button locButton;
    private String currentLoc = "London";

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
}
