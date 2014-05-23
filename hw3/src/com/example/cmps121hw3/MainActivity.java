package com.example.cmps121hw3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener{
	private SensorManager mSensorManager;
	TextView xAccel;
	TextView yAccel;
	TextView zAccel;
	TextView modAccel; //modulus of acceleration [sqrt(xAccel^2 + yAccel^2 + zAccel^2)]
	TextView maxAccel;
	TextView maxAccelTime;
	double record = 0; //maximum recorded modulus of acceleration
	Time recordTime = new Time(); //time that maximum recorded modulus of acceleration was recorded
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xAccel = (TextView)findViewById(R.id.xaccel);
        yAccel = (TextView)findViewById(R.id.yaccel);
        zAccel = (TextView)findViewById(R.id.zaccel);
        modAccel = (TextView)findViewById(R.id.modaccel);
        maxAccel = (TextView)findViewById(R.id.maxaccel);
        maxAccelTime = (TextView)findViewById(R.id.maxacceltime);
        
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorManager.registerListener(this,
        		mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    public void onSensorChanged(SensorEvent event){
    	if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
    		double xAccelVal = event.values[0];
    		double yAccelVal = event.values[1];
    		double zAccelVal = event.values[2];
    		double currAccel = Math.sqrt(xAccelVal * xAccelVal + yAccelVal * yAccelVal + zAccelVal * zAccelVal);
    		
            if(currAccel > record){
            	record = currAccel;
            	recordTime.setToNow();
            }
    		
    		xAccel.setText("X: " + event.values[0]);
            yAccel.setText("Y: " + event.values[1]);
            zAccel.setText("Z: " + event.values[2]);
            modAccel.setText("Acceleration: " + currAccel);
            maxAccel.setText("Acceleration: " + record);
            maxAccelTime.setText("Time: " + recordTime.toString());
    	}
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

}
