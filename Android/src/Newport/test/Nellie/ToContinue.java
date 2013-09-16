package Newport.test.Nellie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ToContinue extends Activity implements View.OnClickListener{
	
	protected void onCreate(Bundle m) {
		// TODO Auto-generated method stub
		super.onCreate(m);
		setContentView(R.layout.tocontinue);
		
		Button custom = (Button) findViewById(R.id.contin);
	
		custom.setOnClickListener(this);
		
		
		
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent("Newport.test.Nellie.MENU");
		startActivity(i);
	}
	
	  @Override
			protected void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
			
				finish();
			}
	
}