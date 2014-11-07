package com.area51.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.area51.utils.Constant;

public class ManageOpenHelper extends SQLiteOpenHelper {

	public ManageOpenHelper(Context context) {
		super(context, Constant.DBname, null, Constant.DBversion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(Constant.CREATE_TABLE_PERSONA);
		db.execSQL(Constant.CREATE_TABLE_USUARIO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(Constant.DROP_TABLE_PERSONA);
		db.execSQL(Constant.DROP_TABLE_USUARIO);
		onCreate(db);
	}

}
