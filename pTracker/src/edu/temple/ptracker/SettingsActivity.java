package edu.temple.ptracker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		try {
			fileTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void fileTest() throws IOException{
		/*
		getDir("output.txt", 0);
		
		File file = new File("output.txt");
		PrintWriter printFileOutput = new PrintWriter(file);
		
		printFileOutput.printf("this is a test");
		printFileOutput.printf("asdfasdfasdfasdf");
		
		printFileOutput.close();
		*/
		
		/*
		String filename = "myfile";
		String string = "Hello world!";
		FileOutputStream outputStream;

		try {
		  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		  outputStream.write(string.getBytes());
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
		*/
		//This will get the SD Card directory and create a folder named MyFiles in it.

		File sdCard = Environment.getExternalStorageDirectory();
		File directory = new File (sdCard.getAbsolutePath() + "/pTracker");
		directory.mkdirs();

		//Now create the file in the above directory and write the contents into it
		File file = new File(directory, "mysdfile.txt");
		FileOutputStream fOut = new FileOutputStream(file);
		//OutputStreamWriter osw = new OutputStreamWriter(fOut);
		PrintWriter pWriter = new PrintWriter(fOut);
		
		pWriter.printf("nachos man dogs,");
		pWriter.printf("asdfasdf");
		pWriter.close();
		//osw.write("HERP A DERPLE DOOOP");
		//osw.flush();
		//osw.close();
	}

}
