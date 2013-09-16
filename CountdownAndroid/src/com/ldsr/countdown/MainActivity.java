
package com.ldsr.countdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	 
	TextView timedisplay,exists,starteddisp,nums,res,ispass,playerid;
	EditText code,diffi,time,name,dist,password,id;
	Button checkgame,create,newnums;
	int timevalue = 100;
	Handler h;
	String codestring,passstring;
	String PlayerID = "";
	String distance;
	ListView lv,lv2;
	MyAdapter my,my2;
	ArrayList<Myobject> mo,mo2;
	
	
    @SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // numbers = (TextView) findViewById(R.id.textView1);
        timedisplay = (TextView) findViewById(R.id.timedis);
        
        lv = (ListView) findViewById(R.id.listView1);
        lv2 = (ListView) findViewById(R.id.listView2);
        
        exists = (TextView) findViewById(R.id.exists);
        starteddisp = (TextView) findViewById(R.id.starteddisp);
        nums = (TextView) findViewById(R.id.nums);
        res = (TextView) findViewById(R.id.res);
        ispass = (TextView) findViewById(R.id.ispass);
        playerid = (TextView) findViewById(R.id.playerid);
        
        code = (EditText) findViewById(R.id.code);
        diffi = (EditText) findViewById(R.id.diffi);
        time = (EditText) findViewById(R.id.time);
        name = (EditText) findViewById(R.id.name);
        dist = (EditText) findViewById(R.id.dist);
        password = (EditText) findViewById(R.id.password);
        id = (EditText) findViewById(R.id.id);
        
        h = new Handler();
        
        h.post(tupdate);
        
        
        mo = new ArrayList<Myobject>();
        mo2 = new ArrayList<Myobject>();
        
        if(Build.VERSION.SDK_INT>10){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
			StrictMode.setThreadPolicy(policy);
		}
        
       
       
        
        my = new MyAdapter(this, R.layout.itemrow,  mo);
        
        my2 = new MyAdapter(this, R.layout.itemrow,  mo2);
        
        
        View header = (View)getLayoutInflater().inflate(R.layout.header, null);
        lv.addHeaderView(header);
        lv.setAdapter(my);
        
        lv2.setAdapter(my2);
        
      lv2.setOnItemClickListener(new OnItemClickListener(){

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			// TODO Auto-generated method stub
			String game = my2.getItem(arg2).person;
			code.setText(game);
		}
    	  
      });
    }
    
    
    public void getgames(View v){
 	   
    	new getgamestask().execute("http://www.jumpingbeans.webege.com/Countdown/get_games.php");
 	   
 	   	/*
 	   	BufferedReader br = null;
 		      InputStream is = null;
 		      
 		      try { 
 		        	DefaultHttpClient httpClient = new DefaultHttpClient();
 					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/get_games.php");
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
 						stringArray[0]= person.getString("name");
 						stringArray[1]= person.getString("numplayers");
 						
 						
 						results[j] = stringArray;
 					}
 				
 					
 					
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
 		      */
 		    
 		        
 	   }
   
    
    
   public void checkifgame(View v){
    	
	   codestring = code.getText().toString();
	   if(codestring.length()<1){
		   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
	   }else{
    	
    	new checkforgametask().execute("http://www.jumpingbeans.webege.com/Countdown/check_for_game.php?code="+codestring);
	   }
    	/*BufferedReader br = null;
	      InputStream is = null;
	      int[] numbers = new int[7];
	      try { 
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
				URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/check_for_game.php?code="+codestring);
				HttpGet httpGet = new HttpGet();
				httpGet.setURI(url);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
				
				return br.readLine();
					exists.setText(line);
				br.close();
				
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        
    }
   
   public void creategame(View v){
   	codestring = code.getText().toString();
   	passstring = password.getText().toString();
    if(codestring.length()<1 && passstring.length()<1){
		   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
	   }else{
 	
   	
   	new noReturn().execute("http://www.jumpingbeans.webege.com/Countdown/new_game.php?code="+codestring+"&pass="+passstring);
	   }
   
	      
	      /*
	      try { 
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
				URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/new_game.php?code="+codestring+"&pass="+passstring);
				HttpGet httpGet = new HttpGet();
				httpGet.setURI(url);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				
				
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
	        */
   }
   
   
   public void newnums(View v){
	   	codestring = code.getText().toString();
	   	String diff = diffi.getText().toString();
	   	String timeint = time.getText().toString();
	   	
	    if(codestring.length()<1 && diff.length()<1 && timeint.length()<1){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	 	
	   	
	   	new noReturn().execute("http://www.jumpingbeans.webege.com/Countdown/new_numbers.php?code="+codestring+"&difficulty="+diff+"&time="+timeint);
		   }
	   	/*
	   	BufferedReader br = null;
		      InputStream is = null;
		      
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
		        	String address = "http://www.jumpingbeans.webege.com/Countdown/new_numbers.php?code="+codestring+"&difficulty="+diff+"&time="+timeint;
					Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();
		        	URI url = new URI(address);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
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
		        */
	   }
   
   
   public void newplayer(View v){
	   
	   	codestring = code.getText().toString();
	   	String namestring = name.getText().toString();
	   	
	    if(codestring.length()<1 && namestring.length()<1 ){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	   	
	   	new newplayertask().execute("http://www.jumpingbeans.webege.com/Countdown/add_new_player.php?code="+codestring+"&name="+namestring);
		   }	/*
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/add_new_player.php?code="+codestring+"&name="+namestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					String line = br.readLine();
					PlayerID = line;
					br.close();
					
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
		        */
	   }
   
   public void isstarted(View v){
	   	codestring = code.getText().toString();
	   	
	    if(codestring.length()<1 ){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	   	new isstartedtask().execute("http://www.jumpingbeans.webege.com/Countdown/check_if_started.php?code="+codestring);
		   }
	   	
	   	/*
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/check_if_started.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					String line = br.readLine();
						starteddisp.setText(line);
					br.close();
					
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
		        */
	   }
   
   public void checkpass(View v){
	   	codestring = code.getText().toString();
	   	passstring = password.getText().toString();
	   	
	    if(codestring.length()<1 && passstring.length()<1){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	   	new checkpasstask().execute("http://www.jumpingbeans.webege.com/Countdown/check_pass.php?code="+codestring+"&pass="+ passstring);
	   	
		   }
	   	/*
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/check_pass.php?code="+codestring+"&pass="+ passstring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					String line = br.readLine();
						ispass.setText(line);
					br.close();
					
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
		        */
	   }
   
   public void start(View v){
	   	codestring = code.getText().toString();
	    if(codestring.length()<1){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	   	
	   	new noReturn().execute("http://www.jumpingbeans.webege.com/Countdown/start_game.php?code="+codestring);
	   
		   }/*	
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/start_game.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		        
	   }
   
   
   public void stopgame(View v){
	   	codestring = code.getText().toString();
	   	
	   	codestring = code.getText().toString();
	    if(codestring.length()<1){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	   	new noReturn().execute("http://www.jumpingbeans.webege.com/Countdown/stop_game.php?code="+codestring);
		   }
		      /*
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/stop_game.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		        
	   }
   
   
   public void getnums(View v){
	   	codestring = code.getText().toString();
	   	
	   	codestring = code.getText().toString();
	    if(codestring.length()<1){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	   	
	   	new getnumstask().execute("http://www.jumpingbeans.webege.com/Countdown/get_numbers.php?code="+codestring);
	   	
		   }/*
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[8];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/get_numbers.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					StringBuffer sb = new StringBuffer("");
					String line = "";
					for(int x = 0; x<8;x++){
						line = br.readLine();
						numbers[x] = Integer.parseInt(line);
						sb.append(line+"\n");
					}
					
					nums.setText(sb.toString());
					timevalue  = numbers[7];
					
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
		        */
	   }
   
   
   public void postdist(View v){
	   distance = dist.getText().toString();
	   PlayerID = id.getText().toString();
	   codestring = code.getText().toString();
	    if(codestring.length()<1&&PlayerID.length()<1){
			   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
		   }else{
	   	
		new noReturn().execute("http://www.jumpingbeans.webege.com/Countdown/post_distance.php?id="+PlayerID+"&distance="+distance);
		   }
		/*	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/post_distance.php?id="+PlayerID+"&distance="+distance);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
					
					
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		        
	   }
   
   
   public void delplayer(View v){
	  
	   PlayerID = id.getText().toString();
	   
	   if(PlayerID.length()<1){
		   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
	   }else{
	   
	   new noReturn().execute("http://www.jumpingbeans.webege.com/Countdown/delete_player.php?id="+PlayerID);
	   }
	   /*	BufferedReader br = null;
		      InputStream is = null;
		      
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/delete_player.php?id="+PlayerID);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
					
					
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		        
	   }
   
   
   public void getres(View v){
	   
	   codestring = code.getText().toString();
	   
	   if(codestring.length()<1){
		   Toast.makeText(getApplicationContext(), "need more variables", 0).show();
	   }else{
	   
	   new getrestask().execute("http://www.jumpingbeans.webege.com/Countdown/get_results.php?code="+codestring);	
	   }
	   /*	BufferedReader br = null;
		      InputStream is = null;
		      
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/get_results.php?code="+codestring);
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
					//res.setText(person.getString("distance"));
					
					String print = String.format("In %s seconds these people got this far from %s", results[0][0], results[0][1]);
					
					res.setText(print);
					
					
					mo.clear();
					for(int player = 0; player<results.length;player++){
						
					 mo.add(new Myobject(results[player][2], results[player][3], results[player][4]));
					 	
					}
					
					my.notifyDataSetChanged();
					
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
					Toast.makeText(getApplicationContext(), codestring, 0).show();
				}
		        */
	   }
  
   
   
  
    protected void updateTimer() {
		// TODO Auto-generated method stub
    	timedisplay.setText(Integer.toString(timevalue));
    	timevalue--;
	}
    
    

    
    
		private Runnable tupdate = new Runnable() {
			   public void run() {
				  
				   updateTimer();
				   //if it is not paused
				   
				   h.postDelayed(this,1000);
				}
			};

		
			
		private class getgamestask extends AsyncTask<String, Integer,String[][]>{

			
			
			@Override
			protected String[][] doInBackground(String... address) {
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
	 				
	 					
	 					String arrayString = br.readLine();
	 							
	 					JSONArray Jresults = new JSONArray(arrayString);
	 					String[][] results = new String[Jresults.length()][];
	 					JSONObject person = Jresults.getJSONObject(0);;
	 					for(int j = 0; j< Jresults.length(); j++){
	 						person = Jresults.getJSONObject(j);
	 						String[] stringArray = new String[5];
	 						stringArray[0]= person.getString("name");
	 						stringArray[1]= person.getString("numplayers");
	 						
	 						
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
				 mo2.clear();
					for(int player = 0; player<results.length;player++){
						
					 mo2.add(new Myobject(results[player][0], "", results[player][1]));
					 	
					}
					
					my2.notifyDataSetChanged();
					
					Toast.makeText(getApplicationContext(), "Done", 0).show();
		     }
			
		}
		
		
		
private class getrestask extends AsyncTask<String, Integer,String[][]>{

			
			
			@Override
			protected String[][] doInBackground(String... address) {
				// TODO Auto-generated method stub
				
				BufferedReader br = null;
	 		     
	 		      
	 		      try { 
	 		        	DefaultHttpClient httpClient = new DefaultHttpClient();
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
				
				res.setText(print);
				
				
				mo.clear();
				for(int player = 0; player<results.length;player++){
					
				 mo.add(new Myobject(results[player][2], results[player][3], results[player][4]));
				 	
				}
				
				my.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "Done", 0).show();
		     }
			
		}



private class getnumstask extends AsyncTask<String, Integer,int[]>{

	
	
	@Override
	protected int[] doInBackground(String... address) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		int[] numbers = new int[8];
	      try { 
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
				URI url = new URI(address[0]);
				HttpGet httpGet = new HttpGet();
				httpGet.setURI(url);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
				
				StringBuffer sb = new StringBuffer("");
				String line = "";
				for(int x = 0; x<8;x++){
					line = br.readLine();
					numbers[x] = Integer.parseInt(line);
					sb.append(line+"\n");
				}
				
				
				
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
		
		
		return numbers;
	}
	
	
	
	protected void onPostExecute(int[] numbers) {
		timevalue  = numbers[7];
		StringBuffer sb = new StringBuffer("");
		for(int x = 0; x<7;x++){
			String line = Integer.toString(numbers[x]);
			
			sb.append(line+"\n");
		}
		nums.setText(sb.toString());
		Toast.makeText(getApplicationContext(), "Done", 0).show();
     }
	
}

		
		
	private class checkforgametask extends AsyncTask<String, Integer,String>{

			
			
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
				exists.setText(result);
				Toast.makeText(getApplicationContext(), "Done", 0).show();
		     }
			
		}
	
	
	private class checkpasstask extends AsyncTask<String, Integer,String>{

		
		
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
			ispass.setText(result);
			Toast.makeText(getApplicationContext(), "Done", 0).show();
	     }
		
	}
		
	
	private class isstartedtask extends AsyncTask<String, Integer,String>{

		
		
		@Override
		protected String doInBackground(String... address) {
			// TODO Auto-generated method stub
			
			BufferedReader br = null;
 		      
 		      
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
			starteddisp.setText(result);

				
				Toast.makeText(getApplicationContext(), "Done", 0).show();
		     
	     }
		
	}
	
	
		
	
	private class newplayertask extends AsyncTask<String, Integer,String>{

		
		
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
		
		
		
		protected void onPostExecute(String idS) {
			PlayerID = idS;
			playerid.setText(idS);
			id.setText(idS);

				
				Toast.makeText(getApplicationContext(), "Done", 0).show();
		     
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










/*package com.ldsr.countdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	 
	TextView timedisplay,exists,starteddisp,nums,res,ispass;
	EditText code,diffi,time,name,dist,password;
	Button checkgame,create,newnums;
	int timevalue = 100;
	Handler h;
	String codestring,passstring;
	String PlayerID = "";
	String distance;
	ListView lv,lv2;
	MyAdapter my,my2;
	ArrayList<Myobject> mo,mo2;
	
	
    @SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // numbers = (TextView) findViewById(R.id.textView1);
        timedisplay = (TextView) findViewById(R.id.timedis);
        
        lv = (ListView) findViewById(R.id.listView1);
        lv2 = (ListView) findViewById(R.id.listView2);
        
        exists = (TextView) findViewById(R.id.exists);
        starteddisp = (TextView) findViewById(R.id.starteddisp);
        nums = (TextView) findViewById(R.id.nums);
        res = (TextView) findViewById(R.id.res);
        ispass = (TextView) findViewById(R.id.ispass);
        
        code = (EditText) findViewById(R.id.code);
        diffi = (EditText) findViewById(R.id.diffi);
        time = (EditText) findViewById(R.id.time);
        name = (EditText) findViewById(R.id.name);
        dist = (EditText) findViewById(R.id.dist);
        password = (EditText) findViewById(R.id.password);
        
        h = new Handler();
        
        h.post(tupdate);
        
        
        mo = new ArrayList<Myobject>();
        mo2 = new ArrayList<Myobject>();
        
        if(Build.VERSION.SDK_INT>10){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
			StrictMode.setThreadPolicy(policy);
		}
        
       
       
        
        my = new MyAdapter(this, R.layout.itemrow,  mo);
        
        my2 = new MyAdapter(this, R.layout.itemrow,  mo2);
        
        
        View header = (View)getLayoutInflater().inflate(R.layout.header, null);
        lv.addHeaderView(header);
        lv.setAdapter(my);
        
        lv2.setAdapter(my2);
        
      
    }
    
    
    public void getgames(View v){
 	   
 	   
 	   	
 	   	BufferedReader br = null;
 		      InputStream is = null;
 		      
 		      try { 
 		        	DefaultHttpClient httpClient = new DefaultHttpClient();
 					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/get_games.php");
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
 						stringArray[0]= person.getString("name");
 						stringArray[1]= person.getString("numplayers");
 						
 						
 						results[j] = stringArray;
 					}
 				
 					
 				
 					
 					
 					
 					
 					mo2.clear();
 					for(int player = 0; player<results.length;player++){
 						
 					 mo2.add(new Myobject(results[player][0], "", results[player][1]));
 					 	
 					}
 					
 					my2.notifyDataSetChanged();
 					
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
 		        
 	   }
   
    
    
   public void checkifgame(View v){
    	codestring = code.getText().toString();
    	BufferedReader br = null;
	      InputStream is = null;
	      int[] numbers = new int[7];
	      try { 
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
				URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/check_for_game.php?code="+codestring);
				HttpGet httpGet = new HttpGet();
				httpGet.setURI(url);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
				
				String line = br.readLine();
					exists.setText(line);
				br.close();
				
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
	        
    }
   
   public void creategame(View v){
   	codestring = code.getText().toString();
   	passstring = password.getText().toString();
   	BufferedReader br = null;
	      InputStream is = null;
	      
	      try { 
	        	DefaultHttpClient httpClient = new DefaultHttpClient();
				URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/new_game.php?code="+codestring+"&pass="+passstring);
				HttpGet httpGet = new HttpGet();
				httpGet.setURI(url);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				
				
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
	        
   }
   
   
   public void newnums(View v){
	   	codestring = code.getText().toString();
	   	String diff = diffi.getText().toString();
	   	String timeint = time.getText().toString();
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
		        	String address = "http://www.jumpingbeans.webege.com/Countdown/new_numbers.php?code="+codestring+"&difficulty="+diff+"&time="+timeint;
					Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();
		        	URI url = new URI(address);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
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
		        
	   }
   
   
   public void newplayer(View v){
	   	codestring = code.getText().toString();
	   	String namestring = name.getText().toString();
	   	
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/add_new_player.php?code="+codestring+"&name="+namestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					String line = br.readLine();
					PlayerID = line;
					br.close();
					
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
		        
	   }
   
   public void isstarted(View v){
	   	codestring = code.getText().toString();
	   	
	   	
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/check_if_started.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					String line = br.readLine();
						starteddisp.setText(line);
					br.close();
					
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
		        
	   }
   
   public void checkpass(View v){
	   	codestring = code.getText().toString();
	   	passstring = password.getText().toString();
	   	
	   	
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/check_pass.php?code="+codestring+"&pass="+ passstring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					String line = br.readLine();
						ispass.setText(line);
					br.close();
					
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
		        
	   }
   
   public void start(View v){
	   	codestring = code.getText().toString();
	   	
	   	
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/start_game.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
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
		        
	   }
   
   
   public void stopgame(View v){
	   	codestring = code.getText().toString();
	   	
	   	
	   	
	   	
		      
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/stop_game.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
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
		        
	   }
   
   
   public void getnums(View v){
	   	codestring = code.getText().toString();
	   	
	   	
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[8];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/get_numbers.php?code="+codestring);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
					
					StringBuffer sb = new StringBuffer("");
					String line = "";
					for(int x = 0; x<8;x++){
						line = br.readLine();
						numbers[x] = Integer.parseInt(line);
						sb.append(line+"\n");
					}
					
					nums.setText(sb.toString());
					timevalue  = numbers[7];
					
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
		        
	   }
   
   
   public void postdist(View v){
	   distance = dist.getText().toString();
	   
	   	
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      int[] numbers = new int[7];
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/post_distance.php?id="+PlayerID+"&distance="+distance);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
					
					
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
		        
	   }
   
   
   public void delplayer(View v){
	  
	   
	   	
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/delete_player.php?id="+PlayerID);
					HttpGet httpGet = new HttpGet();
					httpGet.setURI(url);
					HttpResponse httpResponse = httpClient.execute(httpGet);
					
					
					
					
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
		        
	   }
   
   
   public void getres(View v){
	   codestring = code.getText().toString();
	   
	   	
	   	BufferedReader br = null;
		      InputStream is = null;
		      
		      try { 
		        	DefaultHttpClient httpClient = new DefaultHttpClient();
					URI url = new URI("http://www.jumpingbeans.webege.com/Countdown/get_results.php?code="+codestring);
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
					//res.setText(person.getString("distance"));
					
					String print = String.format("In %s seconds these people got this far from %s", results[0][0], results[0][1]);
					
					res.setText(print);
					
					
					mo.clear();
					for(int player = 0; player<results.length;player++){
						
					 mo.add(new Myobject(results[player][2], results[player][3], results[player][4]));
					 	
					}
					
					my.notifyDataSetChanged();
					
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
					Toast.makeText(getApplicationContext(), codestring, 0).show();
				}
		        
	   }
  
   
   
  
    protected void updateTimer() {
		// TODO Auto-generated method stub
    	timedisplay.setText(Integer.toString(timevalue));
    	timevalue--;
	}
    
    

    
    
		private Runnable tupdate = new Runnable() {
			   public void run() {
				  
				   updateTimer();
				   //if it is not paused
				   
				   h.postDelayed(this,1000);
				}
			};

		

}*/
