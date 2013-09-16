package com.jumpingbeans.onlinecountdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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






import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

@TargetApi(9)
public class Main extends Activity {

	Button new_game;
	ListView lv;
	MyAdapter adapt;
	ArrayList<Myobject> al;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(Build.VERSION.SDK_INT>10){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
			StrictMode.setThreadPolicy(policy);
		}
        
        setContentView(R.layout.lobby);
        
        lv = (ListView) findViewById(R.id.listView1);
        al = new ArrayList<Myobject>();
        new_game = (Button) findViewById(R.id.button1);
        
        adapt = new MyAdapter(this, R.layout.itemrow,  al);
        
        View header = (View)getLayoutInflater().inflate(R.layout.header, null);
        lv.addHeaderView(header);
        lv.setAdapter(adapt);
        
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String gameName = adapt.getItem(arg2-1).name;
				
				
				
				Intent i = new Intent(Main.this, Join.class);
				i.putExtra("gameName", gameName);
				startActivity(i);
			}
        	
        });
        
        if(checkInternetConnection()){
        	new getgamestask().execute(getString(R.string.BaseAddress)+"get_games.php");
        }
        
        new_game.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(Main.this, NewGame.class));
				
			}
        	
        });
        
    }

    public void refresh(View v ){
    	if(checkInternetConnection())
            new getgamestask().execute(getString(R.string.BaseAddress)+"get_games.php");
    }
    
    
    private boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
        	Toast.makeText(getApplicationContext(), "connected", 0).show();
            return true;
        }else{
        	Toast.makeText(getApplicationContext(), "not connected", 0).show();
        	return false;
        }
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



	private class getgamestask extends AsyncTask<String, Integer,String[][]>{

    	ProgressDialog dialog;
    	
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		dialog = new ProgressDialog(Main.this);
    		dialog.setMessage("Loading Games");
            dialog.show();
    		
    	}
		@Override
		protected String[][] doInBackground(String... address) {
			// TODO Auto-generated method stub
			Log.i("MArker","beginiing of http");
			BufferedReader br = null;
 		      InputStream is = null;
 		      
 		     String[][] results = new String[0][0];;
 		      
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
 					if(arrayString.contentEquals("error[]")){
 						Log.i("MArker","we got errror");
 						
 					}else if(arrayString == null){
 						Log.i("MArker","we got null");
 					}else{
 						Log.i("MArker","we aint got null");
 						Log.i("MArker",arrayString);
 					
 					
 						
	 					JSONArray Jresults = new JSONArray(arrayString);
	 					results = new String[Jresults.length()][];
	 					JSONObject person = Jresults.getJSONObject(0);;
	 					
	 					for(int j = 0; j< Jresults.length(); j++){
	 						person = Jresults.getJSONObject(j);
	 						String[] stringArray = new String[5];
	 						stringArray[0]= person.getString("name");
	 						stringArray[1]= person.getString("numplayers");
	 						results[j] = stringArray;
	 					}
 					}
 					
 				
 					return results;
 					
 					
 				} catch (URISyntaxException e1) {
 					// TODO Auto-generated catch block
 					Log.i("MArker",e1.getMessage());
 					e1.printStackTrace();
 				} catch (ClientProtocolException e) {
 					// TODO Auto-generated catch block
 					Log.i("MArker",e.getMessage());
 					e.printStackTrace();
 				} catch (IOException e) {
 					Log.i("MArker",e.getMessage());
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				} catch (JSONException e) {
 					Log.i("MArker",e.getMessage());
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
			
			
			return null;
		}
		
		
		
		protected void onPostExecute(String[][] results) {
			
			
			
			Log.i("results", Integer.toString(results.length));
			 al.clear();
			 if(results.length<1){
				 al.add(new Myobject("No running games. Create one above",""));				
			 }else{
				 for(int player = 0; player<results.length;player++){
					 al.add(new Myobject(results[player][0],  results[player][1]));
				}
			 }
				
				adapt.notifyDataSetChanged();
				
				Toast.makeText(getApplicationContext(), "Done", 0).show();
				
				if (dialog.isShowing()) {
			           dialog.dismiss();
			        }
	     }
	
	        
		
    }
    
    
}
