package com.diplomadourp.ejemplolistados2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

// Hacemos que nuestro Activity implemente estas 2 interfaces para poder recibir
// los 2 tipos de eventos que pueden emitir los elementos de listado
public class MainActivity extends Activity implements OnItemClickListener,
		OnItemSelectedListener {
	Spinner spnDepartamentos;
	// Un ListView se representa con la clase ListView
	ListView lviProvincias;
	
	int[] arraysProvincias = {
			R.array.lista_provincias_1,	R.array.lista_provincias_2,
			R.array.lista_provincias_3,	R.array.lista_provincias_4,
			R.array.lista_provincias_5,	R.array.lista_provincias_6,
			R.array.lista_provincias_7,	R.array.lista_provincias_8,
			R.array.lista_provincias_9,	R.array.lista_provincias_10,
			R.array.lista_provincias_11, R.array.lista_provincias_12,
			R.array.lista_provincias_13, R.array.lista_provincias_14,
			R.array.lista_provincias_15, R.array.lista_provincias_16,
			R.array.lista_provincias_17, R.array.lista_provincias_18,
			R.array.lista_provincias_19, R.array.lista_provincias_20,
			R.array.lista_provincias_21, R.array.lista_provincias_22,
			R.array.lista_provincias_23, R.array.lista_provincias_24
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		spnDepartamentos = (Spinner) findViewById(R.id.spnDepartamentos);
		lviProvincias = (ListView) findViewById(R.id.lviProvincias);
		
		ArrayAdapter<CharSequence> adapterDepartamentos = 
				ArrayAdapter.createFromResource(this, R.array.lista_departamentos,
						android.R.layout.simple_spinner_item);
		adapterDepartamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnDepartamentos.setAdapter(adapterDepartamentos);
		
		// Al igual que los Spinner, los ListView requieren un Adapter
		ArrayAdapter<CharSequence> adapterProvincias = 
				ArrayAdapter.createFromResource(this, R.array.lista_provincias_0,
						android.R.layout.simple_list_item_1);
		lviProvincias.setAdapter(adapterProvincias);
		// Asignamos los listeners para que podamos recibir los eventos
		spnDepartamentos.setOnItemSelectedListener(this);
		lviProvincias.setOnItemClickListener(this);
	}
	
	// La diferencia entre el OnItemSelectedListener y el OnItemClickListener es que
	// el primero se llama solamente cuando la selección cambia; si yo selecciono el
	// mismo elemento previamente seleccionado en un Spinner, por ejemplo, el evento no
	// se disparará.
	// En cambio, el OnItemClickListener recibe eventos siempre que se seleccione un elemento
	// , sin importar si es el mismo o diferente
	@Override
	public void onItemSelected(AdapterView<?> adapterView, View itemView, int position,
			long itemId) {
		// Este es otro modo de cargar un Adapter a partir de un arreglo de Strings definido
		// en strings.xml
		// Primero cargamos el arreglo de Strings desde los recursos
		String[] listaProvincias = getResources().getStringArray(arraysProvincias[position]);
		// Luego creamos el adapter con uno de sus constructores
		lviProvincias.setAdapter(new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_list_item_1, listaProvincias));
	}

	// El OnItemSelectedListener también nos notifica si el usuario no ha hecho una selección
	// (útil más que nada para Spinners)
	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {
		Log.i("EjemploListados2", "No se seleccionó ningún elemento");
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View itemView, int position,
			long itemId) {
		String nombreProvincia = adapterView.getItemAtPosition(position).toString();
		Toast.makeText(this, "Se seleccionó: " + nombreProvincia, Toast.LENGTH_LONG).show();
	}
}
