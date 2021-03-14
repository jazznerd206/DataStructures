public class BinArrayTree {
    private Node root;

    // CONSTRUCTOR
    public BinArrayTree() {
        root = null;
    }

    private class Node {
        public int value;
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
            children = new Node[2];
        }
    }

    public Node parentOf(int v) {
        Node parent = null;
        Node curr = root;
        while (curr != null) {
            if (v < curr.value) {
                parent = curr;
                curr = curr.children[0];
            } else if (v > curr.value) {
                parent = curr;
                curr = curr.children[1];
            } else {
                return parent;
            }
        }
        return parent;
    }

    public Integer findMin() {
        Node min = findMin(root);
        return (min != null) ? min.value : null;
    }

    private Node findMin(Node node) {
        while (node != null && node.children[0] != null) {
            node = node.children[0];
        }
        return node;
    }

    public Integer findMax() {
        Node max = findMax(root);
        return (max != null) ? max.value : null;
    }

    private Node findMax(Node node) {
        while (node != null && node.children[1] != null) {
            node = node.children[1];
        }
        return node;
    }

    public void insert(int v) {
        Node newNode = new Node(v);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = parentOf(v);
            if (parent.children[0] == null && v < parent.value) {
                parent.children[0] = newNode;
                return;
            } else if (parent.children[1] == null) {
                parent.children[1] = newNode;
                return;
            } /* else parent.value == v */
        }
    }

    public boolean remove(int v) {
        if (root == null) {
            return false;
        }

        Node parent = null;
        Node curr = root;

        while (curr != null && curr.value != v) {
            if (v < curr.value) {
                parent = curr;
                curr = curr.children[0];
            } else if (v > curr.value) {
                parent = curr;
                curr = curr.children[1];
            }
        }

        if (curr == null || curr.value != v) {
            return false;
        }

        if (curr.children[0] == null) {
            if (parent.children[0] == curr) {
                parent.children[0] = curr.children[1];
            } else {
                parent.children[1] = curr.children[1];
            }
            return true;
        }

        if (curr.children[1] == null) {
            if (parent.children[0] == curr) {
                parent.children[0] = curr.children[0];
            } else {
                parent.children[1] = curr.children[0];
            }
            return true;
        }

        // if (curr.children[0] != null && curr.children[1] != null)
        Node next = curr.children[0];
        Node nextParent = curr;
        while (next != null && next.children[1] != null) {
            nextParent = next;
            next = next.children[1];
        }

        // update current node to next node value
        curr.value = next.value;

        // remove next node from it's parent
        if (nextParent.children[0] == next) {
            nextParent.children[0] = null;
        } else {
            nextParent.children[1] = null;
        }
        return true;
    }

    // print utilities
    public String printTree(Node root, String type) {
        StringBuilder sb = new StringBuilder();
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
        bt.insert(1);
        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(5);
        bt.insert(6);
        bt.insert(7);
        bt.insert(9);
        // 1, 2, 3, 4, 5, 6, 7, 9

        // System.out.println("bt.remove(6): " + bt.remove(6));
        // System.out.println("bt.remove(6): " + bt.remove(6));
        // 1, 2, 3, 4, 5, 7, 9
        // System.out.println("bt.remove(5): " + bt.remove(5));
        // System.out.println("bt.remove(5): " + bt.remove(5));
        // 1, 2, 3, 4, 7, 9
        // System.out.println("bt.remove(12): " + bt.remove(12));
        // System.out.println("bt.remove(1): " + bt.remove(1));
        // System.out.println("bt.remove(1): " + bt.remove(1));
        // 2, 3, 4, 7, 9
        System.out.println(bt.printTree(bt.root, "inOrder"));
    }
}
