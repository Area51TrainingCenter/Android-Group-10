package com.area51.semana6_1;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	EditText txtFecha;
	ImageButton ibSeleccionarDia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtFecha = (EditText) findViewById(R.id.txtFecha);
		ibSeleccionarDia = (ImageButton) findViewById(R.id.ibSeleccionarDia);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		ibSeleccionarDia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment dialogFragment = new SeleccionarFecha();
				dialogFragment.show(getFragmentManager(), "DatePicker");
			}
		});
	}

	public class SeleccionarFecha extends DialogFragment implements
			OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			txtFecha.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
		}

	}
}
