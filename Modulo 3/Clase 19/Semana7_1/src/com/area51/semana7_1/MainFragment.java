package com.area51.semana7_1;

import java.util.Arrays;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constant;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class MainFragment extends Fragment {

	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;
	LoginButton authButton;
	EditText txtCorreo;
	EditText txtContrasenia;
	Button btnLogin;
	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;

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
				"user_website", "user_work_history", "user_photos"));
		txtCorreo = (EditText) view.findViewById(R.id.txtCorreo);
		txtContrasenia = (EditText) view.findViewById(R.id.txtContrasenia);
		btnLogin = (Button) view.findViewById(R.id.btnLogin);

		return view;
	}

	@SuppressWarnings("deprecation")
	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.d(TAG, "Logging in..");

			Request.executeMeRequestAsync(session, new GraphUserCallback() {

				@Override
				public void onCompleted(GraphUser user, Response response) {
					// TODO Auto-generated method stub
					if (user != null) {

						dbProcesos = dbConexion.getReadableDatabase();
						String sql = "SELECT * FROM " + Constant.TBusuario
								+ " WHERE " + Constant.C_idfacebook + "='"
								+ user.getId() + "'";
						Cursor cursor = dbProcesos.rawQuery(sql, null);
						if (cursor.getCount() > 0) {
							// Intent para pasar a siguiente página
						} else {

							dbProcesos = dbConexion.getWritableDatabase();
							String sqlRegistro = "INSERT INTO "
									+ Constant.TBpersona + "("
									+ Constant.C_nombres + ","
									+ Constant.C_apellidos + ","
									+ Constant.C_genero + ","
									+ Constant.C_correo + ") VALUES('"
									+ user.getName() + "','"
									+ user.getLastName() + "','"
									+ user.getProperty("gender") + "','"
									+ user.getProperty("email") + "')";
							dbProcesos.execSQL(sqlRegistro);
							dbProcesos = dbConexion.getReadableDatabase();
							String sqlCodigo = "SELECT " + Constant.C_idpersona
									+ " FROM " + Constant.TBpersona
									+ " ORDER BY 1 DESC LIMIT 1";
							Cursor cursorCodigo = dbProcesos.rawQuery(
									sqlCodigo, null);
							int codigo = 0;
							if (cursorCodigo.getCount() > 0) {
								if (cursorCodigo.moveToFirst()) {
									do {
										codigo = Integer.parseInt(cursorCodigo.getString(cursorCodigo
												.getColumnIndex(Constant.C_idpersona)));
									} while (cursorCodigo.moveToNext());
								}
							}
							dbProcesos = dbConexion.getWritableDatabase();
							String sqlUsuario = "INSERT INTO "
									+ Constant.TBusuario + " ("
									+ Constant.C_idfacebook + ") VALUES('"
									+ user.getId() + "')";
							dbProcesos.execSQL(sqlUsuario);
							Log.d("TAG", "Todo OK->" + codigo);
						}
					}
				}
			});
		} else if (state.isClosed()) {
			Log.d(TAG, "Logging out..");
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
		dbConexion = new ManageOpenHelper(getActivity());

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!txtCorreo.getText().toString().equals("")
						&& !txtContrasenia.getText().toString().equals("")) {
					String sql = "SELECT * FROM " + Constant.TBusuario
							+ " a INNER JOIN " + Constant.TBpersona
							+ " b ON a." + Constant.C_idpersona + "=b."
							+ Constant.C_idpersona + " WHERE b."
							+ Constant.C_correo + "='"
							+ txtCorreo.getText().toString() + "' AND a."
							+ Constant.C_contrasenia + "'"
							+ txtContrasenia.getText().toString() + "'";
					Cursor cursor = dbProcesos.rawQuery(sql, null);
					if (cursor.getCount() > 0) {
						// Usuario valido pasa a siguiente pantalla
					} else {
						// Usuario no registrado en bd o contraseña incorrecta
					}
				} else {
					// Falta llenar los campos requeridos
				}
			}
		});
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
}