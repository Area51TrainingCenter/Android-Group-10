package com.area51.models;

public class Item {
	protected int id;
	protected String nombre;
	protected int imagen;
	
	public Item(int id, String nombre, int imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	public int getImagen() {
		return imagen;
	}
	public void setImagen(int imagen) {
		this.imagen = imagen;
	}
}
