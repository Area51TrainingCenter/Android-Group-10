package com.area51.semana3_1;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView welcome;
	TextView log_redbooth;
	EditText email;
	EditText password;
	TextView forgot;
	Button login;
	TextView sign_in;
	TextView on_premise;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		welcome = (TextView) findViewById(R.id.lblWelcome);
		log_redbooth = (TextView) findViewById(R.id.lblLogRedbooth);
		email = (EditText) findViewById(R.id.txtEmail);
		password = (EditText) findViewById(R.id.txtPassword);
		forgot = (TextView) findViewById(R.id.lblForgot);
		login = (Button) findViewById(R.id.btnLogin);
		sign_in = (TextView) findViewById(R.id.lblSignIn);
		on_premise = (TextView) findViewById(R.id.lblOnPremise);
	}

	@Override
	protected void onResume() {
		super.onResume();

		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/leadcoat.ttf");
		welcome.setTypeface(typeFace);
		log_redbooth.setTypeface(typeFace);
		email.setTypeface(typeFace);
		password.setTypeface(typeFace);
		login.setTypeface(typeFace);
		sign_in.setTypeface(typeFace);
		on_premise.setTypeface(typeFace);
		
	}

}
