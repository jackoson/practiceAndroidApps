package com.example.notetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteBase extends SQLiteOpenHelper  {

	private static final int DATABASE_VERSION = 3;
   
    
    
    private static final String TABLE_CREATE = "create table TextTable (id INT primary key," +
    		" title TEXT, text TEXT)";

    private static final String TABLE2_CREATE = "create table TitleTable (id INT primary key," +
    		" title TEXT)";
    
    NoteBase(Context context) {
        super(context, "NotesDataBase", null, DATABASE_VERSION);
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(TABLE2_CREATE);
		db.execSQL(TABLE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists Notes");
		this.onCreate(db);
	}

	

}
