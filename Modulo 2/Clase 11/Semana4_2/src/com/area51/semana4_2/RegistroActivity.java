package com.area51.semana4_2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;

public class RegistroActivity extends Activity {

	EditText txtRCorreo;
	EditText txtRContrasenia;
	EditText txtRRepContrasenia;
	Button btnRegistrar;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);

		txtRCorreo = (EditText) findViewById(R.id.txtRCorreo);
		txtRContrasenia = (EditText) findViewById(R.id.txtRContrasenia);
		txtRRepContrasenia = (EditText) findViewById(R.id.txtRRepContrasenia);
		btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
	}

	@Override
	protected void onResume() {
		super.onResume();

		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constant.DBname, null, Constant.DBversion);
		dbProcesos = dbConexion.getWritableDatabase();
		
		btnRegistrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (txtRCorreo.getText().toString().equals(""))
					txtRCorreo.setError("Campo correo requerido");
				else if (txtRContrasenia.getText().toString().equals(""))
					txtRContrasenia.setError("Campo contraseña requerido");
				else if (txtRRepContrasenia.getText().toString().equals(""))
					txtRRepContrasenia
							.setError("Campo repetir contraseña requerido");
				else if (!txtRCorreo.getText().toString().equals("")
						&& !txtRContrasenia.getText().toString().equals("")
						&& !txtRRepContrasenia.getText().toString().equals("")) {
					if (txtRContrasenia.getText().toString()
							.equals(txtRRepContrasenia.getText().toString())) {
						String sql = "INSERT INTO " + Constant.TBUser + "("
								+ Constant.ColCorreo + ","
								+ Constant.ColContrasenia + ") VALUES ('"
								+ txtRCorreo.getText().toString() + "','"
								+ txtRContrasenia.getText().toString() + "')";

						dbProcesos.execSQL(sql);
						Intent intent = new Intent(RegistroActivity.this,
								MainActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(),
								"Contraseñas no coinciden", Toast.LENGTH_SHORT)
								.show();
					}
				}
			}
		});

	}
}
