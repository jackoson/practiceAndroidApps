package com.tygarwen.homevision;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import android.widget.Toast;

import java.io.PrintStream;
import java.net.Socket;


public class Hall extends Activity implements OnClickListener{

	Socket socket;
	
	
	private PrintStream out;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landing);
		ImageButton on,off;
		on = (ImageButton) findViewById(R.id.on);
		off= (ImageButton) findViewById(R.id.off);
		on.setOnClickListener(this);
		off.setOnClickListener(this);
		
		try {
			socket = new Socket("192.168.1.78", 1034);
			
			out = new PrintStream(socket.getOutputStream());
			
			out.println("sendai1.\r\n");
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.off:
			Toast.makeText(getApplicationContext(), "lights off" , Toast.LENGTH_SHORT).show();
			out.println(",O2900\r\n");
			out.flush();
			
			
			
			break;
		case R.id.on:
			Toast.makeText(getApplicationContext(), "lights on" , Toast.LENGTH_SHORT).show();
			
			out.println(",O2b00\r\n");
			out.flush();
			break;
		}
	}

}
