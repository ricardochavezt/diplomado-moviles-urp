package com.diplomadourp.listadomodelos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends Activity implements
	OnClickListener {
	ImageView iviFoto;
	TextView tviNombre;
	TextView tviProfesion;
	TextView tviFecha;
	TextView tviLugar;
	Button butRegresar; 
	Button butCompartir; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalle_modelo);
		iviFoto = (ImageView)findViewById(R.id.iviFoto);
		tviNombre = (TextView)findViewById(R.id.tviNombre);
		tviProfesion = (TextView)findViewById(R.id.tviProfesion);
		tviFecha = (TextView)findViewById(R.id.tviFecha);
		tviLugar = (TextView)findViewById(R.id.tviLugar);
		butRegresar = (Button)findViewById(R.id.butRegresar);
		butCompartir = (Button)findViewById(R.id.butCompartir);
		
		int position = getIntent().getExtras().getInt("position", 0);
		
		DatosModelo datos = MainActivity.getListaModelos()
				.get(position);
		iviFoto.setImageResource(datos.getIdFoto());
		tviNombre.setText(datos.getNombre());
		tviProfesion.setText(datos.getProfesion());
		tviFecha.setText(datos.getFechaNacimiento());
		tviLugar.setText(datos.getCiudad());
		
		butRegresar.setOnClickListener(this);
		butCompartir.setOnClickListener(this);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_detalle, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_editar) {
			Intent editarIntent = 
					new Intent(this, EditarActivity.class);
			int position = getIntent().getExtras()
					.getInt("position", 0);
			editarIntent.putExtra("position", position);
			startActivity(editarIntent);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.butRegresar:
			finish();
			break;
		case R.id.butCompartir://from: http://developer.android.com/training/sharing/send.html
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, 
					"Modelo . - .");
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			break;

		default:
			break;
		}
		
	}
}
