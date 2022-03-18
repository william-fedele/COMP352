package collections.BST;

public class BST {

    private class Node {
        public int key;
        public String value;
        public Node left;
        public Node right;
    }

    public Node root;
    private int size;

    public Node createNode(int key, String value) {
        Node n = new Node();
        n.key = key;
        n.value = value;
//        Entry e = new Entry();
//        e.setKey(key);
//        e.setValue(value);
//        n.element = e;
        return n;
    }

    public void insert(int key, String value) {
        Node newNode = createNode(key, value);

        if(root == null) {
            root = newNode;
            return;
        }
        Node curr = root;
        Node prev = null;

        while(curr != null) {
            prev = curr;
            if(key < curr.key)
                curr = curr.left;
            else
                curr = curr.right;
        }

        if(key < prev.key)
            prev.left = newNode;
        else
            prev.right = newNode;

        size++;
    }

    public Node get(int key) {
        Node current = root;
        while(current != null) {
            if(key == current.key)
                break;

            if(key < current.key)
                current = current.left;
            else
                current = current.right;
        }
        return current;
    }

    public Node delete() {
        return root;
    }

    public void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(String.format("Key: %d, Value: %s", node.key, node.value));
        inorder(node.right);
    }
}
