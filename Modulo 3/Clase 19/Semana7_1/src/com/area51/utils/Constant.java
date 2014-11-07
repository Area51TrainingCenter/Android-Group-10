package com.area51.utils;

public class Constant {

	public static String DBname = "login.db";
	public static int DBversion = 1;
	// Usuario
	public static String TBusuario = "usuario";
	public static String C_idusuario = "idusuario";
	public static String C_usuario = "usuario";
	public static String C_contrasenia = "contrasenia";
	public static String C_idfacebook = "idfacebook";
	// Persona
	public static String TBpersona = "persona";
	public static String C_idpersona = "idpersona";
	public static String C_nombres = "nombres";
	public static String C_apellidos = "apellidos";
	public static String C_genero = "genero";
	public static String C_correo = "correo";
	// Create
	public static String CREATE_TABLE_USUARIO = "CREATE TABLE " + TBusuario
			+ " (" + C_idusuario + " integer primary key autoincrement,"
			+ C_usuario + " text," + C_contrasenia + " text," + C_idfacebook
			+ " text," + C_idpersona + " text)";

	public static String CREATE_TABLE_PERSONA = "CREATE TABLE " + TBpersona
			+ " (" + C_idpersona + " integer primary key autoincrement,"
			+ C_nombres + " text," + C_apellidos + " text," + C_genero
			+ " text," + C_correo + " text)";

	// Drop
	public static String DROP_TABLE_USUARIO = "DROP TABLE " + TBusuario;
	public static String DROP_TABLE_PERSONA = "DROP TABLE " + TBpersona;
}	
