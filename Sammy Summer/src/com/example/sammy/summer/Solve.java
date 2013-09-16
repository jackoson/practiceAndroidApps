package com.example.sammy.summer;
import java.util.ArrayList;

import android.util.Log;


public class Solve {

	String SolveIt(ArrayList<String> Terms){
		
		
		

		//For POWERS
		//run through terms
				for (int x = 0; x < Terms.size(); x++) {
					
					String temp;
					char bob;
					String DigitString;
					Double digit;
					//if it is multiply
					if (Terms.get(x).toString().contains("^")){
						
						
						StringBuilder builder = new StringBuilder();

						double basedigit = Double.parseDouble(Terms.get(x-1).toString());
						double power = Double.parseDouble(Terms.get(x+1).toString());
						
						
						double calculated = Math.pow(basedigit, power);
						
						
						
						
						Terms.set(x-1, Double.toString(calculated));
							Terms.remove(x);
							Terms.remove(x);
						x--;
						
					}
				}					
		
					
				
				//for NCR
				
				for (int x = 0; x < Terms.size(); x++) {
					//if it is multiply
					if (Terms.get(x).toString().equals("C")){
						double first = Double.parseDouble(Terms.get(x-1).toString());
						double second = Double.parseDouble(Terms.get(x+1).toString());
						double temp = factorial(first);
						double tempb = factorial(second);
						double tempc = factorial(first-second);
						double bottom = tempb*tempc;
						double ncr = temp/bottom;
							
						Terms.set(x-1, Double.toString(ncr));
						Terms.remove(x+1);
						Terms.remove(x);	
						x--;
					}
				}

				
				
				
				
				
				
				
				
				
//FOR FACTORIALS
		//
				for (int x = 0; x < Terms.size(); x++) {
					Log.i("FACTORIALS", "EVERYLOOP");
					if (Terms.get(x).toString().contains("!")){
						Log.i("FACTORIALS", "IF CONTAINS !!");
						
						double coef = Double.parseDouble(Terms.get(x-1));
						Log.i("FACTORIALS", Double.toString(coef));
						
						Terms.set(x-1, Double.toString(factorial(coef)));
						
						Terms.remove(x);
						x--;
					}
					
					
				}
				
				
						
		//For DIVIDE
		//run through terms
				for (int x = 0; x < Terms.size(); x++) {
					//if it is multiply
					if (Terms.get(x).toString().contains("/")){
						double first = Double.parseDouble(Terms.get(x-1).toString());
						double second = Double.parseDouble(Terms.get(x+1).toString());
						double product = first/second;
						Terms.set(x-1, Double.toString(product));
						Terms.remove(x+1);
						Terms.remove(x);	
						x--;
					}
				}


		//FOR MULTIPLYING
		//run through terms
		for (int x = 0; x < Terms.size(); x++) {
			//if it is multiply
			if (Terms.get(x).toString().contains("*")){
				double first = Double.parseDouble(Terms.get(x-1).toString());
				double second = Double.parseDouble(Terms.get(x+1).toString());
				double product = first*second;
				Terms.set(x-1, Double.toString(product));
				Terms.remove(x+1);
				Terms.remove(x);	
				x--;
			}
		}


		//FOR ADDING
				//run through terms
				for (int x = 0; x < Terms.size(); x++) {
					//if it is multiply
					if (Terms.get(x).toString().contains("+")){
						double first = Double.parseDouble(Terms.get(x-1).toString());
						double second = Double.parseDouble(Terms.get(x+1).toString());
						double product = first+second;
						Terms.set(x-1, Double.toString(product));
						Terms.remove(x+1);
						Terms.remove(x);	
						x--;
					}
				}

		//FOR SUBTRACTING
		//run through terms
		for (int x = 0; x < Terms.size(); x++) {
			//if it is multiply
			if (Terms.get(x).toString().equals("-")){
				
								
				double first = Double.parseDouble(Terms.get(x-1).toString());
				double second = Double.parseDouble(Terms.get(x+1).toString());
				double product = first-second;
				Terms.set(x-1, Double.toString(product));
				Terms.remove(x+1);
				Terms.remove(x);	
				x--;
			}
		}	
		Log.i("d", Terms.get(0).toString());
		return Terms.get(0).toString();
		
	}
	
	double factorial(double digit) {
		// TODO Auto-generated method stub
		
		double facttotal = digit;
		for(int xx = (int) (digit-1); xx>0; xx--){
			facttotal = facttotal*xx;
		}
		return facttotal;
	}
}
