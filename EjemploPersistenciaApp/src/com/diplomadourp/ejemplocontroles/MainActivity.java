package com.diplomadourp.ejemplocontroles;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText txtNombre;
	private CheckBox chkAdmiracion;
	private RadioGroup rbgTratamiento;
	private Button btnMostrarMensaje;
	
	private String nombreAnterior;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        chkAdmiracion = (CheckBox) findViewById(R.id.chkAdmiracion);
        rbgTratamiento = (RadioGroup) findViewById(R.id.rbgTratamiento);
        btnMostrarMensaje = (Button) findViewById(R.id.btnMostrarMensaje);
        
        btnMostrarMensaje.setOnClickListener(this);
        
        chkAdmiracion.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String mensaje;
				if (isChecked) {
					mensaje = "chkAdmiracion: marcado";
				} else {
					mensaje = "chkAdmiracion: desmarcado";
				}
				Log.i("EjemploControles", mensaje);
			}
		});
        
        rbgTratamiento.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton radioButtonMarcado = (RadioButton) group.findViewById(checkedId);
				Log.i("EjemploControles", "Seleccionado: " + radioButtonMarcado.getText());
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onClick(View button) {
		String textoMensaje = "Hola ";
		String nombre = txtNombre.getText().toString();
		if (nombre.equals(nombreAnterior)) {
			textoMensaje += "de nuevo ";
		}
		switch (rbgTratamiento.getCheckedRadioButtonId()) {
		case R.id.rbSr:
			textoMensaje += "Sr. ";
			break;
		case R.id.rbSra:
			textoMensaje += "Sra. ";
			break;

		default:
			break;
		}
		textoMensaje += nombre;
		if (chkAdmiracion.isChecked()) {
			textoMensaje += "!!";
		}
		
		Toast.makeText(this, textoMensaje, Toast.LENGTH_LONG).show();
		nombreAnterior = nombre;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Siempre llamamos a super.onSaveInstanceState, porque Android guarda aquí
		// el estado de los Views
		super.onSaveInstanceState(outState);
		outState.putString("nombreAnterior", nombreAnterior);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// Aquí también llamamos a super.onRestoreInstanceState, para dejar que Android
		// restaure el estado de los Views
		super.onRestoreInstanceState(savedInstanceState);
		nombreAnterior = savedInstanceState.getString("nombreAnterior");
	}
}
