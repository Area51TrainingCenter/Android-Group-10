package com.area51.models;

public class Item {
	protected int id;
	protected String textoUno;
	protected String textoDos;
	protected String image;

	public Item(int id, String textoUno, String textoDos, String image) {
		super();
		this.id = id;
		this.textoUno = textoUno;
		this.textoDos = textoDos;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextoUno() {
		return textoUno;
	}

	public void setTextoUno(String textoUno) {
		this.textoUno = textoUno;
	}

	public String getTextoDos() {
		return textoDos;
	}

	public void setTextoDos(String textoDos) {
		this.textoDos = textoDos;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
