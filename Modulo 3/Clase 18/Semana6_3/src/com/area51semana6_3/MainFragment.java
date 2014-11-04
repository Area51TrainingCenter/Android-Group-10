package com.area51semana6_3;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainFragment extends Fragment {

	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;
	LoginButton authButton;
	TextView userInfoTextView;
	ImageView ivProfile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, container, false);
		authButton = (LoginButton) view.findViewById(R.id.authButton);
		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays.asList("user_location",
				"user_birthday", "user_likes", "email", "user_relationships",
				"user_website", "user_work_history","user_photos"));
		userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);
		ivProfile = (ImageView) view.findViewById(R.id.ivProfile);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getActivity()).build();
		ImageLoader.getInstance().init(config);

		return view;
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.d(TAG, "Logging in..");
			userInfoTextView.setVisibility(View.VISIBLE);

			Request.executeMeRequestAsync(session, new GraphUserCallback() {

				@Override
				public void onCompleted(GraphUser user, Response response) {
					// TODO Auto-generated method stub
					if (user != null) {
						userInfoTextView.setText(buildUserInfoDisplay(user));
					}
				}
			});
		} else if (state.isClosed()) {
			Log.d(TAG, "Logging out..");
			userInfoTextView.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionStateChange(session, session.getState(), null);
		}
		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {

		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);

		}
	};

	private String buildUserInfoDisplay(GraphUser user) {
		Log.d(TAG, "Usuario--->" + user);
		StringBuilder userInfo = new StringBuilder("");

		userInfo.append("Nombre->" + user.getName());
		userInfo.append("\nBirthday->" + user.getBirthday());
		// timezone
		userInfo.append("\nTimezone->" + user.getProperty("timezone"));
		// verified
		userInfo.append("\nVerified->" + user.getProperty("verified"));

		try {
			JSONArray jsonArray = (JSONArray) user.getProperty("languages");
			if (jsonArray.length() > 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String id = jsonObject.getString("id");
					String name = jsonObject.getString("name");
					userInfo.append("\n id->" + id + " name->" + name);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		GraphObjectList<MyGraph> languages = (user.cast(MyGraphUser.class))
				.getLanguages();
		if (languages.size() > 0) {
			for (MyGraph myGraph : languages) {
				userInfo.append("\nMyGraph->id->" + myGraph.getId() + " name->"
						+ myGraph.getName());
			}
		}
		GraphObjectList<MyGraph> favoriteTeams = (user.cast(MyGraphUser.class))
				.getFavoriteTeams();
		if (favoriteTeams.size() > 0) {
			for (MyGraph myGraph : favoriteTeams) {
				userInfo.append("\nFavoriteMyGraph->id->" + myGraph.getId()
						+ " name->" + myGraph.getName());
			}
		}
		userInfo.append("\nEmail->" + user.getProperty("email"));

		// JSONObject and JSONArray
		try {
			JSONArray jsonArrayWork = (JSONArray) user.getProperty("work");
			if (jsonArrayWork.length() > 0) {
				for (int i = 0; i < jsonArrayWork.length(); i++) {
					userInfo.append("\n");
					JSONObject jsonObject = jsonArrayWork.getJSONObject(i);
					if (jsonObject.has("start_date"))
						userInfo.append("\nStartDate->"
								+ jsonObject.getString("start_date"));
					if (jsonObject.has("end_date"))
						userInfo.append("\nEndDate->"
								+ jsonObject.getString("end_date"));

					if (jsonObject.has("position")) {
						JSONObject jsonObjectPosition = jsonObject
								.getJSONObject("position");
						userInfo.append("\nPosition");
						userInfo.append("\nid->"
								+ jsonObjectPosition.getString("id")
								+ " name->"
								+ jsonObjectPosition.getString("name"));
					}

					if (jsonObject.has("employer")) {
						JSONObject jsonObjectEmployer = jsonObject
								.getJSONObject("employer");
						userInfo.append("\nEmployer");
						userInfo.append("\nid->"
								+ jsonObjectEmployer.getString("id")
								+ " name->"
								+ jsonObjectEmployer.getString("name"));
					}

					if (jsonObject.has("location")) {
						JSONObject jsonObjectLocation = jsonObject
								.getJSONObject("location");
						userInfo.append("\nLocation");
						userInfo.append("\nid->"
								+ jsonObjectLocation.getString("id")
								+ " name->"
								+ jsonObjectLocation.getString("name"));
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		userInfo.append("\nID->" + user.getId());
		ImageLoader.getInstance().displayImage(
				"http://graph.facebook.com/" + user.getId()
						+ "/picture?type=large", ivProfile);

		return userInfo.toString();
	}

	private interface MyGraph extends GraphObject {
		String getId();

		String getName();
	}

	private interface MyGraphUser extends GraphUser {
		GraphObjectList<MyGraph> getLanguages();

		GraphObjectList<MyGraph> getFavoriteTeams();
	}

}
