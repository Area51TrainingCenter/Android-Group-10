package com.area51adapters;

import java.util.List;

import com.area51.models.Item;
import com.area51.semana5_2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter {

	Context context;
	List<Item> listaItem;

	public MenuListAdapter(Context context, List<Item> listaItem) {
		super();
		this.context = context;
		this.listaItem = listaItem;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	static class ViewHolder {
		ImageView icono;
		TextView titulo;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_item, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.titulo = (TextView) convertView
					.findViewById(R.id.titulo);
			viewHolder.icono = (ImageView) convertView.findViewById(R.id.icono);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.icono.setImageResource(listaItem.get(position).getIcono());
		holder.titulo.setText(listaItem.get(position).getTitulo());
		return convertView;
	}

}
