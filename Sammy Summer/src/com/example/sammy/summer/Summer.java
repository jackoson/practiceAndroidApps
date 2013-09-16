package com.example.sammy.summer;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.util.Log;


public class Summer {
	ArrayList<String> Terms = new ArrayList<String>();
	String Equation;
	double total = 0;
	int LowerLimit;
	int UpperLimit;
	String EquationNoX;
	boolean pro = true;
	String sectiontot;
	Calculator calc;
	
	public Summer(String equation, int lowerLimit, int upperLimit, int position ) {
		// TODO Auto-generated constructor stub
		Equation = equation;
		LowerLimit = lowerLimit;
		UpperLimit = upperLimit;
		Log.i("inof", "next sub in e");
				
		
		switch(position){
		case 0:
			Log.i("ff", "sum");
			if(Equation.contains("x")){
				Log.i("ff", "xxxxx");
				for(int x = lowerLimit; x<(upperLimit+1);x++){
					subinX(x);
					
					double sub=Double.parseDouble(calc.Calculate());
					total +=sub;
					Log.i("sum answer", Double.toString(sub));
					Log.i("sum answer", Double.toString(total));
				}
			}else{
				EquationNoX = Equation;
				subInMathValues();
				calc = new Calculator();
				calc.input(EquationNoX);
				sectiontot = calc.Calculate();
				total = Double.parseDouble(sectiontot);
				Log.i("sum answer", sectiontot);
				Log.i("sum answer", Double.toString(total));
			}
			break;
		case 1:
			Log.i("ff", "product");
			if(Equation.contains("x")){
				Log.i("ff", "xxxxx");
				for(int x = lowerLimit; x<(upperLimit+1);x++){
					
					Log.i("sum answer", Double.toString(total));
					subinX(x);
					if(pro){
						total+= Double.parseDouble(calc.Calculate());
						pro = false;
					}else{
						total = total*Double.parseDouble(calc.Calculate());
					}
				}
			}else{
				EquationNoX = Equation;
				subInMathValues();
				calc = new Calculator();
				calc.input(EquationNoX);
				if(pro){
					total+= Double.parseDouble(calc.Calculate());
					Log.i("sum answer", Double.toString(total));
					pro = false;
				}else{
					Log.i("sum answer", Double.toString(total));
					total = total*Double.parseDouble(calc.Calculate());
				}
			}
			break;
		case 2:
			if(Equation.contains("x")){
				total = 0.0;
			}else{
				Log.i("ff", "norm");
				EquationNoX = Equation;
				subInMathValues();
				 calc = new Calculator();
				calc.input(EquationNoX);
				Log.i("sum answer", Double.toString(total));
				total+= Double.parseDouble(calc.Calculate());
				Log.i("d", "not summed done");
			}
			break;
		}
		
		
			
		
	
		
			
		
		
		
		
	
	}
	
	
	
		
	
		
		
		
		
		
		
	
	
	
	
	
	
	private void subInMathValues() {
		Log.i("inof", Equation);
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(EquationNoX);		
		
		for (int x = 0; st.hasMoreTokens(); x++) {
			Terms.add(st.nextToken());
		}
		
		for (int x = 0; x < Terms.size(); x++) {
			if (Terms.get(x).toString().contains("e")){
				Terms.set(x, (Double.toString(Math.E)));
			}
		}	
		
		for (int x = 0; x < Terms.size(); x++) {
			if (Terms.get(x).toString().contains(Character.toString((char) 960))){
				Terms.set(x, (Double.toString(Math.PI)));
			}
		}	
			
		
		
		StringBuffer strBuff = new StringBuffer();
		
		for (int h = 0; h < Terms.size(); h++) {
			strBuff.append(Terms.get(h).toString());
			strBuff.append(" ");
		}	
		
		EquationNoX = strBuff.toString();
		Terms.clear();
		
	
	}




	private void subinX(int xValue) {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(Equation);		
		
		for (int x = 0; st.hasMoreTokens(); x++) {
			Terms.add(st.nextToken());
		}
		
		for (int x = 0; x < Terms.size(); x++) {
			if (Terms.get(x).toString().contains("x")){
				Terms.set(x, Integer.toString(xValue));
			}
		}	
		
		StringBuffer strBuff = new StringBuffer();
		
		for (int h = 0; h < Terms.size(); h++) {
			strBuff.append(Terms.get(h).toString());
			strBuff.append(" ");
		}	
		
		EquationNoX = strBuff.toString();
		
	System.out.println(EquationNoX);
	Terms.clear();
	subInMathValues();
	calc = new Calculator();
	calc.input(EquationNoX);
	Log.i("ff", EquationNoX );
	
	
	
	}




	public Double getAnswer() {
		// TODO Auto-generated method stub
		return total;
	}
	
	
}

