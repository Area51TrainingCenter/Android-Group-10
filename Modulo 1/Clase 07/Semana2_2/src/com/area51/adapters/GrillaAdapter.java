package com.area51.adapters;

import java.util.ArrayList;
import java.util.List;

import com.area51.models.Item;
import com.area51.semana2_2.R;
import com.area51.utils.Constant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class GrillaAdapter extends ArrayAdapter<Item> {

	Context context;
	ArrayList<Item> lista;

	public GrillaAdapter(Context context, int resource, ArrayList<Item> objects) {
		super(context, resource, objects);
		this.context = context;
		this.lista = objects;
	}

	static class ViewHolder {
		ImageView image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_gridview, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.imageGrid);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.image.setImageResource(lista.get(position).getImagen());
		return convertView;
	}

}
