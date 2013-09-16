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
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;

public class NewPlayer extends Activity {

	Button join;
	EditText name;
	String gameName;
	boolean creator;
	int count = 0;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        gameName = getIntent().getExtras().getString("gameName");
        creator = getIntent().getExtras().getBoolean("creator");
        
        if(getIntent().getExtras().getBoolean("Already_Joined")){
        	
        	Toast.makeText(getApplicationContext(), "already joined", 0).show();
        	String PlayerID = getIntent().getExtras().getString("PlayerID");
        	
        	if(creator){
        		Toast.makeText(getApplicationContext(), "is creator", 0).show();
        		Intent i = new Intent(NewPlayer.this, Start.class);
    			i.putExtra("gameName", gameName);
    			i.putExtra("PlayerID", PlayerID);
    			i.putExtra("creator", true);
    			startActivity(i);
        	}else if (creator==false){
        		Toast.makeText(getApplicationContext(), "not creator", 0).show();
        		Intent i = new Intent(NewPlayer.this, PlayScreen.class);
    			i.putExtra("gameName", gameName);
    			i.putExtra("PlayerID", PlayerID);
    			i.putExtra("creator", false);
    			startActivity(i);
        	}
        	
			
			
        }else{
        
        
        setContentView(R.layout.new_player);
        
        join = (Button) findViewById(R.id.button1);
        name = (EditText) findViewById(R.id.editText2);
        
        name.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            public void afterTextChanged(Editable s) {
            	if(name.getText().toString().contains(" ")){
            		Toast.makeText(getApplicationContext(), "No spaces Allowed", 0).show();
	            	String result = name.getText().toString().replaceAll(" ", "");
	                name.setText(result);
	                name.setSelection(name.getText().length());
            	}
            }
        });
        
        
        name.setImeActionLabel("Join", KeyEvent.KEYCODE_ENTER);
        name.setOnEditorActionListener(new OnEditorActionListener(){

			public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				
				String namestring = name.getText().toString();
				if(namestring.length()<1){
					Toast.makeText(getApplicationContext(), "Enter a name", 0).show();
				}else{
					Log.i("count", Integer.toString(count));
					
					if(count<1)
					new newplayertask().execute(getString(R.string.BaseAddress)+"add_new_player.php?code="+gameName+"&name="+namestring);
					count++;
				}
				return false;
		    }
				
		
			        	
        });
        
        
        join.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String namestring = name.getText().toString();
				if(namestring.length()<1){
					Toast.makeText(getApplicationContext(), "Enter a name", 0).show();
				}else{
					new newplayertask().execute(getString(R.string.BaseAddress)+"add_new_player.php?code="+gameName+"&name="+namestring);
				}
			}
        	
        });
        
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_player, menu);
        return true;
    }

private class newplayertask extends AsyncTask<String, Integer,String>{


	ProgressDialog dialog;
	
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		dialog = new ProgressDialog(NewPlayer.this);
		dialog.setMessage("Joining game");
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
		
		
		
		protected void onPostExecute(String idS) {
			
			if(creator){
				Toast.makeText(getApplicationContext(), "is creator", 0).show();
				Intent i = new Intent(NewPlayer.this, Start.class);
				i.putExtra("gameName", gameName);
				i.putExtra("PlayerID", idS);
				i.putExtra("creator", true);
				startActivity(i);
			}else if(creator == false){
				Toast.makeText(getApplicationContext(), "isnt creator", 0).show();
				Intent i = new Intent(NewPlayer.this, PlayScreen.class);
				i.putExtra("gameName", gameName);
				i.putExtra("PlayerID", idS);
				i.putExtra("creator", false);
				startActivity(i);
			}else{
				Toast.makeText(getApplicationContext(), "error", 0).show();
			}
			

				
				Toast.makeText(getApplicationContext(), "Done", 0).show();
		     
				if (dialog.isShowing()) {
			           dialog.dismiss();
			        }
				
	     }
		
	}
    
}
