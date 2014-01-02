package com.webege.jumpingbeans.stopwatch;

public class LapsAndSplits {

	String Lap;
	String Split;
	
	LapsAndSplits(String lap, String split){
		Lap = lap;
		Split = split;
	}

	String getLap(){
		return Lap;
	}
	
	String getSplit(){
		return Split;
	}
}
