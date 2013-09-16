package com.simplex.countdownsimplex;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoresDataBase extends SQLiteOpenHelper{
	
	
	private static final String MAKE_TABLE1_QUERY = "create table Scores (id INT primary key," +
    		" score INT, difficulty TEXT, mode TEXT)";
	
	private static int databaseversion = 1;
	
	

	public ScoresDataBase(Context context) {
		super(context, "PersonalScoreboard", null, databaseversion);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL(MAKE_TABLE1_QUERY);
		
	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("drop table if exists Scores");
		this.onCreate(db);
		
		// TODO Auto-generated method stub
		
	}

}
