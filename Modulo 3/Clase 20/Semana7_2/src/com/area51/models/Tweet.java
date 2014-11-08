package com.area51.models;

public class Tweet {
	private String id;
	private String name;
	private String username;
	private String pathImage;
	private String text;
	private String createAt;

	public Tweet() {
		super();
	}

	public Tweet(String id, String name, String username, String pathImage,
			String text, String createAt) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.pathImage = pathImage;
		this.text = text;
		this.createAt = createAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

}
