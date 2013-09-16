package com.webege.jumpingbeans.stopwatch;


import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements android.view.View.OnClickListener {

	public static TextView time;
	TextView lap1;
	Button start;
	Button stop;
	Button lap;
	TableLayout table;
	static long starttime = 0;
	static long stoptime;
	static long totaltime;
	static int seconds;
	static int minutes;
	static int milliseconds;
	static int hours;
	SharedPreferences getPrefs;
	long counters;
	private Handler mHandler = new Handler();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setup();
	}

	private void setup() {
		// TODO Auto-generated method stub
		 time  = (TextView) findViewById(R.id.time);
		start = (Button) findViewById(R.id.button1);
		stop = (Button) findViewById(R.id.button2);
		lap = (Button) findViewById(R.id.button3);
		table = (TableLayout) findViewById(R.id.table);

		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		lap.setOnClickListener(this);
		
		getPrefs =PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	       
        starttime = getPrefs.getLong("jtime", 0);
        
        if(starttime==0){
        
        if(starttime != 0){
			stoptime = System.currentTimeMillis();
			totaltime = stoptime - starttime;
			milliseconds = (int) totaltime % 1000;
			milliseconds = milliseconds/10;
			seconds = (int) totaltime/1000;
			minutes = seconds/60;
			seconds = seconds % 60;
			hours = minutes/60;
			minutes = minutes%60;
			time.setTextSize((float) 50d);
			if(minutes<1){
				time.setText(String.format("%02d:%d", seconds,milliseconds));
			}else{
			if(hours<1){
			time.setText(String.format("%d:%02d:%d", minutes,seconds,milliseconds));
			}else{
				time.setText(String.format("%d:%d:%02d:%d", hours, minutes,seconds,milliseconds));
			}
			}
        }
        }else{
        	mHandler.removeCallbacks(startTimer);
	        mHandler.postDelayed(startTimer, 0);
        }
        
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.button1:
			starttime = System.currentTimeMillis();
			time.setTextSize((float) 50d);
			time.setText("00:00:00");
			table.removeAllViews();
			SharedPreferences.Editor prefEditor = getPrefs.edit();
			
			prefEditor.putLong("jtime", starttime);  
			prefEditor.commit(); 
			
			//new updateClock().execute(starttime);
			
			mHandler.removeCallbacks(startTimer);
	        mHandler.postDelayed(startTimer, 0);
	        
	        
			
			break;
		case R.id.button2:
			if(starttime != 0){
			stoptime = System.currentTimeMillis();
			totaltime = stoptime - starttime;
			milliseconds = (int) totaltime % 1000;
			milliseconds = milliseconds/10;
			seconds = (int) totaltime/1000;
			minutes = seconds/60;
			seconds = seconds % 60;
			hours = minutes/60;
			minutes = minutes%60;
			time.setTextSize((float) 50d);
			if(minutes<1){
				time.setText(String.format("%02d.%d", seconds,milliseconds));
			}else{
			if(hours<1){
			time.setText(String.format("%d:%02d.%d", minutes,seconds,milliseconds));
			}else{
				time.setText(String.format("%d:%d:%02d.%d", hours, minutes,seconds,milliseconds));
			}
			}
			starttime = 0;
			
			SharedPreferences.Editor prefEditor1 = getPrefs.edit();
			
			prefEditor1.putLong("jtime", starttime);  
			prefEditor1.commit(); 
			
			mHandler.removeCallbacks(startTimer);
			
		}
			
			else {
				time.setTextSize((float) 20d);
				time.setText("You haven't presses Start");
			}
			
			break;
		case R.id.button3:
			if(starttime != 0){
				laptime();
			}
				
				else {
					time.setTextSize((float) 20d);
					time.setText("You haven't presses Start");
				}
				break;
		}
	}

	 static void updateDisplay(){
		if(starttime != 0){
			stoptime = System.currentTimeMillis();
			totaltime = stoptime - starttime;
			milliseconds = (int) totaltime % 1000;
			milliseconds = milliseconds/10;
			seconds = (int) totaltime/1000;
			minutes = seconds/60;
			seconds = seconds % 60;
			hours = minutes/60;
			minutes = minutes%60;
			milliseconds = milliseconds/10;
			time.setTextSize((float) 50d);
			
				time.setText(String.format("%02d:%02d:%02d.%d", hours, minutes,seconds,milliseconds));
			
			
		}
		
	}
	
	
	private void laptime() {
		// TODO Auto-generated method stub
		stoptime = System.currentTimeMillis();
		totaltime = stoptime - starttime;
		milliseconds = (int) totaltime % 1000;
		milliseconds = milliseconds/10;
		seconds = (int) totaltime/1000;
		minutes = seconds/60;
		seconds = seconds % 60;
		hours = minutes/60;
		minutes = minutes%60;
		String laptime;
		if(minutes<1){
			laptime = String.format("%02d:%02d", seconds,milliseconds);
		}else{
		if(hours<1){
		laptime = String.format("%d:%02d:%02d", minutes,seconds,milliseconds);
		}else{
			laptime = String.format("%d:%d:%02d:%02d", hours, minutes,seconds,milliseconds);
		}
		}
		
		//make a new row
		TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
		//write some text
		TextView t = new TextView(this);
		t.setText(laptime);
		t.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
		//add text to row
		row.addView(t);
		table.addView(row,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));	
		
		}
	
	private Runnable startTimer = new Runnable() {
		   public void run() {
			   updateDisplay();
			   mHandler.postDelayed(this,100);
			}
		};
	

	}