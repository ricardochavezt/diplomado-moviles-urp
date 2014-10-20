package com.diplomadourp.almacenamientoexterno;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btnExportar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnExportar = (Button) findViewById(R.id.btnExportar);
		btnExportar.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		String estado = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(estado)) {
			Toast.makeText(this,
					"Memoria externa disponible como solo lectura",
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (!Environment.MEDIA_MOUNTED.equals(estado)) {
			Toast.makeText(this,
					"Memoria externa no disponible",
					Toast.LENGTH_SHORT).show();
			return;
		}
		exportarIcono();
	}

	private void exportarIcono() {
		File directorioAlbum = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File archivoIcono = new File(directorioAlbum, "icono.png");
		InputStream in = getResources().openRawResource(R.drawable.ic_launcher);
		OutputStream out = null;
		try {
			out = new FileOutputStream(archivoIcono);
			byte[] dataIconoApp = new byte[in.available()];
			in.read(dataIconoApp);
			out.write(dataIconoApp);
			Toast.makeText(this, "Se cre— la imagen en la memoria externa",
					Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			Log.w("AlmacenamientoExterno", "No se pudo crear el archivo", e);
			Toast.makeText(this,
					"No se pudo crear el archivo de imagen: " + e.getLocalizedMessage(),
					Toast.LENGTH_SHORT).show();
		} finally {
			try {
				in.close();
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				Log.w("AlmacenamientoExterno", "Error al cerrar archivo", e);
			}
		}
	}
}
