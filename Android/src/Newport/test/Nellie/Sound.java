package Newport.test.Nellie;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Sound extends Activity{
	MediaPlayer fs;
	MediaPlayer gets;
	MediaPlayer ohgas;
	MediaPlayer ohgbs;
	MediaPlayer wows;
	MediaPlayer yas;
	MediaPlayer ohhs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sound);
		
		Button f;
		Button get;
		Button ohga;
		Button ohgb;
		Button wow;
		Button ya;
		Button ohh;
		Button back;
		
		fs = MediaPlayer.create(Sound.this, R.raw.swear);
		gets = MediaPlayer.create(Sound.this, R.raw.getoff);
		ohgas = MediaPlayer.create(Sound.this, R.raw.ohgod);
		ohgbs = MediaPlayer.create(Sound.this, R.raw.ohhgod);
		wows = MediaPlayer.create(Sound.this, R.raw.woo);
		yas = MediaPlayer.create(Sound.this, R.raw.ya);
		ohhs = MediaPlayer.create(Sound.this, R.raw.ohh);
		
		
		 f = (Button) findViewById (R.id.f);
		 get = (Button) findViewById (R.id.get);
		 ohga = (Button) findViewById (R.id.ohga);
		 ohgb= (Button) findViewById (R.id.ohgb);
		 wow = (Button) findViewById (R.id.woow);
		 ya = (Button) findViewById (R.id.ya);
		 ohh = (Button) findViewById (R.id.ohh);
		 back = (Button) findViewById (R.id.back);
		
		f.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fs.start();
			}
		});
		
f.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fs.start();
			}
		});

get.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		gets.start();
	}
});

ohga.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ohgas.start();
	}
});

ohgb.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ohgbs.start();
	}
});

wow.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		wows.start();
	}
});

ya.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		yas.start();
	}
});

ohh.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ohhs.start();
	}
});

back.setOnClickListener(new View.OnClickListener() {
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent("Newport.test.Nellie.MENU");
		startActivity(intent);
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
