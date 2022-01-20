
import java.awt.Rectangle;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project specifications.
 * 
 * @author Seth Brown
 * @author 906388237
 * 
 * @version 26 Sep 2021
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
        int count = 0;
        String[] split = line.split("\\s+");
        
        switch(split[count]) {
            case "insert" :
                count++;
                String nameInsert = split[count];
                count++;
                int xInsert = Integer.parseInt(split[count]);
                count++;
                int yInsert = Integer.parseInt(split[count]);
                count++;
                int wInsert = Integer.parseInt(split[count]);
                count++;
                int hInsert = Integer.parseInt(split[count]);
                Rectangle rect = new Rectangle (xInsert, yInsert, wInsert, hInsert);
                KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>(nameInsert, rect);
                data.insert(pair1);
                break;
            case "remove":
                if (split.length == 2) {
                    count++;
                    String nameRemove = split[count];
                    data.remove(nameRemove);
                    break;
                }
                else if (split.length == 5){
                    count++;
                    int xRemove = Integer.parseInt(split[count]);
                    count++;
                    int yRemove = Integer.parseInt(split[count]);
                    count++;
                    int wRemove = Integer.parseInt(split[count]);
                    count++;
                    int hRemove = Integer.parseInt(split[count]);
                    data.remove(xRemove, yRemove, wRemove, hRemove);
                    break;                 
                }
                else break;
            case "regoinsearch":
                count++;
                int xRegionSearch = Integer.parseInt(split[count]);
                count++;
                int yRegionSearch = Integer.parseInt(split[count]);
                count++;
                int wRegionSearch = Integer.parseInt(split[count]);
                count++;
                int hRegionSearch = Integer.parseInt(split[count]);
                data.regionsearch(xRegionSearch, yRegionSearch, wRegionSearch, hRegionSearch);
                break;
            case "intersections":
                data.intersections();
                break;
            case "search":
                count++;
                String nameSearch = split[count];
                data.search(nameSearch);
                break;
            case "dump":
                data.dump();
                break;
                default:
                    // If none of the cases match then this is an unrecognized parameter.
                    System.out.println("Unrecognized parameter " + split[count]);
                    break; 
        }
        
        
    }
}
