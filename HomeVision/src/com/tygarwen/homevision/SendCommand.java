package com.tygarwen.homevision;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;


import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class SendCommand {
	
	
	
	Socket socket;
	private PrintStream out;
	Context c;
	Resources r;
	
	public SendCommand(Context con , PrintStream out){
		
			c  = con;
			this.out = out;
			r = c.getResources();
			
			
	}
	
	
	public void light_on(String room){
		//each command has a short 200ms pause to avoid buffer overrun on the homevision serial input.
		
		setIFlag();
		setPressedFlag();
		callSwitchHandler(room);
		clearFlags();
		
	}
	
	public void light_off(String room){
		
		set0Flag();
		setPressedFlag();
		callSwitchHandler(room);
		clearFlags();
	}

	private void setPressedFlag() {
		String command = "," + r.getString(R.string.FlagCommand) + r.getString(R.string.Switch_Pressed) + r.getString(R.string.SetFlag) + "\r\n";
//		Toast.makeText(c, command, 0).show();
		out.println(command);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
	}
	
	private void setIFlag() {
		String command = "," + r.getString(R.string.FlagCommand) + r.getString(R.string.ON_Pressed) + r.getString(R.string.SetFlag) + "\r\n";
//		Toast.makeText(c, command, 0).show();
		out.println(command);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
	}
	
	private void set0Flag() {
		String command = "," + r.getString(R.string.FlagCommand) + r.getString(R.string.OFF_Pressed) + r.getString(R.string.SetFlag) + "\r\n";
//		Toast.makeText(c, command, 0).show();
		out.println(command);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
	}
	
	private void callSwitchHandler(String room) {
		String command = "," + r.getString(R.string.MacroCommand) + r.getString(r.getIdentifier(room, "string", c.getPackageName())) + r.getString(R.string.DO_Macro)+"\r\n";
//		Toast.makeText(c, command, 0).show();
		out.println(command);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	private void clearFlags() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String command = "," + r.getString(R.string.FlagCommand) + r.getString(R.string.OFF_Pressed) + r.getString(R.string.ClearFlag) + "\r\n";
		out.println(command);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		command = "," + r.getString(R.string.FlagCommand) + r.getString(R.string.ON_Pressed) + r.getString(R.string.ClearFlag) + "\r\n";
		out.println(command);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		command = "," + r.getString(R.string.FlagCommand) + r.getString(R.string.Switch_Pressed) + r.getString(R.string.SetFlag) + "\r\n";
		out.println(command);
		out.flush();	
	}
}
