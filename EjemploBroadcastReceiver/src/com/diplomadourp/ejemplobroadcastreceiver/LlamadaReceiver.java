package com.diplomadourp.ejemplobroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LlamadaReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("EjemploBroadcastReceiver", "Llamada recibida");
		Log.i("EjemploBroadcastReceiver", 
				intent.getExtras().toString());
		Log.i("EjemploBroadcastReceiver", 
				getResultExtras(true).toString());
	}

}
