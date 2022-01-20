
import student.TestCase;
import student.TestableRandom;
import java.awt.*;

/**
 * This test class helps some of the testing for the methods in the SkipList
 * production class.
 * 
 * @author Seth Brown
 * @author 906388237
 * 
 * @version 26 Sep 2021
 */

public class SkipListTest extends TestCase {
    private SkipList<String, Rectangle> list;

    /**
     * This method sets up the variables used in this class.
     */
    public void setUp() {
        list = new SkipList<>();
    }


    /**
     * Test the search method in the SkipList class
     */
    public void testSearch() {
        KVPair<String, Rectangle> pair1;
        KVPair<String, Rectangle> pair2;
        KVPair<String, Rectangle> pair3;

        Rectangle rect1 = new RectangleHelper();
        rect1.setRect(10, 10, 10, 10);
        pair1 = new KVPair<>("A", rect1);

        Rectangle rect2 = new RectangleHelper();
        rect2.setRect(11, 10, 10, 10);
        pair2 = new KVPair<>("A", rect2);

        Rectangle rect3 = new RectangleHelper();
        rect3.setRect(12, 10, 10, 10);
        pair3 = new KVPair<>("A", rect3);

        TestableRandom.setNextInts(3, 2, 1);

        list.insert(pair1);
        list.insert(pair2);
        list.insert(pair3);

        list.dump();
        list.search("A");
        assertEquals(3, list.size());
    }


    /**
     * Test the remove methods in the SKipList Class
     */
    public void testRemoveByKeyAndValue() {
        KVPair<String, Rectangle> pair1;
        KVPair<String, Rectangle> pair2;
        KVPair<String, Rectangle> pair3;

        Rectangle rect1 = new RectangleHelper();
        rect1.setRect(10, 10, 10, 10);
        pair1 = new KVPair<>("A", rect1);

        Rectangle rect2 = new RectangleHelper();
        rect2.setRect(11, 10, 10, 10);
        pair2 = new KVPair<>("A", rect2);

        Rectangle rect3 = new RectangleHelper();
        rect3.setRect(12, 10, 10, 10);
        pair3 = new KVPair<>("A", rect3);

        TestableRandom.setNextInts(3, 2, 1);
        list.insert(pair1);
        list.insert(pair2);
        list.insert(pair3);

        Rectangle r = new Rectangle(10, 10, 10, 10);
        list.dump();
        list.remove("r");
        assertEquals(3, list.size());

        list.removeByValue(r);
        assertEquals(3, list.size());

    }

}
