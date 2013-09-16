package com.simplex.jotternotes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends Activity {

@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		finish();
	}


String message;
	
	
	EditText editText;
	
	NoteBase NB;
	String Title;
	String Text;
	boolean isnew = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_note);
		
		
		editText = (EditText) findViewById(R.id.editText1);
		
		
		
		
		
		Title = getIntent().getExtras().getString("title");
		
		String temp = getIntent().getExtras().getString("new");
		
		if(temp.equals("no")){
			isnew = false;
		}else{
			isnew = true;
		}
		
		if(isnew){
			Text = "";
			
		}else{
			Text = getIntent().getExtras().getString("text");
			
		}
		
		setTitle("List: "+Title+":EditMode");
		
		
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
        
     
        
        Cursor C = db.query("TextTable", new String[] { "id","text" }, "title=? and text=?", new String[] { Title, Text },  null, null, null);
        		
       		
        if (C == null){
        	
        	//Toast.makeText(getApplicationContext(), "nope", Toast.LENGTH_SHORT).show();
        }else if (!(C.moveToFirst()) || C.getCount() ==0){
        	
        	//Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
        }else{
        	///Toast.makeText(getApplicationContext(), "gto sometink", Toast.LENGTH_SHORT).show();
        	
        	C.moveToLast();
        	do{
        		editText.setText(C.getString(1));
        		     		
        	}while(C.moveToPrevious());
        	
        }
        
      
        
        db.close();
        NB.close();
		
        String newtext = editText.getText().toString();
        editText.setText("");
        editText.append(newtext);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		return true;
	}
	
	public void sendList(View view){
		
		message = editText.getText().toString();
		if(message.length()>0){
		
		
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
		//isnew = true;
		if(isnew){
			dataB.insert("TextTable", null, vals);
		}else{
			Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
			dataB.update("TextTable", vals , "title=? AND text=?", new String[] { Title, Text });
		}
		
		
		
		
		dataB.close();



		}
		else{
			Toast.makeText(getApplicationContext(), "Nothing Entered", Toast.LENGTH_SHORT).show();
		}
		
		editText.setText("");
		
		NotesContentPage.NCP.finish(); 
		
		try{
			Class ourClass = Class.forName("com.example.notetest." + "NotesContentPage");
			Intent intent1 = new Intent(NewNote.this,ourClass);
			intent1.putExtra("NoteSelected",Title);
			startActivity(intent1);
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		
	}

	
	public void cancel(View view){
		
		finish();
		
		/*		
		try{
			Class ourClass = Class.forName("com.example.notetest." + "NotesContentPage");
			Intent intent1 = new Intent(NewNote.this,ourClass);
			intent1.putExtra("NoteSelected",Title);
			startActivity(intent1);
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		*/
		
		/*
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Warning");
		dialogBuilder.setMessage("Are you sure you want to delete all notes?");
		dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialog, int which) {
			}
		});	
		dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				editText.clear();
				Toast.makeText(getApplicationContext(), "List cleared!", Toast.LENGTH_SHORT).show();
				aa.notifyDataSetChanged();
				
				SQLiteDatabase dataB = NB.getWritableDatabase();
				dataB.delete("TextTable", "title=?", new String[] { Title });
				dataB.close();
				
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
		*/
	}

	
}
