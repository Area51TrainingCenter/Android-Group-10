package com.area51.adapters;

import java.util.ArrayList;

import com.area51.models.Item;
import com.area51.semana1_3.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	Context context;
	ArrayList<Item> arreglo;

	public ItemAdapter(Context context, ArrayList<Item> arreglo) {
		super();
		this.context = context;
		this.arreglo = arreglo;
		Log.d("TAG", "ItemAdapter");
	}

	@Override
	public int getCount() {
		Log.d("TAG", "getCount " + arreglo.size());
		return arreglo.size();
	}

	@Override
	public Object getItem(int position) {
		Log.d("TAG", "getItem " + arreglo.get(position).getIdItem());
		return arreglo.get(position).getIdItem();
	}

	@Override
	public long getItemId(int position) {
		Log.d("TAG", "getItemId");
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_lista, parent, false);
		}
		Item objetoItem = arreglo.get(position);
		ImageView imageItem = (ImageView) convertView
				.findViewById(R.id.Itemimage);
		imageItem.setImageResource(objetoItem.getImagenItem());
		TextView textoImagen = (TextView) convertView
				.findViewById(R.id.ItemTexto);
		textoImagen.setText(objetoItem.getNombreItem());
		Log.d("TAG", "getView " + objetoItem.getNombreItem());
		return convertView;
	}

}
