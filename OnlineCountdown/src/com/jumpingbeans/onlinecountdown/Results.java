package com.jumpingbeans.onlinecountdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

@TargetApi(9)
public class Results extends Activity {

	ListView lv;
	MyAdapterres adapt;
	ArrayList<Myobjectres> al;
	Button Continue;
	String gameName;
	TextView message;
	boolean creator;
	String PlayerID;
	boolean firsttime = true;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        
        if(Build.VERSION.SDK_INT>10){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
			StrictMode.setThreadPolicy(policy);
		}
        
        gameName = getIntent().getExtras().getString("gameName");
        creator = getIntent().getExtras().getBoolean("creator");
        PlayerID = getIntent().getExtras().getString("PlayerID");
        
        lv = (ListView) findViewById(R.id.listView1);
        al = new ArrayList<Myobjectres>();
        Continue = (Button) findViewById(R.id.button1);
        message = (TextView) findViewById(R.id.textView2);
        
        adapt = new MyAdapterres(this, R.layout.resrow,  al);
        View header = (View)getLayoutInflater().inflate(R.layout.headres, null);
        lv.addHeaderView(header);
        lv.setAdapter(adapt);
        
        new getrestask().execute(getString(R.string.BaseAddress)+"get_results.php?code="+gameName);
        new noReturn().execute(getString(R.string.BaseAddress)+"stop_game.php?code="+gameName); 
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_results, menu);
        return true;
    }
    
    public void onBackPressed(){
    	
    }

    
    public void Continue(View v){
    	if (creator){
    		Intent i = new Intent(Results.this, RoundSettings.class);
			i.putExtra("gameName", gameName);
			i.putExtra("PlayerID", PlayerID);
			i.putExtra("Already_Joined", true);
			startActivity(i);
    	}else if (creator == false){
    		Intent i = new Intent(Results.this, NewPlayer.class);
			i.putExtra("gameName", gameName);
			i.putExtra("PlayerID", PlayerID);
			i.putExtra("Already_Joined", true);
			i.putExtra("creator",false);
			startActivity(i);
    	}
    }
    
    
    
    private class getrestask extends AsyncTask<String, Integer,String[][]>{

ProgressDialog dialog;
    	
    	
    	@Override
    	protected void onPreExecute() {
    		if(firsttime){
    			dialog = new ProgressDialog(Results.this);
        		dialog.setMessage("Loading");
                dialog.show();
                
    		}
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		
    		
    	}
		
		@Override
		protected String[][] doInBackground(String... address) {
			// TODO Auto-generated method stub
			
			BufferedReader br = null;
 		     
 		      
 		      try { 
 		        	DefaultHttpClient httpClient = new DefaultHttpClient();
 		        	HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(6,true);
					httpClient.setHttpRequestRetryHandler(retryHandler );
 					URI url = new URI(address[0]);
 					HttpGet httpGet = new HttpGet();
 					httpGet.setURI(url);
 					HttpResponse httpResponse = httpClient.execute(httpGet);
 					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
 					
					
					String arrayString = br.readLine();
							
					JSONArray Jresults = new JSONArray(arrayString);
					String[][] results = new String[Jresults.length()][];
					JSONObject person = Jresults.getJSONObject(0);;
					for(int j = 0; j< Jresults.length(); j++){
						person = Jresults.getJSONObject(j);
						String[] stringArray = new String[5];
						stringArray[0]= person.getString("time");
						stringArray[1]= person.getString("target");
						stringArray[2]= person.getString("name");
						stringArray[3]= person.getString("distance");
						stringArray[4]= person.getString("score");
						//res.setText(stringArray[4]);
						results[j] = stringArray;
					}
 				
 					return results;
 				} catch (URISyntaxException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} catch (ClientProtocolException e) {
 					// TODO Auto-generated catch block
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
		
		
		
		protected void onPostExecute(String[][] results) {
			String print = String.format("In %s seconds these people got this far from %s", results[0][0], results[0][1]);
			
			message.setText(print);
			
			Log.i("test", "marker");
			al.clear();
			for(int player = 0; player<results.length;player++){
				
			 al.add(new Myobjectres(results[player][2], results[player][3], results[player][4]));
			 	
			}
			
			adapt.notifyDataSetChanged();
			if(firsttime){
				if (dialog.isShowing()) {
			           dialog.dismiss();
			        }
				firsttime = false;
			}
				
			new getrestask().execute(getString(R.string.BaseAddress)+"get_results.php?code="+gameName);
			
			
			
	     }
		
	}

    private class noReturn extends AsyncTask<String, Integer,String>{

		
		
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
	     }
		
		
	}


    
}
