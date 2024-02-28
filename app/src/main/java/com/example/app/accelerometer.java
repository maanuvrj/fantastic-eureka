package com.example.app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class accelerometer {
    public interface Listener{
        void onTranslation(float tx, float ty, float tz);
    }

    private Listener listener;

    public void setListener(Listener l){
        listener = l;
    }

    private Sensor sensor;
    private SensorManager sensor_manager;
    private SensorEventListener sensor_event_listener;

    accelerometer(Context context){
        sensor_manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensor_manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensor_event_listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(listener!= null){
                    listener.onTranslation(event.values[0],event.values[1],event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void register(){
        sensor_manager.registerListener(sensor_event_listener, sensor, sensor_manager.SENSOR_DELAY_NORMAL);
    }
    public void unregister(){
        sensor_manager.unregisterListener(sensor_event_listener);
    }
}
