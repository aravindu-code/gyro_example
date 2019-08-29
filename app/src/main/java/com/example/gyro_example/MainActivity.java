package com.example.gyro_example;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor mgyro;
    //private String TAG="sensor_value";
    private TextView x, y, z;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (TextView) findViewById(R.id.textView);
        y = (TextView) findViewById(R.id.textView2);
        z = (TextView) findViewById(R.id.textView3);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mgyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mgyro == null)
        {
            while(true)   // if light sensor is not present remove while loop
            {
                Log.d(TAG, "Gyro sensor is not present");
            }
        }
        else
        {
            Log.d(TAG, "Gyro sensor is present");
        }
        //mgyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(final SensorEvent event) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
        float gyr = event.values[0];

        // Do something with this sensor value.
        //Log.e(TAG, lux+ " lux");
        //Log.d(TAG, "onSensorChanged: lux" + lux);

        Log.d(TAG,"Sensor evenType: "+ event.sensor.getType());

        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            Log.d(TAG, "onSensorChanged: X: " + event.values[0]);
            Log.d(TAG, "onSensorChanged: Y: " + event.values[1]);
            Log.d(TAG, "onSensorChanged: Z: " + event.values[2]);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
                    //light.setText(Float.toString(event.values[0]));
               // }
                if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
                    x.setText(Float.toString(event.values[0]));
                    y.setText(Float.toString(event.values[1]));
                    z.setText(Float.toString(event.values[2]));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mgyro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
