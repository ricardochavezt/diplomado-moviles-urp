package com.diplomadourp.loginapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class MensajeDialogFragment extends DialogFragment {
	
	public interface OnDialogResult {
		public void onDialogOK();
	}

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Activity activity = getActivity();
		
		AlertDialog.Builder builder = 
				new AlertDialog.Builder(activity);
		builder.setTitle(R.string.app_name)
			.setMessage("Usuario y/o contraseña inválidos")
			.setPositiveButton(R.string.texto_ok, 
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (activity instanceof OnDialogResult) {
								((OnDialogResult)activity).onDialogOK();
							}
							dialog.dismiss();
						}
					});
		
		return builder.create();
	}
}
