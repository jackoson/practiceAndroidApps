package com.tygarwen.homevision;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inside extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.inside);
	        
	    }
		
	 public void lights(View v){
		 startActivity(new Intent(Inside.this, Lights.class));
	 }
	 
	 public void heating(View v){
		 //startActivity(new Intent(Inside.this, heating.class));
	 }
	
}
