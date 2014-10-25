package com.area51.semana5_2;

import java.util.ArrayList;
import java.util.List;

import com.area51.fragments.ChromeFragment;
import com.area51.fragments.ExplorerFragment;
import com.area51.fragments.FirefoxFragment;
import com.area51.fragments.OperaFragment;
import com.area51.models.Item;
import com.area51adapters.MenuListAdapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	List<Item> listaItem;

	String[] menuTitulos;
	TypedArray menuIconos;

	CharSequence tituloDrawer;
	CharSequence titulo;

	ActionBarDrawerToggle mDrawerToogle;
	MenuListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		titulo = tituloDrawer = getTitle();

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		mDrawerList = (ListView) findViewById(R.id.idList);

		menuTitulos = getResources().getStringArray(R.array.titulos);
		menuIconos = getResources().obtainTypedArray(R.array.iconos);

		listaItem = new ArrayList<Item>();

		for (int i = 0; i < menuTitulos.length; i++) {
			listaItem.add(new Item(menuTitulos[i], menuIconos.getResourceId(i,
					-1)));
		}
		menuIconos.recycle();

		adapter = new MenuListAdapter(getApplicationContext(), listaItem);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				seleccionarVista(position);
			}
		});
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToogle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setTitle(titulo);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(tituloDrawer);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToogle);
		if (savedInstanceState == null)
			seleccionarVista(0);

	}

	@Override
	public void setTitle(CharSequence title) {
		titulo = title;
		getActionBar().setTitle(titulo);
	}

	protected void seleccionarVista(int position) {
		Fragment obj = null;
		switch (position) {
		case 0:
			obj = new ChromeFragment();
			break;
		case 1:
			obj = new ExplorerFragment();
			break;
		case 2:
			obj = new FirefoxFragment();
			break;
		case 3:
			obj = new OperaFragment();
			break;
		}

		if (obj != null) {
			FragmentManager fm = getFragmentManager();
			fm.beginTransaction().replace(R.id.frameContainer, obj).commit();
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (mDrawerToogle.onOptionsItemSelected(item)) {
			return true;
		}
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToogle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToogle.onConfigurationChanged(newConfig);
	}
}
