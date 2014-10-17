package com.area51.utils;

public class Constant {
	public static String DBname = "registro.db";
	public static int DBversion = 1;

	public static String TBUser = "user";
	public static String ColidUser = "iduser";
	public static String ColCorreo = "correo";
	public static String ColContrasenia = "contrasenia";

	public static String createTableUser = "CREATE TABLE " + TBUser + "("
			+ ColidUser + " INTEGER PRIMARY KEY AUTOINCREMENT," + ColCorreo
			+ " text," + ColContrasenia + " text)";

	public static String dropTBUser = "DROP TABLE " + TBUser;
}
