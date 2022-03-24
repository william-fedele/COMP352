/**
 * Helper class used to couple keys along with a value.
 * This value could be a name, DOB, etc..
 */
public class Entry {

    private int key;
    private String value;

    public int getKey() {
        return key;
    }
    public Entry setKey(int key) {
        this.key = key;
        // return this for method chaining
        return this;
    }
    public String getValue() {
        return value;
    }
    public Entry setValue(String value) {
        this.value = value;
        // return this for method chaining
        return this;
    }

}
