package com.example.urbanwarleaderboards;

import webservices.Webservices;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LevelSelectView extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title_screen, menu);
        return true;
    }
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.level_select);
	        
	    }
	  public void onLevelSelected(View v){
		 TitleScreenActivity.webServices.getTop(5);
	  }

}
