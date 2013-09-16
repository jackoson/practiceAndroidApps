package Newport.test.Nellie;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	MyBringBackSurface ourSurfaceView;
	float x =0;
	float y =0;
	float sX =0;
	float sY =0;
	float fX =0;
	float fY =0;
	Bitmap test, smile;
	float dX , dY ,  aniX, aniY , scaledX , scaledY;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		test = BitmapFactory.decodeResource(getResources(), R.drawable.pen);
		smile = BitmapFactory.decodeResource(getResources(), R.drawable.smile);
		setContentView(ourSurfaceView);
		dX = dY = aniX = aniY = scaledX = scaledY = 0;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
			
		}

	public boolean onTouch(View arg0, MotionEvent event) {
		// TODO Auto-generated method stub
		x = event.getX();
		y = event.getY();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
				break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			break;
		}
		return true;
	}
	
	public class MyBringBackSurface extends SurfaceView implements Runnable{

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		Boolean isRunning = false;
		
		public MyBringBackSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder();
			
		}

		public void pause(){
			isRunning = false;
			while(true){
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}
		
		public void resume(){
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}
		
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!ourHolder.getSurface().isValid())
					continue;
				
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(250, 250, 250);
				if(x != 0 && y!= 0){
					canvas.drawBitmap(test, x - (test.getWidth()/2), y - (test.getWidth()/2), null);
				}
				if(sX != 0 && sY!= 0){
					canvas.drawBitmap(smile, x - (smile.getWidth()/2), y - (test.getWidth()/2), null);
				}
				if(fX != 0 && fY!= 0){
					canvas.drawBitmap(smile, x - (smile.getWidth()/2), y - (test.getWidth()/2), null);
				}
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

	
	}

	
