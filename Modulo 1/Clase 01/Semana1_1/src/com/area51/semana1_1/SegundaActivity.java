package com.area51.semana1_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

public class SegundaActivity extends Activity {

	Spinner spProductos;
	TextView lblPrecio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segunda);
		
		spProductos=(Spinner)findViewById(R.id.spProductos);
		lblPrecio=(TextView)findViewById(R.id.lblPrecio);
		
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		spProductos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==0){
					lblPrecio.setText("100");
				}else if(position==1){
					lblPrecio.setText("50");
				}else if (position==2){
					lblPrecio.setText("20");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	
	
	
	
	
	
}
