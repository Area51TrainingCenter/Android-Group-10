package com.area51.semana1_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText txtTexto;
	Button btnEjecutar;
	TextView lblTexto;
	static String texto = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtTexto = (EditText) findViewById(R.id.txtTexto);
		btnEjecutar = (Button) findViewById(R.id.btnEjecutar);
		lblTexto = (TextView) findViewById(R.id.lblTexto);
		
		if(savedInstanceState!=null){
			lblTexto.setText(texto);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		btnEjecutar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!txtTexto.getText().toString().equals("")) {
					texto += Html.fromHtml("<br />"
							+ txtTexto.getText().toString());
					lblTexto.setText(texto);
					txtTexto.setText("");
				} else {
					Toast.makeText(getApplicationContext(),
							getResources().getString(R.string.texto),
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}else if(id==R.id.limpiar){
			lblTexto.setText("");
			texto="";
			return true;
		}else if(id==R.id.segunda){
			Intent intent=new Intent(MainActivity.this,SegundaActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}















