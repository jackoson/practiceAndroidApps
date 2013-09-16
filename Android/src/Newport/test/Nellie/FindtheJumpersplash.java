package Newport.test.Nellie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FindtheJumpersplash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findthejumpersplash);
		
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(5000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openSP = new Intent("Newport.test.Nellie.FINDTHEJUMPER");
					startActivity(openSP);
				}
			}
		};
		timer.start();
	}
    @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
		
			finish();
		}
}
