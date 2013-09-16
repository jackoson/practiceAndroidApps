package com.webege.jumpingbeans.countdowntest;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;


public class ScoreBoard extends Activity {
	
	
	ListView speed;
	ListView timed;
	ArrayList<Myobject> alspeed;
	ArrayList<Myobject> altimed;
	MyAdapter aaspeed;
	MyAdapter aatimed;
	TabSpec spec;
	ScoresDataBase DBClass;
	RadioGroup rgt,rgs;

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
        
        /*spec = th.newTabSpec("three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("free");
        th.addTab(spec);*/
        
       
         rgt = (RadioGroup) findViewById(R.id.radioGroup1);
        rgt.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				
				altimed.clear();
				SQLiteDatabase db = DBClass.getReadableDatabase(); 
				
				
				String queryT = "";
				
				switch(rgt.getCheckedRadioButtonId()){
				case R.id.radio0:
					
					queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='easy' ORDER BY score DESC";
					break;
				case R.id.radio1:
					
					queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='medium' ORDER BY score DESC";
					break;
				case R.id.radio2:
					
					queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='hard' ORDER BY score DESC";
					break;
				case R.id.radio3:
					
					queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='extreme' ORDER BY score DESC";
					break;
				
				}
				
				
			        
			        
			        
			        Cursor results = db.rawQuery(queryT,null );
					
					 if (results == null){
						 altimed.add(new Myobject("Nothing Yet","",""));
				        	
				        }else if (!(results.moveToFirst()) || results.getCount() ==0){
				        	 altimed.add(new Myobject("Nothing Yet","",""));
				        	
				        }else{
				        	
				        	
				        	results.moveToFirst();
				        	do{
				        		altimed.add(new Myobject("Sam",Integer.toString(results.getInt(results.getColumnIndex("score"))),results.getString(results.getColumnIndex("difficulty"))));
				        		     		
				        	}while(results.moveToNext());
				        	
				        }
					 
				        
				        aatimed.notifyDataSetChanged();
			        
