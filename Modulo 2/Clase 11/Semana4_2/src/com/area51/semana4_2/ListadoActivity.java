package com.area51.semana4_2;

import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;

import com.area51.adapters.ListAdapter;
import com.area51.models.Persona;
import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class ListadoActivity extends Activity {

	ListView lvlista;
	ArrayList<Persona> listaPersona;
	ListAdapter adapter;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado);

		lvlista = (ListView) findViewById(R.id.lvlista);
	}

	@Override
	protected void onResume() {
		super.onResume();

		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constant.DBname, null, Constant.DBversion);
		dbProcesos = dbConexion.getReadableDatabase();

		String sql = "SELECT * FROM " + Constant.TBPersona + " WHERE "
				+ Constant.ColidUser + "=" + Constant.idUsuario;
		Cursor cursor = dbProcesos.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			listaPersona = new ArrayList<Persona>();
			if (cursor.moveToFirst()) {
				do {
					// De está manera
//					Persona persona = new Persona();
//					persona.setId(Integer.parseInt(cursor.getString(cursor
//							.getColumnIndex(Constant.ColidPersona))));
//					persona.setNombre(cursor.getString(cursor
//							.getColumnIndex(Constant.ColNombre)));
//					persona.setApellido(cursor.getString(cursor
//							.getColumnIndex(Constant.ColApellido)));
//					listaPersona.add(persona);
					// O de está manera
					listaPersona.add(new Persona(Integer.parseInt(cursor
							.getString(cursor
									.getColumnIndex(Constant.ColidPersona))),
							cursor.getString(cursor
									.getColumnIndex(Constant.ColNombre)),
							cursor.getString(cursor
									.getColumnIndex(Constant.ColApellido))));
				} while (cursor.moveToNext());
			}
			adapter = new ListAdapter(getApplicationContext(), listaPersona);
			lvlista.setAdapter(adapter);
		}
	}
}
