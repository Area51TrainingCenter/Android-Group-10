package com.area51.semana2_2;

import java.util.ArrayList;

import com.area51.adapters.GrillaAdapter;
import com.area51.adapters.ListaItemAdapter;
import com.area51.models.Item;
import com.area51.utils.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

public class SiguienteActivity extends Activity {

	ListaItemAdapter adapterList;
	GrillaAdapter adapterGrilla;
	ListView lista;
	GridView grilla;
	ArrayList<Item> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_siguiente);

		lista = (ListView) findViewById(R.id.lista);
		grilla = (GridView) findViewById(R.id.grilla);
	}

	@Override
	protected void onResume() {
		super.onResume();

		adapterList = new ListaItemAdapter(getApplicationContext(), this);
		lista.setAdapter(adapterList);

		if (list == null){
			list = new ArrayList<Item>();
		list.add(new Item(1, "Imagen 1", "", R.drawable.sample_0));
		list.add(new Item(2, "Imagen 2", "", R.drawable.sample_1));
		list.add(new Item(3, "Imagen 3", "", R.drawable.sample_2));
		list.add(new Item(4, "Imagen 4", "", R.drawable.sample_3));
		list.add(new Item(5, "Imagen 5", "", R.drawable.sample_4));
		list.add(new Item(6, "Imagen 6", "", R.drawable.sample_5));
		list.add(new Item(7, "Imagen 7", "", R.drawable.sample_6));
		list.add(new Item(8, "Imagen 8", "", R.drawable.sample_7));
		list.add(new Item(9, "Imagen 9", "", R.drawable.sample_8));
		if (list != null) {
			Constant.listaGrid = new ArrayList<Item>();
			Constant.listaGrid = list;
		}}
		adapterGrilla = new GrillaAdapter(getApplicationContext(),
				R.layout.activity_siguiente, list);
		grilla.setAdapter(adapterGrilla);

		grilla.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SiguienteActivity.this,
						MostrarActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt(Constant.parametro, position);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
}
