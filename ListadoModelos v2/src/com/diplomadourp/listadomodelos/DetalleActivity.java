package com.diplomadourp.listadomodelos;

import android.app.Activity;
import android.os.Bundle;
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
		
		int position = getIntent().getExtras().getInt("position", 0);
		
		DatosModelo datos = MainActivity.getListaModelos()
				.get(position);
		iviFoto.setImageResource(datos.getIdFoto());
		tviNombre.setText(datos.getNombre());
		tviProfesion.setText(datos.getProfesion());
		tviFecha.setText(datos.getFechaNacimiento());
		tviLugar.setText(datos.getCiudad());
		
		butRegresar.setOnClickListener(this);
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
