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

}
