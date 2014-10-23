package com.area51.semana5_1;

import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class LauncherActivity extends Activity {

	FrameLayout frameLauncher;
	Integer contador = 0;
	Integer delay = 2000;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		frameLauncher = (FrameLayout) findViewById(R.id.frameLauncher);
	}

	@Override
	protected void onResume() {
		super.onResume();
		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constant.DBname, null, Constant.DBversion);
		dbProcesos = dbConexion.getReadableDatabase();

		contador = 0;

		frameLauncher.postDelayed(iniciarApp, delay);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		frameLauncher.removeCallbacks(iniciarApp);
	}

	public Runnable iniciarApp = new Runnable() {

		@Override
		public void run() {
			if (contador < 1) {
				frameLauncher.postDelayed(iniciarApp, delay);
				contador++;
			} else {
				String sql = "SELECT * FROM " + Constant.TablePersona;
				Cursor cursor = dbProcesos.rawQuery(sql, null);
				Intent intent = null;
				if (cursor.getCount() > 0) {
					intent = new Intent(LauncherActivity.this,
							ListarActivity.class);
				} else {
					intent = new Intent(LauncherActivity.this,
							MainActivity.class);
				}
				startActivity(intent);
				finish();
			}
		}
	};

}
