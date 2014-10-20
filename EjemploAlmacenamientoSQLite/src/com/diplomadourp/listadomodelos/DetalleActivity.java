package com.diplomadourp.listadomodelos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends FragmentActivity implements
	OnClickListener {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
		
		Intent intent = getIntent();
		int posicion = intent.getIntExtra("position", 0);
		
		DetalleFragment detalleFragment = 
				(DetalleFragment) getSupportFragmentManager()
				.findFragmentById(R.id.frgDetalle);
		detalleFragment.cargarDatosModelo(posicion);
	}

	@Override
	public void onClick(View view) {
		/*switch (view.getId()) {
		case R.id.butRegresar:
			finish();
			break;

		default:
			break;
		}*/
		//setResult(RESULT_OK);
		finish();
	}
}
