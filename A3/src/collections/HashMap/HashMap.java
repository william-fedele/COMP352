package collections.HashMap;

public class HashMap {

    private static final int DEFAULT_SIZE = 7;
    private static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry[] map;
    private int size;
    private double rehash_threshold;

    public HashMap() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    public HashMap(int mapSize) {
        this(mapSize, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    public HashMap(int mapSize, double threshold) {
        this.map = new Entry[size];
        this.rehash_threshold = threshold;
        this.size = mapSize;
    }

    public int hash(int key) {
        return key%size;
    }
    public int hash2(int key) {
        return 0;
    }

    public void put(int key, String value) {

    }

    public String get(int key) {
        return "";
    }

    public String remove(int key) {
        return "";
    }
}
