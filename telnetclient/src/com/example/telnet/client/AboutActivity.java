package com.example.telnet.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {/*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        MakeLink();

        Button btnOK = (Button) findViewById(R.id.buttonOK);
        btnOK.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) 
            {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    
    private void MakeLink()
    {
    	TextView textView = (TextView)findViewById(R.id.textViewLink);

    	MatchFilter matchFilter = new MatchFilter()
    	{
    		public final boolean acceptMatch(CharSequence s, int start, int end)
    		{
    			return true;
    		}
    	};
    	
    	TransformFilter transformFilter = new TransformFilter()
    	{
    		public final String transformUrl(final Matcher match, String url)
    		{
    			return "www.itsamples.com";
    		}
    	};

    	Pattern pattern = Pattern.compile("www.itsamples.com");
    	String scheme = "http://";
    	Linkify.addLinks(textView, pattern, scheme, matchFilter, transformFilter);*/
    }
}