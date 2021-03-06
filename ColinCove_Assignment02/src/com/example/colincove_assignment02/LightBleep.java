package com.example.colincove_assignment02;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;

public class LightBleep implements SensorEventListener {
	private SensorManager manager;
	private MediaPlayer mPlayer;
	private boolean covered=false;
	private Context c;
	public LightBleep(Context c, SensorManager manager) {
		// TODO Auto-generated constructor stub
		this.manager=manager;
		this.c=c;
		
		mPlayer = MediaPlayer.create(c, R.raw.bleep);
		Sensor lSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		manager.registerListener(this, lSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.values[0]<2.0 && !covered){
			covered=true;
			playSound();
		}
		if(event.values[0]>2.0 && covered){
			covered=false;
		}
	}
	private void playSound(){
		mPlayer.start();
	}
}
