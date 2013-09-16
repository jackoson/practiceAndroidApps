package com.example.vote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends Activity{

	SharedPreferences getPrefs;
	SharedPreferences.Editor prefEditor;
	int[] scores = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
	String[] bands = {"ABBA","Beatles","Busted","Beny Hill","Queen","Scouting For Girls","Alesha Dixon","Beyonce","Duffy", "James Blunt","Katy Perry","Kings of Leon","Lady Gaga","Lily Allen","Nickleback","Paul Mcartney","Alexandra Burke"};
	TextView tv;
	Button reset;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		tv = (TextView) findViewById(R.id.tv);
		reset = (Button) findViewById(R.id.button1);
		getPrefs =PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		int topscore = rank(scores);
		
	    for(int x = 0; x<scores.length;x++){
        scores[x] = getPrefs.getInt(Integer.toString(x), 100);
	    }
	    
	   
	    tv.setText(String.format("The Band Ratings are as follows\n %s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n ",bands[0] ,scores[0],bands[1],scores[1],bands[2],scores[2],bands[3],scores[3],bands[4],scores[4],bands[5],scores[5],bands[6],scores[6],bands[7],scores[7],bands[8],scores[8],bands[9],scores[9],bands[10],scores[10],bands[11],scores[11],bands[12],scores[12],bands[13],scores[13],bands[14],scores[14],bands[15],scores[15],bands[16],scores[16]));
	    reset.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			Toast.makeText(getApplicationContext(), "pic two" , Toast.LENGTH_SHORT).show();
    			for(int i = 0;i<scores.length;i++)
    				scores[i] = 100;
    			
    			 tv.setText(String.format("The Band Ratings are as follows\n %s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n%s:\t %d\n ",bands[0] ,scores[0],bands[1],scores[1],bands[2],scores[2],bands[3],scores[3],bands[4],scores[4],bands[5],scores[5],bands[6],scores[6],bands[7],scores[7],bands[8],scores[8],bands[9],scores[9],bands[10],scores[10],bands[11],scores[11],bands[12],scores[12],bands[13],scores[13],bands[14],scores[14],bands[15],scores[15],bands[16],scores[16]));
    			   
    			prefEditor = getPrefs.edit();
    				for(int i = 0;i<scores.length;i++){
    				
    				prefEditor.putInt(Integer.toString(i), scores[i]);
			
    			}
    				prefEditor.commit();
    		}
    	});
	}

	public int rank(int[] s) {
		// TODO Auto-generated method stub
		double max = s[0] ;
		for ( int k=0; k<s.length; k++ ){
		if ( s[k] > max )
			max = s[k];
		}
		return (int) max;
	}

}
