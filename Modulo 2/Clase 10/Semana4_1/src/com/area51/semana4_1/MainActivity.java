package com.area51.semana4_1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.area51.adapters.GrillaAdapter;
import com.area51.models.Item;
import com.area51.utils.Contants;

public class MainActivity extends Activity {

	GridView grilla;
	GrillaAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		grilla = (GridView) findViewById(R.id.grilla);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (Contants.listaImagenes == null) {
			Contants.listaImagenes = new ArrayList<Item>();
			for (int i = 1; i < 50; i++) {
				// Contants.listaImagenes.add(new Item(i, "Imagen " + i,
				// "Pie de Imagen " + i,
				// "http://assets3.parliament.uk/iv/main-large//ImageVault/Images/id_7382/scope_0/ImageVaultHandler.aspx.jpg"));
				Contants.listaImagenes.add(new Item(i, "Imagen " + i,
						"Pie de Imagen " + i,
						"http://www.johannfjs.com/android/images/HDPackSuperiorWallpapers424_0"
								+ (i < 10 ? "0" + i : i) + ".jpg"));
			}
		}

		adapter = new GrillaAdapter(getApplicationContext(),
				R.layout.grid_item, Contants.listaImagenes);
		grilla.setAdapter(adapter);

		grilla.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this,
						SiguienteActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt(Contants.posicion, position);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

}
