public class BinaryTree {

    private Node root;

    // CONSTRUCTOR
    public BinaryTree() {
        root = null;
    }

    private class Node {
        public int value;
        public Node parent;

        public Node left;
        public Node right;

        /*
         * // V2: replace left and right child pointers with an array fo size 2 public
         * Node[] children; where children is of size 2, and 0 index represents left and
         * 1 represents right https://en.wikipedia.org/wiki/Ternary_tree
         * https://en.wikipedia.org/wiki/M-ary_tree
         */

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

    // return leftmost child from the right side
    // this will essentially be the next lowest number in the tree
    public Node findNextLowest(Node n) {
        if (n.right == null)
            return n;
        Node current = n.right;
        Node parent = n.right;
        while (current != null) {
            parent = current;
            current = current.left;
        }
        return parent;
    }

    // return rightmost child from the left side
    // this will essentially be the next highest number in the tree
    public Node findNextHighest(Node n) {
        if (n.left == null)
            return n;
        Node current = n.left;
        Node parent = n.left;
        while (current != null) {
            parent = current;
            current = current.right;
        }
        return parent;
    }

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

    public boolean remove(int v) {
        // just like a linked list, in order to remove a node we must set it to null and
        // remove the connections from it's neighbors
        // potential connections: parent, child left, child right

        // obtain a reference to the value to be removed
        Node current = traverseTo(v);

        // IF THERE ARE NO CHILDREAN (i.e. leaf node)
        // detach from parent
        if (current.right == null && current.left == null) {
            // sanity check
            if (current.value != v)
                return false;
            // if the current node is the root node, set the root to null
            if (current == root) {
                root = null;
            } else if (current.parent.value < current.value) {
                // if the parent value is less than the current value, sever the connection to
                // the right child
                current.parent.right = null;
            } else {
                // else sever the connection to the left child
                current.parent.left = null;
            }
            return true;
        }

        // IF THERE ARE TWO CHILDREN
        // the correct thing: grab the right-most node from the left child subtree OR
        // grab the left-most node formthe right child subtree
        if (current.right != null && current.left != null) {

            // OBTAIN REPLACEMENT - go right once and then turtles all the way down
            // nextHighest should have no left children since it's the bottom of the tree
            Node nextHighest = findNextHighest(current);

            // change the left node data first
            nextHighest.left = current.left;
            // change parent data
            nextHighest.left.parent = nextHighest;

            // edge case, next highest in the tree has a right child
            if (nextHighest.right != null) {
                // FROM THE REPLACEMENT NODE
                // go one node down to the right, change parent reference
                nextHighest.right.parent = nextHighest.parent;
                // go one node up, change left node reference
                nextHighest.parent.left = nextHighest.right;
                // go to node down to the right, change value
                nextHighest.right = current.right;
                // go to node down to the right, change parent reference
                nextHighest.right.parent = nextHighest;
            } else {
                // else other case
                nextHighest.parent.left = null;
                nextHighest.right = current.right;
                nextHighest.right.parent = nextHighest;
            }

            // if the current value is the root (which has 2 children)
            if (current == root) {
                nextHighest.parent = null;
                root = nextHighest;
                return true;
            } else {
                // you are deleting a node which is not the root
                nextHighest.parent = current.parent;

                // if the parent value is lower than the current value
                if (current.parent.value < current.value)
                    // create a new connection on the right side
                    current.parent.right = nextHighest;
                else
                    // create a new connection on the left side
                    current.parent.left = nextHighest;
                return true;
            }

            // if there is only one child
        } else {
            if (current.right != null) {
                // root check
                if (current == root) {
                    root = current.right;
                    return true;
                }

                current.right.parent = current.parent;

                if (current.value < current.parent.value)
                    current.parent.left = current.right;
                else
                    current.parent.right = current.right;
                return true;
            } else {
                if (current == root) {
                    root = current.left;
                    return true;
                }

                current.left.parent = current.parent;

                if (current.value < current.parent.value)
                    current.parent.left = current.left;
                else
                    current.parent.right = current.left;
                return true;
            }
        }
    }

    // print utilities
    public String printTree(Node root, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("ROOT --> ");
        if (root != null) {
            if (type == "inOrder") {
                printTree(root.left, type);
                System.out.println((root.value + " "));
                printTree(root.right, type);
            } else if (type == "preOrder") {
                System.out.println((root.value + " "));
                printTree(root.left, type);
                printTree(root.right, type);
            } else if (type == "postOrder") {
                printTree(root.left, type);
                printTree(root.right, type);
                System.out.println((root.value + " "));
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
        System.out.println(bt.remove(6));
        bt.remove(5);
        System.out.println(bt.remove(5));
        bt.remove(12);
        System.out.println(bt.remove(12));
        bt.remove(1);
        System.out.println(bt.remove(1));
        System.out.println(bt.printTree(bt.root, "inOrder"));
        // System.out.println(bt.printTree(bt.root, "preOrder"));
        // System.out.println(bt.printTree(bt.root, "postOrder"));
        // bt.printTree(bt.root, "inOrder");
        // bt.printTreePreOrder(bt.root);
        // bt.printTreePostOrder(bt.root);
    }
}
