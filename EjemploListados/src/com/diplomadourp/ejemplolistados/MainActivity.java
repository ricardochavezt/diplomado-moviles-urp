package com.diplomadourp.ejemplolistados;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class MainActivity extends FragmentActivity {
	
	Spinner spnDiasSemana;
	Spinner spnPaises;
	PaisesAdapter paisesAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		paisesAdapter = new PaisesAdapter(this, obtenerListadoPaises());
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.opciones_vista, android.R.layout.simple_spinner_item);
		ActionBar.OnNavigationListener navigationListener = 
				new ActionBar.OnNavigationListener() {
			@Override
			public boolean onNavigationItemSelected(int position, long itemId) {
				Fragment fragmentSel = null;
				FragmentManager fm = getSupportFragmentManager();
				switch (position) {
				case 0:
					fragmentSel = new FragmentListado();
					((FragmentListado)fragmentSel).listadoPaises = obtenerListadoPaises();
					break;
				case 1:
					fragmentSel = new FragmentGrilla();
					((FragmentGrilla)fragmentSel).listadoPaises = obtenerListadoPaises();
					break;

				default:
					break;
				}
				if (fragmentSel != null) {
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.frame_contenido, fragmentSel);
					ft.commit();
				}
				return true;
			}
		};
		actionBar.setListNavigationCallbacks(adapter, navigationListener);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		FragmentListado fl = new FragmentListado();
		fl.listadoPaises = obtenerListadoPaises();
		ft.add(R.id.frame_contenido, fl);
		ft.commit();
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
	
	public static class FragmentListado extends Fragment {
		public List<DataPaises> listadoPaises;
		@Override
		public View onCreateView(LayoutInflater inflater,
				ViewGroup container,
				Bundle savedInstanceState) {
			View layoutListado = inflater.inflate(R.layout.fragment_listado, container, false);
			ListView lviPaises = (ListView) layoutListado.findViewById(R.id.lviPaises);
			lviPaises.setAdapter(new PaisesAdapter(getActivity(), listadoPaises));
			return layoutListado;
		}
	}
	
	public static class FragmentGrilla extends Fragment {
		public List<DataPaises> listadoPaises;
		@Override
		public View onCreateView(LayoutInflater inflater,
				ViewGroup container,
				Bundle savedInstanceState) {
			View layoutGrilla = inflater.inflate(R.layout.fragment_grilla, container, false);
			GridView gviPaises = (GridView) layoutGrilla.findViewById(R.id.gviPaises);
			gviPaises.setAdapter(new PaisesAdapter(getActivity(), listadoPaises));
			return layoutGrilla;
		}
	}
}
