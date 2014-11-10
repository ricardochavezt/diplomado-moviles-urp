package com.diplomadourp.ejemploservices;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnIniciar, btnDetener, btnEnviarMensaje;
	Intent serviceIntent;
	boolean isBound = false;
	ServicioEjemplo servicio;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnIniciar = (Button) findViewById(R.id.btnIniciar);
		btnDetener = (Button) findViewById(R.id.btnDetener);
		btnEnviarMensaje = (Button) findViewById(R.id.btnEnviarMensaje);
		
		final ServiceConnection serviceConn = new ServiceConnection(){
			@Override
			public void onServiceConnected(ComponentName arg0, IBinder binder) {
				servicio = ((ServicioEjemplo.LocalBinder)binder).getService();
			}

			@Override
			public void onServiceDisconnected(ComponentName arg0) {
				servicio = null;
			}
		};
		
		btnIniciar.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				serviceIntent = new Intent(MainActivity.this, ServicioEjemplo.class);
				bindService(serviceIntent, serviceConn, Context.BIND_AUTO_CREATE);
				isBound = true;
				startService(serviceIntent);
			}
		});
		btnDetener.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (isBound) {
					unbindService(serviceConn);
				}
				stopService(serviceIntent);
			}
		});
		btnEnviarMensaje.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				servicio.mostrarMensaje("Hola!");
			}
		});
	}
}
