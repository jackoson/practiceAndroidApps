package com.tygarwen.homevision;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Lights extends Activity {

	ArrayAdapter<String> aa;
	ArrayList<String> refs = new ArrayList<String>();
	ImageView iv;//switch pic
	String room; 
	Context c;
	Socket socket;
	private PrintStream out;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        createMenu();
        
        
    }

	private void createMenu() {
		// TODO Auto-generated method stub
		setContentView(R.layout.lights);
        ListView lv = (ListView) findViewById(R.id.listView1);
        c = this;
        ArrayList<String> list = new ArrayList<String>(); 
        list.add("Landing");
        list.add("Hall");
        list.add("Kitchen");
        list.add("Lounge");
        list.add("DownstairsBathroom");
        list.add("BedroomStandingLamp");
        list.add("UpstairsBathroom");
        list.add("Toilet");
        list.add("Utilityroom");
        list.add("BedroomCupboard");
        list.add("UpstairsBathroomMirror");
        aa = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
        lv.setAdapter(aa);
        
        lv.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
        		try {
    				socket = new Socket("192.168.1.78", 1034);
    				
    				out = new PrintStream(socket.getOutputStream());
    				
    				out.println("sendai1.\r\n");
    				out.flush();
    				Toast.makeText(c, "connected", 0).show();

    				
    			} catch (Exception e) {
    				e.printStackTrace();
    				Toast.makeText(c, "Not connected", 0).show();
    			}
    			
        	

        		
				room = arg0.getAdapter().getItem(arg2).toString();
				setContentView(R.layout.switch_screen);
				
				iv = (ImageView) findViewById(R.id.imageView1);
				
				
				
				iv.setOnTouchListener(new OnTouchListener(){
					
					

					public boolean onTouch(View v, MotionEvent event) {
						Point p = new Point((int)event.getX(),(int)event.getY());
						if(p.y>0 && p.y<(iv.getHeight()/2)){
							
							new SendCommand(c,out ).light_on(room);
							Toast.makeText(getApplicationContext(), "on", 0).show();
							
							
						}else if(p.y>(iv.getHeight()/2)&& p.y<iv.getHeight()){
							
							new SendCommand(c, out).light_off(room);
							Toast.makeText(getApplicationContext(), "off", 0).show();
							
							
						}
						return false;
					}
					
				});
				
			}
        	
        });
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		createMenu();
	}

	
	
}
