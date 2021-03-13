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

    public Node parentOf(int v) {
        Node parent = null;
        Node curr = root;
        while (curr != null) {
            if (v < curr.value) {
                parent = curr;
                curr = curr.left;
            } else if (v > curr.value) {
                parent = curr;
                curr = curr.right;
            } else {
                return parent;
            }
        }
        return parent;
    }

    private Node find(int v) {
        Node curr = root;
        while (curr != null) {
            if (v < curr.value) {
                curr = curr.left;
            } else if (v > curr.value) {
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return curr;
    }

    public Integer findMin() {
        Node min = findMin(root);
        return (min != null) ? min.value : null;
    }

    private Node findMin(Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Integer findMax() {
        Node max = findMax(root);
        return (max != null) ? max.value : null;
    }

    private Node findMax(Node node) {
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void insert(int v) {
        Node newNode = new Node(v);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = parentOf(v);
            if (parent.left == null && v < parent.value) {
                parent.left = newNode;
                return;
            } else if (parent.right == null) {
                parent.right = newNode;
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
                curr = curr.left;
            } else if (v > curr.value) {
                parent = curr;
                curr = curr.right;
            }
        }

        if (curr == null || curr.value != v) {
            return false;
        }

        if (curr.left == null) {
            if (parent.left == curr) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
            return true;
        }

        if (curr.right == null) {
            if (parent.left == curr) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
            return true;
        }

        // if (curr.left != null && curr.right != null)
        Node next = curr.left;
        Node nextParent = curr;
        while (next != null && next.right != null) {
            nextParent = next;
            next = next.right;
        }

        // update current node to next node value
        curr.value = next.value;

        // remove next node from it's parent
        if (nextParent.left == next) {
            nextParent.left = null;
        } else {
            nextParent.right = null;
        }
        return true;
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
        bt.insert(1);
        bt.insert(7);
        bt.insert(2);
        bt.insert(9);
        bt.insert(6);
        // 1, 2, 3, 4, 5, 6, 7, 9
        System.out.println("bt.remove(6): " + bt.remove(6));
        System.out.println("bt.remove(6): " + bt.remove(6));
        // 1, 2, 3, 4, 5, 7, 9
        System.out.println("bt.remove(5): " + bt.remove(5));
        System.out.println("bt.remove(5): " + bt.remove(5));
        // 1, 2, 3, 4, 7, 9
        System.out.println("bt.remove(12): " + bt.remove(12));
        System.out.println("bt.remove(1): " + bt.remove(1));
        System.out.println("bt.remove(1): " + bt.remove(1));
        // 2, 3, 4, 7, 9
        System.out.println(bt.printTree(bt.root, "inOrder"));
        // System.out.println(bt.printTree(bt.root, "preOrder"));
        // System.out.println(bt.printTree(bt.root, "postOrder"));
        // bt.printTree(bt.root, "inOrder");
        // bt.printTreePreOrder(bt.root);
        // bt.printTreePostOrder(bt.root);
    }
}
