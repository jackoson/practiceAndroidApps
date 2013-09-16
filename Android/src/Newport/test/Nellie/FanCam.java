package Newport.test.Nellie;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class FanCam extends Activity implements View.OnClickListener{

	ImageView ivReturnedPic;
	ImageButton TakePic;
	Button SetWP;
	Intent Picture;
	final static int cameraDATA = 0;
	Bitmap bmp;
	TextView set;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fancam);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		ivReturnedPic = (ImageView) findViewById (R.id.ivReturnedPic);
		TakePic = (ImageButton) findViewById (R.id.TakePic);
		SetWP = (Button) findViewById (R.id.SetWP);
		set = (TextView) findViewById (R.id.set);
		TakePic.setOnClickListener(this);
		SetWP.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.SetWP:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				set.setText("Wallpaper Changed");
			}
			break;
		case R.id.TakePic:
			Picture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(Picture, cameraDATA);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			ivReturnedPic.setImageBitmap(bmp);
		}
	}
	
		

}
