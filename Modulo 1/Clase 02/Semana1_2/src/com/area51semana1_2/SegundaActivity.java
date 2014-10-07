package com.area51semana1_2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

public class SegundaActivity extends Activity {

	Spinner sp;
	TextView txt;
	final String TAG = "TAG_ANDROID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_segunda);
		Log.d(TAG, "onCreate");
		sp = (Spinner) findViewById(R.id.spCombo);
		txt = (TextView) findViewById(R.id.txtTexto);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.d(TAG, String.valueOf(position));
				if (position == 0) {
					txt.setText("Producto Uno");
				} else if (position == 1) {
					txt.setText("Producto Dos");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
	}

}
