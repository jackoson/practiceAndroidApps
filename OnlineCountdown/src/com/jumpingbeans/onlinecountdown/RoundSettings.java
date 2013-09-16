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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class RoundSettings extends Activity {

	Spinner spin;
	SeekBar sb;
	Button save;
	String[] diffs;
	ArrayAdapter<String> aa;
	TextView tv;
	String gameName;
	String PlayerID;
	boolean already_joined;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_settings);
        
        gameName = getIntent().getExtras().getString("gameName");
        
        already_joined = getIntent().getExtras().getBoolean("Already_Joined");
        
        if(already_joined){
        	PlayerID = getIntent().getExtras().getString("PlayerID");
        	
        	new getGameValues().execute(getString(R.string.BaseAddress)+"get_game_values.php?code="+gameName);
        	
        	
        	
        }
        		
        save = (Button) findViewById(R.id.button1);
        tv = (TextView) findViewById(R.id.textView2);
        spin = (Spinner) findViewById(R.id.spinner1);
        sb = (SeekBar) findViewById(R.id.seekBar1);
        sb.setMax(300);
        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				tv.setText("Set time Limit ( " + Integer.toString(arg1) + " Seconds ) ");
			}

			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        diffs = new String[]{
        		"Easy","Medium","Hard","Exteme", "All Levels"
        };
        aa =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,diffs);
        spin.setAdapter(aa);
        
        save.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String diff = Integer.toString(spin.getSelectedItemPosition());
				String timeint =  Integer.toString(sb.getProgress());
				
				new noReturn().execute(getString(R.string.BaseAddress)+"new_numbers.php?code="+gameName+"&difficulty="+diff+"&time="+timeint);
				
				//Toast.makeText(getApplicationContext(), spin.getSelectedItem().toString(), 0).show();
			}
        	
        });
    }

    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
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

    private class noReturn extends AsyncTask<String, Integer,String>{

    	ProgressDialog dialog;
    	
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		dialog = new ProgressDialog(RoundSettings.this);
    		dialog.setMessage("Setting Up Game");
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
			
			if (dialog.isShowing()) {
		           dialog.dismiss();
		        }
			
			Intent i = new Intent(RoundSettings.this, NewPlayer.class);
			i.putExtra("gameName", gameName);
			i.putExtra("creator",true);
			i.putExtra("Already_Joined", already_joined);
			i.putExtra("PlayerID", PlayerID);
			startActivity(i);
			
			
	     }
		
		
	}


    private class getGameValues extends AsyncTask<String, Integer,String[]>{

    	ProgressDialog dialog;
    	
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		dialog = new ProgressDialog(RoundSettings.this);
    		dialog.setMessage("Loading");
            dialog.show();
    		
    	}
		@Override
		protected String[] doInBackground(String... address) {
			// TODO Auto-generated method stub
			Log.i("MArker","beginiing of http");
			BufferedReader br = null;
 		      InputStream is = null;
 		      
 		      try { 
 		        	DefaultHttpClient httpClient = new DefaultHttpClient();
 					URI url = new URI(address[0]);
 					HttpGet httpGet = new HttpGet();
 					httpGet.setURI(url);
 					
 					HttpResponse httpResponse = httpClient.execute(httpGet);
 					
 					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
 					
 					
 					String arrayString = br.readLine();
 							
 					JSONArray Jresults = new JSONArray(arrayString);
 					String[] results = new String[Jresults.length()];
 					
 					results[0] = Jresults.getString(0);
 					results[1] = Jresults.getString(1);
 					
 					
 				
 					return results;
 				} catch (URISyntaxException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} catch (ClientProtocolException e) {
 					// TODO Auto-generated catch block
 					Toast.makeText(getApplicationContext(), e.getMessage(), 0).show();
 					e.printStackTrace();
 				} catch (IOException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				} catch (JSONException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 					//Toast.makeText(getApplicationContext(), results[player][0], "", results[player][1]), 0).show();
 				}
			
			
			return null;
		}
		
		
		
		protected void onPostExecute(String[] results) {
			
		sb.setProgress(Integer.parseInt(results[1]));
		spin.setSelection(Integer.parseInt(results[0]));
			
				Toast.makeText(getApplicationContext(), "Done", 0).show();
				
				if (dialog.isShowing()) {
			           dialog.dismiss();
			        }
	     }
	
	        
		
    }
    
    
}
