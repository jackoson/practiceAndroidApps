package com.jumpingbeans.onlinecountdown;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Myobject>{
	
	@Override
	public Myobject getItem(int position) {
		// TODO Auto-generated method stub
		return obj.get(position);
	}


	Context c;
	int LRI;//layoutResourceId
	ArrayList<Myobject> obj = null;
	

	public MyAdapter(Context context, int textViewResourceId,	ArrayList<Myobject> objects) {
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
		
		Myobject objI = obj.get(position);
		
		TextView tv0 = (TextView) row.findViewById(R.id.textView0);
		TextView tv1 = (TextView) row.findViewById(R.id.textView1);
		
		
		
		
		if(tv0 != null){
			tv0.setText(objI.name);
		}
		if(tv1 != null){
			tv1.setText(objI.players);
		}
		
		
		//Log.i("marker", "get view end");
		return row;
	}

	

}
