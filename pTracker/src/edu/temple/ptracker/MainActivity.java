package edu.temple.ptracker;

import edu.temple.ptracker.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

//Things imported for the use of buttons and the transitions they cause between activities.
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.widget.Button;

public class MainActivity extends Activity {
	
	//Declare the button objects.
	Button recordButton;
	Button settingsButton;
	
	//Declare variable to hold HZ settings.
	int samplingRateHZ = 60;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Define where the button objects are.
		recordButton = (Button) findViewById(R.id.recordButton);
		settingsButton = (Button) findViewById(id.settingsButton);
		
		//Handle the actual button presses and take the user to the new action.
		
		//Record Button
		final Intent intentRecordButton = new Intent();
	    intentRecordButton.setClass(MainActivity.this, RecordActivity.class);
	      	
	    recordButton.setOnClickListener(new OnClickListener() 
	    {
	      	public void onClick(View v) { startActivity(intentRecordButton);}
	    });
	    
	    
	    //Settings Button
	  	final Intent intentSettingsButton = new Intent();
	  	intentSettingsButton.setClass(MainActivity.this, SettingsActivity.class);
	  	      	
	  	settingsButton.setOnClickListener(new OnClickListener() 
	  	{
	  		public void onClick(View v) { startActivity(intentSettingsButton);}
	  	});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
