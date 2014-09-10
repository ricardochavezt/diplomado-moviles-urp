package com.diplomadourp.listadomodelosfrg;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListadoFragment extends Fragment implements OnItemClickListener {
	
	public interface OnModeloSelectedListener {
		public void onModeloSelected(int position);
	}
	
	OnModeloSelectedListener listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (activity instanceof OnModeloSelectedListener) {
			listener = (OnModeloSelectedListener) activity;
		} else {
			// Opci—n 1: lanzar una excepci—n
			throw new ClassCastException(activity.getClass().getName()
					+ " no implementa la interfaz OnModeloSelectedListener");
			// Opci—n 2: simplemente escribir un mensaje (o no hacer nada)
//			Log.w("ListadoModelosFragments", activity.getClass().getName()
//					+ " no implementa la interfaz OnModeloSelectedListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listado_modelos, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ListView lviModelos = (ListView) getView().findViewById(R.id.lviModelos);
		ModelosAdapter modelosAdapter = 
				new ModelosAdapter(MainActivity.getListaModelos(), this.getActivity());
		lviModelos.setAdapter(modelosAdapter);
		// Escuchamos los eventos de click en los elementos del ListView para
		// reenviarlos al Activity como eventos de alto nivel
		lviModelos.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View itemView,
			int position, long itemId) {
		// Retransmitimos el evento pero como uno de alto nivel (selecci—n de una modelo)
		listener.onModeloSelected(position);
	}
}
