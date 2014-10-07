package com.area51.adapters;

import com.area51.semana2_2.R;
import com.area51.utils.Constant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaItemAdapter extends BaseAdapter {

	Context context;
	Activity activity;
	

	public ListaItemAdapter(Context context, Activity activity) {
		super();
		this.context = context;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Constant.lista.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Constant.lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	static class ViewHolder {
		TextView nombre;
		TextView apellido;
		ImageView imagen;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_list, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.nombre = (TextView) convertView
					.findViewById(R.id.itemNombre);
			viewHolder.apellido = (TextView) convertView
					.findViewById(R.id.itemApellido);
			viewHolder.imagen = (ImageView) convertView
					.findViewById(R.id.itemImage);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder=(ViewHolder) convertView.getTag();
		holder.nombre.setText(Constant.lista.get(position).getNombre());
		holder.apellido.setText(Constant.lista.get(position).getApellido());
		holder.imagen.setImageResource(Constant.lista.get(position).getImagen());
		
		return convertView;
	}

}
