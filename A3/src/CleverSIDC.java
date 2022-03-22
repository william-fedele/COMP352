import collections.BinarySearchTree;
import collections.HashMap;

import java.util.Objects;
import java.util.Random;

public class CleverSIDC {

    private int sidc_threshold;

    private BinarySearchTree tree = null;
    public HashMap map = null;

    /**
     * Default constructor
     * At size 0, we start with a hashmap.
     * Default threshold for transformation to BST is 1000
     */
    public CleverSIDC() {
        map = new HashMap();
        tree = null;
        sidc_threshold = 1000;
    }

    /**
     * Determines the threshold at which we transform the ADT
     * @param size
     */
    public void SetSIDCThreshold(int size) {
        sidc_threshold = size;
    }

    /**
     * Randomly generates new non-existing key of 8 digits
     * @return Random 8 digit key not already present in the ADT
     */
    public int generate() {
        Random random = new Random();
        int key;
        boolean exists;
        do {
            key = random.nextInt(99999999);
            if(map != null) {
                exists = (map.get(key) != null && !Objects.equals(map.get(key), "DEFUNCT"));
            }
            else
                exists = tree.get(key) != null;

        } while(exists);

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
            // map stuff
            return map.sort();
    }

    /**
     *
     * @param key Identifier for an entry
     * @param value Data associated with the key
     */
    public void add(int key, String value) {
        if(map != null) {
            if(map.size() + 1 > sidc_threshold) {
                mapToTree();
                tree.insert(key, value);
            }
            else
                map.insert(key, value);
        }
        else
            tree.insert(key, value);
    }

    /**
     *
     * @param key Identifier for an entry
     * @return Data associated with the key
     */
    public String remove(int key) {
        if(tree != null) {
            if(tree.size() - 1 <= sidc_threshold) {
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
     *
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
     *  Finds the next key in the sorted sequence of keys
     * @param key Identifier for an entry
     * @return Next key in sorted sequence
     */
    public int nextKey(int key) {
        int[] sortedKeys = tree != null ? tree.sort() : map.sort();
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
        int[] sortedKeys = tree != null ? tree.sort() : map.sort();
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
        int[] sortedKeys = tree != null ? tree.sort() : map.sort();
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
     */
    private void mapToTree() {
        System.out.println("Converting map to tree");
        tree = new BinarySearchTree();
        for(int i = 0; i < map.getCapacity(); i++) {
            if(map.at(i) != null && !Objects.equals(map.at(i).getValue(), "DEFUNCT"))
                tree.insert(map.at(i).getKey(), map.at(i).getValue());
        }
        map = null;
    }

    /**
     *
     */
    private void treeToMap() {
        System.out.println("Converting tree to map");
        map = new HashMap();
        for(int i = 0; i < tree.size(); i++) {
            map.insert(tree.root.getKey(), tree.root.getValue());
            tree.remove(tree.root.getKey());
        }
        tree = null;
    }

    public int size() {
        if(map != null)
            return map.size();
        else
            return tree.size();
    }

    public void reset() {
        map = new HashMap();
        tree = null;

    }

    public void debug() {
        System.out.println("=====================================");
        if(map != null) {
            System.out.println("Current mode: Map");
            System.out.println("Size: " + map.size());
        }
        if(tree != null) {
            System.out.println("Current mode: Tree");
            System.out.println("Size: " + tree.size());
        }
    }
}
