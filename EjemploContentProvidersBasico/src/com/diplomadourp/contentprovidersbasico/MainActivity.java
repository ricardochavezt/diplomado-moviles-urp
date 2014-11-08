package com.diplomadourp.contentprovidersbasico;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tviNombre;
	private ImageView iviFoto;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tviNombre = (TextView) findViewById(R.id.tviNombre);
		iviFoto = (ImageView) findViewById(R.id.iviFoto);
		
		String[] columnas = new String[] {
				Profile._ID,
				Profile.DISPLAY_NAME_PRIMARY,
				Profile.PHOTO_THUMBNAIL_URI
		};
		Cursor cursorContacto = getContentResolver().query(Profile.CONTENT_URI,
				columnas, null, null, null);
		if (cursorContacto != null && cursorContacto.moveToFirst()) {
			String nombrePersona = cursorContacto.getString(cursorContacto
					.getColumnIndex(Profile.DISPLAY_NAME_PRIMARY));
			String uriFoto = cursorContacto.getString(cursorContacto
					.getColumnIndex(Profile.PHOTO_THUMBNAIL_URI));
			
			tviNombre.setText(nombrePersona);
			iviFoto.setImageURI(Uri.parse(uriFoto));
		}
		else {
			tviNombre.setText("No se encuentra informaci—n del usuario");
		}
	}
}