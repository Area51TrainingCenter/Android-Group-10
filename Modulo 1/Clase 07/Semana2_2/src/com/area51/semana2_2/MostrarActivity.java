package com.area51.semana2_2;

import com.area51.adapters.FragmentAdapter;
import com.area51.utils.Constant;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MostrarActivity extends FragmentActivity {
	
	FragmentAdapter fragmentAdapter;
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar);
		
		viewPager=(ViewPager)findViewById(R.id.viewpager);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		Bundle bundle=getIntent().getExtras();
		int posicion=bundle.getInt(Constant.parametro);
		
		fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(fragmentAdapter);
		viewPager.setCurrentItem(posicion);
		
	}
}
