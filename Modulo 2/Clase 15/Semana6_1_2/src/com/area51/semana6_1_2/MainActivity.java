package com.area51.semana6_1_2;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources resources = getResources();

		tabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabHost.TabSpec tab = tabHost.newTabSpec("tab1");
		tab.setContent(new Intent().setClass(this, TabUno.class));
		tab.setIndicator("TAB1",
				resources.getDrawable(android.R.drawable.ic_btn_speak_now));
		tabHost.addTab(tab);

		tab = tabHost.newTabSpec("tab2");
		tab.setContent(new Intent().setClass(this, TabDos.class));
		tab.setIndicator("",
				resources.getDrawable(android.R.drawable.ic_btn_speak_now));
		tabHost.addTab(tab);
		
		tabHost.setCurrentTab(0);
	}

}
