package collections;

public class Entry {

    private int key;
    private String value;

    public int getKey() {
        return key;
    }
    public Entry setKey(int key) {
        this.key = key;
        return this;
    }
    public String getValue() {
        return value;
    }
    public Entry setValue(String value) {
        this.value = value;
        return this;
    }

}
