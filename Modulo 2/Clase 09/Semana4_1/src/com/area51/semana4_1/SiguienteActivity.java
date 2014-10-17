package com.area51.semana4_1;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.area51.adapters.FragmentAdapter;
import com.area51.fragments.DetalleFragment;
import com.area51.utils.Contants;

public class SiguienteActivity extends FragmentActivity {

	FragmentAdapter adapter;
	FrameLayout lyFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_siguiente);

		
		DetalleFragment detalle=new DetalleFragment();
		FragmentTransaction fragment=getFragmentManager().beginTransaction();
		fragment.add(R.id.lyFragment,detalle);
		fragment.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Bundle bundle=getIntent().getExtras();
		int codigo=bundle.getInt(Contants.posicion);
	}
}
