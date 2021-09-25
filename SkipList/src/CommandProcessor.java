
import java.awt.Rectangle;

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
     * exist, so the only constructor takes a database class object to feed
     * commands
     * to.
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database
     * as required. Each line command will be specified by one of the keywords
     * to
     * perform the actions within the database required. These actions are
     * performed
     * on specified objects and include insert, remove, regionsearch, search,
     * intersections, and dump. If the command in the file line is not one of
     * these,
     * an appropriate message will be written in the console. This processor
     * method
     * is called for each line in the file. Note that the methods called will
     * themselves write to the console, this method does not, only calling
     * methods
     * that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        line = line.replaceAll("\\s{2,}", " ").trim();
        String[] tempLine = line.split(" ");
        String command = tempLine[0];
        String key = "";
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;

        if (tempLine.length > 1) {
            key = tempLine[1];
        }
        if (tempLine.length > 2) {
            x = Integer.parseInt(tempLine[2]);
        }
        if (tempLine.length > 3) {
            y = Integer.parseInt(tempLine[3]);
        }
        if (tempLine.length > 4) {
            w = Integer.parseInt(tempLine[4]);
        }
        if (tempLine.length > 5) {
            h = Integer.parseInt(tempLine[5]);
        }
        Rectangle rect = new RectangleHelper();
        rect.setRect(x, y, w, h);
        KVPair<String, Rectangle> pair = new KVPair<>(key, rect);

        if (command.equalsIgnoreCase("insert")) {
            data.insert(pair);
            return;
        }
        else if (command.equalsIgnoreCase("remove")) {
            data.remove(key);
            return;
        }
        else if (command.equalsIgnoreCase("regionsearch")) {
            data.regionsearch(x, y, w, h);
            return;
        }
        else if (command.equalsIgnoreCase("search")) {
            data.search(key);
            return;
        }
        else if (command.equalsIgnoreCase("intersections")) {
            data.intersections();
            return;
        }
        else if (command.equalsIgnoreCase("dump")) {
            data.dump();
            return;
        }
        System.out.println("Not a command. Please input a proper command.");
    }
}
