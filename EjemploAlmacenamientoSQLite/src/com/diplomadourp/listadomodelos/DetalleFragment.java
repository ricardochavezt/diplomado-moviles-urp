package com.diplomadourp.listadomodelos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleFragment extends Fragment {

	ImageView iviFoto;
	TextView tviNombre;
	TextView tviProfesion;
	TextView tviFecha;
	TextView tviLugar;
	Button butRegresar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View detalleView = inflater.inflate(R.layout.detalle_modelo, container,
				false);
		iviFoto = (ImageView)detalleView.findViewById(R.id.iviFoto);
		tviNombre = (TextView)detalleView.findViewById(R.id.tviNombre);
		tviProfesion = (TextView)detalleView.findViewById(R.id.tviProfesion);
		tviFecha = (TextView)detalleView.findViewById(R.id.tviFecha);
		tviLugar = (TextView)detalleView.findViewById(R.id.tviLugar);
		butRegresar = (Button)detalleView.findViewById(R.id.butRegresar);
		return detalleView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		Bundle args = getArguments();
		if (args != null) {
			int position = args.getInt("position", 0);
			cargarDatosModelo(position);
		}
	}
	
	public void cargarDatosModelo(int posicion) {
		DatabaseManager dbmng = new DatabaseManager();
		DatosModelo datos = dbmng.getSQLiteModelos(getActivity()).get(
				posicion);
		iviFoto.setImageResource(datos.getIdFoto());
		tviNombre.setText(datos.getNombre());
		tviProfesion.setText(datos.getProfesion());
		tviFecha.setText(datos.getFechaNacimiento());
		tviLugar.setText(datos.getCiudad());	
	}
}
