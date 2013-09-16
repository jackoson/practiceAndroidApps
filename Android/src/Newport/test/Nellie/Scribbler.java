package Newport.test.Nellie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import android.os.Bundle;
import android.view.MotionEvent;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Scribbler extends Activity implements OnClickListener {
	DrawView drawView;
	boolean toclear;
	boolean dotnotclear;
	int pen = 10;
	boolean qjumper = false;
	int je = 6;
	FrameLayout Game;
	FileOutputStream fos;
	TextView tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Game = new FrameLayout(this);
		drawView = new DrawView(this);
		LinearLayout GameWidgets = new LinearLayout(this);

		Button clear = new Button(this);
		Button draw = new Button(this);
		Button tp = new Button(this);
		tv = new TextView (this);

		clear.setWidth(150);
		clear.setText("Clear");
		clear.setId(1);

		draw.setWidth(150);
		draw.setText("Draw");
		draw.setId(2);
		
		tp.setWidth(190);
		tp.setText("Set Wallpaper");
		tp.setId(3);
		
		
		tv.setId(3);

		GameWidgets.addView(clear);
		GameWidgets.addView(draw);
		GameWidgets.addView(tp);
		GameWidgets.addView(tv);

		Game.addView(drawView);
		Game.addView(GameWidgets);
		
		
		
		

		setContentView(Game);
		clear.setOnClickListener(this);
		draw.setOnClickListener(this);
		tp.setOnClickListener(this);

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case 1:
			toclear = true;
			
			break;

		case 2:
			je=6;
			break;

		case 3:
			
			Game.setDrawingCacheEnabled(true);
			Bitmap b = Game.getDrawingCache();
			try {
				getApplicationContext().setWallpaper(b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			
				Toast toast = Toast.makeText(getApplicationContext(), "Wallpaper Changed", Toast.LENGTH_SHORT);
				toast.show();
			}
			
			/*
			//FileOutputStream fs = null;
			///try {
			//fs = new FileOutputStream("ben");
			//} catch (FileNotFoundException e) {
			//e.printStackTrace();
			//}

			//b.compress(CompressFormat.PNG, 95, fs);
			
			
			
			
			try {
				fos = openFileOutput("bob", Context.MODE_PRIVATE);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Game.setDrawingCacheEnabled(true);
			Bitmap b = Game.getDrawingCache();
			/*File f = new File("bob");
			try {
				fos = new FileOutputStream(f);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				fos = openFileOutput("bob", Context.MODE_PRIVATE);
				fos.write(b.getRowBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			break;
			
			
			
			
		}

	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.jumpermenu, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.black:
			je = 1;
		break;
		
		case R.id.blue:
			je = 2;
			break;
			
		case R.id.pink:
			je = 3;
			break;
			
		case R.id.eone:
			je = 4;
			break;
			
		case R.id.etwo:
			je = 5;
			break;
		
		
			
		case R.id.glass:
			je = 7;
			break;
		}
		return false;
	}



	public class DrawView extends View implements OnTouchListener {
		List<Point> points = new ArrayList<Point>();
		Paint paint = new Paint();
		Bitmap rackham;
		int x1=0;
		int x2=0;
		int x3=0;
		int x4=0;
		int x5=0;
		int x6=0;
		int y1=0;
		int y2=0;
		int y3=0;
		int y4=0;
		int y5=0;
		int y6=0;
		Bitmap jumper;
		Bitmap black,blue,pink,eone,etwo,glass;

		public DrawView(Context context) {
			super(context);
			setFocusable(true);
			setFocusableInTouchMode(true);
			this.setOnTouchListener(this);
			paint.setColor(Color.BLACK);
			InputStream image = getResources().openRawResource(R.drawable.r);
			rackham = BitmapFactory.decodeStream(image);
			this.setBackgroundResource(R.drawable.r);
			
			
			
			InputStream jimage = getResources().openRawResource(R.drawable.jumper);
			jumper = BitmapFactory.decodeStream(jimage);
			
			
			
			
			InputStream bimage = getResources().openRawResource(R.drawable.jumblue);
			blue = BitmapFactory.decodeStream(bimage);
			
			InputStream cimage = getResources().openRawResource(R.drawable.jumpink);
			pink = BitmapFactory.decodeStream(cimage);
			
			InputStream dimage = getResources().openRawResource(R.drawable.eyeone);
			eone = BitmapFactory.decodeStream(dimage);
			
			InputStream eimage = getResources().openRawResource(R.drawable.eyebrowtwo);
			etwo = BitmapFactory.decodeStream(eimage);
			
			InputStream fimage = getResources().openRawResource(R.drawable.sunglass);
			glass = BitmapFactory.decodeStream(fimage);
			
			
			
			
			
			

		}

		@Override
		public void onDraw(Canvas canvas) {
			if (toclear) {
				points.clear();
				toclear = false;
				y1=x1=x2=x3=x4=x5=x6=y2=y3=y4=y5=y6=0;
				
			}
			
			
			
			if (x1 != 0 && y1!= 0){
				Rect scr = new Rect (0,0, blue.getWidth(), blue.getHeight());
				Rect dst = new Rect (x1 - ((blue.getWidth()/2)*3), y1 - blue.getHeight(), x1 + ((blue.getWidth()*3)/2), y1 + (blue.getHeight()));
				canvas.drawBitmap(blue,scr,dst,null);
			}
			
			if (x2 != 0 && y2!= 0){
				Rect scr = new Rect (0,0, pink.getWidth(), pink.getHeight());
				Rect dst = new Rect (x2 - ((pink.getWidth()/2)*3), y2 - pink.getHeight(), x2 + ((pink.getWidth()*3)/2), y2 + (pink.getHeight()));
				canvas.drawBitmap(pink,scr,dst,null);
			}
			
			if (x3 != 0 && y3!= 0){
				Rect scr = new Rect (0,0, eone.getWidth(), eone.getHeight());
				Rect dst = new Rect (x3 - eone.getWidth()/5, y3 - eone.getHeight()/5, x3 + eone.getWidth()/5, y3 + (eone.getHeight())/5);
				canvas.drawBitmap(eone,scr,dst,null);
			}
			
			if (x4 != 0 && y4!= 0){
				Rect scr = new Rect (0,0, etwo.getWidth(), etwo.getHeight());
				Rect dst = new Rect (x4 - etwo.getWidth()/8, y4 - etwo.getHeight()/8, x4 + etwo.getWidth()/8, y4 + (etwo.getHeight()/8));
				canvas.drawBitmap(etwo,scr,dst,null);
			}
			
			if (x5 != 0 && y5!= 0){
				Rect scr = new Rect (0,0, glass.getWidth(), glass.getHeight());
				Rect dst = new Rect (x5 - glass.getWidth()/4, y5 - glass.getHeight()/4, x5 + glass.getWidth()/4, y5 + (glass.getHeight())/4);
				canvas.drawBitmap(glass,scr,dst,null);
			}
			
			if (x6 != 0 && y6!= 0){
				Rect scr = new Rect (0,0, jumper.getWidth(), jumper.getHeight());
				Rect dst = new Rect (x6 - ((jumper.getWidth()/2)*3), y6 - jumper.getHeight(), x6 + ((jumper.getWidth()*3)/2), y6 + (jumper.getHeight()));
				canvas.drawBitmap(jumper,scr,dst,null);
			}
			
			for (Point point : points) {
				canvas.drawCircle(point.x, point.y, 10, paint);
			}
			
		}

		public boolean onTouch(View view, MotionEvent event) {
			switch(je){
				case 1:
					x6 = (int) event.getX();
					y6 = (int) event.getY();
					break;
				case 2:
					x1 = (int) event.getX();
					y1 = (int) event.getY();
					break;
				case 3:
					x2 = (int) event.getX();
					y2 = (int) event.getY();
					break;
				case 4:
					x3 = (int) event.getX();
					y3 = (int) event.getY();
					break;
				case 5:
					x4 = (int) event.getX();
					y4 = (int) event.getY();
					break;
				case 7:
					x5 = (int) event.getX();
					y5 = (int) event.getY();
					break;
				case 6:
					Point point = new Point();
					point.x = event.getX();
					point.y = event.getY();
					points.add(point);
					break;
					
				}
			
		
			invalidate();
			return true;
	}
		
		
	}
	class Point {
		float x, y;
	}

}