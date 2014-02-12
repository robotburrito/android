package edu.temple.ptracker;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class RecordActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	private View view;
	private long lastUpdate;
	 
	 Date today = new Date();
	
	//Did some math... If I sample every 16.67 ms then I am sampling at 60 Hz.
	long millisecondsBetweenSamples = 17;
	
	//Declare the date object for time stamping.
	String fileNameOutput = Long.toString(today.getTime()) + ".csv";	
	File sdCard = Environment.getExternalStorageDirectory();
	File directory = new File (sdCard.getAbsolutePath() + "/pTracker");
	File file = new File(directory, fileNameOutput);
	
	//Declare Button Objects
	Button stopRecordButton;
	
	//Some magic to make the screen not fall asleep. I'm well aware this is the worlds worse
	//way to handle this and that in the future this will run as a service.

	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		lastUpdate = System.currentTimeMillis();

		
		//Setup the buttons
		stopRecordButton = (Button) findViewById(R.id.endRecordButton);

		//Stop Record Button
		final Intent intentStopRecordButton = new Intent();
		intentStopRecordButton.setClass(RecordActivity.this, MainActivity.class);
			      	
		stopRecordButton.setOnClickListener(new OnClickListener() 
		{
		  	public void onClick(View v) {
		  		finish();}
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
	    
	    if ((actualTime - lastUpdate < millisecondsBetweenSamples)) 
	    	{
	    		return;
	    	}
	    
	    //Update the fields if the above doesn't dump out.
	    lastUpdate = actualTime;
	    try {
			updateXYZFields(x,y,z);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return; 
	    
	  }
	  
	public void updateXYZFields(float x, float y, float z) throws IOException {
		  Long timeStampNow = System.currentTimeMillis();

		  /* comment out...
		  TextView xValueText = (TextView) findViewById(R.id.textXValue);
		  TextView yValueText = (TextView) findViewById(R.id.textYValue);
		  TextView zValueText = (TextView) findViewById(R.id.textZValue);
		  TextView dateValueText = (TextView) findViewById(R.id.textTimeElapsed);
		  

		  
		  //Convert the values passed to strings so that they can be set to the text fields.
		  String xStringValue = Float.toString(x);
		  String yStringValue = Float.toString(y);
		  String zStringValue = Float.toString(z);
		  String dateStringValue = Long.toString(timeStampNow);
		  
		
		  //Set values to the text fields.
		  xValueText.setText(xStringValue);
		  yValueText.setText(yStringValue);
		  zValueText.setText(zStringValue);		
		  dateValueText.setText(dateStringValue);
		  */
		
		  FileOutputStream fOut = new FileOutputStream(file, true);
		  PrintWriter pWriter = new PrintWriter(fOut);
		  pWriter.printf("%d,%f,%f,%f\n",timeStampNow,x,y,z );
		  pWriter.close();
		  
	  }
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	public void endRecording() throws IOException{
		
		
	}

	public void openFileWithDate()
	{
		
	
	}
	protected void onResume() {
	    super.onResume();
	    // register this class as a listener for the orientation and
	    // accelerometer sensors
	    sensorManager.registerListener(this,
	        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	        SensorManager.SENSOR_DELAY_NORMAL);
	  }

	protected void onPause() {
	    // unregister listener
	    super.onPause();
	    sensorManager.unregisterListener(this);
	  }
	
}//End of class.




