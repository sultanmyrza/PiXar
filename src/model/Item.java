package model;

public class Item {

	private String type;
	private String name;
	private String src;

	// position and width height properties in JFrame
	private int x, y, width, height;

	public Item(String type, String name, int x, int y, int width, int height) {
		this.type = type;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		// location in file system
		src = "./images/avatar/" + type + "/" + name + ".png";
	}
	/*
	 * Getters and Setters
	 */
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getSrc() {
		return src;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
