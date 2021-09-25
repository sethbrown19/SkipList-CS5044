
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
        String line = "Remove";
        processor.processor(line);

        assertFuzzyEquals("Remove method for string", systemOut().getHistory());
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
        String line = "search";
        processor.processor(line);

        assertFuzzyEquals("Search method", systemOut().getHistory());
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
        String line = "dump";
        processor.processor(line);

        assertFuzzyEquals("Dump method", systemOut().getHistory());
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
