package com.diplomadourp.busquedagoogle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	private EditText eteTerminoBusqueda;
	private Button btnBuscar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		eteTerminoBusqueda = (EditText) findViewById(R.id.eteTerminoBusqueda);
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		btnBuscar.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// Solamente tomamos el texto ingresado por el usuario y lo enviamos
		// al Activity de resultados
		String terminoBusqueda = eteTerminoBusqueda.getText().toString();
		Intent intentBusqueda = new Intent(this, ResultadosActivity.class);
		intentBusqueda.putExtra("terminoBusqueda", terminoBusqueda);
		
		startActivity(intentBusqueda);
	}
}
