package com.Newport.Map;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Main extends MapActivity implements LocationListener{
	MapView map;
	long start,stop;
	MyLocationOverlay compass;
	MapController controller;
	int x,y;
	GeoPoint touchedPoint;
	Drawable d,m;
	List<Overlay> overlaylist;
	LocationManager lm;
	String towers;
	int longi = 0;
	int lat =0;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.main);
		map = (MapView)findViewById(R.id.mvMain);
		map.setBuiltInZoomControls(true);
		
		Touchy t = new Touchy();
		overlaylist = map.getOverlays();
		overlaylist.add(t);
		compass = new MyLocationOverlay(Main.this, map);
		overlaylist.add(compass);
		controller = map.getController();
		
		GeoPoint point = new GeoPoint(51587741, -2998343);
		controller.setZoom(11);
		controller.animateTo(point);
		
		d = getResources().getDrawable(R.drawable.r);
		m = getResources().getDrawable(R.drawable.crs);
		//placing pinpoint location
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();
		towers = lm.getBestProvider(crit, false);
		Location loc = lm.getLastKnownLocation(towers);
		if(loc != null){
			lat = (int) (loc.getLatitude()*1E6);
			longi = (int)(loc.getLongitude()*1e6);
			GeoPoint location = new GeoPoint(lat, longi);
			OverlayItem oli = new OverlayItem(location, "one","two");
			CustomPinPoint cpp = new CustomPinPoint(m, Main.this);
			cpp.insertPinpoint(oli);
			overlaylist.add(cpp);
		}else{
			Toast.makeText(Main.this, "error", Toast.LENGTH_SHORT);
		}
		
		
		
		
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		compass.disableCompass();
		super.onPause();
		lm.removeUpdates(this);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		compass.enableCompass();
		super.onResume();
		lm.requestLocationUpdates(towers, 500, 1, this);
	}


	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class Touchy extends Overlay{
		public boolean onTouchEvent(MotionEvent e, MapView m){
			if (e.getAction() == MotionEvent.ACTION_DOWN){
				start = e.getEventTime();
				x = (int) e.getX();
				y = (int) e.getY();
				touchedPoint = map.getProjection().fromPixels(x, y);
				//Vibrator v = (Vibrator) getSystemService(.VIBRATOR_SERVICE);
				//v.vibrate(300);

				
				
			}
			if (e.getAction() == MotionEvent.ACTION_UP){
				stop = e.getEventTime();
				
			}
			
			if (stop - start > 500 && stop - start < 1200){
				AlertDialog alert = new AlertDialog.Builder(Main.this).create();
				alert.setTitle("|What to do?");
				alert.setMessage("pick option...");
				
				alert.setButton("place a pin", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						CustomPinPoint cpp = new CustomPinPoint(d, Main.this);
						OverlayItem oli = new OverlayItem(touchedPoint, "one","two");
						cpp.insertPinpoint(oli);
						overlaylist.add(cpp);
						
						
						
					}
				});
				
				
				alert.setButton2("get Address",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

								
								Geocoder gc = new Geocoder(getBaseContext(), Locale.getDefault());
								try{
									List<Address> address = gc.getFromLocation(touchedPoint.getLatitudeE6() /1E6, touchedPoint.getLongitudeE6() /1E6, 1);
									String display = "";
									if (address.size() >0){
										for(int i = 0;i<address.get(0).getMaxAddressLineIndex(); i++){
											
											display += address.get(0).getAddressLine(i) + "\n";
										}
										
										Toast t = Toast.makeText(getBaseContext(), display,  Toast.LENGTH_LONG);
										t.show();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}finally{
									
								}
								
							}
						});
				
				alert.setButton3("toggle view",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								
								Intent i = new Intent("com.Newport.Map.TEST");
								startActivity(i);
								
								// TODO Auto-generated method stub
								/*if (map.isSatellite()){
									
									map.setSatellite(false);
									map.setStreetView(true);
								}
								else{
										
									map.setSatellite(true);
									map.setStreetView(false);
									
									}*/
								}
							
						});
				
				alert.show();
				
			}
			
			
		return false;
		}
	}

	public void onLocationChanged(Location l) {
		// TODO Auto-generated method stub
	lat = (int) (l.getLatitude() *1E6);
	longi = (int) (l.getLongitude()*1E6);
	
	GeoPoint location = new GeoPoint(lat, longi);
	OverlayItem oli = new OverlayItem(location, "one","two");
	CustomPinPoint cpp = new CustomPinPoint(m, Main.this);
	cpp.insertPinpoint(oli);
	overlaylist.add(cpp);
	}


	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
