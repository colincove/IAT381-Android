package com.example.colincove_assignment02;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MotionActivity extends Activity implements OnClickListener, SensorEventListener {
public  static final String STATUS_STILL="motion_still",STATUS_MOVING="motion_moving";
	
	private Button updateBtn;
	private TextView statusDisplay;
	private SensorManager manager;
	private Sensor accSensor;
	private float[] pastValues;
	private int valuesSize=20;
	private float avg;
	private float total;
	private String currentStatus=STATUS_STILL;
	
	public MotionActivity() {
		// TODO Auto-generated constructor stub
		pastValues = new float[valuesSize];
	}
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_motion);
		updateBtn=(Button) findViewById(R.id.update_motion_status);
		updateBtn.setOnClickListener(this);
		manager  =(SensorManager) getSystemService(SENSOR_SERVICE);
		accSensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		manager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_NORMAL);
		statusDisplay = (TextView)findViewById(R.id.motion_status);
		statusDisplay.setText(currentStatus);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		//statusDisplay.setText(currentStatus);
		
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		total=0f;
		float tmpVal=pastValues[0];
		float tmpVal2=0f;
		for(int i=1;i<valuesSize;i++){
			total+=pastValues[i];
			tmpVal2=pastValues[i];
			pastValues[i]=tmpVal;
			tmpVal=tmpVal2;
		}
		pastValues[0]=(float)Math.sqrt(
				Math.abs(event.values[0])*Math.abs(event.values[0])+
				Math.abs(event.values[1])*Math.abs(event.values[1])+
				Math.abs(event.values[2])*Math.abs(event.values[2]));
		total+=pastValues[0];
		avg=total/valuesSize;
		
		if(avg>15){
			currentStatus=STATUS_MOVING;
			statusDisplay.setTextColor(getResources().getColor(R.color.heigh_contrast));
		}else{
			currentStatus=STATUS_STILL;
			statusDisplay.setTextColor(getResources().getColor(R.color.low_contrast));
			
		}
		
		statusDisplay.setText(currentStatus);
	}
}
