package com.simplex.countdownsimplex;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;


public class ScoreBoard extends Activity {
	
	
	ListView speed;
	ListView timed;
	ArrayList<String> alspeed;
	ArrayList<String> altimed;
	ArrayAdapter aaspeed;
	ArrayAdapter aatimed;
	TabSpec spec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        
        
        TabHost th = (TabHost) findViewById(R.id.tabhost);
        th.setup();
        spec = th.newTabSpec("one");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Timed");
        th.addTab(spec);
        
        spec = th.newTabSpec("two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Speed");
        th.addTab(spec);
        
        spec = th.newTabSpec("three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("free");
        th.addTab(spec);
        
        
        
        
        
        speed = (ListView) findViewById(R.id.speed);
		alspeed = new ArrayList<String>();
		aaspeed = new ArrayAdapter(this,android.R.layout.simple_list_item_1, alspeed);
		speed.setAdapter(aaspeed);
		
		
		
		timed = (ListView) findViewById(R.id.timed);
		altimed = new ArrayList<String>();
		aatimed = new ArrayAdapter(this,android.R.layout.simple_list_item_1, altimed);
		timed.setAdapter(aatimed);
		
		ScoresDataBase DBClass = new ScoresDataBase(this);
		
		/*
		SQLiteDatabase dataB = DBClass.getWritableDatabase();
		ContentValues vals = new ContentValues();
		
		vals.put("score", 137);
		vals.put("difficulty", "extreme");
		vals.put("mode", "speed");
		
		
		dataB.insert("Scores", null, vals);
		dataB.close();
		*/
		
		
		
		SQLiteDatabase db = DBClass.getReadableDatabase(); 
		
		
		
		Cursor results = db.rawQuery("SELECT * FROM scores WHERE mode='speed' ORDER BY score DESC",null );
		
		 if (results == null){
	        	alspeed.add("Nothing Yet");
	        	
	        }else if (!(results.moveToFirst()) || results.getCount() ==0){
	        	alspeed.add("Nothing Yet");
	        	
	        }else{
	        	
	        	
	        	results.moveToFirst();
	        	do{
	        		alspeed.add(Integer.toString(results.getInt(results.getColumnIndex("score")))+" "+results.getString(results.getColumnIndex("difficulty"))+" "+results.getString(results.getColumnIndex("mode")));
	        		     		
	        	}while(results.moveToNext());
	        	
	        }
		 
	        
	        aaspeed.notifyDataSetChanged();
	        
	        
	        
	        results = db.rawQuery("SELECT * FROM scores WHERE mode='timed' ORDER BY score DESC ",null );
			
			 if (results == null){
		        	altimed.add("Nothing Yet");
		        	
		        }else if (!(results.moveToFirst()) || results.getCount() ==0){
		        	altimed.add("Nothing Yet");
		        	
		        }else{
		        	
		        	
		        	results.moveToFirst();
		        	do{
		        		altimed.add(Integer.toString(results.getInt(results.getColumnIndex("score")))+" "+results.getString(results.getColumnIndex("difficulty"))+" "+results.getString(results.getColumnIndex("mode")));
		        		     		
		        	}while(results.moveToNext());
		        	
		        }
			 
		        
		        aatimed.notifyDataSetChanged();
	        
	        db.close();
	        DBClass.close();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_score_board, menu);
        return true;
    }

    
}
//dataB.delete("scores", null, null);



		/*
		ContentValues vals = new ContentValues();
		
		vals.put("score", 194);
		vals.put("difficulty", "easy");
		vals.put("mode", "speed");
		
		
		dataB.insert("Scores", null, vals);
		
		

		vals = new ContentValues();
		
		vals.put("score", 137);
		vals.put("difficulty", "extreme");
		vals.put("mode", "speed");
		
		
		dataB.insert("Scores", null, vals);
		
		vals = new ContentValues();
		
		vals.put("score", 187);
		vals.put("difficulty", "hard");
		vals.put("mode", "speed");
		
		
		dataB.insert("Scores", null, vals);
		
		vals = new ContentValues();
		
		vals.put("score", 23);
		vals.put("difficulty", "medium");
		vals.put("mode", "speed");
		
		
		dataB.insert("Scores", null, vals);
		
		vals = new ContentValues();
		
		vals.put("score", 4);
		vals.put("difficulty", "easy");
		vals.put("mode", "speed");
		
		
		dataB.insert("Scores", null, vals);
		*/