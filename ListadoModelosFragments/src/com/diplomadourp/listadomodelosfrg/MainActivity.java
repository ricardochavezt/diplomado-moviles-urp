package com.diplomadourp.listadomodelosfrg;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.diplomadourp.listadomodelosfrg.R;

public class MainActivity extends FragmentActivity
	implements ListadoFragment.OnModeloSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public static List<DatosModelo> getListaModelos() {
		List<DatosModelo> listaModelos = new ArrayList<DatosModelo>();
		// añadimos datos
		listaModelos.add(new DatosModelo("Angelina Jolie", "Actriz",
				"Los Angeles, California", "20 de Abril del 1974",
				R.drawable.imagen4));
		listaModelos.add(new DatosModelo("Modelo 1", "Super modelo",
				"Los Angeles, California", "24 de Noviembre del 1977",
				R.drawable.imagen1));
		listaModelos.add(new DatosModelo("Modelo 2", "Modelo y actriz",
				"San Francisco, California", "16 de Febrero del 1980",
				R.drawable.imagen2));
		listaModelos.add(new DatosModelo("Modelo 3", "Actriz",
				"Los Angeles, California", "20 de Abril del 1974",
				R.drawable.imagen3));
		listaModelos.add(new DatosModelo("Modelo 4", "Super modelo",
				"Los Angeles, California", "24 de Noviembre del 1977",
				R.drawable.imagen5));
		listaModelos.add(new DatosModelo("Modelo 5", "Modelo y actriz",
				"San Francisco, California", "16 de Febrero del 1980",
				R.drawable.imagen6));
		return listaModelos;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// El Activity recibe los eventos de alto nivel (onModeloSelected)
	@Override
	public void onModeloSelected(int position) {
		if (findViewById(R.id.fragmentContainer) != null) {
			// Estamos en el layout del tablet, as’ que colocamos el fragment de
			// detalle en el fragmentContainer
			DetalleFragment detalleFragment = new DetalleFragment();
			Bundle args = new Bundle();
			args.putInt("position", position);
			detalleFragment.setArguments(args);
			
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.fragmentContainer, detalleFragment);
			fragmentTransaction.commit();
		} else {
			// Estamos en el layout del telŽfono, as’ que lanzamos otro Activity
			Intent intent = new Intent(this, DetalleActivity.class);
			intent.putExtra("position", position);
			startActivity(intent);
		}
	}
}
