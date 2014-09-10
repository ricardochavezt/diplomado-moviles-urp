package com.diplomadourp.loginapp;

import com.diplomadourp.loginapp.modelo.GestorLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	EditText eteUsuario;
	EditText etePassword;
	Button btnIngresar;
	
	GestorLogin gestorLogin;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		eteUsuario = (EditText) findViewById(R.id.eteUsuario);
		etePassword = (EditText) findViewById(R.id.etePassword);
		btnIngresar = (Button) findViewById(R.id.btnIngresar);
		
		btnIngresar.setOnClickListener(this);
		
		gestorLogin = new GestorLogin();
	}

	@Override
	public void onClick(View view) {
		String usuario = eteUsuario.getText().toString();
		String password = etePassword.getText().toString();
		
		if (gestorLogin.iniciarSesion(usuario, password)) {
			Intent intent = new Intent(this, UsuarioActivity.class);
			intent.putExtra("usuario", usuario);
			startActivity(intent);
		}
		else {
			Toast.makeText(this, "Usuario inv‡lido", Toast.LENGTH_SHORT).show();
		}
	}
}
