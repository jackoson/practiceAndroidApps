package com.example.telnety;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import org.apache.commons.net.telnet.TelnetClient;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	TextView text;
	EditText edit1, edit2, edit3, edit4;
	Editable server, username, password, command;
	private String USER = null;
	private String PASS = null;
	private String CMD = null;
	private String host = null;
	private int port = 23;
	private TelnetClient telnet;
	private InputStream in;
	private PrintStream out;
	StringBuffer sb;
	Handler mHandler = new Handler();
	int len;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		edit1 = (EditText) findViewById(R.id.edit1);
		edit2 = (EditText) findViewById(R.id.edit2);
		edit3 = (EditText) findViewById(R.id.edit3);
		edit4 = (EditText) findViewById(R.id.edit4);
		server = edit1.getEditableText();
		username = edit2.getEditableText();
		password = edit3.getEditableText();
		command = edit4.getEditableText();
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		text.setText("Android Socket" + "\n");
		telnet = new TelnetClient();
	}
	
	public void onClick(View arg0) {
		
		// TODO Auto-generated method stub
				text.setText("Android Socket" + "\n");
				try {
					telnet.connect("192.168.1.78", 1034);
					in = telnet.getInputStream();
					out = new PrintStream(telnet.getOutputStream());
					
					
					out.println("sendai1.\r\n");
					out.flush();
					
					out.println(",O2f00\r\n");
					out.flush();
					System.out.println("done");
					
			
				} catch (Exception e) {
					e.printStackTrace();
				}
		
	}

	

	public void disconnect() {
		try {
			in.close();
			out.close();
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
