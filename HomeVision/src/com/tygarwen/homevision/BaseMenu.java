package com.tygarwen.homevision;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BaseMenu extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	Button in, out, stat;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basemenu);
        setup();
    }

	private void setup() {
		// TODO Auto-generated method stub
		in = (Button) findViewById(R.id.inside);
		out = (Button) findViewById(R.id.outside);
		stat = (Button) findViewById(R.id.stats);
		in.setOnClickListener(this);
		out.setOnClickListener(this);
		stat.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.inside:
			Toast.makeText(getApplicationContext(), "inside" , Toast.LENGTH_SHORT).show();
			Class ourClass;
			try {
				ourClass = Class.forName("com.tygarwen.homevision.Inside");
				Intent intent = new Intent(BaseMenu.this, ourClass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
		case R.id.outside:
			Toast.makeText(getApplicationContext(), "outside" , Toast.LENGTH_SHORT).show();
			break;
		case R.id.stats:
			Toast.makeText(getApplicationContext(), "stats" , Toast.LENGTH_SHORT).show();
			break;
		
		}
	}
}