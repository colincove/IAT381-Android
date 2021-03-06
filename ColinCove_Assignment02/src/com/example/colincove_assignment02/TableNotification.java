package com.example.colincove_assignment02;

import java.util.Calendar;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.widget.Toast;

public class TableNotification implements SensorEventListener {
	private Context c;
	private SensorManager manager;
	private Sensor oSensor;
	private boolean flat=false;
	private float[] values;
	private Vibrator v;
	private int lastFlatTime;
	public TableNotification(Context c, SensorManager manager) {
		// TODO Auto-generated constructor stub
		this.c=c;
		this.manager=manager;
		
		lastFlatTime=getSeconds();
		v=(Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
		oSensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		manager.registerListener(this, oSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		//if(manager.getOrientation())
		values=event.values;
		
		if(values[1]<10 && values[1] > -10){
			if(!flat){
				//device is sitting on a table according to orientation sensor. 
				Toast t=Toast.makeText(c, "Sitting", Toast.LENGTH_LONG);
				t.show();
				if(v!=null)
				{
					v.vibrate(5000);
				}
				flat=true;
				lastFlatTime=getSeconds();
			}
		}else if(getSeconds()-lastFlatTime>5 &&
				values[1]>1 && values[1] < -1 &&
				values[2]>1 && values[2] < -1){
		flat=false;
		}
	}
	private int getSeconds(){
		return (int)(System.currentTimeMillis()/1000);
	}

}
