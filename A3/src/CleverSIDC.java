import java.util.Random;

public class CleverSIDC {

    private int sidc_threshold;
    private final int TRANSFORM_THRESHOLD = 1000;

    private BinarySearchTree tree = null;
    private HashMap map = null;

    /**
     * Default constructor
     * At size 0, we start with a hashmap.
     * Default threshold for transformation to BST is 1000
     */
    public CleverSIDC() {
        map = new HashMap();
        tree = null;
        sidc_threshold = 0;
    }

    /**
     * Determines the threshold at which we transform the ADT
     * @param size
     */
    public void SetSIDCThreshold(int size) {
        sidc_threshold = size;
        if(sidc_threshold > TRANSFORM_THRESHOLD && map != null)
            mapToTree();
    }

    /**
     * Randomly generates new non-existing key of 8 digits
     * @return Random 8 digit key not already present in the ADT
     */
    public int generate() {
        Random random = new Random();
        int key;
        do {
            key = random.nextInt(99999999);
        } while(getValues(key) != null);

        return key;
    }

    /**
     * Returns all keys in CleverSIDC as a sorted sequence
     * @return All keys in a sorted array
     */
    public int[] allKeys() {
        if(tree != null)
            return tree.sort();
        else
            return map.sort();
    }

    /**
     *
     * @param key Identifier for an entry
     * @param value Data associated with the key
     */
    public String add(int key, String value) {
        if(map != null) {
            // transform to tree if we're about to cross the preset threshold
            if(map.size() + 1 > TRANSFORM_THRESHOLD) {
                mapToTree();
                return tree.insert(key, value);
            }
            else
                return map.insert(key, value);
        }
        else
            return tree.insert(key, value);
    }

    /**
     * Remove a key from the ADT.
     * @param key Identifier for an entry
     * @return Data associated with the key
     */
    public String remove(int key) {
        if(tree != null) {
            // transform to map if we're about to cross the preset threshold
            if(tree.size() - 1 <= TRANSFORM_THRESHOLD) {
                treeToMap();
                return map.remove(key);
            }
            else
                return tree.remove(key);
        }
        else
            return map.remove(key);

    }

    /**
     * Get the values associated with the key provided
     * @param key Identifier for an entry
     * @return Data associated with the key
     */
    public String getValues(int key) {
        if(tree != null)
            return tree.get(key);
        else
            return map.get(key);
    }

    /**
     * Finds the next key in the sorted sequence of keys
     * Uses binary search on the sorted keys.
     * @param key Identifier for an entry
     * @return Next key in sorted sequence
     */
    public int nextKey(int key) {
        int[] sortedKeys = allKeys();
        int leftIndex = 0;
        int rightIndex = sortedKeys.length-1;
        int midIndex;
        while (leftIndex <= rightIndex) {
            midIndex = (leftIndex+rightIndex) / 2;
            if(key == sortedKeys[midIndex] && midIndex+1 < sortedKeys.length)
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
     * Uses binary search on the sorted keys.
     * @param key Identifier for an entry
     * @return Previous key in sorted sequence
     */
    public int prevKey(int key) {
        // get the sorted keys
        int[] sortedKeys = allKeys();
        int leftIndex = 0;
        int rightIndex = sortedKeys.length-1;
        int midIndex;
        while (leftIndex <= rightIndex) {
            midIndex = (leftIndex+rightIndex) / 2;
            if(key == sortedKeys[midIndex] && midIndex-1 >= 0)
                return sortedKeys[midIndex-1];
            else if(key < sortedKeys[midIndex])
                rightIndex = midIndex-1;
            else
                leftIndex = midIndex + 1;
        }
        return -1;
    }

    /**
     * Finds the distance between two keys in the sorted key array
     * @param key1 First identifier for an entry
     * @param key2 Second identifier for an entry
     * @return Distance between the two keys. -1 if one or neither weren't found.
     */
    public int rangeKey(int key1, int key2) {
        // get the sorted keys
        int[] sortedKeys = allKeys();
        int first = -1;
        int last = -1;
        for (int i = 0; i < sortedKeys.length; i++) {
            // search for either key
            if(sortedKeys[i] == key1 || sortedKeys[i] == key2) {
                // store first occurrence of either key
                if(first == -1)
                    first = i;
                else
                    // first is already set, store the index of the further key
                    last = i;
            }
        }
        // if either are -1, one or both weren't found
        if(first == -1 || last == -1)
            return -1;
        else
            // return count of elements between the two indexes
            return (last-1) - first;
    }

    /**
     * Inserts every non-null/DEFUNCT value from the map into the tree.
     * Deletes the map from memory.
     */
    private void mapToTree() {
        tree = new BinarySearchTree();
        for(int i = 0; i < map.getCapacity(); i++) {
            // ignore all null indexes as well as previously deleted DEFUNCT values
            if(map.at(i) != null && map.at(i).getValue() != "DEFUNCT")
                tree.insert(map.at(i).getKey(), map.at(i).getValue());
        }
        map = null;
    }

    /**
     * Inserts every value from the tree into the map.
     * Deletes the tree from memory.
     */
    private void treeToMap() {
        map = new HashMap();
        for(int i = 0; i < tree.size(); i++) {
            map.insert(tree.root.getKey(), tree.root.getValue());
            tree.remove(tree.root.getKey());
        }
        tree = null;
    }

    /**
     * Returns the current size of the ADT
     * @return Integer size of currently active keys
     */
    public int size() {
        if(map != null)
            return map.size();
        else
            return tree.size();
    }

    /**
     * Resets the CleverSIDC state to the beginning.
     * Used for testing purposes to wipe all data from the ADT.
     */
    public void reset() {
        map = new HashMap();
        tree = null;
    }

    /**
     * Returns the name of the current data structure.
     * @return Either HashMap or BinarySearchTree, depending on the current data structure
     */
    public String currentMode() {
        return map != null ? "HashMap" : "BinarySearchTree";
    }
}
