package com.area51.semana5_1;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.area51.asynctask.ManageAsyncTask;
import com.area51.models.Persona;
import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ListarActivity extends Activity {

	TextView txtNombre;
	TextView txtApellido;
	TextView txtGenero;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

	ManageAsyncTask manage;
	ArrayList<Persona> listaPersonas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar);

		txtNombre = (TextView) findViewById(R.id.txtNombre);
		txtApellido = (TextView) findViewById(R.id.txtApellido);
		txtGenero = (TextView) findViewById(R.id.txtGenero);
	}

	@Override
	protected void onResume() {
		super.onResume();
		manage = new ManageAsyncTask(ListarActivity.this);
		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constant.DBname, null, Constant.DBversion);
		dbProcesos = dbConexion.getReadableDatabase();

		String sql = "SELECT * FROM " + Constant.TablePersona;
		Cursor cursor = dbProcesos.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				do {
					txtNombre.setText("Nombres: "
							+ cursor.getString(cursor
									.getColumnIndex(Constant.ColNombres)));
					txtApellido
							.setText("Apellidos: "
									+ cursor.getString(cursor
											.getColumnIndex(Constant.ColApellidoPaterno))
									+ " "
									+ cursor.getString(cursor
											.getColumnIndex(Constant.ColApellidoMaterno)));
					txtGenero.setText("Genero: "
							+ (cursor.getString(
									cursor.getColumnIndex(Constant.ColGenero))
									.equals("M") ? "Masculino" : "Femenino"));
				} while (cursor.moveToNext());
			}
		}

		String url = Constant.API + Constant.APISectionList;
		manage.listarDatos(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			dbConexion = new ManageOpenHelper(getApplicationContext(),
					Constant.DBname, null, Constant.DBversion);
			dbProcesos = dbConexion.getWritableDatabase();
			String sql = "DELETE * FROM " + Constant.TablePersona;
			dbProcesos.execSQL(sql);
			Intent intent = new Intent(ListarActivity.this, MainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void listarDatos(String result) {
		try {
			JSONArray jsonArray = new JSONArray(result);
			if (jsonArray.length() > 0) {
				listaPersonas = new ArrayList<Persona>();
				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject jsonObject = jsonArray.getJSONObject(j);
					Persona obj = new Persona();
					obj.setId(Integer.parseInt(jsonObject.getString("id")));
					obj.setNombre(jsonObject.getString("nombres"));
					obj.setApellidoPaterno(jsonObject
							.getString("apellidoPaterno"));
					obj.setApellidoMaterno(jsonObject
							.getString("apellidoMaterno"));
					obj.setGenero(jsonObject.getString("genero"));
					listaPersonas.add(obj);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
