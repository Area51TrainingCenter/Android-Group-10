package com.area51.semana4_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText txtCorreo;
	EditText txtPassword;
	Button btnLogin;
	TextView lblRegistrate;

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

		lblRegistrate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						RegistroActivity.class);
				startActivity(intent);
			}
		});
	}
}
