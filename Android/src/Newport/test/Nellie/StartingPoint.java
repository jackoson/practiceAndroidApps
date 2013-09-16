package Newport.test.Nellie;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity {
 
	

	/** Called when the activity is first created. */
	public static int num;
	int counter;
	String counters;
	Button add, sub, back;
	TextView display;
	int bob;
	SharedPreferences getPrefs;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        add = (Button) findViewById (R.id.addone);
        sub = (Button) findViewById (R.id.minusone);
        display = (TextView) findViewById (R.id.tvDisplay);
      
        back = (Button) findViewById (R.id.back);
       
        counter = 2;
       
        getPrefs =PreferenceManager.getDefaultSharedPreferences(getBaseContext());
       
        counters = getPrefs.getString("jumpernum", "0");
       
        counter = Integer.parseInt(counters);
       
        display.setText("Jumper Count is " + counter);
        
        
        add.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
		counter++;
		display.setText("Jumper Count is " + counter);
		SharedPreferences.Editor prefEditor = getPrefs.edit();
		counters = Integer.toString(counter);
		prefEditor.putString("jumpernum", counters);  
		prefEditor.commit();  
		
		
		}
	});
       
       sub.setOnClickListener(new View.OnClickListener() {
   		
   		public void onClick(View v) {
   		counter=0;
   		display.setText("Jumper Count is " + counter);
   		counters = Integer.toString(counter);
   		SharedPreferences.Editor prefEditor = getPrefs.edit();
		prefEditor.putString("jumpernum", counters);  
		prefEditor.commit();
		
   		}
   	});
        
        
       back.setOnClickListener(new View.OnClickListener() {
   		
   		public void onClick(View v) {
   			Intent openSP = new Intent("Newport.test.Nellie.MENU");
			startActivity(openSP);
   			
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