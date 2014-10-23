package com.area51.libs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

public class RESTClient {

	private static int TIMEOUT_MILLISEC = 30000;
	static HttpClient httpclient = new DefaultHttpClient();

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String connectAndReturnResponse(String url) {

		String result = "";

		

		HttpGet httpget = new HttpGet(url);

		String userAgent = System.getProperty("http.agent");
		httpget.setHeader("User-Agent", userAgent);
		
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			//Log.i("Android_Store",response.getStatusLine().toString());
			
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertStreamToString(instream);
				// Log.i("Android_Store",result);
				instream.close();
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public static String connectAndReturnResponse(String url,
			JSONObject jsonObj) throws Exception 
	{

		String result = "";

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
		HttpClient httpclient = new DefaultHttpClient(httpParams);
		HttpPost httppost = new HttpPost(url);
		
		
		
		HttpResponse response;
		StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
		entity.setContentType("application/json");
		httppost.setEntity(entity);
		response = httpclient.execute(httppost);
		HttpEntity httpentity = response.getEntity();

		if (httpentity != null) {

			InputStream instream = httpentity.getContent();
			result = convertStreamToString(instream);

			instream.close();
		}

		return result;

	}

}