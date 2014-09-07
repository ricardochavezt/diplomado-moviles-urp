package com.diplomadourp.listadomodelos;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ModelosAdapter extends BaseAdapter {
	
	List<DatosModelo> lista;
	LayoutInflater inflater;
	
	public ModelosAdapter(List<DatosModelo> lista, Context context) {
		this.lista = lista;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView;
		ContenedorVistas contenedorVistas;
		if (convertView == null) {
			// crear la vista desde cero
			itemView = inflater.inflate(R.layout.list_item_modelo, null);
			contenedorVistas = new ContenedorVistas();
			contenedorVistas.iviFoto = 
					(ImageView) itemView.findViewById(R.id.iviFoto);
			contenedorVistas.tviNombre = 
					(TextView) itemView.findViewById(R.id.tviNombre);
			contenedorVistas.tviProfesion = 
					(TextView) itemView.findViewById(R.id.tviProfesion);
			itemView.setTag(contenedorVistas);
		}
		else {
			// podemos reutilizar la vista
			itemView = convertView;
			contenedorVistas = (ContenedorVistas) convertView.getTag();
		}
		DatosModelo datos = lista.get(position);
		contenedorVistas.iviFoto.setImageResource(datos.getIdFoto());
		contenedorVistas.tviNombre.setText(datos.getNombre());
		contenedorVistas.tviProfesion.setText(datos.getProfesion());
		
		return itemView;
	}

	class ContenedorVistas {
		ImageView iviFoto;
		TextView tviNombre;
		TextView tviProfesion;
	}
}
