package com.example.notetest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WorkMode extends Activity implements OnItemLongClickListener,
		OnItemClickListener {

	String message;

	EditText editText;
	TextView textBox;
	ListView lv;
	ArrayList<String> al;
	ArrayAdapter aa;
	NoteBase NB;
	int year = 0;
	int day = 0;
	int month = 0;
	int timeInMill ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_mode);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		editText = (EditText) findViewById(R.id.editText1);

		lv = (ListView) findViewById(R.id.lv);
		al = new ArrayList<String>();
		aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);

		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		lv.setFocusableInTouchMode(true);
		lv.requestFocus(1);

		NB = new NoteBase(this);

		SQLiteDatabase dataB = NB.getWritableDatabase();
		/*
		 * ContentValues vals = new ContentValues(); vals.put("id", 5);
		 * vals.put("title", "Berse"); vals.put("text", "sleep");
		 * dataB.insert("TextTable", null, vals);
		 */
		// dataB.execSQL("DELETE FROM " + "TitleTable");
		// dataB.execSQL("DELETE FROM " + "TextTable");
		dataB.close();
		
		refreshArrayList();
		
	

		lv.setAdapter(aa);

	}

	private void refreshArrayList() {
		// TODO Auto-generated method stub
		
		al.clear();
		aa.notifyDataSetChanged();
		
		SQLiteDatabase db = NB.getReadableDatabase();

		// String command = "select distict title from Notes";

		Cursor C = db.query("workTable", new String[] { "id", "job","date" }, null,
				null, null, null, "date");

		if (C == null) {
			al.add("Nothing Yet");
			
		} else if (!(C.moveToFirst()) || C.getCount() == 0) {

		
		} else {
			//Toast.makeText(getApplicationContext(), "gto sometink",Toast.LENGTH_SHORT).show();

			C.moveToLast();
			do {
				final Calendar c = Calendar.getInstance();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				
				c.setTimeInMillis(Long.parseLong(C.getString(C.getColumnIndex("date"))));
				al.add(String.format("%s (by %d/%d/%d)", C.getString(1), c.DAY_OF_MONTH, c.MONTH, c.YEAR));

			} while (C.moveToPrevious());

		}

		aa.notifyDataSetChanged();

		db.close();
		NB.close();

	}

	public void chooseDate(View view) {
		// TODO Auto-generated method stub
		showDialog(999);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 999:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			
			

			//
			final Calendar c = Calendar.getInstance();
			c.set(year, month, day);
			timeInMill = (int) c.getTimeInMillis();
			
			
			
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.workmode:
			startActivity(new Intent(WorkMode.this, WorkMode.class));
			break;
		case R.id.settings:

			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void sendList(View view) {

		message = editText.getText().toString();
		if (message.length() > 0) {
			al.add(0, message);
			Toast.makeText(getApplicationContext(),
					"Your item has been added.", Toast.LENGTH_SHORT).show();
			aa.notifyDataSetChanged();

			SQLiteDatabase db = NB.getReadableDatabase();

			// String command = "select distict title from Notes";

			Cursor C = db.query("workTable", new String[] { "id", "job" },
					null, null, null, null, null);

			C.moveToLast();

			int nextpos;

			if (C.getCount() < 1) {
				nextpos = 1;
			} else {
				nextpos = C.getInt(0);
				nextpos++;
			}

			SQLiteDatabase dataB = NB.getWritableDatabase();

			ContentValues vals = new ContentValues();
			vals.put("id", nextpos);
			vals.put("job", message);
			vals.put("date", timeInMill);

			dataB.insert("workTable", null, vals);
			dataB.close();
			
			refreshArrayList();
			

		} else {
			Toast.makeText(getApplicationContext(), "Nothing Entered",
					Toast.LENGTH_SHORT).show();
		}

		editText.setText("");
	}

	public void clearLists(View view) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Warning");
		dialogBuilder.setMessage("Are you sure you want to delete all notes?");
		dialogBuilder.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
					}
				});
		dialogBuilder.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						al.clear();
						Toast.makeText(getApplicationContext(),
								"List cleared!", Toast.LENGTH_SHORT).show();
						aa.notifyDataSetChanged();

						SQLiteDatabase dataB = NB.getWritableDatabase();
						dataB.execSQL("DELETE FROM " + "workTable");
						dataB.close();
						
						
						final Calendar c = Calendar.getInstance();
						year = c.get(Calendar.YEAR);
						month = c.get(Calendar.MONTH);
						day = c.get(Calendar.DAY_OF_MONTH);

					}
				});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
		
		
		

	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		/*
		 * Toast.makeText(getApplicationContext(),
		 * arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
		 * String NoteSelected = arg0.getItemAtPosition(arg2).toString();
		 * 
		 * 
		 * 
		 * 
		 * try{ Class ourClass = Class.forName("com.example.notetest." +
		 * "NotesContentPage"); Intent intent1 = new
		 * Intent(WorkMode.this,ourClass);
		 * intent1.putExtra("NoteSelected",NoteSelected);
		 * startActivity(intent1); } catch (ClassNotFoundException e){
		 * e.printStackTrace(); }
		 */
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
			final int arg2, long arg3) {
		// TODO Auto-generated method stub

		final String NoteSelected = arg0.getItemAtPosition(arg2).toString();

		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Warning");
		dialogBuilder.setMessage("Are you sure you want to delete: "
				+ NoteSelected);
		dialogBuilder.setNegativeButton("Nope",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
					}
				});
		dialogBuilder.setPositiveButton("Yeah Man",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						al.remove(arg2);
						Toast.makeText(getApplicationContext(),
								"List Deleted!", Toast.LENGTH_SHORT).show();
						aa.notifyDataSetChanged();

						SQLiteDatabase dataB = NB.getWritableDatabase();
						dataB.delete("workTable", "job=?",
								new String[] { NoteSelected });
						
						dataB.close();

					}
				});
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();

		return false;

	}

}
