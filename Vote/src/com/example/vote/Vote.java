package com.example.vote;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Vote extends Activity implements OnClickListener{

	
	ImageView one,two;
	Bitmap picone,pictwo;
	int picnoa,picnob;
	Random ran = new Random();
	int[] scores = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
	SharedPreferences getPrefs;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);
        
        getPrefs =PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	    for(int x = 0; x<scores.length;x++){
        scores[x] = getPrefs.getInt(Integer.toString(x), 100);
        
	    }
        setup();
        
        
    }

	private void setup() {
		// TODO Auto-generated method stub
		one = (ImageView) findViewById(R.id.imageone);
		two = (ImageView) findViewById(R.id.imagetwo);
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		picnoa = ran.nextInt(17);
		switch(picnoa){
		
		
		case 0:
			InputStream isa = getResources().openRawResource(R.drawable.abba);
		picone = BitmapFactory.decodeStream(isa);
		one.setImageBitmap(picone);
			break;
		case 1:
			InputStream isq = getResources().openRawResource(R.drawable.beatles);
			picone = BitmapFactory.decodeStream(isq);
			one.setImageBitmap(picone);
			break;
		case 2:
			InputStream isw = getResources().openRawResource(R.drawable.busted);
			picone = BitmapFactory.decodeStream(isw);
			one.setImageBitmap(picone);
			break;
		case 3:
			InputStream ise = getResources().openRawResource(R.drawable.ernie);
			picone = BitmapFactory.decodeStream(ise);
			one.setImageBitmap(picone);
			break;	
		case 4:
			InputStream isr = getResources().openRawResource(R.drawable.queen);
			picone = BitmapFactory.decodeStream(isr);
			one.setImageBitmap(picone);
			break;
		case 5:
			InputStream ist = getResources().openRawResource(R.drawable.scouting);
			picone = BitmapFactory.decodeStream(ist);
			one.setImageBitmap(picone);
			break;
		case 6:
			InputStream iss = getResources().openRawResource(R.drawable.alesha);
			picone = BitmapFactory.decodeStream(iss);
			one.setImageBitmap(picone);
			break;	
		case 7:
			InputStream isat = getResources().openRawResource(R.drawable.beyonce);
			picone = BitmapFactory.decodeStream(isat);
			one.setImageBitmap(picone);
			break;
		case 8:
			InputStream ists = getResources().openRawResource(R.drawable.duffy);
			picone = BitmapFactory.decodeStream(ists);
			one.setImageBitmap(picone);
			break;	
		case 9:
			InputStream istd = getResources().openRawResource(R.drawable.james);
			picone = BitmapFactory.decodeStream(istd);
			one.setImageBitmap(picone);
			break;	
		case 10:
			InputStream istf = getResources().openRawResource(R.drawable.katy);
			picone = BitmapFactory.decodeStream(istf);
			one.setImageBitmap(picone);
			break;		
		case 11:
			InputStream istg = getResources().openRawResource(R.drawable.kings);
			picone = BitmapFactory.decodeStream(istg);
			one.setImageBitmap(picone);
			break;		
		case 12:
			InputStream isth = getResources().openRawResource(R.drawable.lady);
			picone = BitmapFactory.decodeStream(isth);
			one.setImageBitmap(picone);
			break;		
		case 13:
			InputStream istj = getResources().openRawResource(R.drawable.lily);
			picone = BitmapFactory.decodeStream(istj);
			one.setImageBitmap(picone);
			break;		
		case 14:
			InputStream istk = getResources().openRawResource(R.drawable.nickleback);
			picone = BitmapFactory.decodeStream(istk);
			one.setImageBitmap(picone);
			break;		
		case 15:
			InputStream istl = getResources().openRawResource(R.drawable.paul);
			picone = BitmapFactory.decodeStream(istl);
			one.setImageBitmap(picone);
			break;		
		case 16:
			InputStream iskt = getResources().openRawResource(R.drawable.alex);
			picone = BitmapFactory.decodeStream(iskt);
			one.setImageBitmap(picone);
			break;		
			
			
		
		}
		boolean x = false;
				
		while(x==false){	
			picnob = ran.nextInt(17);
			if(picnob!=picnoa){
				bob();
				x = true;
			}
		}
		
		
		
		
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	private void bob() {
		// TODO Auto-generated method stub
		
		
		
		
		
		switch(picnob){
		
		
		case 0:
			InputStream isa = getResources().openRawResource(R.drawable.abba);
			pictwo = BitmapFactory.decodeStream(isa);
			two.setImageBitmap(pictwo);
			break;
		case 1:
			InputStream isq = getResources().openRawResource(R.drawable.beatles);
			pictwo = BitmapFactory.decodeStream(isq);
			two.setImageBitmap(pictwo);
			break;
		case 2:
			InputStream isw = getResources().openRawResource(R.drawable.busted);
			pictwo = BitmapFactory.decodeStream(isw);
			two.setImageBitmap(pictwo);
			break;
		case 3:
			InputStream ise = getResources().openRawResource(R.drawable.ernie);
			pictwo = BitmapFactory.decodeStream(ise);
			two.setImageBitmap(pictwo);
			break;	
		case 4:
			InputStream isr = getResources().openRawResource(R.drawable.queen);
			pictwo = BitmapFactory.decodeStream(isr);
			two.setImageBitmap(pictwo);
			break;
		case 5:
			InputStream ist = getResources().openRawResource(R.drawable.scouting);
			pictwo = BitmapFactory.decodeStream(ist);
			two.setImageBitmap(pictwo);
			break;	
		case 6:
			InputStream iss = getResources().openRawResource(R.drawable.alesha);
			pictwo = BitmapFactory.decodeStream(iss);
			two.setImageBitmap(pictwo);
			break;	
		case 7:
			InputStream isat = getResources().openRawResource(R.drawable.beyonce);
			pictwo = BitmapFactory.decodeStream(isat);
			two.setImageBitmap(pictwo);
			break;
		case 8:
			InputStream ists = getResources().openRawResource(R.drawable.duffy);
			pictwo = BitmapFactory.decodeStream(ists);
			two.setImageBitmap(pictwo);
			break;	
		case 9:
			InputStream istd = getResources().openRawResource(R.drawable.james);
			pictwo = BitmapFactory.decodeStream(istd);
			two.setImageBitmap(pictwo);
			break;	
		case 10:
			InputStream istf = getResources().openRawResource(R.drawable.katy);
			pictwo = BitmapFactory.decodeStream(istf);
			two.setImageBitmap(pictwo);
			break;		
		case 11:
			InputStream istg = getResources().openRawResource(R.drawable.kings);
			pictwo = BitmapFactory.decodeStream(istg);
			two.setImageBitmap(pictwo);
			break;		
		case 12:
			InputStream isth = getResources().openRawResource(R.drawable.lady);
			pictwo = BitmapFactory.decodeStream(isth);
			two.setImageBitmap(pictwo);
			break;		
		case 13:
			InputStream istj = getResources().openRawResource(R.drawable.lily);
			pictwo = BitmapFactory.decodeStream(istj);
			two.setImageBitmap(pictwo);
			break;		
		case 14:
			InputStream istk = getResources().openRawResource(R.drawable.nickleback);
			pictwo = BitmapFactory.decodeStream(istk);
			two.setImageBitmap(pictwo);
			break;		
		case 15:
			InputStream istl = getResources().openRawResource(R.drawable.paul);
			pictwo = BitmapFactory.decodeStream(istl);
			two.setImageBitmap(pictwo);
			break;		
		case 16:
			InputStream iskt = getResources().openRawResource(R.drawable.alex);
			pictwo = BitmapFactory.decodeStream(iskt);
			two.setImageBitmap(pictwo);
			break;
		
		}
	}

	public void onClick(View view) {
		// TODO Auto-generated method stub
		SharedPreferences.Editor prefEditor;
		switch(view.getId()){
		case R.id.imageone:
			Toast.makeText(getApplicationContext(), "pic one" , Toast.LENGTH_SHORT).show();
			float aownold = scores[picnoa];
			float aoppold = scores[picnob];
			float aoppnew,aownnew;
			float aexpectown = Expectation(aoppold,aownold);
			float aexpectopp = Expectation(aownold,aoppold);
			
			aownnew = newscore(aexpectown,1,aownold);
			aoppnew = newscore(aexpectopp,0,aoppold);
			scores[picnob] = (int) aoppnew;
			scores[picnoa] = (int) aownnew;
			
			prefEditor = getPrefs.edit();
			prefEditor.putInt(Integer.toString(picnob), scores[picnob]);
			prefEditor.putInt(Integer.toString(picnoa), scores[picnoa]);
			prefEditor.commit();
			Class ourClass;
			try {
				ourClass = Class.forName("com.example.vote.Vote");
				Intent intent = new Intent(Vote.this, ourClass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case R.id.imagetwo:
			Toast.makeText(getApplicationContext(), "pic two" , Toast.LENGTH_SHORT).show();
			float ownold = scores[picnob];
			float oppold = scores[picnoa];
			float oppnew,ownnew;
			float expectown = Expectation(oppold,ownold);
			float expectopp = Expectation(ownold,oppold);
			
			ownnew = newscore(expectown,1,ownold);
			oppnew = newscore(expectopp,0,oppold);
			scores[picnob] = (int) ownnew;
			scores[picnoa] = (int) oppnew;
			
			prefEditor = getPrefs.edit();
			prefEditor.putInt(Integer.toString(picnob), scores[picnob]);
			prefEditor.putInt(Integer.toString(picnoa), scores[picnoa]);
			prefEditor.commit();
			
			Class courClass;
			try {
				courClass = Class.forName("com.example.vote.Vote");
				Intent intent = new Intent(Vote.this, courClass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		}
	}

	private static float newscore(float expect, int result, float ownold) {
		// TODO Auto-generated method stub
		float vara = result-expect;
		float varb = vara*40;
		float varc = varb+ownold;
		return varc;
	}

	private static float Expectation(float a, float b) {
		// TODO Auto-generated method stub
		float vara = a-b;
		float varb = vara/400;
		float varc = (float) Math.pow(10, varb);
		float vard = 1 + varc;
		float vare = 1/vard;
		return vare;
	}
	
	
	
}
