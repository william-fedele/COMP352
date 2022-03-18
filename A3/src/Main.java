import collections.BST.BST;

public class Main {

    public static void main(String[] args) {
        //CleverSIDC c = new CleverSIDC();
        BST bst = new BST();
        bst.insert(5, "five");
        bst.insert(2, "two");
        bst.insert(3, "three");
        bst.insert(8, "eight");
        bst.insert(11, "eleven");
        bst.inorder(bst.root);
        System.out.println(String.format("Finding 8: %s", bst.get(8)));
        System.out.println(String.format("Finding 7: %s", bst.get(7)));

        //System.out.println(c.generate());
    }
}
