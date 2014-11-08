package com.area51.fragments;

import java.util.ArrayList;

import com.area51.adapters.TweetAdapter;
import com.area51.models.Tweet;
import com.area51.semana7_2.R;
import com.area51.utils.Constant;
import com.area51.utils.ManageTweet;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HashTagFragment extends Fragment {

	ListView lvLista;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_hashtag, container,
				false);

		lvLista = (ListView) view.findViewById(R.id.lvLista);

		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new HashTagAsync().execute();
	}

	public class HashTagAsync extends AsyncTask<Object, Void, ArrayList<Tweet>> {

		ProgressDialog mensaje;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mensaje = new ProgressDialog(getActivity());
			mensaje.setMessage("Cargando Tweets!");
			mensaje.setCancelable(false);
			mensaje.show();
		}

		@Override
		protected ArrayList<Tweet> doInBackground(Object... params) {
			// TODO Auto-generated method stub
			return ManageTweet.getHashtag(Constant.TWITTER_TERM);
		}

		@Override
		protected void onPostExecute(ArrayList<Tweet> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mensaje.dismiss();
			if (!result.isEmpty())
				cargarTweets(result);
		}

		private void cargarTweets(ArrayList<Tweet> result) {
			// TODO Auto-generated method stub
			lvLista.setAdapter(new TweetAdapter(getActivity(),
					R.layout.row_tweet, result));
		}
	}
}
