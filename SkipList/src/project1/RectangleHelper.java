package project1;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class RectangleHelper extends Rectangle {
	private int x;
	private int y;
	private int width;
	private int height;

	public RectangleHelper(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;	
	}

	@Override
	public String toString() {
		return x + ", " + y + ", " + width + ", " + height;
	}

}
