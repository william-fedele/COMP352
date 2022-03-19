package collections;


public class BST {

    public static class Node {
        public Entry element;
        public Node left;
        public Node right;

        public int getKey() {return element.getKey();}
        public void setKey(int key) {element.setKey(key);}
        public String getValue() {return element.getValue();}
        public void setValue(String value) {element.setValue(value);}
    }

    public Node root;
    private int size = 0;

    public Node createNode(int key, String value) {
        Node n = new Node();
        n.element = new Entry();
        n.setKey(key);
        n.setValue(value);
        return n;
    }

    public void insert(int key, String value) {
        Node newNode = createNode(key, value);

        if(get(key) != null)
            return;

        if(root == null) {
            root = newNode;
            size++;
            return;
        }
        Node curr = root;
        Node prev = null;

        while(curr != null) {
            prev = curr;
            if(key < curr.getKey())
                curr = curr.left;
            else
                curr = curr.right;
        }

        if(key < prev.element.getKey())
            prev.left = newNode;
        else
            prev.right = newNode;

        size++;
    }

    public String get(int key) {
        Node current = root;
        while(current != null) {
            if(key == current.getKey())
                break;

            if(key < current.getKey())
                current = current.left;
            else
                current = current.right;
        }
        if(current == null)
            return null;
        else
            return current.getValue();
    }

    public Node delete(int key) {
        // do nothing if tree is empty
        if(root == null)
            return null;

        Node curr = root;
        Node prev = null;

        // check if the key exists
        if(get(key) == null)
            return null;

        // get pointers to the keys location and parent
        while(curr != null && curr.getKey() != key){
            prev = curr;
            if(key < curr.getKey())
                curr = curr.left;
            else
                curr = curr.right;
        }

        // element has no children, leaf node
        if(curr.left == null && curr.right == null) {

            if(curr == root)
                // deleting root
                root = null;
            else {
                // delete the parents connection to the element
                if(prev.left == curr)
                    prev.left = null;
                else
                    prev.right = null;
            }
        }
        // element has one child
        else if(curr.left == null || curr.right == null) {

            Node replaceNode = (curr.left == null ? curr.right : curr.left);
            // deleting root. set the node itself
            if(curr == root)
                root = replaceNode;
            else {
                // not root. replace deleted element with its only child.
                if(prev.left == curr)
                    prev.left = replaceNode;
                else
                    prev.right = replaceNode;
            }

        }
        // element has 2 children
        else {
            // perform in order traversal to find the smallest number
            // to the right of the element to be deleted
            Node successor = curr.right;
            Node successorParent = null;

            while(successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // successor is to the immediate right
            // attach delete element right tree to successor right tree
            if(successorParent == null)
                curr.right = successor.right;
            // successor has at least one node in between the deleted node
            // replace the successor parents left node with the next highest node
            else
                successorParent.left = successor.right;

            // replace the deleted node with the smallest greater element
            curr.element = successor.element;

        }
        // return the deleted node if needed
        return curr;
    }

    public int[] inorder() {
        int[] sortedKeys = new int[size];
        inorder(root, sortedKeys, 0);
        return sortedKeys;
    }
    public int inorder(Node node, int[] sortedEntries, int index) {
        if (node == null) {return index;}
        index = inorder(node.left, sortedEntries, index);
        sortedEntries[index++] = node.getKey();
        index = inorder(node.right, sortedEntries, index);
        return index;
    }
}
