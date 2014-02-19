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
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	//Test of working branch in github.
	
	//Declare the button objects.
	Button recordButton;
	Button settingsButton;
	
	//Declare the Edit Text field for naming the session.
	EditText nameSessionEditText;
	String sessionName = new String();
	
	//Declare check box and boolean holder.
	CheckBox checkBoxDisplay;
	boolean displayRealTime;
	
	//Declare variable to hold HZ settings.
	int samplingRateHZ = 60;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Define where the button objects are.
		recordButton = (Button) findViewById(R.id.recordButton);
		settingsButton = (Button) findViewById(id.settingsButton);
		
		//Define where the text edit and check box are.
		nameSessionEditText = (EditText) findViewById(R.id.sessionNameEditText);
		checkBoxDisplay = (CheckBox) findViewById(R.id.checkBox1);
		
		
		//Handle the actual button presses and take the user to the new action.
		
		//Record Button
		final Intent intentRecordButton = new Intent();
	    intentRecordButton.setClass(MainActivity.this, RecordActivity.class);
	      	
	    recordButton.setOnClickListener(new OnClickListener() 
	    {
	      	public void onClick(View v) { 
	      		//Grab the value that is in the nameSessionEditText and put it in a string so that it
	      		//can be passed on to the actvity.
	      		
	      		sessionName = nameSessionEditText.getText().toString();
	      		displayRealTime = checkBoxDisplay.isChecked();
	      		
	      		//Alter the intent to push this data.
	      		intentRecordButton.putExtra("sessionName", sessionName);
	      		intentRecordButton.putExtra("displayRealTime", displayRealTime);
	      		
	      		startActivity(intentRecordButton);}
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
