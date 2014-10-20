package com.diplomadourp.listadomodelos;

import java.util.ArrayList;
import java.util.List;

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
	
	ListView lviModelos;
	OnModeloSelectedListener listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (activity instanceof OnModeloSelectedListener) {
			listener = (OnModeloSelectedListener) activity;
		}
		else {
			// 1: podemos enviar una falla
			throw new ClassCastException(activity.getClass().getName() + 
					" no implementa la interfaz OnModeloSelectedListener");
			// 2: o podemos escribir en el log
//			Log.w("ListadoModelosFragment", activity.getClass().getName() + 
//					" no implementa la interfaz OnModeloSelectedListener");
		}
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View listadoView = inflater.inflate(R.layout.activity_main, container,
				false);
		return listadoView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);
		lviModelos = (ListView) getView().findViewById(R.id.lviModelos);
		DatabaseManager dbmng = new DatabaseManager();
		ModelosAdapter modelosAdapter = 
				new ModelosAdapter(
						dbmng.getSQLiteModelos(getActivity()), 
						this.getActivity());
		lviModelos.setAdapter(modelosAdapter);
		lviModelos.setOnItemClickListener(this);	
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View itemView,
			int position, long itemId) {
		listener.onModeloSelected(position);
	}	
}

	
	

