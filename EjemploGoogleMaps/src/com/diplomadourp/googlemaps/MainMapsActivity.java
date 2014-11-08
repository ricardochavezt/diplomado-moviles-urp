package com.diplomadourp.googlemaps;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainMapsActivity extends Activity implements LocationListener {
	
	private GoogleMap mapa;

	private Circle circle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_maps);
		
		mapa = ((MapFragment)getFragmentManager()
				.findFragmentById(R.id.mapa)).getMap();
		
		mapa.setMyLocationEnabled(true);
		
		// centramos la camara sobre las coordenadas
		LatLng coordenadas = new LatLng(-12.132021, -76.980553);
		CameraPosition posicionCamara = 
				new CameraPosition.Builder()
				.target(coordenadas)
				.zoom(16)
				.bearing(45)
				.tilt(30)
				.build();
		
		/*List<CameraUpdate> list = 
				new ArrayList<CameraUpdate>();
		list.add(CameraUpdateFactory
				.newCameraPosition(posicionCamara));
		list.add(CameraUpdateFactory
				.newCameraPosition(
						new CameraPosition.Builder()
						.target(coordenadas)
						.zoom(17)
						.bearing(0)
						.tilt(0)
						.build()));
		
		loopAnimateCamera(list);*/
		
		mapa.animateCamera(
				CameraUpdateFactory
				.newCameraPosition(posicionCamara));
		
		
		
		// colocamos un marcador
		MarkerOptions markerOpt = new MarkerOptions();
		markerOpt.position(coordenadas);
		markerOpt.title("Universidad Ricardo Palma");
		markerOpt.icon(BitmapDescriptorFactory.
				fromResource(R.drawable.pegman));
		mapa.addMarker(markerOpt);
		
		LatLng coordenadas2 = new LatLng(-12.14, -76.99);
		/*MarkerOptions markerOpt2 = new MarkerOptions();
		markerOpt2.position(coordenadas2);
		markerOpt2.title("Punto 2");
		mapa.addMarker(markerOpt2);	*/
		
		CircleOptions circleOptions = new CircleOptions()
	    .center(coordenadas2)
	    .strokeWidth(5)
	    .strokeColor(Color.BLUE)
	    .radius(1000); // In meters
		// Get back the mutable Circle
		circle = mapa.addCircle(circleOptions);
		
		mapa.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng latlng) {
				//latlng
			}
		});
		
		LocationManager locationManager = (LocationManager) 
				getSystemService(LOCATION_SERVICE);
//		Criteria criterios = new Criteria();
//		criterios.setPowerRequirement(Criteria.POWER_LOW);
		List<String> providers = 
//				locationManager.getProviders(criterios, true);
				locationManager.getProviders(true);
		StringBuffer sbProviders = new StringBuffer();
		for (String provider : providers) {
			sbProviders.append(provider).append("\n");
		}
		/*tviProviders.setText("Proveedores localización:\n"
				+ sbProviders.toString());*/
		
		if (providers.isEmpty()) {
			return;
		}
		
		// Ultima ubicación
		Location ubicacion = 
				locationManager.getLastKnownLocation(
						"gps");
		if (ubicacion != null) {
			Log.i("EjemploLBS", "Ubicación: (" + 
					ubicacion.getLatitude() + ", " + 
					ubicacion.getLongitude() + ")");
			/*tviUbicacion.setText("Ubicación: (" + 
					ubicacion.getLatitude() + ", " + 
					ubicacion.getLongitude() + ")");*/
		}
		// Iniciar tracking
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				0, 100,this, null);
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER,
				0, 100,this, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_maps, menu);
		return true;
	}
	
	//from https://github.com/mg6maciej/android-maps-v2-demo/blob/master/NewMapsDemo/src/pl/mg6/newmaps/demo/AnimateCameraChainingExampleActivity.java#L58
	private void loopAnimateCamera(final List<CameraUpdate> updates) {
		if(updates.size() > 0){
			CameraUpdate update = updates.remove(0);
			//updates.add(update);
			mapa.animateCamera(update, 4000, 
					new CancelableCallback() {
				@Override
				public void onFinish() {
					loopAnimateCamera(updates);
				}
				@Override
				public void onCancel() {
				}
			});
		}
	}

	@Override
	public void onLocationChanged(Location ubicacion) {
		Log.i("EjemploLBS", "Ubicación: (" + 
				ubicacion.getLatitude() + ", " + 
				ubicacion.getLongitude() + ")");
		/*String textoAnterior = tviUbicacion.getText().toString();
		tviUbicacion.setText(textoAnterior + "\n" +
				"Ubicación: (" + 
				ubicacion.getLatitude() + ", " + 
				ubicacion.getLongitude() + ")");
				*/
		
		/*mapa.animateCamera(
				CameraUpdateFactory
				.newLatLng(new LatLng(
						ubicacion.getLatitude(),
						ubicacion.getLongitude())));*/
		circle.setRadius(ubicacion.getAccuracy());
	}

	@Override
	public void onProviderDisabled(String arg0) {}

	@Override
	public void onProviderEnabled(String arg0) {}

	@Override
	public void onStatusChanged(String arg0, int arg1,
			Bundle arg2) {}

}
