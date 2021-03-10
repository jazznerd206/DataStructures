public class BinaryTree {

    private Node root;

    // CONSTRUCTOR
    public BinaryTree() {
        root = null;
    }

    private class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        // CONSTRUCTOR
        public Node(int v) {
            value = v;
            left = null;
            right = null;
            parent = null;
        }
    }

    // traverse
    public Node traverseTo(int v) {
        Node c = root;
        while (c != null) {
            if (v < c.value) {
                if (c.left == null)
                    return c;
                c = c.left;
            } else if (v > c.value) {
                if (c.right == null)
                    return c;
                c = c.right;
            } else {
                return c;
            }
        }
        return null;
    }

    // insert
    public void insert(int v) {
        Node newNode = new Node(v);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = traverseTo(v);
            if (v < parent.value) {
                parent.left = newNode;
                parent.left.parent = parent;
                return;
            } else {
                parent.right = newNode;
                parent.right.parent = parent;
                return;
            }
        }
    }

    // remove

    // rebalance

    // print utilities
    public String printTree(Node root, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("ROOT --> ");
        if (root != null) {
            if (type == "inOrder") {
                printTree(root.left, type);
                sb.append(root.value + " ");
                printTree(root.right, type);
            } else if (type == "preOrder") {
                sb.append(root.value + " ");
                printTree(root.left, type);
                printTree(root.right, type);
            } else if (type == "postOrder") {
                printTree(root.left, type);
                printTree(root.right, type);
                sb.append(root.value + " ");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.insert(4);
        bt.insert(3);
        bt.insert(5);
        bt.insert(2);
        bt.insert(1);
        bt.insert(7);
        bt.insert(2);
        bt.insert(9);
        bt.insert(1);
        bt.insert(6);
        System.out.println(bt.printTree(bt.root, "inOrder"));
        System.out.println(bt.printTree(bt.root, "preOrder"));
        System.out.println(bt.printTree(bt.root, "postOrder"));
        // bt.printTreeInOrder(bt.root);
        // bt.printTreePreOrder(bt.root);
        // bt.printTreePostOrder(bt.root);
    }
}
