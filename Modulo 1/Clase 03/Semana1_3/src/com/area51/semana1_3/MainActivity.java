package com.area51.semana1_3;

import java.util.ArrayList;

import com.area51.adapters.ItemAdapter;
import com.area51.models.Item;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	ArrayList<Item> arreglo;
	ItemAdapter adapter;
	ListView lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("TAG", "onCreate MainActivity");
		lista = (ListView) findViewById(R.id.lista);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("TAG", "onResume MainActivity");
		arreglo = new ArrayList<Item>();
		for (int i = 0; i < 10; i++) {
			Item obj=new Item();
			obj.setIdItem(i);
			obj.setNombreItem("imagen");
			obj.setImagenItem(R.drawable.image);
			arreglo.add(obj);
			
			arreglo.add(new Item(i, "Imagen"+i, R.drawable.image));
		}
		
		adapter = new ItemAdapter(getApplicationContext(), arreglo);
		lista.setAdapter(adapter);
	}
}
