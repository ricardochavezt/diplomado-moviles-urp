package com.diplomadourp.ejemplolayouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnVerRelativeLayout;
	private Button btnVerTableLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnVerRelativeLayout = (Button) findViewById(R.id.btnRelativeLayout);
		btnVerTableLayout = (Button) findViewById(R.id.btnTableLayout);
		
		btnVerRelativeLayout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				verRelativeLayout();
			}
		});
		btnVerTableLayout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				verTableLayout();
			}
		});
	}

	protected void verRelativeLayout() {
		// Para pasar a otra pantalla (otro Activity), se crea un Intent
		// asociado a la clase que maneja la pantalla
		Intent intent = new Intent(this, RelativeLayoutActivity.class);
		// Con el metodo startActivity, se pide a Android que muestre la pantalla indicada
		startActivity(intent);
	}

	protected void verTableLayout() {
		Intent intent = new Intent(this, TableLayoutActivity.class);
		startActivity(intent);
	}
}
