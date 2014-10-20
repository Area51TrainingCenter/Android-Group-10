package com.area51.semana4_2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;

public class HomeActivity extends Activity {

	EditText txtNombre;
	EditText txtApellido;
	Button btnRegistro;
	Button btnVer;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		txtNombre = (EditText) findViewById(R.id.txtNombre);
		txtApellido = (EditText) findViewById(R.id.txtApellido);
		btnRegistro = (Button) findViewById(R.id.btnRegistroPersona);
		btnVer = (Button) findViewById(R.id.btnVerRegistros);
	}

	@Override
	protected void onResume() {
		super.onResume();
		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constant.DBname, null, Constant.DBversion);
		dbProcesos = dbConexion.getWritableDatabase();

		btnRegistro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (txtNombre.getText().toString().equals(""))
					txtNombre.setError("Campo requerido");
				else if (txtApellido.getText().toString().equals(""))
					txtApellido.setError("Campo requerido");
				else if (!txtNombre.getText().toString().equals("")
						&& !txtApellido.getText().toString().equals("")) {
					String sql = "INSERT INTO " + Constant.TBPersona + " ("
							+ Constant.ColNombre + "," + Constant.ColApellido
							+ "," + Constant.ColidUser + ") VALUES('"
							+ txtNombre.getText().toString() + "','"
							+ txtApellido.getText().toString() + "',"
							+ Constant.idUsuario + ")";
					Log.d("TAG", sql);
					dbProcesos.execSQL(sql);
				}
			}
		});

		btnVer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,
						ListadoActivity.class);
				startActivity(intent);
			}
		});
	}

}
