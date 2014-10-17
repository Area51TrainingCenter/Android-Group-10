package com.area51.semana4_1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.area51.adapters.FragmentAdapter;
import com.area51.fragments.DetalleFragment;
import com.area51.utils.Contants;
import com.area51.view.CustomViewPager;

public class SiguienteActivity extends FragmentActivity {

	FragmentAdapter adapter;
	DetalleFragment detalle;
	CustomViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_siguiente);

		viewPager = (CustomViewPager) findViewById(R.id.viewpager);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Bundle bundle = getIntent().getExtras();
		int codigo = bundle.getInt(Contants.posicion);
		Log.d("TAG", "Siguiente->" + codigo);
		adapter = new FragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(codigo);
	}
}
