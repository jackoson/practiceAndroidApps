package com.jumpingbeans.sd;

import android.graphics.Bitmap;

public class PlayerStatus {
	
	Bitmap art;
	String name;
	String title;
	String album;
	String artist;
	
	PlayerStatus(Bitmap art, String name, String title,String album,String artist){
		
		this.art = art;
		this.name = name;
		this.title = title;
		this.album = album;
		this.artist = artist;
			
	}
	
}
