package edu.temple.ptracker;

import java.util.ArrayList;
import java.util.Vector;

import android.os.Bundle;

public class activityData {

	//Declare Vector to store our data.
	ArrayList<String> activityVector = new ArrayList<String>(50);
	
	
	//Constructor
	activityData(){
		
	}
		
	//Add X,Y,Z data along with Time to the temp array.
	public void addXYZData(float xData, float yData, float zData, float timeData){
		
		//Combine all the inputs into a string to add to the list.
		String tempString = new String();
		tempString = Float.toString(timeData) + "," + Float.toString(xData) + "," + Float.toString(yData) + "," + Float.toString(zData);
		activityVector.add(tempString);
	}
	
	
	
	
}
