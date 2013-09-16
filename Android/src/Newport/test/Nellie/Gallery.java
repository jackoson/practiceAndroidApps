package Newport.test.Nellie;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Gallery extends ListActivity{
	String[] options = {"Normal Rackham","Casual Rackham", "Rackham and the Coat hanger", "Rackham in the rain",
			"Rackham eating a sausage roll","Rackham eating close-up","RACKHAM WEARING A JUMPER", "Brett being Brett", "BACK"};
	String[] optionC = {"F", "G","one","H","B","C","E","D", "Menu"};
	@Override
	protected void onCreate(Bundle m) {
		// TODO Auto-generated method stub
		super.onCreate(m);
		setListAdapter(new ArrayAdapter <String>(Gallery.this ,android.R.layout.simple_list_item_1 , options ));
	}
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese = optionC[position];
		try{
		Class ourClass = Class.forName("Newport.test.Nellie." + cheese);
		Intent intent = new Intent(Gallery.this,ourClass);
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
		finish();
		break;
		}
		return false;
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
}
}
