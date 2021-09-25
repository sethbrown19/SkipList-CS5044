
/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

	// this is the SkipList object that we are using
	// a string for the name of the rectangle and then
	// a rectangle object, these are stored in a KVPair,
	// see the KVPair class for more information
	private SkipList<String, RectangleHelper> list;

	/**
	 * The constructor for this class initializes a SkipList object with String and
	 * Rectangle a its parameters.
	 */
	public Database() {
		list = new SkipList<>();
	}

	/**
	 * Inserts the KVPair in the SkipList if the rectangle has valid coordinates and
	 * dimensions, that is that the coordinates are non-negative and that the
	 * rectangle object has some area (not 0, 0, 0, 0). This insert will insert the
	 * KVPair specified into the sorted SkipList appropriately
	 * 
	 * @param pair the KVPair to be inserted
	 */
	public void insert(KVPair<String, RectangleHelper> pair) {
		if (!checkKey(pair.getKey()) || !checkValue(pair.getValue())) {
			System.out.println("Rectangle rejected:  " + pair.toString());

		} else {
			list.insert(pair);
			System.out.println("Rectangle inserted:  " + pair.toString());
		}
	}

	private boolean checkKey(String s) { // check if key meets requirements
		for (int i = 0; i < s.length() - 1; i++) {
			if (Character.isLetter(s.charAt(0)) && (s.charAt(i + 1) == '_' || Character.isDigit(s.charAt(i + 1))))
				return true;
		}
		return false;
	}

	private boolean checkValue(RectangleHelper rect) { // check if value is positive and less than 1024
		if (rect.getX() < 0 || rect.getY() < 0 || rect.getWidth() <= 0 || rect.getHeight() <= 0) {
			return false;
		}
		if ((rect.getX() + rect.getWidth() > 1024) || (rect.getY() + rect.getHeight() > 1024)) {
			return false;
		}
		return true;
	}

	/**
	 * Removes a rectangle with the name "name" if available. If not an error
	 * message is printed to the console.
	 * 
	 * @param name the name of the rectangle to be removed
	 */
	public void remove(String name) {
		if (list.remove(name) == null) {
			System.out.println("Rectangle not removed: " + "(" + name + ")");
			return;
		}
		System.out.println("remove by name method" + list.remove(name));
		System.out.println(name.toString());
	}

	/**
	 * Removes a rectangle with the specified coordinates if available. If not an
	 * error message is printed to the console.
	 * 
	 * @param x x-coordinate of the rectangle to be removed
	 * @param y x-coordinate of the rectangle to be removed
	 * @param w width of the rectangle to be removed
	 * @param h height of the rectangle to be removed
	 */
	public void remove(int x, int y, int w, int h) {
		System.out.println("Remove method for specified coordinates");
	}

	/**
	 * Displays all the rectangles inside the specified region. The rectangle must
	 * have some area inside the area that is created by the region, meaning,
	 * Rectangles that only touch a side or corner of the region specified will not
	 * be said to be in the region. You will need a SkipList Iterator for this
	 * 
	 * @param x x-Coordinate of the region
	 * @param y y-Coordinate of the region
	 * @param w width of the region
	 * @param h height of the region
	 */
	public void regionsearch(int x, int y, int w, int h) {
		System.out.println("Region search method");

	}

	/**
	 * Prints out all the rectangles that Intersect each other by calling the
	 * SkipList method for intersections. You will need to use two SkipList
	 * Iterators for this
	 */
	public void intersections() {
		System.out.println("Intersections method");

	}

	/**
	 * Prints out all the rectangles with the specified name in the SkipList. This
	 * method will delegate the searching to the SkipList class completely.
	 * 
	 * @param name name of the Rectangle to be searched for
	 */
	public void search(String name) {
		System.out.println("search method " + list.search(name));

	}

	/**
	 * Prints out a dump of the SkipList which includes information about the size
	 * of the SkipList and shows all of the contents of the SkipList. This will all
	 * be delegated to the SkipList.
	 */
	public void dump() {
		list.dump();
	}

}
