package com.diplomadourp.listadomodelosfrg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleFragment extends Fragment {
	ImageView iviFoto;
	TextView tviNombre;
	TextView tviProfesion;
	TextView tviFecha;
	TextView tviLugar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.detalle_modelo, container, false);
		iviFoto = (ImageView)view.findViewById(R.id.iviFoto);
		tviNombre = (TextView)view.findViewById(R.id.tviNombre);
		tviProfesion = (TextView)view.findViewById(R.id.tviProfesion);
		tviFecha = (TextView)view.findViewById(R.id.tviFecha);
		tviLugar = (TextView)view.findViewById(R.id.tviLugar);
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		Bundle args = getArguments();
		if (args != null) {
			int position = args.getInt("position", 0);
			cargarDatos(position);
		}
	}
	
	public void cargarDatos(int position) {
		DatosModelo datos = MainActivity.getListaModelos().get(position);
		iviFoto.setImageResource(datos.getIdFoto());
		tviNombre.setText(datos.getNombre());
		tviProfesion.setText(datos.getProfesion());
		tviFecha.setText(datos.getFechaNacimiento());
		tviLugar.setText(datos.getCiudad());
	}
}
