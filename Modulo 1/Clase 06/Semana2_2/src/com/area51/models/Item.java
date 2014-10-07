package com.area51.models;

public class Item {
	protected int id;
	protected String nombre;
	protected String apellido;
	protected int imagen;

	public Item(int id, String nombre, String apellido, int imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.imagen = imagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getImagen() {
		return imagen;
	}

	public void setImagen(int imagen) {
		this.imagen = imagen;
	}

}
