package com.diplomadourp.listadomodelos;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends FragmentActivity
	implements ListadoFragment.OnModeloSelectedListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_phone);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		DatabaseManager dbmng = new DatabaseManager();
		dbmng.llenarDatos(this);
		
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onModeloSelected(int position) {
		// buscamos el sitio del fragment
		View fragmentContainer = findViewById(R.id.frmDetalle);
		if (fragmentContainer != null) {
			// estamos en un tablet
			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			
			DetalleFragment detalleFragment = new DetalleFragment();
			Bundle args = new Bundle();
			args.putInt("position", position);
			detalleFragment.setArguments(args);
			transaction.replace(R.id.frmDetalle, detalleFragment);
			transaction.commit();
		}
		else {
			// estamos en un smartphone
			Intent detalleIntent = new Intent(this, DetalleActivity.class);
			detalleIntent.putExtra("position", position);
			startActivity(detalleIntent);
		}
	}

	/*int RESULT_CODE = 100;
	@Override
	protected void onActivityResult(int requestCode, 
			int resultCode, 
			Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_CODE) {
			if(resultCode == RESULT_OK){
				//TODO OK
			}else if(resultCode == RESULT_CANCELED){
				//TODO CANCEL				
			}
		}
	}*/
}
