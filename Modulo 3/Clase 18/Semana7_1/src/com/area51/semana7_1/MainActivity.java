package com.area51.semana7_1;

import com.facebook.widget.LoginButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText txtCorreo;
	EditText txtContrasenia;
	Button btnLogin;
	LoginButton btnLoginFacebook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtCorreo = (EditText) findViewById(R.id.txtCorreo);
		txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLoginFacebook = (LoginButton) findViewById(R.id.authButton);

	}
}
