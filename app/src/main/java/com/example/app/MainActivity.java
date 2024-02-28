package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private accelerometer accelerometer;
private gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        accelerometer = new accelerometer(this);
        gyroscope = new gyroscope(this);
        accelerometer.setListener(new accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if(tz>6){
                    System.out.println("tz "+tz);
                }
            }
        });

        gyroscope.setListener(new gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if(rz > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                }
                if(rz > -1.0f){
//                    getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
    }
}