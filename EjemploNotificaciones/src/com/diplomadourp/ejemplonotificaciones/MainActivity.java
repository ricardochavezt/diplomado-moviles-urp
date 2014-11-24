package com.diplomadourp.ejemplonotificaciones;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btnNotificar;
	TextView tviNotificacion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnNotificar = (Button) findViewById(R.id.btnNotificacion);
		tviNotificacion = (TextView) findViewById(R.id.tviMensaje);
		btnNotificar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				enviarNotificacion();
			}
		});
		boolean notificacion = false;
		if (getIntent().getExtras() != null) {
			notificacion = getIntent().getExtras().getBoolean(
					"notificacion");
		}
		if (notificacion) {
			tviNotificacion.setText("Notificacion leída");
		}
	}

	protected void enviarNotificacion() {
		NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(
				this);
		notifBuilder.setSmallIcon(android.R.drawable.ic_menu_edit)
			.setContentTitle("Notificacion")
			.setContentText("Algo importante")
			.setAutoCancel(true);
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("notificacion", true);
		PendingIntent pendingIntent = 
				PendingIntent.getActivity(this, 0,
						intent, 
						PendingIntent.FLAG_UPDATE_CURRENT);
		notifBuilder.setContentIntent(pendingIntent);
		
		int notificationId = 1;
		NotificationManager notifMr = 
				(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notifMr.notify(notificationId, notifBuilder.build());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
