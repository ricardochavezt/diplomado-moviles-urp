package com.diplomadourp.loginapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UsuarioActivity extends Activity {
	TextView tviBienvenida;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuario);
		
		tviBienvenida = (TextView) findViewById(R.id.tviBienvenida);
		String usuario = getIntent().getExtras().getString("usuario");
		if (usuario != null) {
			tviBienvenida.setText("Bienvenido " + usuario);
		} else {
			tviBienvenida.setText("Bienvenido");
		}
	}
}