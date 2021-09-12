package test_skiplist;

import project1.CommandProcessor;
import student.TestCase;

public class CommandProcessorTest extends TestCase {

	private CommandProcessor processor;

	public void setUp() {
		processor = new CommandProcessor();
	}

	public void testProcessorInsert() {
		String line = "Insert";
		processor.processor(line);

		assertFuzzyEquals("Insert method", systemOut().getHistory());
	}

	public void testProcessorRemove() {
		String line = "Remove";
		processor.processor(line);

		assertFuzzyEquals("Remove method for string", systemOut().getHistory());
	}
	
	public void testProcessorRegionSearch() {
		String line = "regionsearch";
		processor.processor(line);

		assertFuzzyEquals("Region search method", systemOut().getHistory());
	}
	
	public void testProcessorSearch() {
		String line = "search";
		processor.processor(line);

		assertFuzzyEquals("Search method", systemOut().getHistory());
	}
	
	public void testProcessorIntersections() {
		String line = "intersections";
		processor.processor(line);

		assertFuzzyEquals("Intersections method", systemOut().getHistory());
	}
	
	public void testProcessorDump() {
		String line = "dump";
		processor.processor(line);

		assertFuzzyEquals("Dump method", systemOut().getHistory());
	}
	
	public void testProcessorInvalid() {
		String line = "Invalid";
		processor.processor(line);

		assertFuzzyEquals("Not a command. Please input a proper command.", systemOut().getHistory());
	}

}
