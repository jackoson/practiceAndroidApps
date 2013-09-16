package com.example.notetest;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class NotesContentPage extends Activity implements OnItemClickListener, OnItemLongClickListener {

String message;
	
	
	EditText editText;
	TextView textBox;
	ListView lv;
	ArrayList<String> al;
	ArrayAdapter aa;
	NoteBase NB;
	String Title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes_content_page);
		
		
		editText = (EditText) findViewById(R.id.editText1);
		
		lv = (ListView) findViewById(R.id.lv);
		al = new ArrayList<String>();
		aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1, al);

		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		
		Title = getIntent().getExtras().getString("NoteSelected");
		
		setTitle("List: "+Title);
		
		
		NB = new NoteBase(this);
		
		/*
		SQLiteDatabase dataB = NB.getWritableDatabase();
	
		ContentValues vals = new ContentValues();
		vals.put("id", 4);
		vals.put("title", "todo");
		vals.put("text", "do math");
		
		
		dataB.insert("TextTable", null, vals);
		dataB.close();
		
		*/
		
		
       SQLiteDatabase db = NB.getReadableDatabase();
        
     
        
        Cursor C = db.query("TextTable", new String[] { "id","text" }, "title=?", new String[] { Title },  null, null, null);
        		
       		
        if (C == null){
        	//al.add("Nothing Yet");
        	//Toast.makeText(getApplicationContext(), "nope", Toast.LENGTH_SHORT).show();
        }else if (!(C.moveToFirst()) || C.getCount() ==0){
        	
        	//Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
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
		
		return true;
	}
	
	public void sendList(View view){
		
		try{
			Class ourClass = Class.forName("com.example.notetest." + "NewNote");
			Intent intent1 = new Intent(NotesContentPage.this,ourClass);
			intent1.putExtra("title",Title);
			intent1.putExtra("text", "13579");
			intent1.putExtra("new","yes");
			startActivity(intent1);
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		
		/*message = editText.getText().toString();
		if(message.length()>0){
		al.add(0, message);
		Toast.makeText(getApplicationContext(), "Your item has been added.", Toast.LENGTH_SHORT).show();
		aa.notifyDataSetChanged();

		
		SQLiteDatabase db = NB.getReadableDatabase();
        
	       // String command = "select distict title from Notes";
	        
	        Cursor C = db.query("TextTable", new String[] { "id","text" }, null, null, null, null, null);
	        		
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
		vals.put("title", Title );
		vals.put("text", message);
		
		
		dataB.insert("TextTable", null, vals);
		dataB.close();



		}
		else{
			Toast.makeText(getApplicationContext(), "Nothing Entered", Toast.LENGTH_SHORT).show();
		}
		
		editText.setText("");*/
		
	}

	
	
	
	public void quickadd(View view) {
		// TODO Auto-generated method stub
		
		message = editText.getText().toString();
		if(message.length()>0){
		al.add(0, message);
		Toast.makeText(getApplicationContext(), "Your item has been added.", Toast.LENGTH_SHORT).show();
		aa.notifyDataSetChanged();

		
		SQLiteDatabase db = NB.getReadableDatabase();
        
	       // String command = "select distict title from Notes";
	        
	        Cursor C = db.query("TextTable", new String[] { "id","text" }, null, null, null, null, null);
	        		
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
		vals.put("title", Title );
		vals.put("text", message);
		
		
		dataB.insert("TextTable", null, vals);
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
				dataB.delete("TextTable", "title=?", new String[] { Title });
				dataB.close();
				
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		
		String NoteSelected = arg0.getItemAtPosition(arg2).toString();
		
		
		
		
		try{
			Class ourClass = Class.forName("com.example.notetest." + "NewNote");
			Intent intent1 = new Intent(NotesContentPage.this,ourClass);
			intent1.putExtra("title",Title);
			intent1.putExtra("text", NoteSelected);
			intent1.putExtra("new","no");
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
		dialogBuilder.setMessage("Are you sure you want to delete this note? ");
		dialogBuilder.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialog, int which) {
			}
		});	
		dialogBuilder.setPositiveButton("Yeah Man", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				al.remove(arg2);
				Toast.makeText(getApplicationContext(), "Note Deleted!", Toast.LENGTH_SHORT).show();
				aa.notifyDataSetChanged();
				
				SQLiteDatabase dataB = NB.getWritableDatabase();
				dataB.delete("TextTable", "title=? AND text=?", new String[] { Title, NoteSelected });
				dataB.close();
				
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
		
		
		
		
		return false;
		
		
		
		
		
	}
	
	
	
	
}
