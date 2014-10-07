package com.area51semana1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity {

	Button btnHola;
	TextView txtCajaUno;
	TextView txtCajaDos;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btnHola = (Button) findViewById(R.id.button1);
		txtCajaUno = (TextView) findViewById(R.id.textView1);
		txtCajaDos = (TextView) findViewById(R.id.textView2);
	}

	@Override
	protected void onResume() {
		super.onResume();

		btnHola.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,
						SegundaPantallaActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	
	
	
	
	
	
	
	
}
