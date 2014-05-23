package com.example.cmps121hw3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener{
	private SensorManager mSensorManager;
	TextView xAccel;
	TextView yAccel;
	TextView zAccel;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xAccel = (TextView)findViewById(R.id.xaccel);
        yAccel = (TextView)findViewById(R.id.yaccel);
        zAccel = (TextView)findViewById(R.id.zaccel);
        
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorManager.registerListener(this,
        		mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    //setText() crashes program
    public void onSensorChanged(SensorEvent event){
    	if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
    		xAccel.setText("X: " + event.values[0]);
    		yAccel.setText("Y: " + event.values[1]);
    		zAccel.setText("Z: " + event.values[2]);
    	}
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

}
