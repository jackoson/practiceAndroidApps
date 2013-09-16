package com.jumpingbeans.onlinecountdown;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;







import android.annotation.TargetApi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayScreen extends Activity {

	//NoteBase NB;
	public int alertCount = 0;
	private boolean paused = false;
	private boolean ON=true;
	private int number1;
	private int number2;
	private int number3;
	private int number4;
	private int number5;
	private int number6;
	private String string1;
	private String string2;
	private String string3;
	private String string4;
	private String string5;
	private String string6;
	private String originalString1;
	private String originalString2;
	private String originalString3;
	private String originalString4;
	private String originalString5;
	private String originalString6;
	private boolean num1Clicked = false;
	private boolean num2Clicked = false;
	private boolean num3Clicked = false;
	private boolean num4Clicked = false;
	private boolean num5Clicked = false;
	private boolean num6Clicked = false;
	private String temp = " = ";
	private int count = 0;
	private boolean proceed = true;
	private int termCount = 0;
	private int theNum1 = 0;
	private int theNum2 = 0;
	private int ans;
	private int theAns;
	private String operatorType = "";
	private String temp1 = "";
	private String temp2 = "";
	
	Button num1;
	Button num2;
	Button num3;
	Button num4;
	Button num5;
	Button num6;
	Button equals;
	Button add;
	Button times;
	Button minus;
	Button divide;
	Button clear;
	Button reset;
	Button nextGame;
	
	TextView display1;
	TextView display2;
	TextView time;
	
	String gameName;
	String PlayerID;
	
	int timevalue = 100;
	boolean timerRun;
	Handler h;

	int[] numbersfromdatabase;
	
	boolean creator;
	boolean error = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_screen);
		num1 = (Button) findViewById(R.id.num1);
		num2 = (Button) findViewById(R.id.num2);
		num3 = (Button) findViewById(R.id.num3);
		num4 = (Button) findViewById(R.id.num4);
		num5 = (Button) findViewById(R.id.num5);
		num6 = (Button) findViewById(R.id.num6);
		equals = (Button) findViewById(R.id.equals);
		add = (Button) findViewById(R.id.add);
		times = (Button) findViewById(R.id.times);
		minus = (Button) findViewById(R.id.minus);
		divide = (Button) findViewById(R.id.divide);
		clear = (Button) findViewById(R.id.clear);
		reset = (Button) findViewById(R.id.reset);
		nextGame = (Button) findViewById(R.id.nextGame);
		display1 = (TextView) findViewById(R.id.display1);
		display2 = (TextView) findViewById(R.id.display2);
		time = (TextView) findViewById(R.id.time);
		
		gameName = getIntent().getExtras().getString("gameName");
		PlayerID = getIntent().getExtras().getString("PlayerID");
		creator = getIntent().getExtras().getBoolean("creator");
		
		
		new resetDistances().execute(getString(R.string.BaseAddress)+"reset_distance.php?id="+PlayerID);
		
		new isstartedtask().execute(getString(R.string.BaseAddress)+"check_if_started.php?code="+gameName);
		
		
		
		
		
		//setAB();
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    } 
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		System.exit(0);
		return false;
	}
	
	/*@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void setAB(){
		if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB){
			ActionBar ab = getActionBar();
			ab.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		}
	}
	*/
	public void num1Clicked(View view){
		if(ON){
		if(num1Clicked != true){
		updateStrings();
		checkCount();
		checkTermCount();
		if(proceed != false){
		display2.append(string1);
		num1.setText("-");
		num1Clicked = true;
		if(theNum1 != 0){
			if(ON){
		theNum2 = Integer.parseInt(string1);
		temp2 = string1;
			}
		}
		else{
			ON=false;
		theNum1 = Integer.parseInt(string1);
		temp1 = string1;
		}
		count++;
		termCount++;
		}
		else{
		}
		}}
	}
	
	
	public void num2Clicked(View view){
		if(ON){
		if(num2Clicked != true){
			updateStrings();
			checkCount();
			checkTermCount();
			if(proceed != false){
			display2.append(string2);
			num2.setText("-");
			num2Clicked = true; 
			if(theNum1 != 0){
				if(ON){
			theNum2 = Integer.parseInt(string2);
			temp2 = string2;
				}
			}
			else{
				ON=false;
			theNum1 = Integer.parseInt(string2);
			temp1 = string2;
			}
			count++;
			termCount++;
			}
			else{
			}
		}}
	}
	

	public void num3Clicked(View view){
		if(ON){
	if(num3Clicked != true){
		updateStrings();
		checkCount();
		checkTermCount();
		if(proceed != false){
		display2.append(string3);
		num3.setText("-");
		num3Clicked = true;
		if(theNum1 != 0){
			if(ON){
		theNum2 = Integer.parseInt(string3);
		temp2 = string3;
			}
		}
		else{
			ON=false;
		theNum1 = Integer.parseInt(string3);
		temp1 = string3;
		}
		count++;
		termCount++;
		}
		else{}
		}
		else{

		}
	}}
		
	
	public void num4Clicked(View view){
		if(ON){
		if(num4Clicked != true){
			updateStrings();
			checkCount();
			checkTermCount();
			if(proceed != false){
			display2.append(string4);
			num4.setText("-");
			num4Clicked = true;
			if(theNum1 != 0){
				
			theNum2 = Integer.parseInt(string4);
			temp2 = string4;
				
			}
			else{
				ON=false;
			theNum1 = Integer.parseInt(string4);
			temp1 = string4;
			}
			count++;
			termCount++;
			}
			else{}
			}
			else{

			}
		}
		}
	
	
	public void num5Clicked(View view){
		if(ON){
		if(num5Clicked != true){
			updateStrings();
			checkCount();
			checkTermCount();
			if(proceed != false){
			display2.append(string5);
			num5.setText("-");
			num5Clicked = true;
			if(theNum1 != 0){
				
			theNum2 = Integer.parseInt(string5);
			temp2 = string5;
				
			}
			else{
				ON=false;
			theNum1 = Integer.parseInt(string5);
			temp1 = string5;
			}
			count++;
			termCount++;
			}
			else{}
			}
			else{

			}
		}
		}
	
	
	public void num6Clicked(View view){
		if(ON){
		if(num6Clicked != true){
			updateStrings();
			checkCount();
			checkTermCount();
			if(proceed != false){
			display2.append(string6);
			num6.setText("-");
			num6Clicked = true;
			if(theNum1 != 0){
				if(ON){
			theNum2 = Integer.parseInt(string6);
			temp2 = string6;
				}
			}
			else{
				ON=false;
			theNum1 = Integer.parseInt(string6);
			temp1 = string6;
			}
			count++;
			termCount++;
			}
			else{}
			}
			else{

			}
		}
		}
	
	
	public void add(View view){
		updateStrings();
		if(theNum1 != 0 && theNum2 == 0){
		if(operatorType.length()>0){
			Toast.makeText(getApplicationContext(), "Operator already present", Toast.LENGTH_SHORT).show();
		}
		else{
		display2.append(" + ");
		proceed = true;
		count = 0;
		operatorType = "add";
		ON=true;
		}
		}
		 else if(operatorType != "" && temp1 != "" && temp2 != ""){
				//display warning to click equals first
				Toast.makeText(getApplicationContext(),
				"Please click equals", Toast.LENGTH_SHORT)
				.show();
				}
				else{
					//display warning to enter an number first
					Toast.makeText(getApplicationContext(),
					"Enter a number before clicking an operator", Toast.LENGTH_SHORT)
					.show();
				}
		
	}
	
	public void minus(View view){
		updateStrings();
		if(theNum1 != 0 && theNum2 == 0){
		if(operatorType.length()>0){
			Toast.makeText(getApplicationContext(), "Operator already present", Toast.LENGTH_SHORT).show();
		}
		else{
		display2.append(" - ");
		proceed = true;
		count = 0;
		operatorType = "minus";
		ON=true;
		}
		}
		 else if(operatorType != "" && temp1 != "" && temp2 != ""){
				//display warning to click equals first
				Toast.makeText(getApplicationContext(),
				"Please click equals", Toast.LENGTH_SHORT)
				.show();
				}
				else{
					//display warning to enter an number first
					Toast.makeText(getApplicationContext(),
					"Enter a number before clicking an operator", Toast.LENGTH_SHORT)
					.show();
				}
	}
	
	public void divide(View view){
		updateStrings();
		if(theNum1 != 0 && theNum2 == 0){
		if(operatorType.length()>0){
			Toast.makeText(getApplicationContext(), "Operator already present", Toast.LENGTH_SHORT).show();
		}
		else{
		display2.append(" ÷ ");
		proceed = true;
		count = 0;
		operatorType = "divide";
		ON=true;
		}
		}
		 else if(operatorType != "" && temp1 != "" && temp2 != ""){
				//display warning to click equals first
				Toast.makeText(getApplicationContext(),
				"Please click equals", Toast.LENGTH_SHORT)
				.show();
				}
				else{
					//display warning to enter an number first
					Toast.makeText(getApplicationContext(),
					"Enter a number before clicking an operator", Toast.LENGTH_SHORT)
					.show();
				}
	
	}
	
	public void times(View view){
		updateStrings();
		if(theNum1 != 0 && theNum2 == 0){
		if(operatorType.length()>0){
			Toast.makeText(getApplicationContext(), "Operator already present", Toast.LENGTH_SHORT).show();
		}
		else{
		display2.append(" × ");
		proceed = true;
		count = 0;
		ON=true;
		operatorType = "times";
		}
		}
		 else if(operatorType != "" && temp1 != "" && temp2 != ""){
				//display warning to click equals first
				Toast.makeText(getApplicationContext(),
				"Please click equals", Toast.LENGTH_SHORT)
				.show();
				}
				else{
					//display warning to enter an number first
					Toast.makeText(getApplicationContext(),
					"Enter a number before clicking an operator", Toast.LENGTH_SHORT)
					.show();
				}
		}
	
	
	public void nextGame(View view){
		display2.setText("");
		temp1 = "";
		temp2 = "";
		
		operatorType="";
		calcRandNum();
		
	}
	
	
	
	public void reset(View view){
		display2.setText("");
		num1.setText(originalString1);
		num2.setText(originalString2);
		num3.setText(originalString3);
		num4.setText(originalString4);
		num5.setText(originalString5);
		num6.setText(originalString6);

		string1 = originalString1;
	string2 = originalString2;
	string3 = originalString3;
	string4 = originalString4;
	string5 = originalString5;
	string6 = originalString6;

		
		num1Clicked = false;
		num2Clicked = false;
		num3Clicked = false;
		num4Clicked = false;
		num5Clicked = false;
		num6Clicked = false;
		
		count = 0;
		proceed = true;
		termCount = 0;
		theNum1 = 0;
		theNum2 = 0;
		temp1 = "";
		temp2 = "";
		ON=true;
		updateStrings();
		operatorType = "";
	}
	
	public void equals(View view){
		if(termCount == 2 && operatorType != ""){
			if(operatorType == "add"){
				ans = theNum1 + theNum2;
				temp1 = "";
				temp2 = "";
				setButton();
				updateStrings();
			}
			
			if(operatorType == "minus"){
				ans = theNum1 - theNum2;
				temp1 = "";
				temp2 = "";
				setButton();
				updateStrings();
			}
			
			if(operatorType == "times"){
				ans = theNum1 * theNum2;
				temp1 = "";
				temp2 = "";
				setButton();
				updateStrings();
			}
			
			if(operatorType == "divide"){
				if(theNum2 == 0){
					Toast.makeText(getApplicationContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();
					clear(display2);
				}
				else if(theNum2 !=0){
				ans = theNum1 /theNum2;
				temp1 = "";
				temp2 = "";
				setButton();
				updateStrings();
				}
			}
			
			if(theAns == ans){
				display1.setText("You did it");
				
				
				
				operatorType = "";
				
				temp1 = "";
				temp2 = "";
				
				sendDistance();
				
			}else{
				display2.setText("");
			}
			
			
			theNum1 = 0;
			theNum2 = 0;
			ON=true;
		}
		else{
			Toast.makeText(getApplicationContext(), "Incomplete sum", Toast.LENGTH_SHORT).show();
			updateStrings();
		}
	
}
	
	
	public void sendDistance(){
		//THIS REPLACES WHAT IS COMMENTED OUT BELLOW TO DEAL WITH TIMER
		
				//stops the time
				timerRun=false;
				
				//if sum is in calculation textview
				if (termCount == 2 && operatorType != "") {
					equals(null);
				}else if(theNum1!=0){//if it just has a number in it
					//put back on to a button
					String calc = Integer.toString(theNum1);
					 
					if (num1.getText() == "-") {
						num1.setText("" + calc);
					} else if (num2.getText() == "-") {
						num2.setText("" + calc);
					} else if (num3.getText() == "-") {
						num3.setText("" + calc);
					} else if (num4.getText() == "-") {
						num4.setText("" + calc);
					} else if (num5.getText() == "-") {
						num5.setText("" + calc);
					} else if (num6.getText() == "-") {
						num6.setText("" + calc);
					}
					
				}
				
				
				
				
				//check which of their numbers are closest
				//make sure i have the latest values
				updateStrings();
				
				//if button is blanck set to zero
				if (string1 == "-") {
					string1 = "0";
				}
				if (string2 == "-") {
					string2 = "0";
				}
				if (string3 == "-") {
					string3 = "0";
				}
				if (string4 == "-") {
					string4 = "0";
				}
				if (string5 == "-") {
					string5 = "0";
				}
				if (string6 == "-") {
					string6 = "0";
				}
				
				
				//change the button strings in to integer
				int num1 = Integer.parseInt(string1);
				int num2 = Integer.parseInt(string2);
				int num3 = Integer.parseInt(string3);
				int num4 = Integer.parseInt(string4);
				int num5 = Integer.parseInt(string5);
				int num6 = Integer.parseInt(string6);
				
				//find distances
				num1 = Math.abs((theAns-num1));
				num2 = Math.abs((theAns-num2));
				num3 = Math.abs((theAns-num3));
				num4 = Math.abs((theAns-num4));
				num5 = Math.abs((theAns-num5));
				num6 = Math.abs((theAns-num6));
				
				//find smallest
				int smallestdifference = Math.min(Math.min(Math.min(num1, num2),Math.min(num3, num4)),Math.min(num5, num6));
				
				//find closest by comparing.
				//and set to text box for saving
				if(smallestdifference == num1){
					display2.setText(string1);
				}else if(smallestdifference == num2){
					display2.setText(string2);
				}else if(smallestdifference == num3){
					display2.setText(string3);
				}else if(smallestdifference == num4){
					display2.setText(string4);
				}else if(smallestdifference == num5){
					display2.setText(string5);
				}else if(smallestdifference == num6){
					display2.setText(string6);
				}
				//resets stuff
				
				operatorType = "";
				temp1 = "";
				temp2 = "";
				
				//Stores the value and swaps player
				Toast.makeText(getApplicationContext(), getString(R.string.BaseAddress)+"post_distance.php?id="+PlayerID+"&distance="+Integer.toString(smallestdifference), 0).show();
				
				new noReturn().execute(getString(R.string.BaseAddress)+"post_distance.php?id="+PlayerID+"&distance="+Integer.toString(smallestdifference));
				
				
				
	}
	
	
	public void setButton(){
		operatorType = "";
		if(num1.getText() == "-"){
			num1.setText(""+ans);
			num1Clicked = false;
			}
			else if(num2.getText() == "-"){
			num2.setText(""+ans);
			num2Clicked = false;
			}
			else if(num3.getText() == "-"){
			num3.setText(""+ans);
			num3Clicked = false;
			}
			else if(num4.getText() == "-"){
			num4.setText(""+ans);
			num4Clicked = false;
			}
			else if(num5.getText() == "-"){
			num5.setText(""+ans);
			num5Clicked = false;
			}
			else if(num6.getText() == "-"){
			num6.setText(""+ans);
			num6Clicked = false;
			}
			termCount = 0;
			count = 0;
	}
	
	
	public void calcRandNum(){
		String mode = "";
		Random rand = new Random();
		operatorType = "";
		temp1 = "";
		temp2 = "";
		/*
		Intent intent = getIntent();
		String message = intent.getStringExtra(FreePlayLevels.EXTRA_MESSAGE);
		
		if(message.equals("easy")){
			theAns = rand.nextInt(200)+101;
			if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB){
				mode="Easy";
				setABTitle(mode);
			}
		}
		else if(message.equals("medium")){
			theAns = rand.nextInt(200)+301;
			if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB){
				mode="Medium";
				setABTitle(mode);
			}
		}
		else if(message.equals("hard")){
			theAns = rand.nextInt(250)+501;
			if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB){
				mode="Hard";
				setABTitle(mode);
			}
		}
		else if(message.equals("extreme")){
			theAns = rand.nextInt(250)+751;
			if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB){
				mode="Extreme";
				setABTitle(mode);
			}
		}
		else if(message.equals("all")){
			theAns = rand.nextInt(900)+101;
			if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB){
				mode="All";
				setABTitle(mode);
			}
		}
*/
		theAns = numbersfromdatabase[6];
		display1.setText(""+theAns);
		number1 = numbersfromdatabase[1];
		number2 = numbersfromdatabase[2];
		number3 = numbersfromdatabase[3];
		number4 = numbersfromdatabase[4];
		number5 = numbersfromdatabase[5];
		number6 = numbersfromdatabase[0];

		
		originalString1 = Integer.toString(number1);
		originalString2 = Integer.toString(number2);
		originalString3 = Integer.toString(number3);
		originalString4 = Integer.toString(number4);
		originalString5 = Integer.toString(number5);
		originalString6 = Integer.toString(number6);


		string1 = originalString1;
		string2 = originalString2;
		string3 = originalString3;
		string4 = originalString4;
		string5 = originalString5;
		string6 = originalString6;


		num1.setText(string1);
		num2.setText(string2);
		num3.setText(string3);
		num4.setText(string4);
		num5.setText(string5);
		num6.setText(string6);


		num1Clicked = false;
		num2Clicked = false;
		num3Clicked = false;
		num4Clicked = false;
		num5Clicked = false;
		num6Clicked = false;
		count = 0;
		proceed = true;
		termCount = 0;
			theNum1 = 0;
				theNum2 = 0;
				ON=true;
	}
	
	public void updateStrings(){
		string1 = num1.getText().toString();
		string2 = num2.getText().toString();
		string3 = num3.getText().toString();
		string4 = num4.getText().toString();
		string5 = num5.getText().toString();
		string6 = num6.getText().toString();
	}
	
	public void checkCount(){
		if(count%2 == 1){
				proceed = false;
			}
			else{
			proceed = true;
			}
		}

	public void checkTermCount(){
		if(termCount == 2){
			proceed = false;
		}
		else{
			proceed = true;
		}
	}
	

	
	
	public void clear(View view){
		if(temp1.length()>0){
			if(num1.getText() == "-"){
		num1.setText(temp1);
		num1Clicked = false;
		}
		else if(num2.getText() == "-"){
		num2.setText(temp1);
		num2Clicked = false;
		}
	else if(num3.getText() == "-"){
		num3.setText(temp1);
		num3Clicked = false;
		}
		else if(num4.getText() == "-"){
		num4.setText(temp1);
		num4Clicked = false;
	}
		else if(num5.getText() == "-"){
		num5.setText(temp1);
		num5Clicked = false;
		}
		else if(num6.getText() == "-"){
		num6.setText(temp1);
				num6Clicked = false;		}
		termCount = 0;
	count = 0;
	ON=true;
	
			
			
			if(temp2.length()>0){
			if(num1.getText() == "-"){
		num1.setText(temp2);
		num1Clicked = false;
		}
		else if(num2.getText() == "-"){
		num2.setText(temp2);
		num2Clicked = false;
		}
		else if(num3.getText() == "-"){
		num3.setText(temp2);
		num3Clicked = false;
		}
		else if(num4.getText() == "-"){
		num4.setText(temp2);
		num4Clicked = false;
		}
		else if(num5.getText() == "-"){
		num5.setText(temp2);
		num5Clicked = false;
		}
		else if(num6.getText() == "-"){
		num6.setText(temp2);
		num6Clicked = false;
		}
		termCount = 0;
		count = 0;
		ON=true;
			}
			
			else if(operatorType != "" && temp1 == "" && temp2 == ""){
				operatorType = "";
				display2.setText("");
			}
			count=0;
			temp1="";
			temp2="";
			theNum1 = 0;
			theNum2 = 0;
			termCount=0;
			operatorType = "";
			checkCount();
			checkTermCount();
			updateStrings();
			display2.setText("");
			ON=true;
			}

	}

		/*@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void setABTitle(String mode){
			ActionBar ab = getActionBar();
			ab.setTitle("Free Play");
			ab.setSubtitle(mode);
		}*/
	
		
		@Override
		public void onBackPressed(){/*
			paused = true;
			alertCount = 0;
			if(paused == true){
				//stops timer when the app closes
				if(alertCount == 0){
					AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
					//set content
					dialogBuilder.setTitle("Warning");
					dialogBuilder.setMessage("Game Paused. Are you sure you want to leave?");
					//make okay button
					dialogBuilder.setPositiveButton("Resume",
					new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
					
					//starts it immediately
					        alertCount = 0;
					       
					}
					});
					dialogBuilder.setNegativeButton("Leave",
							new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int which) {
								alertCount = 0;
								//startActivity(new Intent(MainActivity.this, FreePlayLevels.class));
							       finish();
							       
							}
							});
					alertCount = 1;
					AlertDialog alertDialog = dialogBuilder.create();
					alertDialog.setCanceledOnTouchOutside(false);
					alertDialog.show();
				}
			}
*/
			}
		
		
		@Override
		protected void onResume() {
		// TODO Auto-generated method stub
		if(paused == true){
			if(alertCount == 0){
		//stops timer when the app closes
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
			//set content
			dialogBuilder.setTitle("Paused");
			dialogBuilder.setMessage("Game Paused, Click Continue To Resume");
			//make okay button
			dialogBuilder.setPositiveButton("Resume",
			new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
			
			//starts it immediately
			        alertCount=0;
			       
			}
			});
			dialogBuilder.setNegativeButton("Back",
					new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						alertCount=0;
						//startActivity(new Intent(MainActivity.this, FreePlayLevels.class));
					       finish();
					       
					}
					});
			alertCount=1;
			AlertDialog alertDialog = dialogBuilder.create();
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
			}

		}
		super.onResume();
		}
		
		
		@Override
		protected void onPause() {
		// TODO Auto-generated method stub

		//stops timer when the app closes
		
		paused = true;
		super.onPause();
		}
		
		public void saveQuestion(){
			/*NoteBase NB;
			NB = new NoteBase(this);
			SQLiteDatabase dataB = NB.getWritableDatabase();
			ContentValues newValues = new ContentValues();
			newValues.put("TNUM", display1.getText().toString());
			newValues.put("NUM1", originalString1);
			newValues.put("NUM2", originalString2);
			newValues.put("NUM3", originalString3);
			newValues.put("NUM4", originalString4);
			newValues.put("NUM5", originalString5);
			newValues.put("NUM6", originalString6);
			dataB.insert(NoteBase.TABLE2_NAME, null, newValues);
			dataB.close();
*/		}
		
		
		
		private class isstartedtask extends AsyncTask<String, Integer,String>{

			
			
			@Override
			protected String doInBackground(String... address) {
				// TODO Auto-generated method stub
				
				BufferedReader br = null;
	 		      
	 		      
	 		      try { 
	 		        	DefaultHttpClient httpClient = new DefaultHttpClient();
	 					URI url = new URI(address[0]);
	 					HttpGet httpGet = new HttpGet();
	 					httpGet.setURI(url);
	 					HttpResponse httpResponse = httpClient.execute(httpGet);
	 					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
	 					
	 					String line = br.readLine();
	 						
	 					br.close();
	 					
	 					return line;
	 				
	 					
	 				} catch (URISyntaxException e1) {
	 					// TODO Auto-generated catch block
	 					e1.printStackTrace();
	 				} catch (ClientProtocolException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				} catch (IOException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				} 
				return null;
			}
			
			
			
			protected void onPostExecute(String result) {
				
				if(result.equals("0")){
					new isstartedtask().execute(getString(R.string.BaseAddress)+"check_if_started.php?code="+gameName);
					
					display2.setText("Waiting For Game To start.");
				}else{
					Toast.makeText(getApplicationContext(), "Game Started", 0).show();
					new getnumstask().execute(getString(R.string.BaseAddress)+"get_numbers.php?code="+gameName);
				}
					
					
			     
		     }
			
		}
		
		
		private class getnumstask extends AsyncTask<String, Integer,int[]>{

			ProgressDialog dialog;
			
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				dialog = new ProgressDialog(PlayScreen.this);
				dialog.setMessage("Getting the numbers...");
		        dialog.show();
				
			}
			
			@Override
			protected int[] doInBackground(String... address) {
				// TODO Auto-generated method stub
				BufferedReader br = null;
				int[] numbers = new int[8];
			
			      try { 
			        	DefaultHttpClient httpClient = new DefaultHttpClient();
						URI url = new URI(address[0]);
						HttpGet httpGet = new HttpGet();
						httpGet.setURI(url);
						HttpResponse httpResponse = httpClient.execute(httpGet);
						br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
						
						
						String[] lines = new String[8];
						error = false;
						
						for(int x = 0; x<8;x++){
							lines[x] = br.readLine();
							
							if(lines[x].contentEquals("error")){
								error = true;
								Log.i("EROR", "nio numbers");
							}
						}
						
						if(error){
							new getnumstask().execute(getString(R.string.BaseAddress)+"get_numbers.php?code="+gameName);
						}else{
							for(int x = 0; x<8;x++){
								numbers[x] = Integer.parseInt(lines[x]);
							}
						}
						
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							Log.i("playscreen", e1.getMessage());
							e1.printStackTrace();
						} catch (ClientProtocolException e) {
							Log.i("playscreen", e.getMessage());
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							Log.i("playscreen", e.getMessage());
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				
				return numbers;
			}
			
			
			
			protected void onPostExecute(int[] numbers) {
				
				if(error==false){
					numbersfromdatabase = numbers;
					
					
					
					timevalue  = numbers[7];
					timerRun = true;
					h = new Handler();
			        h.post(tupdate);
					
			        if (dialog.isShowing()) {
				           dialog.dismiss();
				        }
			        
			        display2.setText("");
			        
			        calcRandNum();
				}
				
		        
		       
		        
		     }
			
		}

		  protected void updateTimer() {
				// TODO Auto-generated method stub
		    	time.setText(Integer.toString(timevalue));
		    	
		    	if(timevalue < 1){
		    		sendDistance();
		    	}
		    	timevalue--;
			}
		    
		    

		    
		    
				private Runnable tupdate = new Runnable() {
					   public void run() {
						  
						   updateTimer();
						   //if it is not paused
						   if(timerRun)
						   h.postDelayed(this,1000);
						}
					};




					private class noReturn extends AsyncTask<String, Integer,String>{

						ProgressDialog dialog;
				    	
				    	
				    	@Override
				    	protected void onPreExecute() {
				    		// TODO Auto-generated method stub
				    		super.onPreExecute();
				    		dialog = new ProgressDialog(PlayScreen.this);
				    		dialog.setMessage("Posting Scores");
				            dialog.show();
				    		
				    	}
						
						@Override
						protected String doInBackground(String... address) {
							// TODO Auto-generated method stub
							
							 try { 
						        	DefaultHttpClient httpClient = new DefaultHttpClient();
									URI url = new URI(address[0]);
									HttpGet httpGet = new HttpGet();
									httpGet.setURI(url);
									httpClient.execute(httpGet);
									Log.i("POSTED", address[0]);
									
								} catch (URISyntaxException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClientProtocolException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							 
							 return "";
						}
						
						
						protected void onPostExecute(String result) {
							
							
							
							Intent i = new Intent(PlayScreen.this, Results.class);
							i.putExtra("gameName", gameName);
							i.putExtra("creator", creator);
							i.putExtra("PlayerID", PlayerID);
							startActivity(i);
							
							
							
					     }
						
						
					}
				

					private class resetDistances extends AsyncTask<String, Integer,String>{

						
						@Override
						protected String doInBackground(String... address) {
							// TODO Auto-generated method stub
							
							 try { 
						        	DefaultHttpClient httpClient = new DefaultHttpClient();
									URI url = new URI(address[0]);
									HttpGet httpGet = new HttpGet();
									httpGet.setURI(url);
									httpClient.execute(httpGet);
									Log.i("reset", address[0]);
									
								} catch (URISyntaxException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClientProtocolException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							 
							 return "";
						}
						
						
						
					}
				
				


}