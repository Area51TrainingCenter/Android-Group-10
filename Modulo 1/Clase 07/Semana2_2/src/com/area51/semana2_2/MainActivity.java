package com.area51.semana2_2;

import java.util.ArrayList;

import com.area51.models.Item;
import com.area51.utils.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText txtNombre;
	EditText txtApellido;
	Button btnGrabar;
	Button btnSiguiente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtNombre = (EditText) findViewById(R.id.txtNombre);
		txtApellido = (EditText) findViewById(R.id.txtApellido);
		btnGrabar = (Button) findViewById(R.id.btnGrabar);
		btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (Constant.lista == null)
			Constant.lista = new ArrayList<Item>();

		btnSiguiente.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SiguienteActivity.class);
				startActivity(intent);
			}
		});

		btnGrabar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!txtNombre.getText().toString().equals("")
						&& !txtApellido.getText().toString().equals("")) {

					Constant.lista.add(new Item(Constant.lista.size(),
							txtNombre.getText().toString(), txtApellido
									.getText().toString(), R.drawable.image));
					txtNombre.setText("");
					txtApellido.setText("");
				} else {
					Toast.makeText(getApplicationContext(),
							"Ingrese nombre o apellido", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}

}
