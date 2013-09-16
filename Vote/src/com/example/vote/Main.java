package com.example.vote;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	Button result,vote;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        vote = (Button) findViewById(R.id.button1);
        result = (Button) findViewById(R.id.button2);
        vote.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			
				Class ourClass;
				try {
					ourClass = Class.forName("com.example.vote.Vote");
					Intent intent = new Intent(Main.this, ourClass);
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
    	});
        
        result.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			
				Class courClass;
				try {
					courClass = Class.forName("com.example.vote.Results");
					Intent intent = new Intent(Main.this, courClass);
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
    	});
        
    }


   
    
}
