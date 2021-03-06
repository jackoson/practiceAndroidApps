package com.simplex.jotternotes;
import java.util.ArrayList;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {

	String message;
	
	EditText editText;
	TextView textBox;
	ListView lv;
	ArrayList<String> al;
	ArrayAdapter aa;
	NoteBase NB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
		
		editText = (EditText) findViewById(R.id.editText1);
		
		editText.setTypeface(myTypeface);
		
		Button b1 = (Button) findViewById(R.id.homeAdd);
		Button b2 = (Button) findViewById(R.id.homeClear);
		
		b1.setTypeface(myTypeface);
		b2.setTypeface(myTypeface);
		
		int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		TextView actionBarTextView = (TextView)findViewById(actionBarTitleId); 
		actionBarTextView.setTextColor(Color.rgb(63, 182, 182));
		
		lv = (ListView) findViewById(R.id.listview);
		al = new ArrayList<String>();
		aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1, al);

		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		lv.setFocusableInTouchMode(true);
		lv.requestFocus(1);
		
		NB = new NoteBase(this);
		
		
		SQLiteDatabase dataB = NB.getWritableDatabase();
		/*
		ContentValues vals = new ContentValues();
		vals.put("id", 5);
		vals.put("title", "Berse");
		vals.put("text", "sleep");
		dataB.insert("TextTable", null, vals);
		*/
		//dataB.execSQL("DELETE FROM " + "TitleTable");
		//dataB.execSQL("DELETE FROM " + "TextTable");
		dataB.close();
	
		
		
		
       SQLiteDatabase db = NB.getReadableDatabase();
        
       // String command = "select distict title from Notes";
        
        Cursor C = db.query("TitleTable", new String[] { "id","title" }, null, null, null, null, null);
        		
       		
        if (C == null){
        	al.add("Nothing Yet");
        	//Toast.makeText(getApplicationContext(), "nope", Toast.LENGTH_SHORT).show();
        }else if (!(C.moveToFirst()) || C.getCount() ==0){
        	
        //	Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
        }else{
        	//Toast.makeText(getApplicationContext(), "gto sometink", Toast.LENGTH_SHORT).show();
        	
        	C.moveToLast();
        	do{
        		al.add(C.getString(1));
        		     		
        	}while(C.moveToPrevious());
        	
        }
        
        aa.notifyDataSetChanged();
        
        db.close();
        NB.close();
		
		
		lv.setAdapter(aa);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendList(View view){
		
		message = editText.getText().toString();
		if(message.length()>0){
		al.add(0, message);
		Toast.makeText(getApplicationContext(), "Your item has been added.", Toast.LENGTH_SHORT).show();
		aa.notifyDataSetChanged();

		
		SQLiteDatabase db = NB.getReadableDatabase();
        
	       // String command = "select distict title from Notes";
	        
	        Cursor C = db.query("TitleTable", new String[] { "id","title" }, null, null, null, null, null);
	        		
		C.moveToLast();
		
		int nextpos;
		
		if(C.getCount()<1){
			nextpos = 1;
		}else{
			nextpos = C.getInt(0);
			nextpos++;	
		}
		
		SQLiteDatabase dataB = NB.getWritableDatabase();
		
		ContentValues vals = new ContentValues();
		vals.put("id", nextpos);
		vals.put("title", message );
		//vals.put("text", "sleep");
		
		
		dataB.insert("TitleTable", null, vals);
		dataB.close();



		}
		else{
			Toast.makeText(getApplicationContext(), "Nothing Entered", Toast.LENGTH_SHORT).show();
		}
		
		editText.setText("");
	}

	
	public void clearLists(View view){
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Warning");
		dialogBuilder.setMessage("Are you sure you want to delete all notes?");
		dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialog, int which) {
			}
		});	
		dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				al.clear();
				Toast.makeText(getApplicationContext(), "List cleared!", Toast.LENGTH_SHORT).show();
				aa.notifyDataSetChanged();
				
				
				SQLiteDatabase dataB = NB.getWritableDatabase();
				dataB.execSQL("DELETE FROM " + "TitleTable");
				dataB.execSQL("DELETE FROM " + "TextTable");
				dataB.close();
				
				
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
		String NoteSelected = arg0.getItemAtPosition(arg2).toString();
		
		try{
			Class ourClass = Class.forName("com.simplex.jotternotes.NotesContentPage");
			Intent intent1 = new Intent(MainActivity.this, ourClass);
			intent1.putExtra("NoteSelected",NoteSelected);
			startActivity(intent1);
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		final String NoteSelected = arg0.getItemAtPosition(arg2).toString();
		
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Warning");
		dialogBuilder.setMessage("Are you sure you want to delete the note '" + NoteSelected +"'?");
		dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialog, int which) {
			}
		});	
		dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				al.remove(arg2);
				Toast.makeText(getApplicationContext(), "List Deleted!", Toast.LENGTH_SHORT).show();
				aa.notifyDataSetChanged();
				
				SQLiteDatabase dataB = NB.getWritableDatabase();
				dataB.delete("TitleTable", "title=?", new String[] { NoteSelected });
				dataB.delete("TextTable", "title=?", new String[] { NoteSelected });
				dataB.close();
				
				
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
				
		return false;
	}
	
	public void goToWorkMode(){
		startActivity(new Intent(MainActivity.this, WorkMode.class));
	}
	
	
	
}


