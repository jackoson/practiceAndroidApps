package com.jumpingbeans.onlinecountdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.view.Menu;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


public class Join extends Activity {

	Button connect;
	EditText pass;
	TextView message;
	
	String gameName; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        
       gameName = getIntent().getExtras().getString("gameName");
        
        connect = (Button) findViewById(R.id.button1);
        pass = (EditText) findViewById(R.id.editText2);
        message = (TextView) findViewById(R.id.textView1);
        
        pass.setImeActionLabel("Connect", KeyEvent.KEYCODE_ENTER);
        pass.setOnEditorActionListener(new OnEditorActionListener(){

			public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				
				String password = pass.getText().toString();
				if(password.length()<1){
					Toast.makeText(getApplicationContext(), "Enter a password", 0).show();
				}else{
					new checkpasstask().execute(getString(R.string.BaseAddress)+"check_pass.php?code="+gameName+"&pass="+ password);
				}
				
				return false;
		    }
				
		
			        	
        });
        
        
        connect.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String password = pass.getText().toString();
				if(password.length()<1){
					Toast.makeText(getApplicationContext(), "Enter a password", 0).show();
				}else{
					new checkpasstask().execute(getString(R.string.BaseAddress)+"check_pass.php?code="+gameName+"&pass="+ password);
				}
			}
        	
        });
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    } 
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		System.exit(0);
		return false;
	}

private class checkpasstask extends AsyncTask<String, Integer,String>{
	
	
	
	ProgressDialog dialog;
	
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		dialog = new ProgressDialog(Join.this);
		dialog.setMessage("Checking password");
        dialog.show();
		
	}




		
		
		@Override
		protected String doInBackground(String... address) {
			// TODO Auto-generated method stub
			
			BufferedReader br = null;
 		      InputStream is = null;
 		      
 		      try { 
 		        	DefaultHttpClient httpClient = new DefaultHttpClient();
 					URI url = new URI(address[0]);
 					HttpGet httpGet = new HttpGet();
 					httpGet.setURI(url);
 					HttpResponse httpResponse = httpClient.execute(httpGet);
 					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
 					
 					String line = br.readLine();
 						
 					br.close();
 					
 					return line;
 				
 					
 				} catch (URISyntaxException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} catch (ClientProtocolException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				} catch (IOException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				} 
			return null;
		}
		
		
		
		protected void onPostExecute(String result) {
		
			if(result.equals("1")){
				Intent i = new Intent(Join.this, NewPlayer.class);
				i.putExtra("gameName", gameName);
				i.putExtra("creator",false);
				i.putExtra("Already_Joined", false);
				startActivity(i);
			}else{
				message.setText("Wrong! Try again");
				pass.setText("");
			}
			
			Toast.makeText(getApplicationContext(), "Done", 0).show();
	    
			if (dialog.isShowing()) {
		           dialog.dismiss();
		        }
		}
		
	}
}
