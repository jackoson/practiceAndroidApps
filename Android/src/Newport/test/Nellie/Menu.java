package Newport.test.Nellie;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{
	String[] options = {"Jumper Counter","Find the jumper!", "The Rackham Gallery", "Sounds of the J ROD", 
			"Send Fan Mail","Fan Cam","Zaldicade Cast","How long until he puts on a jumper","Give him a jumper", "save your name"};
	String[] optionC = {"StartingPoint", "FindtheJumpersplash","Gallery","Sound", 
			"Email", "FanCam", "WebBrowser","StopWatch","Scribbler", "Stream"};
	@Override
	protected void onCreate(Bundle e) {
		// TODO Auto-generated method stub
		super.onCreate(e);
		setListAdapter(new ArrayAdapter <String>(Menu.this ,android.R.layout.simple_list_item_1 , options ));
	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese = optionC[position];
		try{
		Class ourClass = Class.forName("Newport.test.Nellie." + cheese);
		Intent intent = new Intent(Menu.this,ourClass);
		startActivity(intent);} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}


	
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.cool_menu, menu);
		return true;
	}


	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.about:
		Intent ab = new Intent("Newport.test.Nellie.ABOUTUS");
		startActivity(ab);
		break;
		
		case R.id.exit:
		System.exit(0);
		break;
		
		case R.id.prefs:
			Intent b = new Intent("Newport.test.Nellie.PREFS");
			startActivity(b);
			break;
		
		}
		return false;
	}

	
	
	
	

}
