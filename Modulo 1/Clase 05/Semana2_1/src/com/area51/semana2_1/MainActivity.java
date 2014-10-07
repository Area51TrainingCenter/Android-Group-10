package com.area51.semana2_1;

import java.util.ArrayList;

import com.area51.adapters.ItemAdapter;
import com.area51.models.Item;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends Activity {

	ItemAdapter adapter;
	GridView grilla;
	ArrayList<Item> lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		grilla = (GridView) findViewById(R.id.grilla);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (lista == null)
			lista = new ArrayList<Item>();

		for (int i = 0; i < 15; i++) {
			lista.add(new Item(i, "Imagen " + i, R.drawable.sample_0));
		}

		adapter = new ItemAdapter(getApplicationContext(),
				R.layout.item_gridview, lista);
		grilla.setAdapter(adapter);
	}
}
