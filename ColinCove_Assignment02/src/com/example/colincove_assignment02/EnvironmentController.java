package com.example.colincove_assignment02;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EnvironmentController implements OnClickListener, SensorEventListener {
	private Context context;
	private Button button;
	private Sensor sensor;
	private SensorManager manager;
	private float[] currentSensorValue;
	public EnvironmentController(Context context, Button button) {
		this.context=context;
		this.button=button;
		button.setOnClickListener(this);
		
		manager=(SensorManager)context.getSystemService(Service.SENSOR_SERVICE);
		sensor  = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		
		MediaRecorder recorder  = new MediaRecorder();
		
	}
	@Override
	public void onClick(View v) {
		String text="";
		if(currentSensorValue==null){
			text="No Proximity Sensor Found";
		}else{
			text=Float.toString(currentSensorValue[0])+":"+
					Float.toString(currentSensorValue[1])+":"+
					Float.toString(currentSensorValue[2]);
		}
		Toast t = Toast.makeText(context,text, Toast.LENGTH_LONG);
		t.show();
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		currentSensorValue=event.values;
	}

}
