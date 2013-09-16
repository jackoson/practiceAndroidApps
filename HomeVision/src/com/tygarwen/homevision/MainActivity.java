package com.tygarwen.homevision;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

	Socket socket;
	TextView tv;
	private InputStream in;
	private PrintStream out;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.textView1);
        
			try {
				socket = new Socket("192.168.1.78", 1034);
				in = socket.getInputStream();
				out = new PrintStream(socket.getOutputStream());
				
				out.println("sendai1.\r\n");
				out.flush();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
    }
    
    public void on(View v){
		out.println(",O2f00\r\n");
		out.flush();
		
    }
    
    public void off(View v){
		out.println(",O2D00\r\n");
		out.flush();
		
    }
    
}

