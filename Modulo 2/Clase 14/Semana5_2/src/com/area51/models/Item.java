package com.area51.models;

public class Item {
	private String titulo;
	private int icono;

	public Item(String titulo, int icono) {
		super();
		this.titulo = titulo;
		this.icono = icono;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIcono() {
		return icono;
	}

	public void setIcono(int icono) {
		this.icono = icono;
	}

}
