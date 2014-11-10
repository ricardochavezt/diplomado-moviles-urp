package com.diplomadourp.ejemploservices;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServicioEjemplo extends Service {
	TareaServicio tareaServicio;
	Intent intent;
	IBinder binder = new LocalBinder();
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("EjemploService", "Servicio creado");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("EjemploService", "Servicio iniciado");
		tareaServicio = new TareaServicio();
		tareaServicio.execute((Void[])null);
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void mostrarMensaje(String mensaje) {
		Log.i("EjemploService", mensaje);
	}
	
	@Override
	public void onDestroy() {
		tareaServicio.cancel(true);
		Log.i("EjemploService", "Servicio detenido");
	}
	
	class TareaServicio extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			int cont = 0;
			while (!isCancelled() && cont < 20) {
				Log.i("EjemploService", "Contador: " + cont++);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			ServicioEjemplo.this.stopSelf();
		}
	}
	
	public class LocalBinder extends Binder {
		ServicioEjemplo getService() {
			return ServicioEjemplo.this;
		}
	}
}