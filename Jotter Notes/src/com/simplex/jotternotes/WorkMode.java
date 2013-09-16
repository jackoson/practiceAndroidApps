package com.simplex.jotternotes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WorkMode extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.work_mode_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.work_mode, menu);
		return true;
	}

}
