package com.diplomadourp.ejemplolistados;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	
	Spinner spnDiasSemana;
	Spinner spnPaises;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		spnDiasSemana = (Spinner) findViewById(R.id.spnDiasSemana);
		spnPaises = (Spinner) findViewById(R.id.spnPaises);
		
		// Este es el caso más sencillo: cuando tenemos un arreglo constante de cadenas
		// Previamente, hemos definido en el archivo strings.xml un string-array con nombre diasSemana
		// Para utilizarlo, creamos un ArrayAdapter con el método createFromResource
		ArrayAdapter<CharSequence> adapterDiasSemana = 
				ArrayAdapter.createFromResource(this, R.array.diasSemana,
						android.R.layout.simple_spinner_item);
		adapterDiasSemana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnDiasSemana.setAdapter(adapterDiasSemana);
		
		// Para el caso personalizado, hemos creado previamente nuestra clase PaisesAdapter y
		// nuestro layout para el item del listado (list_item_paises.xml)
		// Aquí creamos el adapter y lo asignamos al Spinner
		PaisesAdapter adapterPaises = new PaisesAdapter(this, obtenerListadoPaises());
		spnPaises.setAdapter(adapterPaises);
	}
	
	private List<DataPaises> obtenerListadoPaises() {
		List<DataPaises> listadoPaises = new ArrayList<DataPaises>();
		listadoPaises.add(new DataPaises("Argentina", R.drawable.ic_argentina));
		listadoPaises.add(new DataPaises("Australia", R.drawable.ic_australia));
		listadoPaises.add(new DataPaises("Austria", R.drawable.ic_austria));
		listadoPaises.add(new DataPaises("Belgica", R.drawable.ic_belgica));
		listadoPaises.add(new DataPaises("Brasil", R.drawable.ic_brasil));
		listadoPaises.add(new DataPaises("Camerun", R.drawable.ic_camerun));
		listadoPaises.add(new DataPaises("Canada", R.drawable.ic_canada));
		listadoPaises.add(new DataPaises("Chile", R.drawable.ic_chile));
		return listadoPaises;
	}
}
