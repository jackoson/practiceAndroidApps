package com.jumpingbeans.sd;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.bff.squeezeserver.Player;
import org.bff.squeezeserver.Playlist;
import org.bff.squeezeserver.SqueezeServer;
import org.bff.squeezeserver.exception.ConnectionException;
import org.bff.squeezeserver.exception.SqueezeException;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.View;
import android.widget.ListView;

import android.widget.Toast;


public class MainActivity extends Activity {

	SqueezeServer ss;
	ListView lv;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv = (ListView) findViewById(R.id.lv);
        
        
        try {
			ss = new SqueezeServer("192.168.1.141",9090,9000);
			showPlayers();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

	private void showPlayers() {
		// TODO Auto-generated method stub
		List<Player> players = new ArrayList<Player>(ss.getAllPlayers());
		ArrayList<PlayerStatus> status = new ArrayList<PlayerStatus>();
		
		
		
		MyAdapter a = new MyAdapter(this, R.layout.itemrow,  status);
		lv.setAdapter(a);
		
		
		
		for (Player player : players) {
	         
        	Bitmap art = null;
        	String alb = null;
			String tit = null;
			String artist = null;
			String name = null;
			Playlist Nowplaying = null;
        	
    		try {
    			System.out.println("\t" + player.getName());
    			
    			Nowplaying = ss.getPlaylist(player);
    			
    			
    			String url = "http://192.168.1.141:9000/music/current/cover.jpg?player="+player.getId();
    			
    			art = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
    			name = player.getName();
    			
    		} catch (MalformedURLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    			System.out.println("EERRRROOOORRRR1");
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println("EERRRROOOORRRR2");
    		}catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println("EERRRROOOORRRR3");
    		} catch (SqueezeException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.out.println("EERRRROOOORRRR4");
    		}
    			
    		
            
    		
			try {
				alb = Nowplaying.getCurrentAlbum();
				
			} catch (SqueezeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				alb = "null";
			}catch(NullPointerException e){
				
			}
			
			try {
				tit = Nowplaying.getCurrentTitle();
			} catch (SqueezeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tit = "null";
			}catch(NullPointerException e){
				
			}
			try {
				artist = Nowplaying.getCurrentArtist();
			} catch (SqueezeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				artist = "null";
			} catch(NullPointerException e){
				
			}
			
			status.add(new PlayerStatus(art, name, tit,alb,artist));

			a.notifyDataSetChanged();
           
            
        }
		
		
		Toast.makeText(getApplicationContext(), "done", 0).show();
		
		
//		Toast.makeText(getApplicationContext(), status.size(), 0).show();
//		Toast.makeText(getApplicationContext(), lv.getCount(), 0).show();

	}
}
