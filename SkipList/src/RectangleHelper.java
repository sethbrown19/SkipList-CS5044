
import java.awt.*;

/**
 * This Rectangle Helper class extends the java built in rectangle class. It
 * uses a formatted toString method to match expected output.
 * 
 * @author Seth Brown
 * @author 906388237
 * 
 * @version 26 Sep 2021
 *
 */
@SuppressWarnings("serial")
public class RectangleHelper extends Rectangle {

    /**
     * Constructor for Rectangle Helper
     */
    public RectangleHelper() {
        super();
    }


    /**
     * The toString used when printing out the dimensions of a rectangle. This
     * overrides the toString method in awt.Rectangle.
     * 
     * @return the toString method in the (x, y, w, h) layout
     */
    @Override
    public String toString() {
        return (int)getX() + ", " + (int)getY() + ", " + (int)getWidth() + ", "
            + (int)getHeight();
    }

}
