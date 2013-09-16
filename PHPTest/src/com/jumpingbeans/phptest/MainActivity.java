package com.jumpingbeans.phptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

	
	

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      
        BufferedReader br = null;
        InputStream is = null;;
        
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setText("still nope");
    
        int[] numbers = new int[6];
        
        
        
        
       
        try { 
        	DefaultHttpClient httpClient = new DefaultHttpClient();
			URI url = new URI("http://www.jumpingbeans.webege.com/test.php");
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			for(int x = 0; x<6;x++){
				line = br.readLine();
				numbers[x] = Integer.parseInt(line);
				sb.append(line+"\n");
			}
			br.close();
			tv.setText(sb.toString());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
