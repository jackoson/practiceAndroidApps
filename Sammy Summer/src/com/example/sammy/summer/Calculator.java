package com.example.sammy.summer;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.util.Log;


public class Calculator {
	String InputString = "";
	ArrayList<String> Terms = new ArrayList<String>();
	String output = "nothing";
	StringTokenizer st;
	int t = 0;
	int n = 0;
	
	void input(String g) {
		InputString = g;
	}
	
		
	
	String Calculate(){
		
	
		st = new StringTokenizer(InputString);		
		//put in to list
		for (int x = 0; st.hasMoreTokens(); x++) {
			Terms.add(st.nextToken());
		}
		
		//   4 + ( 4 + ( 7 * 1 ) ) 
		
		/*StringBuffer strBuff = new StringBuffer();
		for (int h = 0; h < Terms.size(); h++) {
			strBuff.append(Terms.get(h).toString());
			strBuff.append(" ");
		}	
	System.out.println(strBuff.toString());*/
		
		
		//For BRACKETS
				//run through terms
						for (int x = 0; x < Terms.size(); x++) {
							//if it is multiply
							//System.out.println(x + ":"+ Terms.get(x).toString());
							
							if (Terms.get(x).toString().contains(")")){
								
								
								ArrayList<String> TermsInBracket = new ArrayList<String>();
								int openingBracketPosition = 0;
								int q;
								
								for(q = x; Terms.get(q).toString().contains("(")== false; q--){
									openingBracketPosition = q; 
								}
								
								
								
								
								
								
								for (int d = openingBracketPosition ;d < x ; d++) {
									TermsInBracket.add(Terms.get(d).toString());
								}
								
								
								int NoOfTermsInBracket = TermsInBracket.size();
								
								
								Solve solve = new Solve();
								
								String bracketvalue = solve.SolveIt(TermsInBracket);
								
								
								String checkForFunction = Terms.get(q);
								
								if(checkForFunction.contains("sin")){
									bracketvalue = Double.toString(Math.sin(Double.parseDouble(bracketvalue)));
								}else if(checkForFunction.contains("cos")){
									bracketvalue = Double.toString(Math.cos(Double.parseDouble(bracketvalue)));
								}else if(checkForFunction.contains("tan")){
									bracketvalue = Double.toString(Math.tan(Double.parseDouble(bracketvalue)));
								}
								
								
								Terms.set(q,bracketvalue);
								
								for(int a = 0 ; a < NoOfTermsInBracket+1;a++){
									Terms.remove(q+1);
								}
								
								//System.out.println("bracket value: " + bracketvalue);
								solve = null;
								TermsInBracket = null;
								
								
								x = x - (NoOfTermsInBracket+1);
								
								
							}
							//System.out.println("f");
						}
		
		
		
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
		
						
		
		
		StringBuffer strBuf = new StringBuffer();
			for (int x = 0; x < Terms.size(); x++) {
				strBuf.append(Terms.get(x).toString());
				strBuf.append(" ");
			}	
		output = strBuf.toString();
		
		return output;
		
	}



	double factorial(double digit) {
		// TODO Auto-generated method stub
		if(digit<1){
			return 1.0;
		}
		double facttotal = digit;
		for(int xx = (int) (digit-1); xx>0; xx--){
			facttotal = facttotal*xx;
		}
		return facttotal;
	}
	
	
	
}
