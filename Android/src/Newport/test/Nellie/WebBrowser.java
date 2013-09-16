package Newport.test.Nellie;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebBrowser extends Activity implements OnClickListener{
	
	
	EditText url;
	WebView ourWV;
	String URLstart = "http://";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simbrowser);
		
		ourWV = (WebView) findViewById(R.id.wv);
		
		ourWV.getSettings().setJavaScriptEnabled(true);
		ourWV.getSettings().setLoadWithOverviewMode(true);
		ourWV.getSettings().setUseWideViewPort(true);
		
		ourWV.setWebViewClient(new ourViewClient());
	
		try{
		ourWV.loadUrl(URLstart + "www.youtube.com/watch?v=QocCe3_CBS0");
		}catch (Exception e){
			e.printStackTrace();
		}
	
	Button go = (Button) findViewById(R.id.bgo);
	Button back = (Button) findViewById(R.id.bback);
	Button refresh = (Button) findViewById(R.id.breload);
	Button forward = (Button) findViewById(R.id.bforward);
	Button clearHistory = (Button) findViewById(R.id.bhistory);
	url = (EditText) findViewById(R.id.eturl);
	go.setOnClickListener(this);
	back.setOnClickListener(this);
	refresh.setOnClickListener(this);
	forward.setOnClickListener(this);
	clearHistory.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bback:
			if(ourWV.canGoBack())
				ourWV.goBack();
			break;
		case R.id.bforward:
			if(ourWV.canGoForward())
				ourWV.goForward();
			break;
		case R.id.bgo:
			String search = url.getText().toString();
			String theWebsite = String.format("www.google.com/search?q=%s", search);
			ourWV.loadUrl(URLstart + theWebsite);
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(),0);
			break;
		case R.id.breload:
			ourWV.reload();
			break;
		case R.id.bhistory:
			ourWV.clearHistory();
			break;
		}
		
	}
	
}
