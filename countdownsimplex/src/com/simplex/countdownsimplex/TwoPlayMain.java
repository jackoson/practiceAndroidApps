package com.simplex.countdownsimplex;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Math;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



public class TwoPlayMain extends Activity {

	//to make sure two numbers are not click consecutively
	private boolean ON = true;
	//randomly numbers that you use to make the target number
	private int number1;
	private int number2;
	private int number3;
	private int number4;
	private int number5;
	private int number6;
	//scores
	private int p1Score = 0;
	private int p2Score = 0;
	//strings of what the 6 buttons say at the moment
	private String string1;
	private String string2;
	private String string3;
	private String string4;
	private String string5;
	private String string6;
	//strings of what the 6 buttons said at beginning of round
	private String originalString1;
	private String originalString2;
	private String originalString3;
	private String originalString4;
	private String originalString5;
	private String originalString6;
	//variable to stop you using the same number twice.
	private boolean num1Clicked = false;
	private boolean num2Clicked = false;
	private boolean num3Clicked = false;
	private boolean num4Clicked = false;
	private boolean num5Clicked = false;
	private boolean num6Clicked = false;
	
	private String temp = " = ";
	//to stop you adding two numbers with no opperator
	//0 means number was not the last thing to be pressed
	//it gets set to odd when you press a nmber
	//it gets set to even (0) when you press an operator or when it is reset 
	private int count = 0;
	//true if count is even
	private boolean proceed = true;
	//another error handling variable -only allows you to enter 2 variables in total. but is made redundent really by other proccesses
	private int termCount = 0;
	//stores the two values ready for a calculation.
	private int theNum1 = 0;
	private int theNum2 = 0;
	//answer to the sum
	private int ans;
	//target answer
	private int theAns;
	
	private String operatorType = "";
	//used to hold numbers in calculation so if cleared can be put back
	private String temp1 = "";
	private String temp2 = "";
	// used to decide who to store the score as when you press done.
	private boolean p1Go = true;
	//player 1 and 2 closest number to target in a string and integer
	String theirAns1;
	String theirAns2;
	int theirNum1;
	int theirNum2;
	//calculated integer of how far their value was from the target (with sign)
	int distanceAway1;
	int distanceAway2;
	//absolute value of above
	int d1;
	int d2;
	
	//to check if it is the first go through - to do with starting timer
	boolean firsttime = true;
	
	//TIMING VARIABLES
	//time they have to make the target number
	int totaltime = 60;//seconds
	//current time
	int currentTime;
	//updateinterval of timer
	int updateinterval = 1000;//milliseconds
	
	//should timer carry on.
	boolean timmergo =true;
	
	//handles the repeated calling of the timer updater
	private Handler mHandler = new Handler();
	
	//saves your steps for sharing to facebook
	String steps = "";
	int target;
	
	int[] nums;
	String code;
	
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
	TextView display3;

	TextView countdown;

	@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two_play_main);
		
		//set up buttons and text fields references.
		
		
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
		//reset = (Button) findViewById(R.id.reset);
		nextGame = (Button) findViewById(R.id.nextGame);
		display1 = (TextView) findViewById(R.id.display1);
		display2 = (TextView) findViewById(R.id.display2);
		display3 = (TextView) findViewById(R.id.display3);
		 
