package com.webege.jumpingbeans.SammmySolver;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class Solver {

	
	String input;
	String output = "";
	StringTokenizer st;
	String[] tokenArray, left, right;
	ArrayList alXVariables = new ArrayList();
	ArrayList alConstants = new ArrayList();
	int equalsTerm = 100;
	Context c;
	double coefintx = 0;
	double coefintxsq = 0;
	double constant = 0;
	double valueOfX;
	boolean xsq = false;
	boolean xv = false;
	double xa=7;
	double xb=7;
	boolean checkforonlyxsquared = true;
	double valueOfXsquared,absvalueOfXsquared;
	String extratext = "";
	boolean checksin = false;
	boolean checkcos = false;
	double coefsin = 0;
	double coefsinsq = 0;
	double coefcos = 0;
	double coefcossq = 0;
	double coeftan = 0;
	double coeftansq = 0;
	ArrayList<Double> xvalues = new ArrayList();
	boolean xaneg = false;
	boolean xbneg = false;
	boolean degs;
	double asinxa=0;
	double	asinxb=0;
	

	Solver(String q) {
		output = q;
	}

	void input(String g,Context bcontext) {
		input = g;
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(bcontext);
		degs = getPrefs.getBoolean("units", true);
		
		
	}

	String output() {
		if (output.contentEquals("")) {
			return input;
		} else {
			return output;
		}
	}

	public String solve(Context context) {
		// creating the string splitter
		st = new StringTokenizer(input);
		createArray();
		populateArray();
		if (containsX()) {
			c = context;
			
			findEqualsTerm();
			if (equalsTerm == 100) {
				output = "there is no equals sign";
			} else {// if there are two sides to the equation
				if (tokenArray.length - equalsTerm - 1 == 0) {
					output = "it doesn't equal anything";

				} else {

					createLeftRightArrays();
					putInToLeftRight();
					
					leftSeparateX();
					rightSeparateX();
					// now have 2 arrays one with constants in one with x
					// variables in

					/*
					6cos²x + sinx - 4 = 0
					*/
					
					findConstant();
					findCoeffOfX();
					output = String.format("coeff x:%f coeff xsq:%f coeff sin:%f coeff sinsq:%f coeff cos:%f coeff cosq:%f coeff tan:%f coeff tansq:%f constant:%f ",
							coefintx, coefintxsq, coefsin ,coefsinsq,coefcos,coefcossq,coeftan,coeftansq, constant);
					
					if(containssinandcos()){
						if(coeftan == 0){
						//output = "there is botoh friend";
						
						if(hasInItJustSin()){
							//System.out.println("sinsq");
							coefsinsq =coefsinsq - coefcossq;
							constant = constant - coefcossq;
							solvequadratic(coefsinsq,coefsin,-constant);
							solvexvaluesfromsin();
							
						}else{
							
							coefcossq -= coefsinsq;
							constant -= coefsinsq;
							System.out.println(Double.toString(coefcossq));
							System.out.println(Double.toString(constant));
							solvequadratic(coefcossq,coefcos,-constant);
							solvexvaluesfromcos();
							
						}
						
					}else{
						output = "i can't quite cope with that";
					}
					}else if(coefsin != 0){
						output = "there inly sin";
						if(checkquadtrig("sin")){
							solvequadratic(coefsinsq,coefsin,-constant);
							solvexvaluesfromsin();
						}
					}else if(coefcos != 0){
						//output = "there is cos";
						if(checkquadtrig("cos")){
							solvequadratic(coefcossq,coefcos,-constant);
							solvexvaluesfromcos();
						}
					}else if(coeftan != 0){
						//output = "there is cos";
						if(checkquadtrig("tan")){
							solvequadratic(coeftansq,coeftan,-constant);
							solvexvaluesfromtan();
						}
					}else{
					/*	output = String.format("coeff x:%f coeff xsq:%f coeff sin:%f coeff sinsq:%f coeff cos:%f coeff cosq:%f constant:%f ",
							coefintx, coefintxsq, coefsin ,coefsinsq,coefcos,coefcossq, constant);*/
					
					
					
					
					//Toast.makeText(c, "x squared:"+Double.toString(coefintxsq)+"  X:"+Double.toString(coefintx) , Toast.LENGTH_SHORT).show();
					// check if it quadratic
					if (checkquad()) {
						//Toast.makeText(c, "is quad" , Toast.LENGTH_SHORT).show();
						
						solvequadratic(coefintxsq,coefintx,-constant);
						output = String.format("the value of x is %f or %f",xa, xb);
						
						Log.i("Debugging", "solved");
						//output = String.format("x can equal  %f or %f", xa, xb);
						
					} else {

						// check if it conatains sin

						
						findValueOfX();
						
					}
					}
					clearall();
				}
			}

		} else {
			output = "there is no x";
		}

		return output;
	}

	
	private void solvexvaluesfromtan() {
		// TODO Auto-generated method stub
xvalues = new ArrayList();
		
		//check if negitive
		if(xa<0){
			xaneg = true;
			xa = Math.abs(xa);
		}
		
		if(xb<0){
			xbneg = true;
			xb = Math.abs(xb);
		}
		//finding asin
		asinxa = Math.atan(xa);
		asinxb = Math.atan(xb);
		
		if(degs){
			asinxa = asinxa*180/Math.PI;
			asinxb = asinxb*180/Math.PI;
		}
		
		//finding all posible x values between 0 and 360
		//degs
				if(degs){
					Log.i("default", "degree");
					if(xaneg){
						xvalues.add(180+asinxa);
						xvalues.add(180-asinxa);
					}else{
						xvalues.add(360-asinxa);
						xvalues.add(asinxa);
					}
					
					if(xbneg){
						xvalues.add(180+asinxa);
						xvalues.add(180-asinxa);
					}else{
						xvalues.add(360-asinxa);
						xvalues.add(asinxa);
					}
				}else{//rads
					Log.i("default", "rad");
					if(xaneg){
						xvalues.add(Math.PI+asinxa);
						xvalues.add(Math.PI-asinxa);
					}else{
						xvalues.add(Math.PI*2-asinxa);
						xvalues.add(asinxa);
					}
					
					if(xbneg){
						xvalues.add(Math.PI+asinxa);
						xvalues.add(Math.PI-asinxa);
					}else{
						xvalues.add(Math.PI*2-asinxa);
						xvalues.add(asinxa);
					}
				}
		output = String.format("the values of x are: %f, %f, %f, %f", xvalues.get(0), xvalues.get(1), xvalues.get(2), xvalues.get(3));
		
		
	}

	private void solvexvaluesfromcos() {
		// TODO Auto-generated method stub
		xvalues = new ArrayList();
		
		//check if negitive
		if(xa<0){
			xaneg = true;
			xa = Math.abs(xa);
		}
		
		if(xb<0){
			xbneg = true;
			xb = Math.abs(xb);
		}
		//finding asin
		asinxa = Math.acos(xa);
		asinxb = Math.acos(xb);
		
		if(degs){
			asinxa = asinxa*180/Math.PI;
			asinxb = asinxb*180/Math.PI;
		}
		
		//finding all posible x values between 0 and 360
		
		//degs
		if(degs){
			Log.i("default", "degree");
			if(xaneg){
				xvalues.add(180+asinxa);
				xvalues.add(180-asinxa);
			}else{
				xvalues.add(360-asinxa);
				xvalues.add(asinxa);
			}
			
			if(xbneg){
				xvalues.add(180+asinxa);
				xvalues.add(180-asinxa);
			}else{
				xvalues.add(360-asinxa);
				xvalues.add(asinxa);
			}
		}else{//rads
			Log.i("default", "rad");
			if(xaneg){
				xvalues.add(Math.PI+asinxa);
				xvalues.add(Math.PI-asinxa);
			}else{
				xvalues.add(Math.PI*2-asinxa);
				xvalues.add(asinxa);
			}
			
			if(xbneg){
				xvalues.add(Math.PI+asinxa);
				xvalues.add(Math.PI-asinxa);
			}else{
				xvalues.add(Math.PI*2-asinxa);
				xvalues.add(asinxa);
			}
		}
		output = String.format("the values of x are: %f, %f, %f, %f", xvalues.get(0), xvalues.get(1), xvalues.get(2), xvalues.get(3));
		
		
	}

	private void solvexvaluesfromsin() {
		// TODO Auto-generated method stub
		xvalues = new ArrayList();
		//check if negitive
		if(xa<0){
			xaneg = true;
			xa = Math.abs(xa);
		}
		
		if(xb<0){
			xbneg = true;
			xb = Math.abs(xb);
		}
		//finding asin
		asinxa = Math.asin(xa);
		asinxb = Math.asin(xb);
		
		if(degs){
			asinxa = asinxa*180/Math.PI;
			asinxb = asinxb*180/Math.PI;
		}
		
		//finding all posible x values between 0 and 360
		//degs
				if(degs){
					Log.i("default", "degree");
					if(xaneg){
						xvalues.add(180+asinxa);
						xvalues.add(180-asinxa);
					}else{
						xvalues.add(360-asinxa);
						xvalues.add(asinxa);
					}
					
					if(xbneg){
						xvalues.add(180+asinxa);
						xvalues.add(180-asinxa);
					}else{
						xvalues.add(360-asinxa);
						xvalues.add(asinxa);
					}
				}else{//rads
					Log.i("default", "rad");
					if(xaneg){
						xvalues.add(Math.PI+asinxa);
						xvalues.add(Math.PI-asinxa);
					}else{
						xvalues.add(Math.PI*2-asinxa);
						xvalues.add(asinxa);
					}
					
					if(xbneg){
						xvalues.add(Math.PI+asinxa);
						xvalues.add(Math.PI-asinxa);
					}else{
						xvalues.add(Math.PI*2-asinxa);
						xvalues.add(asinxa);
					}
				}
		output = String.format("the values of x are: %f, %f, %f, %f", xvalues.get(0), xvalues.get(1), xvalues.get(2), xvalues.get(3));
		
		
	}

	private boolean hasInItJustSin() {
		// TODO Auto-generated method stub
		
		for(int x=0;x<tokenArray.length;x++){
			
			if (tokenArray[x].contains("sin") && tokenArray[x].contains(Character.toString((char) 178))==false) {
				return true;
			}
			
			
		}
		return false;
		
	}

	private boolean checkquadtrig(String string) {
		// TODO Auto-generated method stub
		for (int x = 0; x < left.length; x++) {
			if (left[x].contains(string + Character.toString((char) 178))) {
				xsq = true;
			}

			if (left[x].contains(Character.toString((char) 178)) == false && left[x].contains(string)) {
				xv = true;
			}
		}
		if (xsq && xv) {
			return true;
		} else {
			return false;
		}
	}

	private boolean containssinandcos() {
		// TODO Auto-generated method stub
	
		for(int x=0;x<tokenArray.length;x++){
			
				if (tokenArray[x].contains("sin")) {
					checksin = true;
				}

				if (tokenArray[x].contains("cos")) {
					checkcos = true;
				}
			}
			if (checksin && checkcos) {
				return true;
			} else {
				return false;
			}
			
		
	}



	private void solvequadratic(double a, double b,double cs) {
		// TODO Auto-generated method stub
		
		double tempa = 4 * a * cs;
		
		double tempb = b * b;
		double tempc = tempb - tempa;
		if (tempc <= 0) {
			output = "there are no real solutions";
		} else {
			double tempd = Math.sqrt(tempc);
			double tempe = 0 - b;
			double tempf = 2 * a;
			xa = (tempe + tempd) / tempf;
			xb = (tempe - tempd) / tempf;
			
			
			
		}
		
		
	}

	private boolean checkquad() {
		// TODO Auto-generated method stub

		/*for (int x = 0; x < left.length; x++) {
			if (left[x].contains(Character.toString((char) 178))) {
				xsq = true;
			}

			if (left[x].contains(Character.toString((char) 178)) == false && left[x].contains("x")) {
				xv = true;
			}
		}*/
		
		
		
		if (coefintx != 0 && coefintxsq != 0) {
			Log.i("Debugging", "true");
			return true;
		} else {Log.i("Debugging", "false");
			return false;
		}

	}

	private boolean containsX() {
		// TODO Auto-generated method stub
		for (int x = 0; x < tokenArray.length; x++) {
			if (tokenArray[x].contains("x")) {
				return true;
			}
		}
		return false;
	}

	private void clearall() {
		// TODO Auto-generated method stub
		alXVariables.clear();
		alConstants.clear();
		coefintx = 0;
		coefintxsq = 0;
		constant = 0;
		tokenArray = null;
		left = null;
		right = null;
		st = null;
		equalsTerm = 100;
		xsq = false;
		xv = false;
		checkforonlyxsquared = true;
		valueOfXsquared = 0;
		absvalueOfXsquared = 0;
		checksin = false;
		checkcos = false;
		coefsin = 0;
		coefsinsq = 0;
		coefcos = 0;
		coefcossq = 0;
		coeftan = 0;
		coeftansq = 0;
		xvalues = null;
		xaneg = false;
		xbneg = false;

		asinxa=0;
		asinxb=0;
		
	}

	private void createArray() {
		// TODO Auto-generated method stub
		tokenArray = new String[st.countTokens()];

	}

	private void populateArray() {
		// TODO Auto-generated method stub
		for (int x = 0; st.hasMoreTokens(); x++) {
			tokenArray[x] = st.nextToken();

		}

	}

	private void findEqualsTerm() {
		// TODO Auto-generated method stub
		for (int x = 0; x < tokenArray.length; x++) {
			if (tokenArray[x].contentEquals("=")) {
				equalsTerm = x;
				// Toast.makeText(c, "found =" , Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void createLeftRightArrays() {
		// TODO Auto-generated method stub
		left = new String[equalsTerm];
		right = new String[tokenArray.length - equalsTerm - 1];
		// Toast.makeText(c, Integer.toString(tokenArray.length - equalsTerm -1)
		// , Toast.LENGTH_SHORT).show();
	}

	private void putInToLeftRight() {
		// TODO Auto-generated method stub
		for (int x = 0; x < left.length; x++) {
			left[x] = tokenArray[x];
		}
		if (right.length != 0) {
			for (int x = 0; x < right.length; x++) {
				right[x] = tokenArray[x + left.length + 1];
			}
		}
		// Toast.makeText(c, "arrays filled"+left[0]+"   " +right[0],
		// Toast.LENGTH_SHORT).show();

	}

	private void leftSeparateX() {
		// TODO Auto-generated method stub
		for (int x = 0; x < left.length; x++) {
			if (left[x].contains("x") || left[x].contains("X")) {
				// //Toast.makeText(c, "x found" , Toast.LENGTH_SHORT).show();
				if (x == 0) {
					alXVariables.add("+");
				} else {
					alXVariables.add(left[x - 1]);
				}
				alXVariables.add(left[x]);
			} else if (left[x].contains("+") == false
					|| left[x].contains("-") == false) {

				if (x == 0 || left[x - 1].contentEquals("+")) {
					alConstants.add("-");
				} else {
					alConstants.add("+");
				}
				alConstants.add(left[x]);
			}
		}
		// Toast.makeText(c, "left separate" , Toast.LENGTH_SHORT).show();
	}

	private void rightSeparateX() {
		// TODO Auto-generated method stub
		for (int x = 0; x < right.length; x++) {
			// Toast.makeText(c, right[x] , Toast.LENGTH_SHORT).show();
			if (right[x].contains("x") || right[x].contains("X")) {

				if (x == 0 || right[x - 1].contentEquals("+")) {
					alXVariables.add("-");
				} else {
					alXVariables.add("+");
				}
				alXVariables.add(right[x]);

			} else {
				if (right[x].contains("+") == false
						&& right[x].contains("-") == false) {
					if (x == 0) {
						alConstants.add("+");
						// Toast.makeText(c, "first term",
						// Toast.LENGTH_SHORT).show();

					} else {
						alConstants.add(right[x - 1]);
						// Toast.makeText(c, "pronlem"+right[x-1] ,
						// Toast.LENGTH_SHORT).show();

					}
					alConstants.add(right[x]);

				} else {
					// Toast.makeText(c, "is a sign",
					// Toast.LENGTH_SHORT).show();
				}
			}
		}
		// Toast.makeText(c, "right separate" , Toast.LENGTH_SHORT).show();
	}

	private void findCoeffOfX() {
		// TODO Auto-generated method stub
int l = 0 ;
		String coefString = "";
		String temp;
		char bob;
		for (int x = 0; x < alXVariables.size(); x++) {
			coefString = "";
			Log.i("MyActivity", Integer.toString(l));l++;
			if (alXVariables.get(x).toString().contains("x")
					|| alXVariables.get(x).toString().contains("X")) {
				//if contains x

				if (alXVariables.get(x).toString().contains(Character.toString((char) 178)) && alXVariables.get(x).toString().contains("sin")==false && alXVariables.get(x).toString().contains("cos")==false && alXVariables.get(x).toString().contains("tan")==false) {
					Log.i("MyActivity", Integer.toString(l));l++;
					//if contains x squared
					Log.i("MyActivity", Integer.toString(l));l++;
					
					
					if (alXVariables.get(x).toString().length() < 3) {
						//if no coefficient
						coefString = "1";

					} else {
						//if has coefficient
						StringBuilder builder = new StringBuilder();

						for (int i = 0; i < (alXVariables.get(x).toString()
								.length() - 2); i++) {

							temp = alXVariables.get(x).toString();
							bob = temp.charAt(i);
							builder.append(bob);
							coefString = builder.toString();

						}
					
					}

					if (alXVariables.get(x).toString().contains("+")
							|| alXVariables.get(x).toString().contains("-")) {
						//not sure what this is for
					} else {
						if (alXVariables.get(x - 1).toString().contains("+")) {
							//checking sing before to see to add or minus
							coefintxsq += Double.parseDouble(coefString);
						} else if (alXVariables.get(x - 1).toString()
								.contains("-")) {
							coefintxsq -= Double.parseDouble(coefString);
						}

					}
					
					
					//end
					
					
				} else if(alXVariables.get(x).toString().contains("sin")){
					if(alXVariables.get(x).toString().contains("sin" + Character.toString((char) 178))){
						//contains sin squared
						Log.i("MyActivity", Integer.toString(l));l++;
						if (alXVariables.get(x).toString().length() < 6) {
							//if no coefficient
							coefString = "1";

						} else {
							//if has coefficient
							StringBuilder builder = new StringBuilder();

							for (int i = 0; i < (alXVariables.get(x).toString()
									.length() - 5); i++) {

								temp = alXVariables.get(x).toString();
								bob = temp.charAt(i);
								builder.append(bob);
								coefString = builder.toString();

							}
						
						}

						if (alXVariables.get(x).toString().contains("+")
								|| alXVariables.get(x).toString().contains("-")) {
							//not sure what this is for
						} else {
							if (alXVariables.get(x - 1).toString().contains("+")) {
								//checking sing before to see to add or minus
								coefsinsq += Double.parseDouble(coefString);
							} else if (alXVariables.get(x - 1).toString()
									.contains("-")) {
								coefsinsq -= Double.parseDouble(coefString);
							}

						}
						
					
						
					}else{
						//contains sin
						Log.i("MyActivity", Integer.toString(l));l++;
						if (alXVariables.get(x).toString().length() < 5) {
							//if no coefficient
							coefString = "1";

						} else {
							//if has coefficient
							StringBuilder builder = new StringBuilder();

							for (int i = 0; i < (alXVariables.get(x).toString()
									.length() - 4); i++) {

								temp = alXVariables.get(x).toString();
								bob = temp.charAt(i);
								builder.append(bob);
								coefString = builder.toString();

							}
						
						}

						if (alXVariables.get(x).toString().contains("+")
								|| alXVariables.get(x).toString().contains("-")) {
							//not sure what this is for
						} else {
							if (alXVariables.get(x - 1).toString().contains("+")) {
								//checking sing before to see to add or minus
								coefsin += Double.parseDouble(coefString);
							} else if (alXVariables.get(x - 1).toString()
									.contains("-")) {
								coefsin -= Double.parseDouble(coefString);
							}

						}
						
					}
					
					
					
					
					
				}else if(alXVariables.get(x).toString().contains("cos")){
					
					if(alXVariables.get(x).toString().contains("cos" + Character.toString((char) 178))){
						//contains cos squared
						Log.i("MyActivity", Integer.toString(l));l++;
						if (alXVariables.get(x).toString().length() < 6) {
							//if no coefficient
							coefString = "1";

						} else {
							//if has coefficient
							StringBuilder builder = new StringBuilder();

							for (int i = 0; i < (alXVariables.get(x).toString()
									.length() - 5); i++) {

								temp = alXVariables.get(x).toString();
								bob = temp.charAt(i);
								builder.append(bob);
								coefString = builder.toString();

							}
						
						}

						if (alXVariables.get(x).toString().contains("+")
								|| alXVariables.get(x).toString().contains("-")) {
							//not sure what this is for
						} else {
							if (alXVariables.get(x - 1).toString().contains("+")) {
								//checking sing before to see to add or minus
								coefcossq += Double.parseDouble(coefString);
							} else if (alXVariables.get(x - 1).toString()
									.contains("-")) {
								coefcossq -= Double.parseDouble(coefString);
							}

						}
						
					}else{
						//contains cos
						Log.i("MyActivity", Integer.toString(l));l++;
						if (alXVariables.get(x).toString().length() < 5) {
							//if no coefficient
							coefString = "1";

						} else {
							//if has coefficient
							StringBuilder builder = new StringBuilder();

							for (int i = 0; i < (alXVariables.get(x).toString()
									.length() - 4); i++) {

								temp = alXVariables.get(x).toString();
								bob = temp.charAt(i);
								builder.append(bob);
								coefString = builder.toString();

							}
						
						}

						if (alXVariables.get(x).toString().contains("+")
								|| alXVariables.get(x).toString().contains("-")) {
							//not sure what this is for
						} else {
							if (alXVariables.get(x - 1).toString().contains("+")) {
								//checking sing before to see to add or minus
								coefcos += Double.parseDouble(coefString);
							} else if (alXVariables.get(x - 1).toString()
									.contains("-")) {
								coefcos -= Double.parseDouble(coefString);
							}

						}
						
					}
					
					
					
				}else if(alXVariables.get(x).toString().contains("tan")){
					
					if(alXVariables.get(x).toString().contains("tan" + Character.toString((char) 178))){
						//contains tan squared
						Log.i("MyActivity", Integer.toString(l));l++;
						Log.i("MyActivity", Integer.toString(l));l++;
						if (alXVariables.get(x).toString().length() < 6) {
							//if no coefficient
							coefString = "1";

						} else {
							//if has coefficient
							StringBuilder builder = new StringBuilder();

							for (int i = 0; i < (alXVariables.get(x).toString()
									.length() - 5); i++) {

								temp = alXVariables.get(x).toString();
								bob = temp.charAt(i);
								builder.append(bob);
								coefString = builder.toString();

							}
						
						}

						if (alXVariables.get(x).toString().contains("+")
								|| alXVariables.get(x).toString().contains("-")) {
							//not sure what this is for
						} else {
							if (alXVariables.get(x - 1).toString().contains("+")) {
								//checking sing before to see to add or minus
								coeftansq += Double.parseDouble(coefString);
							} else if (alXVariables.get(x - 1).toString()
									.contains("-")) {
								coeftansq -= Double.parseDouble(coefString);
							}

						}
						
					}else{
						//contains tan
						Log.i("MyActivity", Integer.toString(l));l++;
						if (alXVariables.get(x).toString().length() < 5) {
							//if no coefficient
							coefString = "1";

						} else {
							//if has coefficient
							StringBuilder builder = new StringBuilder();

							for (int i = 0; i < (alXVariables.get(x).toString()
									.length() - 4); i++) {

								temp = alXVariables.get(x).toString();
								bob = temp.charAt(i);
								builder.append(bob);
								coefString = builder.toString();

							}
						
						}

						if (alXVariables.get(x).toString().contains("+")
								|| alXVariables.get(x).toString().contains("-")) {
							//not sure what this is for
						} else {
							if (alXVariables.get(x - 1).toString().contains("+")) {
								//checking sing before to see to add or minus
								coeftan += Double.parseDouble(coefString);
							} else if (alXVariables.get(x - 1).toString()
									.contains("-")) {
								coeftan -= Double.parseDouble(coefString);
							}

						}
						
					}
					
					
					
				}else{//must be just x
					checkforonlyxsquared = false;
					if (alXVariables.get(x).toString().length() < 2) {
						//check for no coeff
						coefString = "1";

					} else {
						StringBuilder builder = new StringBuilder();

						for (int i = 0; i < (alXVariables.get(x).toString()
								.length() - 1); i++) {

							temp = alXVariables.get(x).toString();
							bob = temp.charAt(i);
							builder.append(bob);
							coefString = builder.toString();

						}
						// Toast.makeText(c, coefString ,
						// Toast.LENGTH_SHORT).show();
					}

					if (alXVariables.get(x).toString().contains("+")
							|| alXVariables.get(x).toString().contains("-")) {
					} else {
						if (alXVariables.get(x - 1).toString().contains("+")) {
							coefintx += Double.parseDouble(coefString);
						} else if (alXVariables.get(x - 1).toString()
								.contains("-")) {
							coefintx -= Double.parseDouble(coefString);
						}

					}

				}
			}
		}
		// Toast.makeText(c, "found coeff" , Toast.LENGTH_SHORT).show();
		// output = Integer.toString(coefintx);

	}

	//@SuppressLint("ParserError")
	private void findConstant() {
		// TODO Auto-generated method stub
		for (int x = 0; x < alConstants.size(); x++) {

			// Toast.makeText(c, "," , Toast.LENGTH_SHORT).show();
			// Toast.makeText(c, alConstants.get(x).toString() ,
			// Toast.LENGTH_SHORT).show();

			if (alConstants.get(x).toString().contains("+")
					|| alConstants.get(x).toString().contains("-")) {
			} else {
				if (x == 0 || alConstants.get(x - 1).toString().contains("+")) {
					constant += Double.parseDouble(alConstants.get(x).toString());
				} else if (alConstants.get(x - 1).toString().contains("-")) {
					constant -= Double.parseDouble(alConstants.get(x).toString());
				}

			}

		}
		output = Double.toString(constant);
	}

	private void findValueOfX() {
		// TODO Auto-generated method stub
		
		if(checkforonlyxsquared){
			valueOfXsquared = constant / coefintxsq;
			if(valueOfXsquared<0){
				valueOfXsquared = Math.abs(valueOfXsquared);
				valueOfX = Math.sqrt(valueOfXsquared);
				extratext = "imaginary";
				
				
			}else{
			valueOfX = Math.sqrt(valueOfXsquared);
			extratext = Character.toString((char) 177);
			}
			
		}else{
			valueOfX = constant / coefintx;
		}
		output = String.format("the value of x is %s%f",extratext, valueOfX);
	}



}
