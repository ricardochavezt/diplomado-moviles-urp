package com.diplomadourp.busquedagoogle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadosActivity extends Activity {
	private TextView tviTituloResultado;
	private ListView lviResultados;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultados);
		
		tviTituloResultado = (TextView) findViewById(R.id.tviTituloResultado);
		lviResultados = (ListView) findViewById(R.id.lviResultados);
		
		String terminoBusqueda = getIntent().getExtras().getString("terminoBusqueda");
		realizarBusquedaGoogle(terminoBusqueda);
	}

	private void realizarBusquedaGoogle(final String terminoBusqueda) {
		// Primero, cambiamos el texto de tviTituloResultado para indicar que estamos cargando los resultados
		tviTituloResultado.setText(getString(R.string.titulo_cargando));
		
		String urlBusqueda = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + terminoBusqueda;
		// Creamos el AsyncHttpClient y le decimos que obtenga mediante HTTP GET el contenido de la URL de búsqueda
		// que hemos generado en la línea anterior, y que nos devuelva el contenido como texto
		AsyncHttpClient clienteBusquedaGoogle = new AsyncHttpClient();
		clienteBusquedaGoogle.get(urlBusqueda, new TextHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseText,
					Throwable error) {
				// En caso de error, mostramos de todas formas el título, y un elemento en el listado que indique que
				// ha ocurrido un error
				tviTituloResultado.setText(
						getResources().getString(R.string.titulo_resultado,
								terminoBusqueda));
				cargarResultados(crearResultadoError());
				// Y un Toast que indique el error también
				Toast.makeText(getApplicationContext(),
						"Error al realizar la búsqueda: " + error.getLocalizedMessage(),
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, String responseText) {
				tviTituloResultado.setText(
						getResources().getString(R.string.titulo_resultado,
								terminoBusqueda));
				try {
					JSONObject jsonResponse = new JSONObject(responseText);
					List<Map<String, String>> listaResultados = parsearJSON(jsonResponse);
					cargarResultados(listaResultados);
				} catch (JSONException e) {
					// Las clases para interpretar JSON propias de Android arrojan errores en caso el resultado JSON
					// no tenga el formato correcto
					// En ese caso, mostramos un error también
					cargarResultados(crearResultadoError());
					Toast.makeText(getApplicationContext(),
							"Error al interpretar resultado JSON: " + e.getLocalizedMessage(),
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	// Este método obtiene los resultados del JSON de respuesta del servicio y los vuelca en un List de Maps
	// que luego podemos utilizar con el SimpleAdapter
	protected List<Map<String, String>> parsearJSON(JSONObject jsonResponse) throws JSONException {
		List<Map<String, String>> resultados = new ArrayList<Map<String, String>>();
		//	Con estas líneas obtenemos acceso al objeto 'results' que contiene los resultados de búsqueda en sí
		JSONArray resultsArray = jsonResponse.getJSONObject("responseData").getJSONArray("results");
		for (int i = 0; i < resultsArray.length(); i++) {
			// Recorremos cada elemento de listado de resultados y sacamos los datos relevantes
			JSONObject jsonResult = resultsArray.getJSONObject(i);
			Map<String, String> resultado = new HashMap<String, String>();
			resultado.put("titulo", jsonResult.getString("titleNoFormatting"));
			resultado.put("url", jsonResult.getString("url"));
			resultado.put("descripcion", jsonResult.getString("content"));
			resultados.add(resultado);
		}
		return resultados;
	}

	protected void cargarResultados(List<Map<String, String>> listaResultados) {
		// Estas son las llaves que hemos utilizado para guardar los datos en los Maps
		String[] llavesMap = new String[] { "titulo", "url", "descripcion" };
		// Estos son los ids de los componentes correspondientes donde queremos que vaya cada dato
		int[] idsComponentes = new int[] { R.id.tviResultadoTitulo,
				R.id.tviResultadoURL, R.id.tviResultadoDescripcion };
		// Hemos diseñado un layout, resultado_list_item.xml, con el aspecto que queremos que tenga
		// cada elemento del listado
		// Con el SimpleAdapter, podemos utilizar este layout y definir qué datos van en cuales
		// de los componentes
		SimpleAdapter adapter = new SimpleAdapter(this, listaResultados,
				R.layout.resultado_list_item, llavesMap, idsComponentes);
		lviResultados.setAdapter(adapter);
	}
	
	// Este método nos sirve solamente para crear un elemento del listado
	// que muestre un mensaje de error
	protected List<Map<String, String>> crearResultadoError() {
		Map<String, String> resultadoError = new HashMap<String, String>();
		resultadoError.put("titulo", "Error al realizar la búsqueda");
		resultadoError.put("url", "-");
		resultadoError.put("descripcion", "");
		
		return Collections.singletonList(resultadoError);
	}
}
