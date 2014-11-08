package com.area51.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.area51.models.Tweet;
import com.area51.semana7_2.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetAdapter extends ArrayAdapter<Tweet> {

	Context context;
	ArrayList<Tweet> listaTweets;

	public TweetAdapter(Context context, int resource, ArrayList<Tweet> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listaTweets = objects;
	}

	static class ViewHolder {
		ImageView imagen;
		TextView user;
		TextView username;
		TextView createAt;
		TextView text;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.row_tweet, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imagen = (ImageView) convertView
					.findViewById(R.id.ivImagen);
			viewHolder.user = (TextView) convertView.findViewById(R.id.lblName);
			viewHolder.username = (TextView) convertView
					.findViewById(R.id.lblUsername);
			viewHolder.createAt = (TextView) convertView
					.findViewById(R.id.lblCreateAt);
			viewHolder.text = (TextView) convertView
					.findViewById(R.id.lblTexto);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		ImageLoader.getInstance().displayImage(
				listaTweets.get(position).getPathImage(), holder.imagen);
		holder.user.setText(listaTweets.get(position).getName());
		holder.username.setText(listaTweets.get(position).getUsername());
		holder.createAt.setText(listaTweets.get(position).getCreateAt());
		holder.text.setText(listaTweets.get(position).getText());
		return convertView;
	}

}
