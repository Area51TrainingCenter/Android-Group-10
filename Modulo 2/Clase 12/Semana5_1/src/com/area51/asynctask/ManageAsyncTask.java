package com.area51.asynctask;

import android.content.Context;

public class ManageAsyncTask {

	Context context;

	public ManageAsyncTask(Context context) {
		super();
		this.context = context;
	}

	public void validarUsuario(String url) {
		ValidarUsuario obj = new ValidarUsuario(context);
		obj.execute(url);
	}

}
