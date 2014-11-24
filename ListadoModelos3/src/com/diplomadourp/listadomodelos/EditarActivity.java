package com.diplomadourp.listadomodelos;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class EditarActivity extends Activity {
	
	EditText txtNombre;
	EditText txtFechaNacimiento;
	EditText txtProfesion;
	EditText txtLugar;
	int idFoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar);
		
		txtNombre = (EditText) findViewById(R.id.txtNombre);
		txtFechaNacimiento = (EditText) findViewById(R.id.txtFechaNacimiento);
		txtProfesion = (EditText) findViewById(R.id.txtProfesion);
		txtLugar = (EditText) findViewById(R.id.txtLugar);
		
		if (getIntent().getExtras() != null) {
			int posicion = getIntent().getExtras()
					.getInt("position");
			DatosModelo datos = MainActivity.getListaModelos()
					.get(posicion);
			txtNombre.setText(datos.getNombre());
			txtProfesion.setText(datos.getProfesion());
			txtFechaNacimiento.setText(datos.getFechaNacimiento());
			txtLugar.setText(datos.getCiudad());
			idFoto = datos.getIdFoto();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_editar, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		guardarDatosModelo();
	}

	private void guardarDatosModelo() {
		if (getIntent().getExtras() != null) {
			DatosModelo nuevosDatos = new DatosModelo(
					txtNombre.getText().toString(),
					txtProfesion.getText().toString(),
					txtLugar.getText().toString(),
					txtFechaNacimiento.getText().toString(),
					idFoto);
			int posicion = getIntent().getExtras()
					.getInt("position");
			MainActivity.getListaModelos()
				.set(posicion, nuevosDatos);
			Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT)
				.show();
		}		
	}
}
