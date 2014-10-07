package com.area51.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.semana2_2.NuevaPantallaActivity;
import com.area51.semana2_2.R;
import com.area51.utils.Constant;

public class MostrarFragment extends Fragment {
	
	public String FRAGMENTO_POSICION="posicion";
	ImageView imagen;
	TextView lblDescripcion;
	Button btnNuevaPantalla;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View vistaFragment = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_detalle, container, false);
		
		imagen=(ImageView)vistaFragment.findViewById(R.id.image);
		lblDescripcion=(TextView)vistaFragment.findViewById(R.id.lblDescripcion);
		btnNuevaPantalla=(Button)vistaFragment.findViewById(R.id.btnEnviar);
		
		Bundle bundle=getArguments();
		final int codigo=bundle.getInt(FRAGMENTO_POSICION);
		
		imagen.setImageResource(Constant.listaGrid.get(codigo).getImagen());
		lblDescripcion.setText(Constant.listaGrid.get(codigo).getNombre());
		
		btnNuevaPantalla.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),NuevaPantallaActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("PARAMETRO", codigo);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		return vistaFragment;
	}
}
