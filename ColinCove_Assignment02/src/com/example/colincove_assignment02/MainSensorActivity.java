package com.example.colincove_assignment02;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainSensorActivity extends Activity implements SensorEventListener, OnClickListener {
	
	private SensorManager sensorManager;
	private List<Sensor> sensors;
	private LightBleep lBleep;
	private TableNotification tNotivication;
	private Button motionBtn;
	private EnvironmentController eController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_sensor);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensors=sensorManager.getSensorList(Sensor.TYPE_ALL);
		setupSensors();
		lBleep  =new LightBleep(this,sensorManager);
		tNotivication = new TableNotification(this, sensorManager);
		
		motionBtn =(Button) findViewById(R.id.motion);
		motionBtn.setOnClickListener(this);
		
		eController = new EnvironmentController(this, (Button)findViewById(R.id.environment));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_sensor, menu);
		return true;
	}
	
	private void setupSensors(){
		GridLayout layout =(GridLayout) findViewById(R.id.main_grid_view);
		for(Sensor sensor : sensors){
			//sensor.
			TextView text = new TextView(this);
			Button btn =new Button(this);
			
			layout.addView(text);
			layout.addView(btn);
			
			SensorController controller  = new SensorController(btn, text, sensor, sensorManager, this);
			
			sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL );
		}
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.motion){
			Intent intent = new Intent(this,MotionActivity.class);
			startActivity(intent);
		}
	}
}
