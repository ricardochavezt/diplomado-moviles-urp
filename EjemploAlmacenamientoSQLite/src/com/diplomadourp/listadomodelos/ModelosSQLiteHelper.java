package com.diplomadourp.listadomodelos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ModelosSQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Modelos.db";
	private static final int DATABASE_VERSION = 1;
	private static final String SQL_MODELOS = 
			"CREATE TABLE IF NOT EXISTS T_MODELO \r\n" + 
			"(id integer primary key AUTOINCREMENT, \r\n" + 
			"nombre TEXT, profesion TEXT, ciudad TEXT, \r\n" + 
			"fechanacimiento TEXT, foto TEXT)";
	
	public ModelosSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, 
				DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_MODELOS);
		Log.i("SQLiteEjemplo", " Se creo la BD");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
