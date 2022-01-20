
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Seth Brown
 * @author 906388237
 * 
 * @version 26 Sep 2021
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 * @param <SkipNode>
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            to be searched for
     * @return pair the ArrayList KVPair or null if not found
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        SkipNode x = head; // start at dummy head
        ArrayList<KVPair<K, V>> arrList = new ArrayList<>();
        for (int i = x.level; i >= 0; i--) {
            while ((x.forward[i] != null) && (x.forward[i].pair.getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i]; // move forward
            }
        }
        x = x.forward[0]; // move to actual record, if it exists

        if ((x != null) && (x.pair.getKey().compareTo(key) == 0)) {
            arrList.add(x.pair);
            while (x.forward[0] != null && x.forward[0].pair.getKey().compareTo(
                key) == 0) {
                x = x.forward[0];
                arrList.add(x.pair);
            }
        }
        else {
            return null;
        }

        return arrList;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by
     * its lexicographical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int newLevel = randomLevel(); // New node's level
        if (newLevel > head.level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
        // Track end of level
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode x = head; // Start at header node
        for (int i = head.level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x

        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices
     * than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= head.level; i++) {
            head.forward[i] = temp.forward[i];
        }
        temp.level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter
     * 
     * @param key
     *            the key to be used to search and remove if found.
     * @return returns the removed pair if the pair was valid and null if not
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode x = head; // Start at header node
        for (int i = head.level; i >= 0; i--) { // move forward to find key
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i];
            }
            update[i] = x; // update array filled with x array
        }

        x = x.forward[0];

        if (x != null && x.pair.getKey().compareTo(key) == 0) {
            for (int i = 0; i <= x.level; i++) { // Splice into list
                update[i].forward[i] = x.forward[i];
            }
            size--;
            return x.element();
        }

        return null;
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> removeByValue(V val) {
        SkipNode x = head; // Start at header node
        while ((x.forward[0] != null) && (!x.forward[0].pair.getValue().equals(
            val))) {
            x = x.forward[0];
        }

        if (x.forward[0] == null) {
            return null;
        }
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode y = head;
        for (int i = head.level; i >= 0; i--) {
            while ((y.forward[i] != null) && (y.forward[i].element().getKey()
                .compareTo(x.forward[0].pair.getKey()) < 0)) {
                y = y.forward[i];
            }
            update[i] = y; // track end at level i
        }
        y = y.forward[0]; // move to actual record

        for (int i = 0; i <= y.level; i++) {
            update[i].forward[i] = y.forward[i];
        }
        size--;

        return y.element();
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        System.out.println("SkipList dump: ");
        {
            SkipNode x = head;
            System.out.println("Node has depth " + (x.level + 1) + ", Value ("
                + x.element() + ")");
            while (x.forward[0] != null) {
                x = x.forward[0];
                RectangleHelper r = (RectangleHelper)x.element().getValue();
                System.out.println("Node has depth " + (x.level + 1)
                    + ", Value (" + x.element().getKey() + ", " + r.x + ", "
                    + r.y + ", " + r.width + ", " + r.height + ")");

            }

            System.out.println("SkipList size is: " + size());
        }
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author Seth Brown
     * 
     * @version 15 Sep 2021
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from
         * the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;

        }


        @Override
        public boolean hasNext() {
            if (current.forward[0] != null) {
                current = current.forward[0];
                return true;
            }
            return false;
        }


        @Override
        public KVPair<K, V> next() {
            return current.element();
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
