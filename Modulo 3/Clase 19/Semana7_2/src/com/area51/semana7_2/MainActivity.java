package com.area51.semana7_2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.area51.adapters.FragmentAdapter;
import com.area51.utils.NetworkApp;

public class MainActivity extends FragmentActivity {

	NetworkApp networkApp;
	ViewPager viewpager;
	FragmentAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		networkApp = new NetworkApp(this);
		if (networkApp.getNetwork()) {
			setContentView(R.layout.activity_main);
			iniciarApp();
		} else {
			setContentView(R.layout.network_disconnect);
		}
	}

	private void iniciarApp() {
		// TODO Auto-generated method stub
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		adapter = new FragmentAdapter(getSupportFragmentManager());
		viewpager.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}
}
