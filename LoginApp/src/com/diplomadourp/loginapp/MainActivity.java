package com.diplomadourp.loginapp;

import com.diplomadourp.loginapp.modelo.GestorLogin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends FragmentActivity 
		implements OnClickListener, 
			MensajeDialogFragment.OnDialogResult,
			GestorLogin.LoginListener {
	
	EditText eteUsuario;
	EditText etePassword;
	Button btnIngresar;
	Button btnRegistrarUsuario;
	
	GestorLogin gestorLogin;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		eteUsuario = (EditText) findViewById(R.id.eteUsuario);
		etePassword = (EditText) findViewById(R.id.etePassword);
		btnIngresar = (Button) findViewById(R.id.btnIngresar);
		btnRegistrarUsuario = (Button) findViewById(R.id.btnRegistrarUsuario);
		
		btnIngresar.setOnClickListener(this);
		btnRegistrarUsuario.setOnClickListener(this);
		
		gestorLogin = new GestorLogin();
		gestorLogin.setListener(this);
		
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		String ultimoUsuario = preferences.getString("ultimoUsuario", "");
		eteUsuario.setText(ultimoUsuario);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnIngresar:
			String usuario = eteUsuario.getText().toString();
			String password = etePassword.getText().toString();
			
			llamarLogin(usuario, password);
			break;
		case R.id.btnRegistrarUsuario:
			Intent intent = new Intent(this, RegistroActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}		
	}
	
	public void llamarLogin(final String usuario, final String password) {
		gestorLogin.iniciarSesion(usuario, password);
	}
	

	@Override
	public void onDialogOK() {
		eteUsuario.setText("");
		etePassword.setText("");
		eteUsuario.requestFocus();
	}

	@Override
	public void onLoginResponse(boolean usuarioValido) {
		if (usuarioValido) {
			Intent intent = new Intent(MainActivity.this, 
					UsuarioActivity.class);
			
			String usuario = eteUsuario.getText().toString();
			intent.putExtra("usuario", usuario);
			startActivity(intent);
			Log.i("LoginApp", "usuario v�lido");
		}
		else {
			MensajeDialogFragment dialogo = 
					new MensajeDialogFragment();
			dialogo.show(getSupportFragmentManager(), 
					"mensaje");
			Log.i("LoginApp", "usuario inv�lido");
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("ultimoUsuario", eteUsuario.getText().toString());
		editor.commit();
	}
}
