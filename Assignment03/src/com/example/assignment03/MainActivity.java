package com.example.assignment03;

import java.io.BufferedReader;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;

public class MainActivity extends Activity implements IRecieveMesh  {
	private ObjData data=null;
	private ObjReader objReader;
	private ObjMesh pyramidMesh;
	public MainActivity(){
		super();
	
        
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(data==null){
		Resources res = getResources();
        objReader = new ObjReader(R.raw.pyramid, res);
        objReader.read(this, getMainLooper());
        
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void recieveMesh(ObjData data) {
		// TODO Auto-generated method stub
		pyramidMesh = new ObjMesh(data);
	}

}
