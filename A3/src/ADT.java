/**
 * Used by both BinarySearchTree and HashMap to keep method names consistent
 */
public interface ADT {
    String get(int key);
    String insert(int key, String value);
    String remove(int key);
    int size();
    int[] sort();
}
