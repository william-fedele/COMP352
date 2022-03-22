package collections;


public class BinarySearchTree implements ADT {

    /**
     * Helper class to store data along with children.
     */
    public static class TreeNode {
        public Entry element;
        public TreeNode left;
        public TreeNode right;

        public int getKey() {return element.getKey();}
        public void setKey(int key) {element.setKey(key);}
        public String getValue() {return element.getValue();}
        public void setValue(String value) {element.setValue(value);}
    }

    public TreeNode root;
    private int size = 0;

    /**
     * Creates a Node object given a key and value
     * @param key Identifier for an entry
     * @param value Data associated with the key
     * @return Node ready to use in the BST
     */
    public TreeNode createNode(int key, String value) {
        TreeNode n = new TreeNode();
        n.element = new Entry();
        n.setKey(key);
        n.setValue(value);
        return n;
    }
    public TreeNode createNode(Entry entry) {
        TreeNode n = new TreeNode();
        n.element = entry;
        return n;
    }

    /**
     * Insert a node into the BST.
     * BST insertion chooses one path, left or right, at every node.
     * Complexity: O(h) where h = logn often times, but h = n is possible depending on the data.
     * @param key Identifier for an entry
     * @param value Data associated with the key
     */
    @Override
    public String insert(int key, String value) {
        TreeNode newNode = createNode(key, value);
        String oldValue = null;
        if(root == null) {
            root = newNode;
            size++;
            return null;
        }
        TreeNode curr = root;
        TreeNode prev = null;

        while(curr != null) {
            prev = curr;

            // Do nothing if they key is already present
            if(key == curr.getKey()) {
                oldValue = curr.getValue();
                curr.setValue(value);
                break;
            }

            else if(key < curr.getKey())
                curr = curr.left;
            else
                curr = curr.right;
        }

        if(key < prev.element.getKey())
            prev.left = newNode;
        else
            prev.right = newNode;

        size++;
        return oldValue;
    }

    /**
     * Get the value for a given key
     * Performs a binary search to find the location of the key in the BST.
     * Complexity: O(h) where h=logn often times, but h=n is possible depending on the data.
     * @param key Identifier for an entry
     * @return The string value associated with the key
     */
    @Override
    public String get(int key) {
        TreeNode current = root;
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

    /**
     * Deletes a node given a key
     * Performs a binary search to find the location of the key in the BST
     * If node has no children, sever the connection with the parent.
     * If node has 1 child, replace the connection to the parent with the node's child.
     * If node has 2 children, perform inorder traversal to find the smallest number on the right path as a replacement
     * @param key Identifier for an entry
     * @return The node being deleted
     */
    @Override
    public String remove(int key) {
        // do nothing if tree is empty
        if(root == null)
            return null;

        TreeNode curr = root;
        TreeNode prev = null;

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
                // delete the parent's connection to the element
                if(prev.left == curr)
                    prev.left = null;
                else
                    prev.right = null;
            }
        }
        // element has one child
        else if(curr.left == null || curr.right == null) {

            TreeNode replaceNode = (curr.left == null ? curr.right : curr.left);
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
            TreeNode successor = curr.right;
            TreeNode successorParent = null;

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
        return curr.element.getValue();
    }

    /**
     * Helper function for an inorder traversal
     * @return All keys in a sorted array
     */
    public int[] sort() {
        int[] sortedKeys = new int[size];
        inorder(root, sortedKeys, 0);
        return sortedKeys;
    }

    /**
     * Perform inorder traversal recursively
     * Complexity: O(n). Every node must be visited.
     * @param node Initialized as root. Used for traversal
     * @param sortedEntries Initialized as empty array. Accumulates the keys sorted.
     * @param index Tracks the next available index in the sorted key arrays.
     * @return Index used only within this function. Keeps track of next available index of the sorted array
     */
    public int inorder(TreeNode node, int[] sortedEntries, int index) {
        if (node == null) {return index;}
        index = inorder(node.left, sortedEntries, index);
        sortedEntries[index++] = node.getKey();
        index = inorder(node.right, sortedEntries, index);
        return index;
    }


    /**
     * Returns the current size of the tree
     * @return Current amount of entries in the BST
     */
    @Override
    public int size() {
        return size;
    }
}
