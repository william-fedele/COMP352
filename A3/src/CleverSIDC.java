public class CleverSIDC {

    float LOAD_FACTOR = 0.75f;
    int TREE_THRESHOLD = 1000000;
    int sidc_threshold = 0; 

    CleverSIDC() {
    
    }

    public void SetSIDCThreshold(int size) {
        sidc_threshold = size;
    }

    public int generate() {
        return 0;
    }

    public void add(int key, String value) {
        
    }

    public String remove(int key) {
        return "Not implemented";
    }

    public String[] getValues(int key) {
        return null;
    }

    public int nextKey(int key) {
        return 0;
    }

    public int prevKey(int key) {
        return 0;
    }

    public int rangeKey(int key1, int key2) {
        return 0;
    }
}
