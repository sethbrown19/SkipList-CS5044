import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author Seth Brown
 * @author 906388237
 * 
 * @version 26 Sep 2021
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and
     * Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and
     * dimensions, that is that the coordinates are non-negative and that the
     * rectangle object has some area (not 0, 0, 0, 0). This insert will insert
     * the
     * KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        if (!valueInRange(pair.getValue()) || !checkKey(pair.getKey())
            || !valuePositive(pair.getValue())) {
            System.out.println("Rectangle rejected:  " + pair.toString());
        }
        else {
            list.insert(pair);
            System.out.println("Rectangle inserted:  " + pair.toString());
        }
    }


    /**
     * Check if the key has a letter first
     * 
     * @param s
     *            the string to check
     * @return true if the string has a letter first
     */
    private boolean checkKey(String s) { // check if key meets requirements
        return Character.isLetter(s.charAt(0));
    }


    /**
     * Check if the values input into the rectangles are positive
     * 
     * @param rect
     *            dimensions
     * @return true if the values are positive
     */
    private boolean valuePositive(Rectangle rect) { // check if value is positive
        return rect.getX() >= 0 && rect.getY() >= 0 && (rect.getWidth() > 0
            && rect.getHeight() > 0);
    }

    /**
     * 
     * Check to see if the rectangles values are less than 1024
     * 
     * @param rect
     *            rectangles dimensions to be checked
     * @return true if the rectangle is less than 1024 from (0, 0) ordinates.
     */
    private boolean valueInRange(Rectangle rect) { 
        return rect.getX() + rect.getWidth() < 1024 && rect.getY() + rect
            .getHeight() < 1024;
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        try {
            System.out.println("Rectangle removed: " + list.remove(name));
        }
        catch (Exception NullPointerException) {
            System.out.println("Rectangle not removed: " + "(" + name + ")");
            // print name if null value
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an
     * error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        Rectangle rect = new RectangleHelper();
        rect.setRect(x, y, w, h);
        try {
            System.out.println("Rectangle removed: " + list.removeByValue(rect)
                .toString());
        }
        catch (Exception NullPointerException) {
            // do nothing if null value
        }

    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must
     * have some area inside the area that is created by the region, meaning,
     * Rectangles that only touch a side or corner of the region specified will
     * not
     * be said to be in the region. You will need a SkipList Iterator for this
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        ArrayList<KVPair<String, Rectangle>> pair = new ArrayList<>();
        Rectangle searchRectangle = new Rectangle(x, y, w, h);
        if (list.iterator().hasNext()) {
            KVPair<String, Rectangle> pairToAdd = list.iterator().next();
            if (pairToAdd != null) {
                Rectangle rect = pairToAdd.getValue();
                if (searchRectangle.contains(rect)) {
                    pair.add(pairToAdd);
                }
            }
        }
        if (!pair.isEmpty()) {
            System.out.println("Rectangles intersecting region (" + x + ", " + y
                + ", " + w + ", " + h + "):");
            System.out.println(pair);
        }

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
     * Prints out all the rectangles with the specified name in the SkipList.
     * This
     * method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Rectangle>> printList =
            new ArrayList<KVPair<String, Rectangle>>();
        printList = list.search(name);
        if (printList == null) {
            System.out.println("Rectangles not found: " + name);
        }
        else {
            System.out.println("Rectangles found: ");
            for (KVPair<String, Rectangle> element : printList) {
                System.out.println("(" + element.getKey() + ", " + element
                    .getValue().x + ", " + element.getValue().y + ", " + element
                        .getValue().width + ", " + element.getValue().height
                    + ")");
            }
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size
     * of the SkipList and shows all of the contents of the SkipList. This will
     * all
     * be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
