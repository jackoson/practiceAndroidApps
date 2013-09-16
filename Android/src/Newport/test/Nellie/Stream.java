package Newport.test.Nellie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Stream extends Activity implements OnClickListener{

	Button s,l;
	TextView t;
	EditText e;
	FileOutputStream fos;
	String fn = "internalstorage";
	String data,c;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savedata);
		setup();
	}

	
	
	private void setup() {
		// TODO Auto-generated method stub
		s = (Button) findViewById(R.id.s);
		l = (Button) findViewById(R.id.l);
		t = (TextView) findViewById(R.id.t);
		e = (EditText) findViewById(R.id.e);
		s.setOnClickListener(this);
		l.setOnClickListener(this);
		
		try {
			fos = openFileOutput(fn,Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.s:
			Toast.makeText(getApplicationContext(), "save", Toast.LENGTH_SHORT).show();
			data = e.getText().toString();
			Intent i = new Intent("Newport.test.Nellie.TEST");
			startActivity(i);
			
			try {
				
				fos = openFileOutput(fn,Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case R.id.l:
			
			Toast.makeText(getApplicationContext(), "load", Toast.LENGTH_SHORT).show();
			FileInputStream fis = null;
			
			try {
				
				fis = openFileInput(fn);
				byte[] dataArray = new byte [fis.available()];
				while (fis.read(dataArray)!= -1){
					c = new String(dataArray);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			t.setText(c);
			
			
			break;
		}
	}





}
