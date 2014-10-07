package com.area51.semana2_2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NuevaPantallaActivity extends Activity {
	
	TextView txtPosicion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nueva_pantalla);
		txtPosicion=(TextView)findViewById(R.id.txtPosicion);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Bundle bundle=getIntent().getExtras();
		int posicion=bundle.getInt("PARAMETRO");
		
		txtPosicion.setText("Posición "+posicion);
		
	}
}
