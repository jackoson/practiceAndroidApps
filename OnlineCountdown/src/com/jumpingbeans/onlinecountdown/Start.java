package com.jumpingbeans.onlinecountdown;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Start extends Activity {

	String gameName;
	String PlayerID;
	boolean creator;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        
        gameName = getIntent().getExtras().getString("gameName");
		PlayerID = getIntent().getExtras().getString("PlayerID");
		creator = getIntent().getExtras().getBoolean("creator");
		
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start, menu);
        return true;
    }

    public void start(View v){
    	new noReturn().execute(getString(R.string.BaseAddress)+"start_game.php?code="+gameName);
    }
    
    
    private class noReturn extends AsyncTask<String, Integer,String>{

		
    	ProgressDialog dialog;
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(Start.this);
			dialog.setMessage("Starting the game...");
	        dialog.show();
			
		}
		@Override
		protected String doInBackground(String... address) {
			// TODO Auto-generated method stub
			
			 try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI(address[0]);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					httpClient.execute(httpGet);
					
					
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
			 
			 return "";
		}
		
		
		protected void onPostExecute(String result) {
			
			Toast.makeText(getApplicationContext(), "Done", 0).show();
			if (dialog.isShowing()) {
		           dialog.dismiss();
		        }
			
			Intent i = new Intent(Start.this, PlayScreen.class);
			i.putExtra("gameName", gameName);
			i.putExtra("PlayerID", PlayerID);
			i.putExtra("creator", true);
			startActivity(i);
			
	     }
		
		
	}



    
    
    
}
