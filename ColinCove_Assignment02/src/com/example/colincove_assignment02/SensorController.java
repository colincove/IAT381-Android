package com.example.colincove_assignment02;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SensorController implements SensorEventListener, OnClickListener {

	private Button btn;
	private TextView text;
	private Sensor sensor;
	private SensorManager manager;
	private Context context;
	public SensorController(Button btn, TextView text, Sensor sensor, SensorManager manager, Context context) {
		// TODO Auto-generated constructor stub
		this.btn=btn;
		this.text=text;
		this.sensor=sensor;
		this.manager=manager;
		this.context=context;
		
		btn.setText(sensor.getName());
		
		manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		
		btn.setOnClickListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		text.setText(Float.toString(event.values[0]));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context,SensorDisplayActivity.class);
		SensorDisplayActivity.displayedSensor=sensor;
		context.startActivity(intent);
	}

}
