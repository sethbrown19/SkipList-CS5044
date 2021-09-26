import student.TestCase;

/**
 * use this class to test the command processor class.
 * 
 * @author Seth Brown
 * @version 25 Sep 2021
 */

public class CommandProcessorTest extends TestCase {

    private CommandProcessor processor;

    /**
     * Set up processor constructor for testing.
     */
    public void setUp() {
        processor = new CommandProcessor();
    }


    /**
     * Test insert method call in command processor.
     */
    public void testProcessorInsert() {
        String line = "Insert r2 15 3 5 19";
        processor.processor(line);
        assertFuzzyEquals("Rectangle inserted: (r2, 15, 3, 5, 19)", systemOut()
            .getHistory());
    }


    /**
     * Test remove method call in command processor.
     */
    public void testProcessorRemove() {
        String line = "insert r2 15 3 5 19";
        String line2 = "insert r2 15 3 5 19";
        String remove = "remove r2";
        processor.processor(line);
        processor.processor(line2);
        processor.processor(remove);

        assertFuzzyEquals("Rectangle removed: (r2, 15, 3, 5, 19)", systemOut()
            .getHistory());
    }


    /**
     * Test region search method call in command processor.
     */
    public void testProcessorRegionSearch() {
        String line = "regionsearch";
        processor.processor(line);

        assertFuzzyEquals("Region search method", systemOut().getHistory());
    }


    /**
     * Test search method call in command processor.
     */
    public void testProcessorSearch() {
        String insert = "insert r14 15 15 30 30 ";
        String insert15 = "insert r15 14 14 30 30 ";
        String insert16 = "insert r16 16 16 30 30 ";
        String insert17 = "insert r17 17 17 30 30 ";
        String search1 = "search   r14  ";
        String search = "search r18";
        processor.processor(insert);
        processor.processor(insert15);
        processor.processor(insert16);
        processor.processor(insert17);

        processor.processor(search1);
        assertFuzzyEquals("Rectangles found: [(r14, 15, 15, 30, 30)]",
            systemOut().getHistory());

        processor.processor(search);
        assertFuzzyEquals("search method []", systemOut().getHistory());
    }


    /**
     * Test intersections method call in command processor.
     */
    public void testProcessorIntersections() {
        String line = "intersections";
        processor.processor(line);

        assertFuzzyEquals("Intersections method", systemOut().getHistory());
    }


    /**
     * Test dump method call in command processor.
     */
    public void testProcessorDump() {
        String one = "insert r1 10 10 5 5";
        String two = "insert r3 7 7 10 10";
        String three = "insert r4 20 25 7 9";
        String four = "insert r5 6 7 11 9";
        String five = "insert r14 120 117 93 706";
        String dump = "dump";

        processor.processor(one);
        processor.processor(two);
        processor.processor(three);
        processor.processor(four);
        processor.processor(five);
        processor.processor(dump);

        assertFuzzyEquals("SkipList Dump: \n"
            + "Node has depth 2, Value (null)\n"
            + "Node has depth 1, Value (r1, 10, 10, 5, 5)\n"
            + "Node has depth 1, Value (r14, 120, 117, 93, 706)\n"
            + "Node has depth 2, Value (r3, 7, 7, 10, 10)\n"
            + "Node has depth 1, Value (r4, 20, 25, 7, 9)\n"
            + "Node has depth 1, Value (r5, 6, 7, 11, 9)\n"
            + "SkipList size is: 5", systemOut().getHistory());
    }


    /**
     * Test invalid input in command processor.
     */
    public void testProcessorInvalid() {
        String line = "Invalid";
        processor.processor(line);

        assertFuzzyEquals("Not a command. Please input a proper command.",
            systemOut().getHistory());
    }

}
