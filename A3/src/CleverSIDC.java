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
     * @return
     */
    public int generate() {
        return 0;
    }

    /**
     * Returns all keys in CleverSIDC as a sorted sequence
     * @return
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
     * @param key
     * @param value
     */
    public void add(int key, String value) {
        if(bst != null)
            bst.insert(key, value);
    }

    /**
     *
     * @param key
     * @return
     */
    public String remove(int key) {

        if(bst != null)
            return bst.delete(key).getValue();
        else
            return map.remove(key);

    }

    /**
     *
     * @param key
     * @return
     */
    public String getValues(int key) {
        if(bst != null)
            return bst.get(key);
        else
            return map.get(key);
    }

    /**
     *
     * @param key
     * @return
     */
    public int nextKey(int key) {
        return 0;
    }

    /**
     *
     * @param key
     * @return
     */
    public int prevKey(int key) {
        return 0;
    }

    /**
     *
     * @param key1
     * @param key2
     * @return
     */
    public int rangeKey(int key1, int key2) {
        return 0;
    }

    public int size() {
        return bst.getSize();
    }
    public void empty() {
        bst.clear();
    }
}
