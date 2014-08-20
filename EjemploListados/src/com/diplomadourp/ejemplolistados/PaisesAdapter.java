package com.diplomadourp.ejemplolistados;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PaisesAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
	private Context context;
	private List<DataPaises> lista;
	
	public PaisesAdapter(Context context, List<DataPaises> lista) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.lista = lista;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int pos) {
		return lista.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		View itemView;
		ContenedorVistas contenedorVistas;
		// Verificamos si podemos reutilizar la vista
		if (convertView == null) {
			// Tenemos que crear la vista de nuestro item a partir del layout XML
			itemView = inflater.inflate(R.layout.list_item_pases, null);
			// Creamos también el contenedor para guardar las referencias a nuestras vistas
			contenedorVistas = new ContenedorVistas();
			// Buscamos las vistas y guardamos las referencias en el contenedor
			contenedorVistas.iviBandera = (ImageView) itemView.findViewById(R.id.iviBandera);
			contenedorVistas.tviNombre = (TextView) itemView.findViewById(R.id.tviNombre);
			// Guardamos el contenedor en la vista, con setTag
			// Así lo podremos recuperar a la siguiente llamada a getView
			itemView.setTag(contenedorVistas);
		} else {
			// Podemos reutilizar la vista, que nos llega en el parámetro convertView
			itemView = convertView;
			contenedorVistas = (ContenedorVistas) convertView.getTag();
		}
		// Finalmente, seteamos los datos correctos a través del contenedor,
		// que tiene las referencias a los componentes
		DataPaises dataPais = lista.get(pos);
		contenedorVistas.iviBandera.setImageResource(dataPais.getIdImagenBandera());
		contenedorVistas.tviNombre.setText(dataPais.getNombrePais());
		return itemView;
	}

	class ContenedorVistas {
		TextView tviNombre;
		ImageView iviBandera;
	}
}
