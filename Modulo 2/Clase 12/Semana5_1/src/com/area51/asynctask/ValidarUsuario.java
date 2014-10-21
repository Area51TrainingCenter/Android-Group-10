package com.area51.asynctask;

import com.area51.libs.RESTClient;
import com.area51.semana5_1.MainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ValidarUsuario extends AsyncTask<String, Void, String> {

	Context context;
	ProgressDialog mensaje;
	

	public ValidarUsuario(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		mensaje=new ProgressDialog(context);
		mensaje.setMessage("Cargando!!");
		mensaje.show();
	}

	@Override
	protected String doInBackground(String... params) {
		String resultado = "";
		for (String url : params) {
			try {
				resultado = RESTClient.connectAndReturnResponse(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mensaje.dismiss();
		((MainActivity) context).validarUsuario(result);
	}

}
