package com.area51.semana4_2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;

public class MainActivity extends Activity {

	EditText txtCorreo;
	EditText txtPassword;
	Button btnLogin;
	TextView lblRegistrate;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtCorreo = (EditText) findViewById(R.id.txtCorreo);
		txtPassword = (EditText) findViewById(R.id.txtContrasenia);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		lblRegistrate = (TextView) findViewById(R.id.lblRegistro);
	}

	@Override
	protected void onResume() {
		super.onResume();
		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constant.DBname, null, Constant.DBversion);
		dbProcesos = dbConexion.getReadableDatabase();

		lblRegistrate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						RegistroActivity.class);
				startActivity(intent);
			}
		});

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (txtCorreo.getText().toString().equals(""))
					txtCorreo.setError("Campo requerido");
				else if (txtPassword.getText().toString().equals(""))
					txtPassword.setError("Campo requerido");
				else if (!txtCorreo.getText().toString().equals("")
						&& !txtPassword.getText().toString().equals("")) {
					String sql = "select * from " + Constant.TBUser + " where "
							+ Constant.ColCorreo + "='"
							+ txtCorreo.getText().toString() + "' and "
							+ Constant.ColContrasenia + "='"
							+ txtPassword.getText().toString() + "'";
					Log.d("TAG", sql);
					Cursor cursor = dbProcesos.rawQuery(sql, null);
					if (cursor.getCount() > 0) {
						if (cursor.moveToFirst()) {
							do {
								Constant.idUsuario = Integer.parseInt(cursor.getString(cursor
										.getColumnIndex(Constant.ColidUser)));
							} while (cursor.moveToNext());
						}

						Intent intent = new Intent(MainActivity.this,
								HomeActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(),
								"Usuario y/o contraseña incorrectos.",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

	}
}
