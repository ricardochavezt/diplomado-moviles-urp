package com.diplomadourp.listadomodelos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager {
	public void init(Context context){
		ModelosSQLiteHelper dbHelper = 
				new ModelosSQLiteHelper(context);
		
	}
	public void llenarDatos(Context context){
		ModelosSQLiteHelper dbHelper = new ModelosSQLiteHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		List<DatosModelo> listado = getListadoModelos();
		for (DatosModelo modelo : listado) {
			ContentValues valores = new ContentValues();
			valores.put("nombre", modelo.getNombre());
			valores.put("profesion", modelo.getProfesion());
			valores.put("fechanacimiento", modelo.getFechaNacimiento());
			valores.put("ciudad", modelo.getCiudad());
			valores.put("foto", "");
			db.insert("T_MODELO", null, valores);
		}
		
		db.close();
		Log.i("SQLiteEjemplo" , " Nuevo");
	}
	public List<DatosModelo> getSQLiteModelos(Context context){
		ModelosSQLiteHelper dbHelper = new ModelosSQLiteHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String[] projection = {"nombre","profesion",
				"fechanacimiento", "ciudad", "foto"};
		String sortOrder = "nombre ASC";
		String selection = null;
		String[] selectionArgs = null;

		Cursor c = db.query(
		    "T_MODELO",  //tabla
		    projection,        // columnas
		    selection,         // columnas WHERE
		    selectionArgs,     // valores WHERE 
		    null,              // grupo
		    null,              // filtro de grupo
		    sortOrder          // orden
		    );
		List<DatosModelo> list = new ArrayList<DatosModelo>();
		while (c.moveToNext()) {
			DatosModelo modelo = new DatosModelo(
					c.getString(0), //nombre
					c.getString(1), //profesion
					c.getString(3), //ciudad
					c.getString(2), //fechanacimiento
					0);//c.getString(4)//foto
			list.add(modelo);
		}
		return list;
	}
	public List<DatosModelo> getListadoModelos(){
		List<DatosModelo> listaModelos = new ArrayList<DatosModelo>();
		// añadimos datos
		listaModelos.add(new DatosModelo("Angelina Jolie", "Actriz",
				"Los Angeles, California", "20 de Abril del 1974",
				R.drawable.imagen4));
		listaModelos.add(new DatosModelo("Modelo 1", "Super modelo",
				"Los Angeles, California", "24 de Noviembre del 1977",
				R.drawable.imagen1));
		listaModelos.add(new DatosModelo("Modelo 2", "Modelo y actriz",
				"San Francisco, California", "16 de Febrero del 1980",
				R.drawable.imagen2));
		listaModelos.add(new DatosModelo("Modelo 3", "Actriz",
				"Los Angeles, California", "20 de Abril del 1974",
				R.drawable.imagen3));
		listaModelos.add(new DatosModelo("Modelo 4", "Super modelo",
				"Los Angeles, California", "24 de Noviembre del 1977",
				R.drawable.imagen5));
		listaModelos.add(new DatosModelo("Modelo 5", "Modelo y actriz",
				"San Francisco, California", "16 de Febrero del 1980",
				R.drawable.imagen6));
		return listaModelos;
	}
}
