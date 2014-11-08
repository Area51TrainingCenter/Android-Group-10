package com.area51.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkApp {

	Context context;
	NetworkInfo network;
	Boolean wifiConnected = false;
	Boolean mobileConnection = false;

	public NetworkApp(Context context) {
		super();
		this.context = context;
	}

	public Boolean getNetwork() {
		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		network = con.getActiveNetworkInfo();
		if (network != null && network.isConnected()) {
			Log.d("TAG", "is connected");
			wifiConnected = network.getType() == ConnectivityManager.TYPE_WIFI;
			mobileConnection = network.getType() == ConnectivityManager.TYPE_MOBILE;
			if (wifiConnected) {
				Log.d("TAG", "wifi");
				return true;
			} else if (mobileConnection) {
				Log.d("TAG", "mobile");
				return true;
			}
		} else {
			Log.d("TAG", "is disconnected");
		}
		return false;
	}

}
