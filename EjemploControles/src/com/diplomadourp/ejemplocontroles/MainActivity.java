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

	// Estas variables corresponden a nuestros componentes en la pantalla
	private EditText txtNombre;
	private CheckBox chkAdmiracion;
	private RadioGroup rbgTratamiento;
	private Button btnMostrarMensaje;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Aquí obtenemos las referencias a nuestros componentes
        // para poderlas manejar en el código Java
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        chkAdmiracion = (CheckBox) findViewById(R.id.chkAdmiracion);
        rbgTratamiento = (RadioGroup) findViewById(R.id.rbgTratamiento);
        btnMostrarMensaje = (Button) findViewById(R.id.btnMostrarMensaje);
        
        // Indicamos que queremos que este Activity reciba los eventos de click de nuesto botón
        btnMostrarMensaje.setOnClickListener(this);
        
        // Los CheckBox también pueden recibir eventos cuando cambian de estado (marcado / no marcado)
        chkAdmiracion.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// buttonView es una referencia al control que ha cambiado (en este caso chkAdmiración)
				// isChecked indica el nuevo estado del botón (verdadero: marcado, falso: desmarcado)
				String mensaje;
				if (isChecked) {
					mensaje = "chkAdmiracion: marcado";
				} else {
					mensaje = "chkAdmiracion: desmarcado";
				}
				Log.i("EjemploControles", mensaje);
			}
		});
        
        // Los RadioGroup también pueden recibir eventos cuando los RadioButton que incluyen cambian de estado
        rbgTratamiento.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// group es una referencia al RadioGroup que contiene los RadioButton que han cambiado de estado
				// checkedId es el id. del botón que ha sido marcado
				// Podemos buscar el RadioButton que ha sido marcado del siguiente modo
				RadioButton radioButtonMarcado = (RadioButton) group.findViewById(checkedId);
				// Con getText podemos obtener el texto que le asignamos al botón
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
		// Con el método getCheckedRadioButtonId obtenemos el id. del RadioButton seleccionado
		// Si ninguno ha sido seleccionado, el método devuelve -1
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
		// Con el método getText de un EditText obtenemos el texto ingresado por el usuario
		textoMensaje += txtNombre.getText().toString();
		// El método isChecked de un CheckBox devuelve verdadero si el check está marcado, y falso en caso contrario
		if (chkAdmiracion.isChecked()) {
			textoMensaje += "!!";
		}
		
		Toast.makeText(this, textoMensaje, Toast.LENGTH_LONG).show();
	}
}
