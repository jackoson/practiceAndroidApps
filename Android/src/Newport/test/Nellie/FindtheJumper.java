package Newport.test.Nellie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


	public class FindtheJumper extends Activity {
	    /** Called when the activity is first created. */
		
		Button jump;
		Button eye;
		Button give;
		Thread timer;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.findthejumper);
	        
	        
	        jump = (Button) findViewById (R.id.jumper);
	        eye = (Button) findViewById (R.id.eyebrow);
	        give = (Button) findViewById (R.id.giveup);
	        
	        
	       give.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent openM = new Intent("Newport.test.Nellie.UNLUCKY");
				startActivity(openM);
				
			}
		});
	        		
	       jump.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent openM = new Intent("Newport.test.Nellie.WHAT");
					startActivity(openM);
					
				}
			});
	       
	       eye.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Intent openM = new Intent("Newport.test.Nellie.WHAT");
					startActivity(openM);
					
				}
			});
	       
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
	
	
	    
	
	

