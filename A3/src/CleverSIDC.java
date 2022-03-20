import collections.BST;
import collections.HashMap;

public class CleverSIDC {

    private int sidc_threshold = 1000000;

    private BST bst = null;
    private HashMap map = null;

    public CleverSIDC() {
        bst = new BST();
    }

    public void SetSIDCThreshold(int size) {
        sidc_threshold = size;
    }

    /**
     * Randomly generates new non-existing key of 8 digits
     * @return Random 8 digit key not already present in the ADT
     */
    public int generate() {
        return 0;
    }

    /**
     * Returns all keys in CleverSIDC as a sorted sequence
     * @return All keys in a sorted array
     */
    public int[] allKeys() {
        if(bst != null)
            return bst.inorder();
        else
            // map stuff
            return new int[0];
    }

    /**
     *
     * @param key Identifier for an entry
     * @param value Data associated with the key
     */
    public void add(int key, String value) {
        if(bst != null)
            bst.insert(key, value);
    }

    /**
     *
     * @param key Identifier for an entry
     * @return Data associated with the key
     */
    public String remove(int key) {

        if(bst != null)
            return bst.delete(key).getValue();
        else
            return map.remove(key);

    }

    /**
     *
     * @param key Identifier for an entry
     * @return Data associated with the key
     */
    public String getValues(int key) {
        if(bst != null)
            return bst.get(key);
        else
            return map.get(key);
    }

    /**
     *  Finds the next key in the sorted sequence of keys
     * @param key Identifier for an entry
     * @return Next key in sorted sequence
     */
    public int nextKey(int key) {
        int[] sortedKeys = bst != null ? bst.inorder() : map.sort();
        int leftIndex = 0;
        int rightIndex = sortedKeys.length-1;
        int midIndex = (leftIndex+rightIndex / 2);
        while (leftIndex <= rightIndex) {
            if(key == sortedKeys[midIndex])
                return sortedKeys[midIndex+1];
            else if(key < sortedKeys[midIndex])
                rightIndex = midIndex-1;
            else
                leftIndex = midIndex + 1;
        }
        return -1;
    }

    /**
     * Finds the previous key in the sorted sequence of keys
     * @param key Identifier for an entry
     * @return Previous key in sorted sequence
     */
    public int prevKey(int key) {
        int[] sortedKeys = bst != null ? bst.inorder() : map.sort();
        int leftIndex = 0;
        int rightIndex = sortedKeys.length-1;
        int midIndex = (leftIndex+rightIndex / 2);
        while (leftIndex <= rightIndex) {
            if(key == sortedKeys[midIndex])
                return sortedKeys[midIndex-1];
            else if(key < sortedKeys[midIndex])
                rightIndex = midIndex-1;
            else
                leftIndex = midIndex + 1;
        }
        return -1;
    }

    /**
     * Finds the index distance between two keys
     * @param key1 First identifier for an entry
     * @param key2 Second identifier for an entry
     * @return Distance between the two keys
     */
    public int rangeKey(int key1, int key2) {
        int[] sortedKeys = bst != null ? bst.inorder() : map.sort();
        int first = -1;
        int last = -1;
        for (int i = 0; i < sortedKeys.length; i++) {
            if(sortedKeys[i] == key1 || sortedKeys[i] == key2) {
                if(first == -1)
                    first = i;
                else
                    last = i;
            }
        }
        if(first == last)
            return -1;
        else
            return last - first;
    }

    /**
     *
     * @return Amount of entries currently in the ADT
     */
    public int size() {
        return bst.getSize();
    }
    public void empty() {
        bst.clear();
    }
}
