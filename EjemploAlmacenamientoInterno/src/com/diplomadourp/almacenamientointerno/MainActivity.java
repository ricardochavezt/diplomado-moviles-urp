package com.diplomadourp.almacenamientointerno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
		implements OnClickListener {
	EditText eteContenido;
	Button btnGuardar;
	TextView tviDirectorio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		eteContenido = (EditText) findViewById(R.id.eteContenido);
		btnGuardar = (Button) findViewById(R.id.btnGuardar);
		tviDirectorio = (TextView) findViewById(R.id.tviDirectorio);
		
		btnGuardar.setOnClickListener(this);
		
		tviDirectorio.setText(getFilesDir().getPath());
		FileInputStream in = null; 
		try {
			in = openFileInput("contenido");
			Reader reader = new InputStreamReader(in);
			char[] buffer = new char[100];
			StringBuffer stringBuffer = new StringBuffer();
			int charsRead;
			do {
				charsRead = reader.read(buffer);
				if (charsRead != -1) {
					stringBuffer.append(buffer, 0, 
							charsRead);
				}
			} while (charsRead != -1);
			eteContenido.setText(stringBuffer);
		} catch (FileNotFoundException e) {
			Log.w("EjemploAlmacenamientoInterno", 
					"No existe el archivo, se creará");
		} catch (IOException e) {
			Log.e("EjemploAlmacenamientoInterno", 
					"Error al leer archivo", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					Log.e("EjemploAlmacenamientoInterno", 
							"Error al cerrar archivo", e);
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		Writer writer = null;
		FileOutputStream out = null;
		try {
			out = openFileOutput("contenido", MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(eteContenido.getText().toString());
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("EjemploAlmacenamientoInterno", 
					"Error al leer archivo", e);
			Toast.makeText(this,
					"Error al guardar archivo: " + 
							e.getLocalizedMessage(),
					Toast.LENGTH_SHORT).show();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					Log.e("EjemploAlmacenamientoInterno", 
							"Error al cerrar archivo", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					Log.e("EjemploAlmacenamientoInterno", 
							"Error al cerrar archivo", e);
				}
			}
		}
	}

}
