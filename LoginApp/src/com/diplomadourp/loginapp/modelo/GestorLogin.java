package com.diplomadourp.loginapp.modelo;

public class GestorLogin {

	public boolean iniciarSesion(String usuario, String password) {
		return usuario.equals("admin") && password.equals("admin");
	}
}
