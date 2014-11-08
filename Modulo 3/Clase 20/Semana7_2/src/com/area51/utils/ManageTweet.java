package com.area51.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Base64;
import android.util.Log;

import com.area51.models.Tweet;

public class ManageTweet {

	public static String appAuthentication() {

		HttpURLConnection httpConnection = null;
		OutputStream outpuStream = null;
		BufferedReader bufferedReader = null;
		StringBuilder response = null;

		try {
			URL url = new URL(Constant.URL_AUTHENTICATION);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);

			String accessCredential = Constant.CONSUMER_KEY + ":"
					+ Constant.CONSUMER_SECRET;
			String authorization = "Basic "
					+ Base64.encodeToString(accessCredential.getBytes(),
							Base64.NO_WRAP);
			String param = "grant_type=client_credentials";

			httpConnection.addRequestProperty("Authorization", authorization);
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			httpConnection.connect();

			outpuStream = httpConnection.getOutputStream();
			outpuStream.write(param.getBytes());
			outpuStream.flush();
			outpuStream.close();

			bufferedReader = new BufferedReader(new InputStreamReader(
					httpConnection.getInputStream()));
			String line;
			response = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				response.append(line);
			}
			Log.d(Constant.TAG_APP, "JSON response: " + response.toString());
		} catch (Exception e) {
			// TODO: handle exception

		} finally {
			if (httpConnection != null)
				httpConnection.disconnect();
		}

		return response.toString();
	}

	public static ArrayList<Tweet> getHashtag(String searchTerm) {

		HttpURLConnection httpConnection = null;
		BufferedReader bufferedReader = null;
		StringBuilder response = new StringBuilder();
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();

		try {
			URL url = new URL(Constant.URL_SEARCH + searchTerm + "&count="
					+ Constant.TWITTER_COUNT);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");

			String jsonString = appAuthentication();
			JSONObject jsonObjectDocument = new JSONObject(jsonString);
			String token = jsonObjectDocument.getString("token_type") + " "
					+ jsonObjectDocument.getString("access_token");

			httpConnection.setRequestProperty("Authorization", token);
			httpConnection.setRequestProperty("Content-Type",
					"application/json");
			httpConnection.connect();

			bufferedReader = new BufferedReader(new InputStreamReader(
					httpConnection.getInputStream()));

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				response.append(line);
			}

			Log.d(Constant.TAG_APP,
					"GET response code: "
							+ String.valueOf(httpConnection.getResponseCode()));
			Log.d(Constant.TAG_APP, "JSON response: " + response.toString());

			JSONObject jsonResponse = new JSONObject(response.toString());

			JSONArray jsonArray = jsonResponse.getJSONArray("statuses");
			JSONObject jsonObject;

			for (int i = 0; i < jsonArray.length(); i++) {

				jsonObject = (JSONObject) jsonArray.get(i);
				Tweet tweet = new Tweet();

				tweet.setId(jsonObject.getString("id_str"));
				tweet.setName(jsonObject.getJSONObject("user")
						.getString("name"));
				tweet.setUsername(jsonObject.getJSONObject("user").getString(
						"screen_name"));
				tweet.setPathImage(jsonObject.getJSONObject("user").getString(
						"profile_image_url"));
				tweet.setText(jsonObject.getString("text"));
				tweet.setCreateAt(jsonObject.getString("created_at"));

				tweets.add(i, tweet);
			}

		} catch (Exception e) {
			Log.e(Constant.TAG_APP, "GET error: " + Log.getStackTraceString(e));

		} finally {
			if (httpConnection != null) {
				httpConnection.disconnect();
			}
		}

		return tweets;
	}

}
