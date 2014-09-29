package com.diplomadourp.loginapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegistroActivity extends Activity implements OnClickListener {
	Button btnRegistrar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);
		
		btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
		btnRegistrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// C—digo para conexi—n y registro
		
	}
}
