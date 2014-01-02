package com.webege.jumpingbeans.stopwatch;


import java.util.ArrayList;


import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import android.widget.TextView;


public class MainActivity extends Activity implements android.view.View.OnClickListener {

	public static TextView time;
	Button start;
	Button stop;
	Button lap;
	ListView lapsAndSplits;
	MyAdapter aa;
	ArrayList splits;
	static long starttime = 0;
	static long stoptime = 0;
	static long lastLap = 0;
	
	SharedPreferences getPrefs;
	long counters;
	private Handler mHandler = new Handler();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("TAG", "start");
		setup();
		
	}

	private void setup() {
		// conect xml layout to code
		time  = (TextView) findViewById(R.id.time);
		start = (Button) findViewById(R.id.button1);
		stop = (Button) findViewById(R.id.button2);
		lap = (Button) findViewById(R.id.split);
		lapsAndSplits = (ListView) findViewById(R.id.listView1);
		
		//title
		View header = (View)getLayoutInflater().inflate(R.layout.headrow, null);
		lapsAndSplits.addHeaderView(header);
		
		splits = new ArrayList<LapsAndSplits>();
		aa = new MyAdapter(this,R.layout.tablerow,splits);
		lapsAndSplits.setAdapter(aa);
		
		//call listeners for buttons
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		lap.setOnClickListener(this);
		
		//get running time, even if program was stopped
		getPrefs =PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	    lastLap = starttime = getPrefs.getLong("jtime", 0);
        
        if(starttime!=0){
        	Log.i("TAG", " on create call runnable");
        	mHandler.removeCallbacks(startTimer);
	        mHandler.postDelayed(startTimer, 0);
        }
        
	}

	public void onClick(View arg0) {
		
		switch(arg0.getId()){
		//start button
		case R.id.button1:
			//set start time from current time
			starttime = System.currentTimeMillis();
			//store value as lest lap
			lastLap = starttime;
			//set up display
			time.setTextSize((float) 50d);
			time.setText("00:00:00");
			//remove old split times 
			splits.clear();
			aa.notifyDataSetChanged();
			//write start time to file in case program is closed
			SharedPreferences.Editor prefEditor = getPrefs.edit();
			prefEditor.putLong("jtime", starttime);  
			prefEditor.commit(); 
			
			
			Log.i("TAG", "on start call runnable");
			//cancel all running clocks and start new
			mHandler.removeCallbacks(startTimer);
	        mHandler.postDelayed(startTimer, 0);
	        
			break;
			
		//stop	
		case R.id.button2:
			//if haven't started yet
			if(starttime != 0){
			
				
			//stop all running clocks
			mHandler.removeCallbacks(startTimer);	
			updateDisplay();	
			
			//reset program values
			starttime = 0;
			SharedPreferences.Editor prefEditor1 = getPrefs.edit();
			prefEditor1.putLong("jtime", 0);  
			prefEditor1.commit(); 
		}else {
				time.setTextSize((float) 20d);
				time.setText("You haven't presses Start");
			}
			
			break;
			//lap/split button
		case R.id.split:
			if(starttime != 0){
				newLaptime();
			}
				
				else {
					time.setTextSize((float) 20d);
					time.setText("You haven't presses Start");
				}
				break;
		}
	}

	private void updateDisplay(){
		if(starttime != 0){
			
			
			time.setTextSize((float) 50d);
			time.setText(getTimeString(starttime, System.currentTimeMillis()));
			
			
		}
		
	}
	
	private String getTimeString(long start, long stop){
		
		long totaltime = stop - start;
		int milliseconds = (int) totaltime % 1000;
		milliseconds = milliseconds/10;
		int seconds = (int) totaltime/1000;
		int minutes = seconds/60;
		seconds = seconds % 60;
		int hours = minutes/60;
		minutes = minutes%60;
		milliseconds = milliseconds/10;
		return String.format("%02d:%02d:%02d.%d", hours, minutes,seconds,milliseconds);
	}
	
	private void newLaptime() {
		
		int textsize = 20;
		
		long current = System.currentTimeMillis();
				
		
		
		splits.add(new LapsAndSplits(getTimeString(lastLap, current), getTimeString(starttime, current)));
		aa.notifyDataSetChanged();
		lastLap = current;
		
		
		
		
			
		
		}
	
	private Runnable startTimer = new Runnable() {
		   public void run() {
			   updateDisplay();
			   mHandler.postDelayed(this,100);
			}
		};
	

	}