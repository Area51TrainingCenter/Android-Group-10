package com.area51.models;

public class Item {
	protected int idItem;
	protected String nombreItem;
	protected int imagenItem;
	
	public Item(){
		
	}
	
	public Item(int idItem, String nombreItem, int imagenItem) {
		super();
		this.idItem = idItem;
		this.nombreItem = nombreItem;
		this.imagenItem = imagenItem;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getNombreItem() {
		return nombreItem;
	}
	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}
	public int getImagenItem() {
		return imagenItem;
	}
	public void setImagenItem(int imagenItem) {
		this.imagenItem = imagenItem;
	}
	

}