/*
		num1 = (Button) findViewById(R.id.button1);
		num2 = (Button) findViewById(R.id.button1);
		num3 = (Button) findViewById(R.id.button1);
		num4 = (Button) findViewById(R.id.button1);
		num5 = (Button) findViewById(R.id.button1);
		num6 = (Button) findViewById(R.id.button1);
		equals = (Button) findViewById(R.id.button1);
		add = (Button) findViewById(R.id.button1);
		times = (Button) findViewById(R.id.button1);
		minus = (Button) findViewById(R.id.button1);
		divide = (Button) findViewById(R.id.button1);
		clear = (Button) findViewById(R.id.button1);
		reset = (Button) findViewById(R.id.button1);
		nextGame = (Button) findViewById(R.id.button1);
		display1 = (TextView) findViewById(R.id.textView1);
		display2 = (TextView) findViewById(R.id.textView1);
		display3 = (TextView) findViewById(R.id.textView1);*/

		countdown = (TextView) findViewById(R.id.countdown);
		
		//fix for online stuff
		if(Build.VERSION.SDK_INT>10){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		//start whole thing off
		newNums();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void num1Clicked(View view) {
		//check if a number can be enntered eg. not if another number has just been clicked
		if (ON) {
			//check if you have already used that value
			if (num1Clicked != true) {
				//makes for example string1 = to what is on the button
				updateStrings();
				//check if numbers have been entered with out operator
				checkCount();
				//check if 2 numbers have already been entered
				checkTermCount();
				//checks boolean as above
				if (proceed != false) {
					//add number on button to the sum
					display2.append(string1);
					//delete the number from the button
					num1.setText("-");
					//change variable so button cannot be clicked again
					num1Clicked = true;
					//if this is the second number
					if (theNum1 != 0) {
						//check a operator has been clicked
						if (ON) {
							//set the second calculation number to what was on the button
							theNum2 = Integer.parseInt(string1);
							//store what was on the button to a temp variable. so if calulation cleared then the value can be returned
							temp2 = string1;
						}
					} else {//if this is first number
						//set variable so a number cannot be click straight after
						ON = false;
						//set the second calculation number to what was on the button
						theNum1 = Integer.parseInt(string1);
						//store what was on the button to a temp variable. so if calulation cleared then the value can be returned
						temp1 = string1;
					}
					//set variable to say a number has just been clicked
					count++;
					//add one to the int counting the number variables in the equation
					termCount++;
				} else {
					//do nothing
				}
			}
		}
	}
	public void num2Clicked(View view) {
		
		if (ON) {
			if (num2Clicked != true) {
				updateStrings();
				checkCount();
				checkTermCount();
				if (proceed != false) {
					display2.append(string2);
					num2.setText("-");
					num2Clicked = true;
					if (theNum1 != 0) {
						if (ON) {
							theNum2 = Integer.parseInt(string2);
							temp2 = string2;
						}
					} else {
						ON = false;
						theNum1 = Integer.parseInt(string2);
						temp1 = string2;
					}
					count++;
					termCount++;
				} else {
				}
			}
		}
	}
	public void num3Clicked(View view) {
		if (ON) {
			if (num3Clicked != true) {
				updateStrings();
				checkCount();
				checkTermCount();
				if (proceed != false) {
					display2.append(string3);
					num3.setText("-");
					num3Clicked = true;
					if (theNum1 != 0) {
						if (ON) {
							theNum2 = Integer.parseInt(string3);
							temp2 = string3;
						}
					} else {
						ON = false;
						theNum1 = Integer.parseInt(string3);
						temp1 = string3;
					}
					count++;
					termCount++;
				} else {
				}
			} else {

			}
		}
	}
	public void num4Clicked(View view) {
		if (ON) {
			if (num4Clicked != true) {
				updateStrings();
				checkCount();
				checkTermCount();
				if (proceed != false) {
					display2.append(string4);
					num4.setText("-");
					num4Clicked = true;
					if (theNum1 != 0) {

						theNum2 = Integer.parseInt(string4);
						temp2 = string4;

					} else {
						ON = false;
						theNum1 = Integer.parseInt(string4);
						temp1 = string4;
					}
					count++;
					termCount++;
				} else {
				}
			} else {

			}
		}
	}
	public void num5Clicked(View view) {
		if (ON) {
			if (num5Clicked != true) {
				updateStrings();
				checkCount();
				checkTermCount();
				if (proceed != false) {
					display2.append(string5);
					num5.setText("-");
					num5Clicked = true;
					if (theNum1 != 0) {

						theNum2 = Integer.parseInt(string5);
						temp2 = string5;

					} else {
						ON = false;
						theNum1 = Integer.parseInt(string5);
						temp1 = string5;
					}
					count++;
					termCount++;
				} else {
				}
			} else {

			}
		}
	}
	public void num6Clicked(View view) {
		if (ON) {
			if (num6Clicked != true) {
				updateStrings();
				checkCount();
				checkTermCount();
				if (proceed != false) {
					display2.append(string6);
					num6.setText("-");
					num6Clicked = true;
					if (theNum1 != 0) {
						if (ON) {
							theNum2 = Integer.parseInt(string6);
							temp2 = string6;
						}
					} else {
						ON = false;
						theNum1 = Integer.parseInt(string6);
						temp1 = string6;
					}
					count++;
					termCount++;
				} else {
				}
			} else {

			}
		}
	}

	public void add(View view) {
		//makes for example string1 = to what is on the button. - i don't think this is needed
		updateStrings();
		//check that only the first number is entered
		if (theNum1 != 0 && theNum2 == 0) {
			//making sure there is no operator already
			if (operatorType.length() > 0) {
				//display warning that there is already an operator
				Toast.makeText(getApplicationContext(),
						"Operator already present", Toast.LENGTH_SHORT).show();
			} else {//if there is NO operator
				//add the operator to the string
				display2.append(" + ");
				//allow next number to be pressed - not sure if needed
				proceed = true;
				//allow next number to be pressed
				count = 0;
				//stores the operator used
				operatorType = "add";
				//allow next number to be pressed MOST RECENT
				ON = true;
			}
		} else {
			//display warning to enter an number first
			Toast.makeText(getApplicationContext(),
					"Enter a term before using an operator", Toast.LENGTH_SHORT)
					.show();
		}
	}
	public void minus(View view) {
		updateStrings();
		if (theNum1 != 0 && theNum2 == 0) {
			if (operatorType.length() > 0) {
				Toast.makeText(getApplicationContext(),
						"Operator already present", Toast.LENGTH_SHORT).show();
			} else {
				display2.append(" − ");
				proceed = true;
				count = 0;
				operatorType = "minus";
				ON = true;
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"Enter a term before using an operator", Toast.LENGTH_SHORT)
					.show();
		}
	}
	public void divide(View view) {
		updateStrings();
		if (theNum1 != 0 && theNum2 == 0) {
			if (operatorType.length() > 0) {
				Toast.makeText(getApplicationContext(),
						"Operator already present", Toast.LENGTH_SHORT).show();
			} else {
				display2.append(" ÷ ");
				proceed = true;
				count = 0;
				operatorType = "divide";
				ON = true;
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"Enter a term before using an operator", Toast.LENGTH_SHORT)
					.show();
		}

	}
	public void times(View view) {
		updateStrings();
		if (theNum1 != 0 && theNum2 == 0) {
			if (operatorType.length() > 0) {
				Toast.makeText(getApplicationContext(),
						"Operator already present", Toast.LENGTH_SHORT).show();
			} else {
				display2.append(" × ");
				proceed = true;
				count = 0;
				ON = true;
				operatorType = "times";
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"Enter a term before using an operator", Toast.LENGTH_SHORT)
					.show();
		}
	}

	public void nextGame(View view) {//on click of done button and of timer end
		
		
		//THIS REPLACES WHAT IS COMMENTED OUT BELLOW TO DEAL WITH TIMER
		
		//stops the time
		timmergo=false;
		
		//if sum is in calculation textview
		if (termCount == 2 && operatorType != "") {
			equals(null);
		}else if(theNum1!=0){//if it just has a number in it
			//put back on to a button
			String calc = Integer.toString(theirNum1);
			 
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
		calcRandNum();
		
		
		/*
		//clear the calulation variables again
		temp1 = "";
		temp2 = "";
		//making sure no calculation is taking place
		if (operatorType != "") {
			//warning
			Toast.makeText(getApplicationContext(), "Operator present",
					Toast.LENGTH_SHORT).show();
		} else {
			operatorType = "";
			//store the score and swap player
			calcRandNum();
		}*/
	}

	public void reset(View view) {//on click resets buttons and temp variables back to start
		
		
		
		
		//clears calculation screen
		display2.setText("");
		//resets buttons back to start
		num1.setText(originalString1);
		num2.setText(originalString2);
		num3.setText(originalString3);
		num4.setText(originalString4);
		num5.setText(originalString5);
		num6.setText(originalString6);
		
		//sets temp vars back to start
		string1 = originalString1;
		string2 = originalString2;
		string3 = originalString3;
		string4 = originalString4;
		string5 = originalString5;
		string6 = originalString6;
		
		//makes everything clickable again
		num1Clicked = false;
		num2Clicked = false;
		num3Clicked = false;
		num4Clicked = false;
		num5Clicked = false;
		num6Clicked = false;
		
		//resets the rest of the variables
		count = 0;
		proceed = true;
		termCount = 0;
		theNum1 = 0;
		theNum2 = 0;
		temp1 = "";
		temp2 = "";
		ON = true;
		updateStrings();
		operatorType = "";
		steps = "";
		
		
		
	}

	public void equals(View view) {
		//if all three compounents to sum are ready 
		if (termCount == 2 && operatorType != "") {
			
				
			
			
			//if the operator used is add 
			if (operatorType == "add") {
				//add the two numbers together to get the answer to the sum
				ans = theNum1 + theNum2;
				//reset the two temp variables for one and two
				temp1 = "";
				temp2 = "";
				//put the answer to the sum back in one of the buttons.
				setButton();
			}

			if (operatorType == "minus") {
				ans = theNum1 - theNum2;
				temp1 = "";
				temp2 = "";
				setButton();
			}

			if (operatorType == "times") {
				ans = theNum1 * theNum2;
				temp1 = "";
				temp2 = "";
				setButton();
			}

			if (operatorType == "divide") {
				ans = theNum1 / theNum2;
				temp1 = "";
				temp2 = "";
				setButton();
			}

			//if player 2's go
			if(p1Go==false){
				//add calculation to sharing string
				steps = steps + display2.getText().toString() + " = "+ans+"\n";		
			}
			
			if (theAns == ans) {
				operatorType = "";
				display2.setText("");
				temp1 = "";
				temp2 = "";
			}
			
			

			display2.setText("");
			theNum1 = 0;
			theNum2 = 0;
			ON = true;
		} else {
			//warn it is not complete
			Toast.makeText(getApplicationContext(), "Incomplete sum",
					Toast.LENGTH_SHORT).show();
		}

		updateStrings();
	}

	public void setButton() {//putting the answer to the sum back on a button
		//clear the operator used - strange place to put it
		operatorType = "";
		//checking for a free button (containing a dash) 
		//and putting the answer on the first one it finds.
		//allowing that button to be clicked
		if (num1.getText() == "-") {
			num1.setText("" + ans);
			num1Clicked = false;
		} else if (num2.getText() == "-") {
			num2.setText("" + ans);
			num2Clicked = false;
		} else if (num3.getText() == "-") {
			num3.setText("" + ans);
			num3Clicked = false;
		} else if (num4.getText() == "-") {
			num4.setText("" + ans);
			num4Clicked = false;
		} else if (num5.getText() == "-") {
			num5.setText("" + ans);
			num5Clicked = false;
		} else if (num6.getText() == "-") {
			num6.setText("" + ans);
			num6Clicked = false;
		}
		//reseting the vairables counting how many variables and operarotrs are entered.
		termCount = 0;
		count = 0;
	}

	public void calcRandNum() {//store the score and swap player
		
		//if it is player one's go
		if (p1Go == true) {
			//check if their number is in the text field
			if (display2.getText().toString().length() == 0) {
				//tell them to put it there
				Toast.makeText(getApplicationContext(),
						"Click Your Number Before Clicking Done",
						Toast.LENGTH_SHORT).show();
			} else {
				//store their number as a string and integer
				theirAns1 = display2.getText().toString();
				theirNum1 = Integer.parseInt(theirAns1);
				//calculate distance away
				distanceAway1 = theAns - theirNum1;
				//calculate absoolute value
				d1 = Math.abs(distanceAway1);
				//tell them how close they were or how far!!!
				Toast.makeText(getApplicationContext(),
						"You got " + d1 + " away!", Toast.LENGTH_SHORT).show();
				//change it to player 2's go
				p1Go = false;
				//tell the user it is player 2's go
				display3.setText("Player 2's Go!");
				
				reset2();
			}
		}else{//if it is player two's go
			//check if their number is in the text field
			if (display2.getText().toString().length() == 0) {
				//tell them to put it there
				Toast.makeText(getApplicationContext(),
						"Click Your Number Before Clicking Done",
						Toast.LENGTH_SHORT).show();
			} else {
				//store their number as a string and integer
				theirAns2 = display2.getText().toString();
				theirNum2 = Integer.parseInt(theirAns2);
				//calculate distance away
				distanceAway2 = theAns - theirNum2;
				//calculate absolute value
				d2 = Math.abs(distanceAway2);
				//tell them how close they were or how far!!!
				Toast.makeText(getApplicationContext(),
						"You got " + d2 + " away!", Toast.LENGTH_SHORT).show();
				
				//CHANGE SCORES
				//if player one is closer (abs distance is smaller)
				if (d2 > d1) {
					//give him a point
					p1Score++;
					//visa versa
				} else if (d1 > d2) {
					p2Score++;
					
					//if same
				} else if (d1 == d2) {
					//do nothing
				}
				
				//make it player 1's go again.
				p1Go = true;
				//let players know
				display3.setText("Player 1's Go!");
				
				//let multiplayer know its over
				clearserver(code);	
				
				//start whole ting again but doesn't delete running score
				newNums();
				
				//put up the running scores
				showScore();
				//unessessary 
				ON = true;
			}

		}
	}

	

	public void updateStrings() {//sets what is on the button to some temporary variables
		
		//put what is on the button in to temp variables
		string1 = num1.getText().toString();
		string2 = num2.getText().toString();
		string3 = num3.getText().toString();
		string4 = num4.getText().toString();
		string5 = num5.getText().toString();
		string6 = num6.getText().toString();
	}

	public void checkCount() {// sets the proceed (can enter a number) variable according to if a number has just been pressed 
		if (count % 2 == 1) {
			proceed = false;
		} else {
			proceed = true;
		}
	}

	public void checkTermCount() {//// sets the proceed (can enter a number) variable according to if 2 numbers have already been entered
		if (termCount == 2) {
			proceed = false;
		} else {
			proceed = true;
		}
	}
	
	
	/*
	* public boolean onOptionsItemSelected(MenuItem item){ switch
	 * (item.getItemId()){
	 * 
	 * case android.R.id.home: startActivity(new Intent(TwoPlayMain.this,
	 * WelcomeScreen.class)); break; } return false;
	 * 
	 * }
	 */

	
	public void clear(View view) {
		
		if (temp1.length() > 0) {
			if (num1.getText() == "-") {
				num1.setText(temp1);
				num1Clicked = false;
			} else if (num2.getText() == "-") {
				num2.setText(temp1);
				num2Clicked = false;
			} else if (num3.getText() == "-") {
				num3.setText(temp1);
				num3Clicked = false;
			} else if (num4.getText() == "-") {
				num4.setText(temp1);
				num4Clicked = false;
			} else if (num5.getText() == "-") {
				num5.setText(temp1);
				num5Clicked = false;
			} else if (num6.getText() == "-") {
				num6.setText(temp1);
				num6Clicked = false;
			}
			termCount = 0;
			count = 0;
			ON = true;

			if (temp2.length() > 0) {
				if (num1.getText() == "-") {
					num1.setText(temp2);
					num1Clicked = false;
				} else if (num2.getText() == "-") {
					num2.setText(temp2);
					num2Clicked = false;
				} else if (num3.getText() == "-") {
					num3.setText(temp2);
					num3Clicked = false;
				} else if (num4.getText() == "-") {
					num4.setText(temp2);
					num4Clicked = false;
				} else if (num5.getText() == "-") {
					num5.setText(temp2);
					num5Clicked = false;
				} else if (num6.getText() == "-") {
					num6.setText(temp2);
					num6Clicked = false;
				}
				termCount = 0;
				count = 0;
				ON = true;
			}

			count = 0;
			temp1 = "";
			temp2 = "";
			theNum1 = 0;
			theNum2 = 0;
			termCount = 0;
			operatorType = "";
			checkCount();
			checkTermCount();
			updateStrings();
			display2.setText("");
			ON = true;
		}

	}

	public void newNums() {//starts whole thing off (creates target number and 6 numbers to use setting them to screen)
		Random rand = new Random();
		//for calculations
		//stores which operator used
		operatorType = "";
		//stores the two numbers to use
		temp1 = "";
		temp2 = "";
	/*	//makes a random number for target
		theAns = rand.nextInt(900) + 100;
		
		//make 6 random numbers to use
		number1 = rand.nextInt(9) + 2;
		number2 = rand.nextInt(9) + 2;
		number3 = rand.nextInt(9) + 2;
		number4 = rand.nextInt(9) + 2;
		number5 = rand.nextInt(9) + 2;
		number6 = rand.nextInt(9) + 2;
		
		//make sure they are not the same - keep making new ones until they are different
		while (number1 == number2 || number1 == number3 || number1 == number4
				|| number1 == number5 || number1 == number6
				|| number2 == number3 || number2 == number4
				|| number2 == number5 || number2 == number6
				|| number3 == number4 || number3 == number5
				|| number3 == number6 || number4 == number5
				|| number4 == number6 || number5 == number6) {
			number2 = rand.nextInt(9) + 2;
			number3 = rand.nextInt(9) + 2;
			number4 = rand.nextInt(9) + 2;
			number5 = rand.nextInt(9) + 2;
			number6 = rand.nextInt(9) + 2;
		}*/
		
		
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("KeyCode");
		alert.setMessage("Either enter a new code to start a match or enter a friends to join their game");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  code =  input.getText().toString();
		  
		  getonlinenumbers(code);
		  timmergo = true;
		//start new timer for new player
			mHandler.removeCallbacks(startTimer);
			//starts it immediately
	        mHandler.postDelayed(startTimer, 0);
		  getnums();
		  
//		  nums[0] = 2;
//		  nums[1] = 2;
//		  nums[2] = 2;
//		  nums[3] = 2;
//		  nums[4] = 2;
//		  nums[5] = 2;
//		  nums[6] = 2;
		  
		  }

		
		});

		

		alert.show();
		
	}
	
	private void clearserver(String code2) {
		// TODO Auto-generated method stub
		try { 
        	DefaultHttpClient httpClient = new DefaultHttpClient();
			URI url = new URI("http://www.jumpingbeans.webege.com/solved.php?code="+code);
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public void getnums(){
		
		
		
		number1 = nums[0];
		number2 = nums[1];
		number3 = nums[2];
		number4 = nums[3];
		number5 = nums[4];
		number6 = nums[5];
		theAns = nums[6];
		
		//display target on screen
		display1.setText("" + theAns);
		
		//variables to remember the values for each of 6 buttons
		originalString1 = Integer.toString(number1);
		originalString2 = Integer.toString(number2);
		originalString3 = Integer.toString(number3);
		originalString4 = Integer.toString(number4);
		originalString5 = Integer.toString(number5);
		originalString6 = Integer.toString(number6);

		//set up temp strings
		string1 = originalString1;
		string2 = originalString2;
		string3 = originalString3;
		string4 = originalString4;
		string5 = originalString5;
		string6 = originalString6;

		// set the numbers to each button
		num1.setText(string1);
		num2.setText(string2);
		num3.setText(string3);
		num4.setText(string4);
		num5.setText(string5);
		num6.setText(string6);
		
		//set up if button click variables to make sure the same button cannot be clicked twice
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
		
		//make sure working box is empty
		display2.setText("");
		
		//reset both players answers
		theirAns1 = "";
		theirAns2 = "";
		theirNum1 = 0;
		theirNum2 = 0;
		distanceAway1 = 0;
		distanceAway2 = 0;
		d1 = 0;
		d2 = 0;
		
		//set up variable that means you can press a number - to stop you pressing two numbers aftr each other.
		ON = true;
		
		//starts the timer
		//sets current time to the time limmit
		currentTime = totaltime;
		//clears the timer
		//mHandler.removeCallbacks(startTimer);
		
		
		
		//if firstime through
		if(firsttime){
			
			mHandler.removeCallbacks(startTimer);
			//starts it immediately
	        mHandler.postDelayed(startTimer, 0);
	        //set it to false
	        firsttime = false;
		}
		
	}

	private void getonlinenumbers(String code) {
		// TODO Auto-generated method stub
		
		
		
		BufferedReader br = null;
        InputStream is = null;;
        
       
        int[] numbers = new int[7];
        
        
        
        
       
        try { 
        	DefaultHttpClient httpClient = new DefaultHttpClient();
			URI url = new URI("http://www.jumpingbeans.webege.com/test.php?code="+code);
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			
			String line = "";
			for(int x = 0; x<7;x++){
				line = br.readLine();
				
				numbers[x] = Integer.parseInt(line);
				
			}
			br.close();
			
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
        
		
		
		nums = numbers;
		
	}

	public void reset2() {//reset variables for player 2's go
		//clear calculation text box again
		display2.setText("");
		//reset the numbers to what player one had
		num1.setText(originalString1);
		num2.setText(originalString2);
		num3.setText(originalString3);
		num4.setText(originalString4);
		num5.setText(originalString5);
		num6.setText(originalString6);

		//update the strings to original numbers
		string1 = originalString1;
		string2 = originalString2;
		string3 = originalString3;
		string4 = originalString4;
		string5 = originalString5;
		string6 = originalString6;

		//allow all numbers to be pressed again
		num1Clicked = false;
		num2Clicked = false;
		num3Clicked = false;
		num4Clicked = false;
		num5Clicked = false;
		num6Clicked = false;

		//reset rest of the variables
		count = 0;
		proceed = true;
		termCount = 0;
		theNum1 = 0;
		theNum2 = 0;
		temp1 = "";
		temp2 = "";
		operatorType = "";
		ON = true;

		//unessessarly sync string values with buttons
		updateStrings();
		
		//incase it has magically become player 1s go again	change it back	
		p1Go = false;
		
				//starts the timer
				//sets current time to the time limmit
				currentTime = totaltime;
				timmergo=true;
				
		        
		
		
	}

	public void showScore() {//pop up of running scores
		
		//store target for sharing before it's deleted 
		target = theAns;
		
		//store distance for sharing before it's deleted 
		final int distance = d2;
		
		//making a new pop up
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		//set content
		dialogBuilder.setTitle("Score");
		dialogBuilder.setMessage("Trying to make: "+theAns+" \nPlayer 1 was " + d1 + " away \n"
				+ "Player 2 was " + d2 + " away \n" + "Player 1: " + p1Score
				+ "\n" + "Player 2: " + p2Score+"\nShare:\n"+steps);
		//make okay button
		dialogBuilder.setPositiveButton("Okay",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						
				       
				       
					}
				});
		
		dialogBuilder.setNeutralButton("Share", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				
				//open a new dialogue so after you have shared you can carry on playing.
				showScore();
				
				//make string to share
				String shareString = String.format("I've been playing countdown on my android. Gary would be proud! I was trying to make %d and i got %d away by \n%s", target, distance , steps);
				
				Uri uri = Uri.parse("file:///sdcard/dcim/.thumbnails/10-31B8A338-1605-800.jpg");
				
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				
				//shareIntent.setType("text/plain");
				
				shareIntent.setType("*/*");
				
				shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
				shareIntent.putExtra(Intent.EXTRA_TEXT, shareString);
				
				startActivity(Intent.createChooser(shareIntent, "Show off you Epic-ness at countdown with..."));
		       
				steps = "";
				
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
	}
	
	void updateTimer(){//takes away 1 second and then update the clock.
		//write seconds to screen
		countdown.setText(Integer.toString(currentTime)+"s");
		//check if time is zero
		if(currentTime <1){
			//end users go
			mHandler.removeCallbacks(startTimer);
			nextGame(nextGame);
		}else{
			//countdown one second
			currentTime--;
		}
		
		
	}

	private Runnable startTimer = new Runnable() {//timer proccess - runs continuously
		   public void run() {
			   //count down the time
			   updateTimer();
			   //if it is not paused
			   if(timmergo)
			   mHandler.postDelayed(this,updateinterval);//repeat again after a second
			}
		};

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		//stops timer when the app closes
		mHandler.removeCallbacks(startTimer);
		timmergo = false;
		
		super.onPause();
	}
		
		
}
