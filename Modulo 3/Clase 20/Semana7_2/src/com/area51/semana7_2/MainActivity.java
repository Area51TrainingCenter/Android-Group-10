package com.area51.semana7_2;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.area51.adapters.FragmentAdapter;
import com.area51.utils.NetworkApp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends FragmentActivity implements TabListener {

	NetworkApp networkApp;
	ViewPager viewpager;
	FragmentAdapter adapter;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).build();
		ImageLoader.getInstance().init(config);

		networkApp = new NetworkApp(this);
		if (networkApp.getNetwork()) {
			setContentView(R.layout.activity_main);
			iniciarApp();
		} else {
			setContentView(R.layout.network_disconnect);
		}
	}

	@SuppressWarnings("deprecation")
	private void iniciarApp() {
		// TODO Auto-generated method stub
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		adapter = new FragmentAdapter(getSupportFragmentManager());
		viewpager.setAdapter(adapter);

		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("HashTag")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("TimeLine")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Search")
				.setTabListener(this));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
