package com.area51.utils;

public class Constant {
	public static String API = "http://www.johannfjs.com/android/ws/";
	public static String APISection = "validarUsuario.php?";
	public static String APISectionList="index.php";
	public static String APIvalorCorreo = "&correo=";
	public static String APIvalorContrasenia = "&contrasenia=";

	// SQLite
	public static String DBname = "semana5.db";
	public static int DBversion = 1;

	public static String TablePersona = "persona";
	public static String CodidPersona = "idpersona";
	public static String ColNombres = "nombres";
	public static String ColApellidoPaterno = "apellidopaterno";
	public static String ColApellidoMaterno = "apellidomaterno";
	public static String ColGenero = "genero";

	public static String createTablePersona = "CREATE TABLE " + TablePersona
			+ "(" + CodidPersona + " INTEGER," + ColNombres + " TEXT,"
			+ ColApellidoPaterno + " TEXT," + ColApellidoMaterno + " TEXT,"
			+ ColGenero + " TEXT)";

	public static String dropTAblePersona = "DROP TABLE " + TablePersona;

}
