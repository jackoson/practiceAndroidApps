package com.tygarwen.homevision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RoomMenu extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	Button Hall,Landing,Kitchen,Lounge,Bathroom,UpstairsBathroom,Bedroom;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roommenu);
        setup();
    }

	private void setup() {
		// TODO Auto-generated method stub
		Hall = (Button) findViewById(R.id.Hall);
		Landing = (Button) findViewById(R.id.Landing);
		Kitchen = (Button) findViewById(R.id.Kitchen);
		Lounge= (Button) findViewById(R.id.Lounge);
		Bathroom= (Button) findViewById(R.id.Bathroom);
		UpstairsBathroom= (Button) findViewById(R.id.UpstairsBathroom);
		Bedroom= (Button) findViewById(R.id.Bedroom);
		
		
		
		Hall.setOnClickListener(this);
		Landing.setOnClickListener(this);
		Kitchen.setOnClickListener(this);
		Lounge.setOnClickListener(this);
		Bathroom.setOnClickListener(this);
		UpstairsBathroom.setOnClickListener(this);
		Bedroom.setOnClickListener(this);
	}
		

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.Hall:
			Toast.makeText(getApplicationContext(), "Hall" , Toast.LENGTH_SHORT).show();
			try {
				Class ourClass = Class.forName("com.tygarwen.homevision.Hall");
				Intent intent = new Intent(RoomMenu.this, ourClass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case R.id.Landing:
			Toast.makeText(getApplicationContext(), "Landing" , Toast.LENGTH_SHORT).show();
			try {
				Class ourClass = Class.forName("com.tygarwen.homevision.Landing");
				Intent intent = new Intent(RoomMenu.this, ourClass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.Kitchen:
			Toast.makeText(getApplicationContext(), "Kitchen" , Toast.LENGTH_SHORT).show();
			try {
				Class ourClass = Class.forName("com.tygarwen.homevision.LandingTest");
				Intent intent = new Intent(RoomMenu.this, ourClass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.Lounge:
			Toast.makeText(getApplicationContext(), "Lounge" , Toast.LENGTH_SHORT).show();
			break;
		case R.id.Bathroom:
			Toast.makeText(getApplicationContext(), "Bathroom" , Toast.LENGTH_SHORT).show();
			break;
		case R.id.UpstairsBathroom:
			Toast.makeText(getApplicationContext(), "UpstairsBathroom" , Toast.LENGTH_SHORT).show();
			break;
		case R.id.Bedroom:
			Toast.makeText(getApplicationContext(), "Bedroom" , Toast.LENGTH_SHORT).show();
			break;
		
		}
	}
}