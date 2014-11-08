package com.diplomadourp.ejemplolbs;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tviProviders;
	private TextView tviUbicacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tviProviders = (TextView) findViewById(R.id.tviProviders);
		tviUbicacion = (TextView) findViewById(R.id.tviPosicion);
		
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
		tviProviders.setText("Proveedores localización:\n"
				+ sbProviders.toString());
		
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
			tviUbicacion.setText("Ubicación: (" + 
					ubicacion.getLatitude() + ", " + 
					ubicacion.getLongitude() + ")");
		}
		// Iniciar tracking
		locationManager.requestLocationUpdates("gps",
				0, 100,
				new LocationListener(){
					@Override
					public void onLocationChanged(Location ubicacion) {
						Log.i("EjemploLBS", "Ubicación: (" + 
								ubicacion.getLatitude() + ", " + 
								ubicacion.getLongitude() + ")");
						String textoAnterior = tviUbicacion.getText().toString();
						tviUbicacion.setText(textoAnterior + "\n" +
								"Ubicación: (" + 
								ubicacion.getLatitude() + ", " + 
								ubicacion.getLongitude() + ")");
					}

					@Override
					public void onProviderDisabled(String arg0) {}

					@Override
					public void onProviderEnabled(String arg0) {}

					@Override
					public void onStatusChanged(String arg0, int arg1,
							Bundle arg2) {}
					}, 
				null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
