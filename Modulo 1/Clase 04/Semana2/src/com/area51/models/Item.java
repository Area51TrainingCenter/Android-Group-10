package com.area51.models;

public class Item {
	protected int id;
	protected String nombreItem;
	protected String imagen;

	//Constructor
	public Item(int id, String nombreItem, String imagen) {
		super();
		this.id = id;
		this.nombreItem = nombreItem;
		this.imagen = imagen;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreItem() {
		return nombreItem;
	}
	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
