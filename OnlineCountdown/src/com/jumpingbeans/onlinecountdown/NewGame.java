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

public class NewGame extends Activity {

	EditText name,pass;
	Button create;
	String gameName;
	int count = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);
        
        create = (Button) findViewById(R.id.button1);
        name = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.editText2);

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
        
        pass.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            public void afterTextChanged(Editable s) {
            	if(pass.getText().toString().contains(" ")){
            		Toast.makeText(getApplicationContext(), "No spaces Allowed", 0).show();
	            	String result = pass.getText().toString().replaceAll(" ", "");
	            	pass.setText(result);
	            	pass.setSelection(pass.getText().length());
            	}
            }
        });
        
        pass.setImeActionLabel("Create", KeyEvent.KEYCODE_ENTER);
        
        pass.setOnEditorActionListener(new OnEditorActionListener(){

			public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				

				// TODO Auto-generated method stub
				gameName = name.getText().toString();
				String passstring = pass.getText().toString();
				if(gameName.length()<1 || passstring.length()<1){
					Toast.makeText(getApplicationContext(), "Enter details", 0).show();
				}else{
					Log.i("count", Integer.toString(count));
					 
					if(count<1)
					new noReturn().execute(getString(R.string.BaseAddress)+"new_game.php?code="+gameName+"&pass="+passstring);
					
					count++;
				}
				return false;
		    }
				
		
			        	
        });
        
        
        
        create.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				gameName = name.getText().toString();
				String passstring = pass.getText().toString();
				if(gameName.length()<1 || passstring.length()<1){
					Toast.makeText(getApplicationContext(), "Enter details", 0).show();
				}else{
					
					new noReturn().execute(getString(R.string.BaseAddress)+"new_game.php?code="+gameName+"&pass="+passstring);
				}
			}
        	
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_game, menu);
        return true;
    }
    
    
    
    
    
    
    

    private class noReturn extends AsyncTask<String, Integer,String>{

    	ProgressDialog dialog;
    	
    	
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		dialog = new ProgressDialog(NewGame.this);
    		dialog.setMessage("Creating Game");
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
					Toast.makeText(getApplicationContext(), e.getMessage(), 0).show();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			 return "";
		}
		
		
		protected void onPostExecute(String result) {
			
			Intent i = new Intent(NewGame.this, RoundSettings.class);
			i.putExtra("gameName", gameName);
			i.putExtra("Already_Joined", false);
			startActivity(i);
			
			
	     }
		
		
	}
    
    

   
    
}
