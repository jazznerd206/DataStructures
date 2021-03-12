public class BinArrayTree {
    private Node root;

    // CONSTRUCTOR
    public BinArrayTree() {
        root = null;
    }

    private class Node {
        public int value;
        public Node parent;
        public Node[] children;

        /*
         * // V2: replace left and right child pointers with an array of size 2 public
         * Node[] children; where children is of size 2, and 0 index represents left and
         * 1 represents right https://en.wikipedia.org/wiki/Ternary_tree
         * https://en.wikipedia.org/wiki/M-ary_tree
         */

        // CONSTRUCTOR
        public Node(int v) {
            value = v;
            parent = null;
            children = new Node[2];
        }
    }

    // traverse
    public Node traverseTo(int v) {
        Node c = root;
        while (c != null) {
            if (v < c.value) {
                if (c.children[0] == null)
                    return c;
                c = c.children[0];
            } else if (v > c.value) {
                if (c.children[1] == null)
                    return c;
                c = c.children[1];
            } else {
                return c;
            }
        }
        return null;
    }

    // return leftmost child from the right side
    // this will essentially be the next lowest number in the tree
    public Node findNextLowest(Node n) {
        if (n.children[1] == null)
            return n;
        Node current = n.children[1];
        Node parent = n.children[1];
        while (current != null) {
            parent = current;
            current = current.children[0];
        }
        return parent;
    }

    // return rightmost child from the left side
    // this will essentially be the next highest number in the tree
    public Node findNextHighest(Node n) {
        if (n.children[0] == null)
            return n;
        Node current = n.children[0];
        Node parent = n.children[0];
        while (current != null) {
            parent = current;
            current = current.children[1];
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
                parent.children[0] = newNode;
                parent.children[0].parent = parent;
                return;
            } else {
                parent.children[1] = newNode;
                parent.children[1].parent = parent;
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
        if (current.children[1] == null && current.children[0] == null) {
            // sanity check
            if (current.value != v)
                return false;
            // if the current node is the root node, set the root to null
            if (current == root) {
                root = null;
            } else if (current.parent.value < current.value) {
                // if the parent value is less than the current value, sever the connection to
                // the right child
                current.parent.children[1] = null;
            } else {
                // else sever the connection to the left child
                current.parent.children[0] = null;
            }
            return true;
        }

        // IF THERE ARE TWO CHILDREN
        // the correct thing: grab the right-most node from the left child subtree OR
        // grab the left-most node formthe right child subtree
        if (current.children[1] != null && current.children[0] != null) {

            // OBTAIN REPLACEMENT - go right once and then turtles all the way down
            // nextHighest should have no left children since it's the bottom of the tree
            Node nextHighest = findNextHighest(current);

            // change the left node data first
            nextHighest.children[0] = current.children[0];
            // change parent data
            nextHighest.children[0].parent = nextHighest;

            // edge case, next highest in the tree has a right child
            if (nextHighest.children[1] != null) {
                // FROM THE REPLACEMENT NODE
                // go one node down to the right, change parent reference
                nextHighest.children[1].parent = nextHighest.parent;
                // go one node up, change left node reference
                nextHighest.parent.children[0] = nextHighest.children[1];
                // go to node down to the right, change value
                nextHighest.children[1] = current.children[1];
                // go to node down to the right, change parent reference
                nextHighest.children[1].parent = nextHighest;
            } else {
                // else other case
                nextHighest.parent.children[0] = null;
                nextHighest.children[1] = current.children[1];
                nextHighest.children[1].parent = nextHighest;
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
                    current.parent.children[1] = nextHighest;
                else
                    // create a new connection on the left side
                    current.parent.children[0] = nextHighest;
                return true;
            }

            // if there is only one child
        } else {
            if (current.children[1] != null) {
                // root check
                if (current == root) {
                    root = current.children[1];
                    return true;
                }

                current.children[1].parent = current.parent;

                // SAME CONDITIONAL TO ASSIGN NEW CONNECTIONS BETWEEN PARENT AND CHILD AFTER
                // REMOVAL
                if (current.value < current.parent.value)
                    current.parent.children[0] = current.children[1];
                else
                    current.parent.children[1] = current.children[1];
                return true;
            } else {
                if (current == root) {
                    root = current.children[0];
                    return true;
                }

                current.children[0].parent = current.parent;

                // SAME CONDITIONAL TO ASSIGN NEW CONNECTIONS BETWEEN PARENT AND CHILD AFTER
                // REMOVAL
                if (current.value < current.parent.value)
                    current.parent.children[0] = current.children[0];
                else
                    current.parent.children[1] = current.children[0];
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
                printTree(root.children[0], type);
                System.out.println((root.value + " "));
                printTree(root.children[1], type);
            } else if (type == "preOrder") {
                System.out.println((root.value + " "));
                printTree(root.children[0], type);
                printTree(root.children[1], type);
            } else if (type == "postOrder") {
                printTree(root.children[0], type);
                printTree(root.children[1], type);
                System.out.println((root.value + " "));
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        BinArrayTree bt = new BinArrayTree();
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
