package com.webege.jumpingbeans.SammmySolver;

import java.io.InputStream;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;




public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle x) {
		// TODO Auto-generated method stub
		super.onCreate(x);
		 setContentView(R.layout.splash);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Class ourClass;
					try {
						ourClass = Class.forName("com.webege.jumpingbeans.SammmySolver.MainActivity");
						Intent intent = new Intent(Splash.this,ourClass);
						startActivity(intent);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		timer.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	
}