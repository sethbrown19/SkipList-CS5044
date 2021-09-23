
import student.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CommandProcessorTest extends TestCase {

	private CommandProcessor processor;

	@Before
	public void setUp() {
		processor = new CommandProcessor();
	}

	@Test
	public void testProcessorInsert() {
		String line = "Insert r2 15 3 5 19";
		processor.processor(line);
		assertFuzzyEquals("Rectangle inserted: (r2, 15, 3, 5, 19)", systemOut().getHistory());
	}

	@Test
	public void testProcessorRemove() {
		String line = "Remove";
		processor.processor(line);

		assertFuzzyEquals("Remove method for string", systemOut().getHistory());
	}

	@Test
	public void testProcessorRegionSearch() {
		String line = "regionsearch";
		processor.processor(line);

		assertFuzzyEquals("Region search method", systemOut().getHistory());
	}

	@Test
	public void testProcessorSearch() {
		String line = "search";
		processor.processor(line);

		assertFuzzyEquals("Search method", systemOut().getHistory());
	}

	@Test
	public void testProcessorIntersections() {
		String line = "intersections";
		processor.processor(line);

		assertFuzzyEquals("Intersections method", systemOut().getHistory());
	}

	@Test
	public void testProcessorDump() {
		String line = "dump";
		processor.processor(line);

		assertFuzzyEquals("Dump method", systemOut().getHistory());
	}

	@Test
	public void testProcessorInvalid() {
		String line = "Invalid";
		processor.processor(line);

		assertFuzzyEquals("Not a command. Please input a proper command.", systemOut().getHistory());
	}

}
