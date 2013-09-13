package com.example.urbanwarleaderboards;

import webservices.Webservices;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class TitleScreenActivity extends Activity {
  public static Webservices webServices;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TitleScreenActivity.webServices = new Webservices();
        setContentView(R.layout.activity_title_screen);
    }
  @Override
protected void onStart(){
	super.onStart();
	webServices.start();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title_screen, menu);
        return true;
    }
    
    public void onLeaderboardsClick(View v){
    	System.out.println("onLeaderboardsClick");
    	Intent viewLevelsIntent = new Intent(this, LevelSelectView.class);
    	startActivity(viewLevelsIntent);
    }
    
    public void onAboutClick(View v){
    	
    }
    
    public void onAboutGame(View v){
    	
    }
    
}
