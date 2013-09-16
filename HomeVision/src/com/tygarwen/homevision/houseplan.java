package com.tygarwen.homevision;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class houseplan extends Activity {

	ImageView groundf,firstf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	setContentView(R.layout.houseplan);
	
	groundf = (ImageView) findViewById(R.id.imageView1);
	firstf = (ImageView) findViewById(R.id.imageView2);
	groundf.setOnTouchListener(new OnTouchListener(){

		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			return false;
			
			
			
			
			
		}
		
	});

		
		
	}

}
