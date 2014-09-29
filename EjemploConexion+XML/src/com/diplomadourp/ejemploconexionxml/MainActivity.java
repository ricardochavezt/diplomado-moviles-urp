package com.diplomadourp.ejemploconexionxml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.xmlpull.v1.XmlPullParserException;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	
    private static final String URL =
            "http://stackoverflow.com/feeds/tag?tagnames=android&sort=newest";

    private TextView tviTitulo;
	private ListView lviArticulos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tviTitulo = (TextView) findViewById(R.id.tviTitulo);
		lviArticulos = (ListView) findViewById(R.id.lviArticulos);
		
		obtenerListaPosts();
	}

	private void obtenerListaPosts() {
		// Utilizamos el AsyncHttpClient para conectarnos a la URL indicada y obtener la respuesta
		// que después parsearemos como XML
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(URL, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				List<Entry> listaPosts;
				StackOverflowXmlParser feedParser = new StackOverflowXmlParser();
				try {
					listaPosts = feedParser.parse(new ByteArrayInputStream(responseBody));
					tviTitulo.setText(feedParser.getTitulo());
				} catch (XmlPullParserException e) {
					listaPosts = new ArrayList<Entry>();
					Entry errorEntry = new Entry();
					errorEntry.setTitle("Error al leer XML");
					errorEntry.setTags(e.getLocalizedMessage());
					listaPosts.add(errorEntry);
				} catch (IOException e) {
					listaPosts = new ArrayList<Entry>();
					Entry errorEntry = new Entry();
					errorEntry.setTitle("Error de E/S");
					errorEntry.setTags(e.getLocalizedMessage());
					listaPosts.add(errorEntry);
				}
				cargarListaPosts(listaPosts);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				List<Entry> listaPosts = new ArrayList<Entry>();
				Entry errorEntry = new Entry();
				errorEntry.setTitle("Error de conexion al servicio");
				errorEntry.setTags("Error HTTP " + statusCode + ": " + error.getLocalizedMessage());
				listaPosts.add(errorEntry);
				cargarListaPosts(listaPosts);
			}
		});
	}
	
	private void cargarListaPosts(List<Entry> listaPosts) {
		// Aquí vamos a utilizar el SimpleAdapter
		// Este Adapter nos permite asociar los elementos de un Map a vistas de un item
		// de un ListView
		
		// Primero, creamos nuestro List de Maps que guardaran dos valores:
		// - el título del post, con la llave "titulo"
		// - los tags del post, con la llave "tags"
		List<Map<String, String>> contenidoListView = new ArrayList<Map<String, String>>();
		for (Entry post : listaPosts) {
			Map<String, String> item = new HashMap<String, String>();
			item.put("titulo", post.getTitle());
			item.put("tags", post.getTags());
			contenidoListView.add(item);
		}
		// Luego creamos el SimpleAdapter. Con estas líneas le estamos indicando que 
		// tome los elementos del listado contenidoListView y que utilice uno de los
		// layouts predeterminados (android.R.layout.simple_list_item_2), asociando
		// los valores "titulo" y "tags" de cada uno de los elementos del listado
		// a los cuadros de texto android.R.id.text1 y android.R.id.text2
		// (que corresponden a los títulos y subtítulos)
		SimpleAdapter adapter = new SimpleAdapter(this, contenidoListView,
				android.R.layout.simple_list_item_2, 
				new String[] { "titulo", "tags" }, 
				new int[] { android.R.id.text1, android.R.id.text2 });
		// Finalmente lo asignamos al ListView
		lviArticulos.setAdapter(adapter);
	}
}
