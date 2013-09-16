package com.example.bitmapmanipulation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	Bitmap bmp;
	ImageView iv;
	Canvas c;
	Paint p;
	EditText et;
	int Yval;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.share).copy(Bitmap.Config.ARGB_8888, true);
        iv = (ImageView) findViewById(R.id.imageView1);
        et = (EditText) findViewById(R.id.editText1); 
         
         
        c = new Canvas(bmp);
        
        
        
        p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(30);
        Toast.makeText(getApplicationContext(), Integer.toString(c.getWidth()), 0).show();
        c.drawText("I was trying to make 300", 70, 205, p);
        c.drawText("And got 3 away in 10 seconds", 70, 235, p);
        c.drawText("by", 70, 265, p);
        
        
        iv.setImageBitmap(bmp);
        
        iv.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String filename = "sharePic.png";
				String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
			    OutputStream outStream = null;
			    File file = new File(extStorageDirectory, filename);
			    try {
				     outStream = new FileOutputStream(file);
				     bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
				     
				     outStream.flush();
				     outStream.close();
			    }
			    catch(Exception e){}
				Toast.makeText(getApplicationContext(), extStorageDirectory + "/" + filename, 0).show();
				
				Uri uri = Uri.parse("file:///sdcard/"+filename);
				
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				
				
				
				shareIntent.setType("image/png");
				
				shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
				
				
				startActivity(Intent.createChooser(shareIntent, "Show off you Epic-ness at countdown with..."));
		       
			}
        	
        });
        
        Yval = 265;
    }

   
    
    
    public void add(View v){
    	Yval+=30;
    	c.drawText(et.getText().toString(), 70, Yval, p);
    	et.setText("");
    	iv.setImageBitmap(bmp);
    }
    
}
