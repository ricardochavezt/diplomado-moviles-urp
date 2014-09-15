package com.diplomadourp.loginapp.modelo;

public class GestorLogin {

	public boolean iniciarSesion(String usuario, String password) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return usuario.equals("admin") && password.equals("admin");
	}
}
