package com.area51.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.area51.libs.ImageLoader;
import com.area51.models.Item;
import com.area51.semana4_1.R;

public class GrillaAdapter extends ArrayAdapter<Item> {

	Context context;
	ArrayList<Item> listaImagenes;

	public GrillaAdapter(Context context, int resource, ArrayList<Item> objects) {
		super(context, resource, objects);
		this.context = context;
		this.listaImagenes = objects;
	}

	static class ViewHolder {
		ImageView imagen;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.grid_item, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imagen = (ImageView) convertView
					.findViewById(R.id.ivImagenMostrar);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		ImageLoader loader = new ImageLoader(context);
		loader.DisplayImage(listaImagenes.get(position).getImage(),
				holder.imagen);

		return convertView;
	}
}
