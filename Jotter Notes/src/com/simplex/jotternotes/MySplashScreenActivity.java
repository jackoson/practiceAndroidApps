package com.simplex.jotternotes;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MySplashScreenActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mysplashscreen);
		
		int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		TextView actionBarTextView = (TextView)findViewById(actionBarTitleId); 
		actionBarTextView.setTextColor(Color.rgb(63, 182, 182));
	
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		TextView tv3 = (TextView) findViewById(R.id.textView3);
		TextView tv4 = (TextView) findViewById(R.id.textView4);
		
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
		
		tv1.setTypeface(myTypeface);
		tv2.setTypeface(myTypeface);
		tv3.setTypeface(myTypeface);
		tv4.setTypeface(myTypeface);
		
		Thread logoTimer = new Thread() {
			
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 2500){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    startActivity(new Intent(MySplashScreenActivity.this, MainActivity.class));
                } 
                 
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 
                finally{
                    finish();
                }
            }
        };
         
        logoTimer.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.my_splash_screen, menu);
		return true;
	}
	

}
