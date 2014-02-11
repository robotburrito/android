package edu.temple.ptracker;


import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


//Import the stuff to manage the hardware accelerometer.
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class RecordActivity extends Activity implements SensorEventListener {
	
	private SensorManager sensorManager;
	private View view;
	private long lastUpdate;

	//Setup file stream.
	FileOutputStream outputFile;
	
	//Declare Button Objects
	Button stopRecordingButton;
	
	int refreshRate = 500;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();
	
		//Setup the buttons
	  	final Intent intentStopRecordingButton = new Intent();
	  	intentStopRecordingButton.setClass(RecordActivity.this, MainActivity.class);
	  	      	
		stopRecordingButton.setOnClickListener(new OnClickListener() 
	  	{
	  		public void onClick(View v) { 
	  			endRecording();
	  			startActivity(intentStopRecordingButton);
	  		}
	  	});		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record, menu);
		
		return true;
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
	    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	      getAccelerometer(event);
	    }

	  }
	  
	private void getAccelerometer(SensorEvent event) {
	    float[] values = event.values;
	    // Movement
	    float x = values[0];
	    float y = values[1];
	    float z = values[2];

	    long actualTime = System.currentTimeMillis();
	    
	    
	    //So I am going to check if less than 200 MS has gone by, if so, lets dump out. However if more than 200 MS
	    //has gone by then we are going to go ahead and update our fields on the screen.
	    
	    if ((actualTime - lastUpdate < refreshRate)) 
	    	{
	    		return;
	    	}
	    
	    //Update the fields if the above doesn't dump out.
	    lastUpdate = actualTime;
	    updateXYZFields(x,y,z);
	    
	    return; 
	    
	  }
	  
	public void updateXYZFields(float x, float y, float z){
		  TextView xValueText = (TextView) findViewById(R.id.textXValue);
		  TextView yValueText = (TextView) findViewById(R.id.textYValue);
		  TextView zValueText = (TextView) findViewById(R.id.textZValue);

		  
		  //Convert the values passed to strings so that they can be set to the text fields.
		  String xStringValue = Float.toString(x);
		  String yStringValue = Float.toString(y);
		  String zStringValue = Float.toString(z);
		  
		  //Set values to the text fields.
		  xValueText.setText(xStringValue);
		  yValueText.setText(yStringValue);
		  zValueText.setText(zStringValue);		
		  
		  //Let us go ahead and try to write this data to a file on the device.
		  //String FILENAME = "XYZ_output.txt"; 
		  
	  }
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	public void endRecording(){
		//Put some code in here to do file closings and to save data.
	}


}




