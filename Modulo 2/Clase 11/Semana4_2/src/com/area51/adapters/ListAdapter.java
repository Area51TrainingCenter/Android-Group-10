package com.area51.adapters;

import java.util.ArrayList;

import com.area51.models.Persona;
import com.area51.semana4_2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	Context context;
	ArrayList<Persona> listaPersonas;

	public ListAdapter(Context context, ArrayList<Persona> listaPersonas) {
		super();
		this.context = context;
		this.listaPersonas = listaPersonas;
	}

	@Override
	public int getCount() {
		return listaPersonas.size();
	}

	@Override
	public Object getItem(int position) {
		return listaPersonas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	static class ViewHolder {
		TextView lblNombre;
		TextView lblApellido;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.lblNombre = (TextView) convertView
					.findViewById(R.id.lblNombre);
			viewHolder.lblApellido = (TextView) convertView
					.findViewById(R.id.lblApellido);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.lblNombre.setText(listaPersonas.get(position).getNombre());
		holder.lblApellido.setText(listaPersonas.get(position).getApellido());
		return convertView;
	}

}
