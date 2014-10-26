package com.diplomado.ejemploviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScreenSlidePageFragment extends Fragment {
	// Constante para el nombre del argumento que guarda la p‡gina
	public static final String ARG_PAGINA = "pagina";
	
	private int numPagina;
	
	public static ScreenSlidePageFragment crear(int numeroPagina) {
		ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
		// Creamos un Bundle y lo asignamos como argumentos del Fragment
		// Es una forma de guardar informaci—n para tenerla disponible en el onCreate
		Bundle argumentos = new Bundle();
		argumentos.putInt(ARG_PAGINA, numeroPagina);
		fragment.setArguments(argumentos);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Aqu’ recuperamos el nœmero de p‡gina de los argumentos del Fragment
		numPagina = getArguments().getInt(ARG_PAGINA);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_screen_slide_page, container, false);
		// Cambiamos el texto del TextView para mostrar la p‡gina
		TextView tviTextoSlide = (TextView) rootView.findViewById(R.id.tviTextoSlide);
		tviTextoSlide.setText(String.format("P‡gina %d", numPagina));
		return rootView;
	}
}
