package com.example.firstproject;

import java.io.File;
import java.io.IOException;

import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String DEBUG_TAG="MyFirstDebug";
	
	private MediaPlayer mp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        Log.i("HelloFromMyApp", DEBUG_TAG);
        
        try {
			playMusicFromWeb();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        getLocation();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void playMusicFromWeb() throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
    	try{
    		Uri file = Uri.parse("http://www.darkmatterproductions.com/mp3/Sumi%20Jo%20-%20the%20Rhapsody.mp3");
    		mp = MediaPlayer.create(this, file);
    		mp.start();
    	}catch(Exception e){
    		
    	}
    }
    protected void onStop(){
    	if(mp!=null){
    		mp.stop();
    		mp.release();
    	}
    	super.onStop();
    }
    private void getLocation(){
    	LocationManager locM = (LocationManager)getSystemService(LOCATION_SERVICE);
    	Location loc = locM.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	//Log.i("loc: "+loc.toString(),DEBUG_TAG);
    	System.out.println(loc.toString());
    }
}
