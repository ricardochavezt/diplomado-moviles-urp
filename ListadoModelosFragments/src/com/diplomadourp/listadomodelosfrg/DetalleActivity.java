package com.diplomadourp.listadomodelosfrg;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.diplomadourp.listadomodelosfrg.R;

public class DetalleActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
		
		int position = getIntent().getExtras().getInt("position", 0);
		DetalleFragment detalleFragment = (DetalleFragment) getSupportFragmentManager()
				.findFragmentById(R.id.frgDetalle);
		detalleFragment.cargarDatos(position);
	}
}
