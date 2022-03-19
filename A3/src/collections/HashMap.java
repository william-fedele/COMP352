package collections;


public class HashMap {

    private static final int DEFAULT_SIZE = 7;
    private static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry[] map;
    private int table_capacity;
    private int active_keys;
    private double load_factor;
    private int prime;

    public HashMap() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    public HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR_THRESHOLD);
    }
    public HashMap(int capacity, double threshold) {
        this.table_capacity = capacity;
        this.map = new Entry[table_capacity];
        this.load_factor = threshold;
    }

    public int hash(int key) {
        return key % table_capacity;
    }
    public int hash2(int key) {
        return prime - (key % prime);
    }

    public void put(int key, String value) {
        int index = hash(key);
        int offset = hash2(key);
        // iterate through the map until an available index is found
        while(map[index] != null && map[index].getValue() != "DEFUNCT") {
            index = (index + offset) % table_capacity;
        }
        // insert the entry
        map[index].setKey(key);
        map[index].setValue(value);
        active_keys++;
    }

    public String get(int key) {
        int index = hash(key);
        int offset = hash2(key);
        while(map[index] != null) {
            if(map[index].getKey() == key)
                return map[index].getValue();
            index = (index + offset) % table_capacity;
        }
        return null;
    }

    public String remove(int key) {
        if(get(key) == null)
            return null;
        int index = hash(key);
        int offset = hash2(key);

        while(map[index].getKey() != key)
            index = (index + offset) % table_capacity;

        String temp = map[index].getValue();
        map[index].setValue("DEFUNCT");
        return temp;
    }
}
