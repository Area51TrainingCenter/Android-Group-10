package com.area51.semana5_1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.area51.asynctask.ManageAsyncTask;
import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;

public class MainActivity extends Activity {

	EditText txtCorreo;
	EditText txtContrasenia;
	Button btnLogin;
	ManageAsyncTask manage;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtCorreo = (EditText) findViewById(R.id.txtCorreo);
		txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);
		btnLogin = (Button) findViewById(R.id.btnLogin);
	}

	@Override
	protected void onResume() {
		super.onResume();
		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constant.DBname, null, Constant.DBversion);
		dbProcesos = dbConexion.getWritableDatabase();

		manage = new ManageAsyncTask(MainActivity.this);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!txtCorreo.getText().toString().equals("")
						&& !txtContrasenia.getText().toString().equals("")) {
					String url = Constant.API + Constant.APISection
							+ Constant.APIvalorCorreo
							+ txtCorreo.getText().toString()
							+ Constant.APIvalorContrasenia
							+ txtContrasenia.getText().toString();
					Log.d("TAG", url);
					manage.validarUsuario(url);
				}
			}
		});
	}

	public void validarUsuario(String result) {
		Log.d("LOG", "json-->" + result);
		try {
			JSONArray jsonArray = new JSONArray(result);
			if (jsonArray.length() > 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String sql = "INSERT INTO " + Constant.TablePersona + "("
							+ Constant.CodidPersona + "," + Constant.ColNombres
							+ "," + Constant.ColApellidoPaterno + ","
							+ Constant.ColApellidoMaterno + ","
							+ Constant.ColGenero + ") VALUES("
							+ jsonObject.getString("id") + ",'"
							+ jsonObject.getString("nombres") + "','"
							+ jsonObject.getString("apellidoPaterno") + "','"
							+ jsonObject.getString("apellidoMaterno") + "','"
							+ jsonObject.getString("genero") + "')";
					dbProcesos.execSQL(sql);
					Intent intent = new Intent(MainActivity.this,
							ListarActivity.class);
					startActivity(intent);
					finish();
					/*
					 * Log.d("TAG", "id->" + jsonObject.getString("id"));
					 * Log.d("TAG", "nombres->" +
					 * jsonObject.getString("nombres")); Log.d("TAG",
					 * "apellidoPaterno->" +
					 * jsonObject.getString("apellidoPaterno")); Log.d("TAG",
					 * "apellidoMaterno->" +
					 * jsonObject.getString("apellidoMaterno")); Log.d("TAG",
					 * "genero->" + jsonObject.getString("genero"));
					 */
				}
			} else {
				Toast.makeText(getApplicationContext(), "Usuario invalido",
						Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
