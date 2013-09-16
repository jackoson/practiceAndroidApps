package Newport.test.Nellie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class B extends Activity {
	 /** Called when the activity is first created. */
	
		int counter;
		Button back;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.b);
	        
	        counter=0;
	        back = (Button) findViewById (R.id.back);
	        
	       back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent openG = new Intent("Newport.test.Nellie.GALLERY");
				startActivity(openG);
				
			}
		});
	       
	       
	        
	        
	    }
	    @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
		
			finish();
		}
}