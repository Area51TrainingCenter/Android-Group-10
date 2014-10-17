package com.area51.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.libs.ImageLoader;
import com.area51.semana4_1.MainActivity;
import com.area51.semana4_1.R;
import com.area51.utils.Contants;

public class DetalleFragment extends Fragment {

	ImageView imagen;
	TextView textoUno;
	TextView textoDos;
	Button btnAtras;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View vista = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_detalle, container, false);

		imagen = (ImageView) vista.findViewById(R.id.ivImagenDetalle);
		textoUno = (TextView) vista.findViewById(R.id.lblTextoUno);
		textoDos = (TextView) vista.findViewById(R.id.lblTextoDos);
		btnAtras = (Button) vista.findViewById(R.id.btnAtras);

		Bundle bundle = getArguments();
		int codigo = bundle.getInt(Contants.posicion);
		ImageLoader loader = new ImageLoader(getActivity());
		loader.DisplayImage(Contants.listaImagenes.get(codigo).getImage(),
				imagen);
		textoUno.setText(Contants.listaImagenes.get(codigo).getTextoUno());
		textoDos.setText(Contants.listaImagenes.get(codigo).getTextoDos());

		btnAtras.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
			}
		});

		return vista;
	}
}
