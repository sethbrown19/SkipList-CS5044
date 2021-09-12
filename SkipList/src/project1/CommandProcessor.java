package project1;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project specifications.
 * 
 * @author Seth Brown
 * 
 * @version 11 Sep 2021
 */
public class CommandProcessor {

	// the database object to manipulate the
	// commands that the command processor
	// feeds to it
	private Database data;

	/**
	 * The constructor for the command processor requires a database instance to
	 * exist, so the only constructor takes a database class object to feed commands
	 * to.
	 */
	public CommandProcessor() {
		data = new Database();
	}

	/**
	 * This method identifies keywords in the line and calls methods in the database
	 * as required. Each line command will be specified by one of the keywords to
	 * perform the actions within the database required. These actions are performed
	 * on specified objects and include insert, remove, regionsearch, search,
	 * intersections, and dump. If the command in the file line is not one of these,
	 * an appropriate message will be written in the console. This processor method
	 * is called for each line in the file. Note that the methods called will
	 * themselves write to the console, this method does not, only calling methods
	 * that do.
	 * 
	 * @param line a single line from the text file
	 */
	public void processor(String line) {
		if (line.toLowerCase().contains("insert")) {
			data.insert(null);
			return;
		} else if (line.toLowerCase().contains("remove")) {
			data.remove(line);
			return;
		} else if (line.toLowerCase().contains("regionsearch")) {
			data.regionsearch(0, 0, 0, 0);
			return;
		} else if (line.toLowerCase().contains("search")) {
			data.search(line);
			return;
		} else if (line.toLowerCase().contains("intersections")) {
			data.intersections();
			return;
		} else if (line.toLowerCase().contains("dump")) {
			data.dump();
			return;
		}
		System.out.println("Not a command. Please input a proper command.");

	}

}