			        db.close();
			        DBClass.close();
				
			}
        	
        });
        
        
        rgs = (RadioGroup) findViewById(R.id.radioGroup2);
        rgs.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				
				alspeed.clear();
				SQLiteDatabase db = DBClass.getReadableDatabase(); 
				
				
				String querys = "";
				
				switch(rgs.getCheckedRadioButtonId()){
				case R.id.radio4:
					
					querys = "SELECT * FROM scores WHERE mode='speed' and difficulty='easy' ORDER BY score DESC";
					break;
				case R.id.radio5:
					
					querys = "SELECT * FROM scores WHERE mode='speed' and difficulty='medium' ORDER BY score DESC";
					break;
				case R.id.radio6:
					
					querys = "SELECT * FROM scores WHERE mode='speed' and difficulty='hard' ORDER BY score DESC";
					break;
				case R.id.radio7:
					
					querys = "SELECT * FROM scores WHERE mode='speed' and difficulty='extreme' ORDER BY score DESC";
					break;
				
				}
				
				
			        
			        
			        
			        Cursor results = db.rawQuery(querys,null );
					
					 if (results == null){
						 alspeed.add(new Myobject("Nothing Yet","",""));
				        	
				        }else if (!(results.moveToFirst()) || results.getCount() ==0){
				        	 alspeed.add(new Myobject("Nothing Yet","",""));
				        	
				        }else{
				        	
				        	
				        	results.moveToFirst();
				        	do{
				        		alspeed.add(new Myobject("Sam",Integer.toString(results.getInt(results.getColumnIndex("score"))),results.getString(results.getColumnIndex("difficulty"))));
				        		     		
				        	}while(results.moveToNext());
				        	
				        }
					 
				        
				        aaspeed.notifyDataSetChanged();
			        
			        db.close();
			        DBClass.close();
				
			}
        	
        });
        
        
        try {
            for (int i=0; i < th.getTabWidget().getChildCount();i++) {
                TextView tv = (TextView) th.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                if (tv != null) {
                    tv.setTextColor(Color.parseColor("#666666"));
                    
                    tv.setTextSize(40);
                    
                    
                }
                TextView tv2 = (TextView) th.getCurrentTabView().findViewById(android.R.id.title); // Selected Tab
                if (tv2 != null) {
                	tv2.setTextSize(40);
                	tv2.setTextColor(Color.parseColor("#666666"));
                   
                }
            }
        } catch (ClassCastException e) {
            // A precaution, in case Google changes from a TextView on the tabs.
        }
        
        
        speed = (ListView) findViewById(R.id.speed);
        View header = (View)getLayoutInflater().inflate(R.layout.header, null);
        speed.addHeaderView(header);
		alspeed = new ArrayList<Myobject>();
		aaspeed =  new MyAdapter(this, R.layout.itemrow,  alspeed);
		speed.setAdapter(aaspeed);
		
		
		
		timed = (ListView) findViewById(R.id.timed);
		header = (View)getLayoutInflater().inflate(R.layout.header, null);
        timed.addHeaderView(header);
		altimed = new ArrayList<Myobject>();
		aatimed =  new MyAdapter(this, R.layout.itemrow,  altimed);
		timed.setAdapter(aatimed);
		
		DBClass = new ScoresDataBase(this);
		
		/*
		SQLiteDatabase dataB = DBClass.getWritableDatabase();
		
		ContentValues vals = new ContentValues();
		
		vals.put("score", 4);
		vals.put("difficulty", "easy");
		vals.put("mode", "speed");
		
		
		dataB.insert("Scores", null, vals);
		dataB.close();
		*/
		
		
		
		SQLiteDatabase db = DBClass.getReadableDatabase(); 
		
		String queryS = "";
		String queryT = "";
		
		switch(rgt.getCheckedRadioButtonId()){
		case R.id.radio0:
			queryS = "SELECT * FROM scores WHERE mode='speed' and difficulty='easy' ORDER BY score DESC";
			queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='easy' ORDER BY score DESC";
			break;
		case R.id.radio1:
			queryS = "SELECT * FROM scores WHERE mode='speed' and difficulty='medium' ORDER BY score DESC";
			queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='medium' ORDER BY score DESC";
			break;
		case R.id.radio2:
			queryS = "SELECT * FROM scores WHERE mode='speed' and difficulty='hard' ORDER BY score DESC";
			queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='hard' ORDER BY score DESC";
			break;
		case R.id.radio3:
			queryS = "SELECT * FROM scores WHERE mode='speed' and difficulty='extreme' ORDER BY score DESC";
			queryT = "SELECT * FROM scores WHERE mode='timed' and difficulty='extreme' ORDER BY score DESC";
			break;
		
		}
		
		Cursor results = db.rawQuery(queryS,null );
		
		 if (results == null){
	        	alspeed.add(new Myobject("Nothing Yet","",""));
	        	
	        }else if (!(results.moveToFirst()) || results.getCount() ==0){
	        	alspeed.add(new Myobject("Nothing Yet","",""));
	        	
	        }else{
	        	
	        	
	        	results.moveToFirst();
	        	do{
	        		alspeed.add(new Myobject("Sam",Integer.toString(results.getInt(results.getColumnIndex("score"))),results.getString(results.getColumnIndex("difficulty"))));
	
	        	}while(results.moveToNext());
	        	
	        }
		 
	        
	        aaspeed.notifyDataSetChanged();
	        
	        
	        
	        results = db.rawQuery(queryT,null );
			
			 if (results == null){
				 altimed.add(new Myobject("Nothing Yet","",""));
		        	
		        }else if (!(results.moveToFirst()) || results.getCount() ==0){
		        	 altimed.add(new Myobject("Nothing Yet","",""));
		        	
		        }else{
		        	
		        	
		        	results.moveToFirst();
		        	do{
		        		altimed.add(new Myobject("Sam",Integer.toString(results.getInt(results.getColumnIndex("score"))),results.getString(results.getColumnIndex("difficulty"))));
		        		     		
		        	}while(results.moveToNext());
		        	
		        }
			 
		        
		        aatimed.notifyDataSetChanged();
	        
	        db.close();
	        DBClass.close();
        
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