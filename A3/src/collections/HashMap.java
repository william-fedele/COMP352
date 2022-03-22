package collections;


import java.util.Objects;

public class HashMap implements ADT {

    // load factor = active keys / total capacity. Maintain load factor < 0.75 to avoid many collisions
    private static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;
    // Storing prime map capacities to avoid runtime calculations
    private static final int[] PRIME_CAPACITIES = new int[]{7, 19, 41, 83, 167, 331, 661, 1361};
    private static final int[] PRIME_PROBING = new int[]{5, 17, 37, 79, 163, 317, 659, 1327};
    private int current_prime_index = 0;


    // map and metadata
    private Entry[] map;
    private int active_keys;
    private double load_factor;

    /**
     * Initializes the hashmap with the default load factor
     */
    public HashMap() {
        this(DEFAULT_LOAD_FACTOR_THRESHOLD);
    }

    /**
     * Accepts an alternate load_factor if required
     * @param load_factor Percentage threshold at which the hashmap is rehashed.
     */
    public HashMap(double load_factor) {
        this.map = new Entry[PRIME_CAPACITIES[0]];
        this.load_factor = load_factor;
    }

    /**
     * Hashes an integer to an index
     * @param key Identifier for an entry
     * @return Index at which to try placing the key
     */
    public int hash(int key) {
        return key % map.length;
    }

    /**
     * Used for double hashing.
     * Provides an offset to probe through all map indices.
     * @param key Identifier for an entry
     * @return Secondary hash offset to probe through the map for an available spot
     */
    public int hash2(int key) {
        return PRIME_PROBING[current_prime_index] - (key % PRIME_PROBING[current_prime_index]);
    }

    /**
     * Inserts key/value pair into the map.
     * Start at the default hash value for the key.
     * Follow up with adding the offset hash value if the index is occupied.
     * @param key Identifier for an entry
     * @param value Data associated with the key
     */
    @Override
    public String insert(int key, String value) {
        Entry newEntry = new Entry().setKey(key).setValue(value);
        String oldValue = null;
        // initial index and offset hash values
        int index = hash(key);
        int offset = hash2(key);

        // iterate through the map until an available index is found
        while(map[index] != null && !Objects.equals(map[index].getValue(), "DEFUNCT")) {
            if(map[index].getKey() == key) {
                oldValue = map[index].getValue();
                map[index].setValue(value);
                break;
            }
            index = (index + offset) % map.length;
        }
        // insert the entry
        map[index] = newEntry;
        active_keys++;
        // Check if the map must be rehashed to maintain the load_factor
        rehash();
        return oldValue;
    }

    /**
     * Retrieve the value for a given key.
     * @param key Identifier for an entry
     * @return Data associated with the key
     */
    @Override
    public String get(int key) {
        // Initial index and offset hash values
        int index = hash(key);
        int offset = hash2(key);

        String temp = null;
        // Probe the map, looking for the key or a null value
        // If a null value is found, stop searching. The value would've been placed there if it existed.
        while(map[index] != null) {
            if(map[index].getKey() == key)
                temp = map[index].getValue();
            index = (index + offset) % map.length;
        }
        return temp;
    }

    /**
     * Removes the key from the map, returning the value associated with it.
     * The key remains in the map, but the value is marked as DEFUNCT to be occupied by another value
     * @param key Identifier for an entry
     * @return Data associated with the key. Null if not found
     */
    @Override
    public String remove(int key) {
        // initial index and offset hash values
        int index = hash(key);
        int offset = hash2(key);

        // Probe the map, looking for the key or a null value
        // If a null value is found, stop searching. The value would've been placed there if it existed.
        String temp = null;
        while(map[index] != null) {
            if(map[index].getKey() == key) {
                temp = map[index].getValue();
                // Marking the key as defunct lets another key take this spot
                map[index].setValue("DEFUNCT");
                active_keys--;
                break;
            }
            // Next probe index
            index = (index + offset) % map.length;
        }

        // Return the value associated with the key if needed.
        // Returns null if the key wasn't found
        return temp;
    }

    /**
     * Retrieves the entry at the given index.
     * @param index Literal index of the map array
     * @return Entry at the given index
     */
    public Entry at(int index) {
        return map[index];
    }

    /**
     * Recreate the map with a modified size.
     * All entries are rehashed to new indices.
     */
    public void rehash() {

        // Do nothing if we haven't passed the load_factor threshold
        if((double)active_keys / (double)map.length < load_factor)
            return;

        // Save the current map to a temp variable and recreate it with the new size.
        Entry[] previousMap = map;
        map = new Entry[PRIME_CAPACITIES[++current_prime_index]];
        active_keys = 0;

        // Add every entry to the new map, excluding the DEFUNCT values
        for(Entry e : previousMap)
            if(e != null && !Objects.equals(e.getValue(), "DEFUNCT"))
                insert(e.getKey(), e.getValue());
    }

    /**
     * Sorts all entry keys
     * @return Sorted array of keys
     */
    public int[] sort() {
        // TODO
        int[] sortedKeys = new int[active_keys];
        for(int i = 0; i < map.length; i++) {
            if(at(i) != null && !Objects.equals(at(i).getValue(), "DEFUNCT"))
                sortedKeys[i] = at(i).getKey();
        }
        return sortedKeys;
    }

    /**
     * Active_keys maintains the count of real keys in the map.
     * This excludes all DEFUNCT and null indices.
     * @return All real entries (non-DEFUNCT/null)
     */
    public int size() {
        return active_keys;
    }

    /**
     * Retrieves the map array's length.
     * @return Map array length
     */
    public int getCapacity() {
        return map.length;
    }
}
