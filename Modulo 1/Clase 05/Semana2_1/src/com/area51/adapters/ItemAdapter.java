package com.area51.adapters;

import java.util.ArrayList;
import java.util.List;

import com.area51.models.Item;
import com.area51.semana2_1.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ItemAdapter extends ArrayAdapter<Item> {
	// Declaración de varibles
	Context context;
	ArrayList<Item> lista;

	// Constructor
	public ItemAdapter(Context context, int resource, ArrayList<Item> objects) {
		super(context, resource, objects);
		// Inicializamos variables
		this.context = context;
		this.lista = objects;
	}

	static class ViewHolder {
		public ImageView imagen;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Verificamos si la vista está vacía
		if (convertView == null) {
			// Asociamos nuestra parte visual a las iteraciones de nuestro
			// codigo
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_gridview, parent, false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imagen = (ImageView) convertView
					.findViewById(R.id.imagen);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.imagen.setImageResource(lista.get(position).getImagen());
		return convertView;
	}

}
