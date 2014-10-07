package com.area51.adapters;

import com.area51.semana2.R;
import com.area51.util.Constant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
	// Context sabe todos los activitys que tenemos
	Context context;
	// Especificamos en que activity estamos
	Activity actividad;

	// Constructor
	public ItemAdapter(Context context, Activity actividad) {
		super();
		this.context = context;
		this.actividad = actividad;
	}

	// Contar cuantos elementos tiene nuestra lista
	@Override
	public int getCount() {
		return Constant.lista.size();
	}

	// Nos ayuda a recuperar un objeto de la lista
	@Override
	public Object getItem(int position) {
		return Constant.lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	// Clase creada para hacer referencia de los objetos de la lista
	static class ViewHolder {
		public ImageView imagen;
		public TextView texto;
	}

	// Nos permite hacer la unión de nuestros items a nuestra parte visual
	// haciendo iteraciones
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			// Método que nos ayuda a enlazar nuestro codigo con nuestro
			// item_lista el cual vamos a iterar
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_lista, parent, false);
			// Declaramos nuestro viewHolder y lo inicializamos
			ViewHolder viewHolder = new ViewHolder();
			// Seteamos los valores de nuestro objeto
			viewHolder.imagen = (ImageView) convertView
					.findViewById(R.id.imagen);
			viewHolder.texto = (TextView) convertView
					.findViewById(R.id.textoImagen);
			// Agregamos el viewHolder al tag para luego ser llamado y verificar
			// que no sea null
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.texto.setText(Constant.lista.get(position).getNombreItem());

		//Obtiene el codigo hexadecimal de la imagen
		int imagen = actividad.getResources().getIdentifier(
				Constant.lista.get(position).getImagen(), null,
				actividad.getPackageName());

		//Obtiene la imagen mediante el codigo hexadecimal
		holder.imagen.setImageDrawable(actividad.getResources().getDrawable(imagen));

		return convertView;
	}

}
