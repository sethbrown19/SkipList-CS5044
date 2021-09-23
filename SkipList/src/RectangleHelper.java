

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

	public <V> RectangleHelper(V value) {
	}

	@Override
	public String toString() {
		return x + ", " + y + ", " + width + ", " + height;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
