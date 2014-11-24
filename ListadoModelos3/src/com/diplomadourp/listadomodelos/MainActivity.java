package com.diplomadourp.listadomodelos;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity
	implements OnItemClickListener {
	ListView lviModelos;
	static List<DatosModelo> listaModelos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lviModelos = (ListView) findViewById(R.id.lviModelos);
		ModelosAdapter modelosAdapter = 
				new ModelosAdapter(getListaModelos(), this);
		lviModelos.setAdapter(modelosAdapter);
		lviModelos.setOnItemClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("ListadoModelos", "onResume");
		lviModelos.setAdapter(new ModelosAdapter(getListaModelos(), this));
	}

	public static List<DatosModelo> getListaModelos() {
		if (listaModelos == null) {
			listaModelos = new ArrayList<DatosModelo>();
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
		}
		return listaModelos;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter,
			View arg1view, 
			int position, long id) {
		Intent intent = new Intent(this,
				DetalleActivity.class);
		//intent.getExtras().putInt("position", position);
		//solo despues de usar put
		intent.putExtra("position", position);
		startActivity(intent);
		
		//startActivityForResult(intent, RESULT_CODE);
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
