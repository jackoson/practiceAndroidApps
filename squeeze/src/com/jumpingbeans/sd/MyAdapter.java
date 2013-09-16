package com.jumpingbeans.sd;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<PlayerStatus>{
	
	@Override
	public PlayerStatus getItem(int position) {
		// TODO Auto-generated method stub
		return obj.get(position);
	}


	Context c;
	int LRI;//layoutResourceId
	ArrayList<PlayerStatus> obj = null;
	

	public MyAdapter(Context context, int textViewResourceId,	ArrayList<PlayerStatus> objects) {
		super(context,  textViewResourceId, objects);
		
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
		
		PlayerStatus p = obj.get(position);
		
		TextView tv0 = (TextView) row.findViewById(R.id.textView0);
		TextView tv1 = (TextView) row.findViewById(R.id.textView1);
		TextView tv2 = (TextView) row.findViewById(R.id.textView2);
		TextView tv3 = (TextView) row.findViewById(R.id.textView3);
		ImageView iv = (ImageView) row.findViewById(R.id.imageView1);
		
		
		if(iv != null){
			iv.setImageBitmap(p.art);
		}
		if(tv0 != null){
			tv0.setText(p.name);
		}
		if(tv1 != null){
			tv1.setText(p.title);
		}
		if(tv2 != null){
			tv2.setText(p.album);
		}
		
		if(tv3 != null){
			tv3.setText(p.artist);
		}
		
		//Log.i("marker", "get view end");
		return row;
	}

	

}
