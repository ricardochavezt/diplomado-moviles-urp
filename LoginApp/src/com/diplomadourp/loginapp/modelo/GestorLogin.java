package com.diplomadourp.loginapp.modelo;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class GestorLogin {
	
	public interface LoginListener {
		public void onLoginResponse(boolean usuarioValido);
	}
	
	private LoginListener listener;

	public LoginListener getListener() {
		return listener;
	}

	public void setListener(LoginListener listener) {
		this.listener = listener;
	}

	public void iniciarSesion(String usuario, String password) {
		AsyncHttpClient clienteHttp = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("usuario", usuario);
		params.put("password", password);
		clienteHttp.setTimeout(20000);
		
		clienteHttp.get("http://desolate-brushlands-2688.herokuapp.com/login", 
				params, 
				new TextHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers, 
							String response) {
						if (response.equals("OK")) {
							listener.onLoginResponse(true);
						}
						else {
							listener.onLoginResponse(false);
						}
					}
					
					@Override
					public void onFailure(int statusCode, Header[] headers, 
							String response, Throwable error) {
						listener.onLoginResponse(false);
					}
				}
		);
	}
}
