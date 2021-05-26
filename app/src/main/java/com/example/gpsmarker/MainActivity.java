package com.example.gpsmarker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Coordinates> coordinatesList = new ArrayList<>();
    public QueryController queryController = new QueryController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize arraylist with database
        coordinatesList = queryController.fillList();

    }
}