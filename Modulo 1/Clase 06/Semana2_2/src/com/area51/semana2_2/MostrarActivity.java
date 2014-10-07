package com.area51.semana2_2;

import com.area51.utils.Constant;

import android.app.Activity;
import android.os.Bundle;

public class MostrarActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		Bundle bundle=getIntent().getExtras();
		int posicion=bundle.getInt(Constant.parametro);
	}
}
