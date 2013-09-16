package com.tygarwen.homevision;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

//import org.apache.commons.net.telnet.TelnetClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class LandingTest extends Activity implements OnClickListener {

	
	//TelnetClient telnet = new TelnetClient();
	String server = "192.168.1.78";
	int port = 1034;
	InputStream in;
	PrintStream out;
	String password = "sendai1.";

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landing);
		ImageButton on, off;
		on = (ImageButton) findViewById(R.id.on);
		off = (ImageButton) findViewById(R.id.off);
		on.setOnClickListener(this);
		off.setOnClickListener(this);
		/*try {
			telnet.connect(server, port);
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readUntil("Password: ");
		*/write(password);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.off:
			Toast.makeText(getApplicationContext(), "lights on",
					Toast.LENGTH_SHORT).show();
			write(",O2F00\n");

			break;
		case R.id.on:
			Toast.makeText(getApplicationContext(), "lights off",
					Toast.LENGTH_SHORT).show();
			write(",O2D00\n");
			break;
		}
	}

	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			char ch = (char) in.read();
			while (true) {
				System.out.print(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void write(String value) {
		try {
			out.println(value);
			out.flush();
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}