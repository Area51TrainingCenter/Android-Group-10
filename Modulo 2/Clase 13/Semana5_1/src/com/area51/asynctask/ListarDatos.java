package com.area51.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.area51.libs.RESTClient;
import com.area51.semana5_1.ListarActivity;

public class ListarDatos extends AsyncTask<String, Void, String> {

	Context context;

	public ListarDatos(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
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
		((ListarActivity) context).listarDatos(result);
	}
}