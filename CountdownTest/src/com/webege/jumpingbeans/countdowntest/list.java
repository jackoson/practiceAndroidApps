package com.webege.jumpingbeans.countdowntest;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class list extends Activity {

	ListView lv;
	MyAdapter my;
	ArrayList<Myobject> mo;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.i("marker", "start");
        lv = (ListView) findViewById(R.id.listView1);
        
        mo = new ArrayList<Myobject>();
       
        mo.add(new Myobject("Sam", "13", "easy"));
        mo.add(new Myobject("Lewis", "43", "easy"));
        mo.add(new Myobject("Sam", "663", "hard"));
        mo.add(new Myobject("Bob", "66", "medium"));
        mo.add(new Myobject("Jim", "663", "hard"));
        mo.add(new Myobject("Gary", "253", "extreme"));
        mo.add(new Myobject("jon", "69", "easy"));
        mo.add(new Myobject("josh", "377","hard"));
        mo.add(new Myobject("ian", "35", "hard"));
        mo.add(new Myobject("peter", "854","extreme"));
        mo.add(new Myobject("lewis", "91", "hard"));
        mo.add(new Myobject("greg", "927", "hard"));
        
        
        my = new MyAdapter(this, R.layout.itemrow,  mo);
        
        
        View header = (View)getLayoutInflater().inflate(R.layout.header, null);
        lv.addHeaderView(header);
        lv.setAdapter(my);
        
    }

    

    
}
