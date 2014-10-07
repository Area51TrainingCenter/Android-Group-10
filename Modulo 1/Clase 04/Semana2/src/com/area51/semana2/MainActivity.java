package com.area51.semana2;

import java.util.ArrayList;

import com.area51.adapters.ItemAdapter;
import com.area51.models.Item;
import com.area51.util.Constant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// Declaración de nuestras variables
	ItemAdapter adapter;
	ListView lista;

	EditText txtTexto;
	Button btnGrabar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Inicializamos nuestra variable lista(java) y la referenciamos con
		// nuestro componente lista(xml)
		lista = (ListView) findViewById(R.id.lista);
		txtTexto = (EditText) findViewById(R.id.txtTexto);
		btnGrabar = (Button) findViewById(R.id.btnGrabar);

	}

	@Override
	protected void onResume() {
		super.onResume();

		if (Constant.lista == null) {
			Constant.lista = new ArrayList<Item>();
		}
		adapter = new ItemAdapter(getApplicationContext(), this);
		lista.setAdapter(adapter);
		btnGrabar.setTag(-1);//Setteamos el TAG del boton grabar para indicar que estamos haciendo un nuevo registro
		
		btnGrabar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Obtenemos el id del TAG del boton grabar
				int codigo = Integer.parseInt(btnGrabar.getTag().toString());
				//Si es mayor a -1 significa que estamos "Modificando" 
				if (codigo == -1) {
					//Registro Nuevo
					if (!txtTexto.getText().toString().equals("")) {
						Constant.lista.add(new Item(Constant.lista.size(),
								txtTexto.getText().toString(), "drawable/image"));
						//Notificamos al adapter y a la lista que a habido cambios, esto sirve para que se actualice
						adapter.notifyDataSetChanged();
						txtTexto.setText("");
					} else {
						Toast.makeText(getApplicationContext(),
								"Ingrese texto", Toast.LENGTH_SHORT).show();
					}
				} else {
					//Modificar
					Constant.lista.get(codigo).setNombreItem(
							txtTexto.getText().toString());
					adapter.notifyDataSetChanged();
					txtTexto.setText("");
					btnGrabar.setTag(-1);
				}
			}
		});

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				//Creamos un alertDialog con dos opciones, "Modificar", "Eliminar"
				AlertDialog.Builder dialogo = new AlertDialog.Builder(
						MainActivity.this);
				dialogo.setTitle("Opciones").setItems(R.array.lista_opciones,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (which) {
								case 0://Para modificar un registro
									txtTexto.setText(Constant.lista.get(
											position).getNombreItem());
									//Guardamos el id del item en el valor TAG del boton grabar
									btnGrabar.setTag(Constant.lista.get(
											position).getId());
									break;
								case 1://Para eliminar un registro
									Constant.lista.remove(position);
									adapter.notifyDataSetChanged();
									break;
								default:
									break;
								}
							}
						});
				//Sirve para que se muestre el dialogo
				dialogo.show();
			}
		});
	}
}
