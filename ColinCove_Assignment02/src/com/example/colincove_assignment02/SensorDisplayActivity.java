package com.example.colincove_assignment02;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class SensorDisplayActivity extends Activity implements SensorEventListener {

	public static Sensor displayedSensor;
	
	private SensorManager manager;
	
	private TextView dataDisplay;
private TextView  sensorNameDisplay; 
	private TableLayout layout;
	
	///////////////////// Data Displays /////////////////////
	private TextView nameDisplay;
	private TextView typeDisplay;
	private TextView resolutionDisplay;
	private TextView vendorDisplay;
	private TextView versionDisplay;
	private TextView maxDisplay;
	private TextView minDisplay;
	private TextView powerDisplay;
	private TextView value01Display;
	private TextView value02Display;
	private TextView value03Display;
	
	
	public SensorDisplayActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_info);
		manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		if(displayedSensor !=null){
			manager.registerListener(this, displayedSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		layout=(TableLayout)findViewById(R.id.sensor_display_layout);
		
		typeDisplay=(TextView)findViewById(R.id.type_display);
		resolutionDisplay=(TextView)findViewById(R.id.resolution_display);
		vendorDisplay=(TextView)findViewById(R.id.vendor_display);
		versionDisplay=(TextView)findViewById(R.id.version_display);
		maxDisplay=(TextView)findViewById(R.id.max_range_display);
		minDisplay=(TextView)findViewById(R.id.min_range_display);
		powerDisplay=(TextView)findViewById(R.id.power_display);
		value01Display=(TextView)findViewById(R.id.value_01);
		value02Display=(TextView)findViewById(R.id.value_02);
		value03Display=(TextView)findViewById(R.id.value_03);
		nameDisplay=(TextView)findViewById(R.id.sensor_name_display);
		
		typeDisplay.setText(Integer.toString(displayedSensor.getType()));
		resolutionDisplay.setText(Float.toString(displayedSensor.getResolution()));
		vendorDisplay.setText(displayedSensor.getVendor());
		versionDisplay.setText(Integer.toString(displayedSensor.getVersion()));
		maxDisplay.setText(Float.toString(displayedSensor.getMaximumRange()));
		minDisplay.setText(Float.toString(displayedSensor.getMinDelay()));
		powerDisplay.setText(Float.toString(displayedSensor.getPower()));
		nameDisplay.setText(displayedSensor.getName());
		
		manager.registerListener(this, displayedSensor, SensorManager.SENSOR_DELAY_GAME);
		//layout.addView(dataDisplay);
		/*layout.addView(sensorNameDisplay);*/
		
		
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		//dataDisplay.setText(Float.toString(event.values[0]));
		value01Display.setText(Float.toString(event.values[0]));
		value02Display.setText(Float.toString(event.values[1]));
		value03Display.setText(Float.toString(event.values[2]));
	}
}
