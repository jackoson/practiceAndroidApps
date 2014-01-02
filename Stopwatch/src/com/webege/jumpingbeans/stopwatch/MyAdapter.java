package com.webege.jumpingbeans.stopwatch;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<LapsAndSplits>{
	
	Context c;
	int LRI;//layoutResourceId
	ArrayList<LapsAndSplits> obj = null;
	

	public MyAdapter(Context context, int textViewResourceId,	ArrayList<LapsAndSplits> objects) {
		super(context,  textViewResourceId, objects);
		Log.i("marker", "construcor");
		this.c = context;
		this.LRI = textViewResourceId;
		this.obj = objects;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Log.i("marker", "get view start");
		View row = convertView;
		
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(LRI, null);
		}
		
		LapsAndSplits objI = obj.get(obj.size() - position - 1);
		
		
		TextView tv1 = (TextView) row.findViewById(R.id.textView1);
		TextView tv2 = (TextView) row.findViewById(R.id.textView2);
		
		
		
		
		
		if(tv1 != null){
			tv1.setText(objI.getLap());
		}
		if(tv2 != null){
			tv2.setText(objI.getSplit());
		}
		
		
		
		//Log.i("marker", "get view end");
		return row;
	}

	

}
