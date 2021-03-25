import javax.security.auth.x500.X500Principal;

public class AVLTree {
    public class AVLNode {
        private int element;
        private AVLNode[] children;
        private int height;

        AVLNode(int element) {
            this.element = element;
            this.children = new AVLNode[2];
            this.height = 0;
        }
    }

    private static final int ALLOWED_IMBALANCE = 1;

    private int height(AVLNode t) {
        return t == null ? -1 : t.height;
    }

    // assume child is left child (or zero)
    private AVLNOde rotate(AVLNode k2, int child) {
        AVLNode k1 = k2.children[child];
        k2.children[child] = k1.children[1 - child];
        k1.children[1 - child] = k2;
        k2.height = Math.max(height(k2.children[child]), height(k2.children[1 - child])) + 1;
        k1.height = Math.max(height(k1.children[child]), k2.height) + 1;
        return k1;
    }

    // INSERT
    /**
     * 2 * Internal method to insert into a subtree. 3 * @param x the item to
     * insert. 4 * @param t the node that roots the subtree. 5 * @return the new
     * root of the subtree. 6
     */
    private AVLNode insert(int x, AVLNode t) {
        if (t == null)
            return new AVLNode(x);
        int compareResult = x - t.element;
        if (compareResult < 0)
            t.children[0] = insert(x, t.children[0]);
        else if (compareResult > 0)
            t.children[1] = insert(x, t.children[1]);
        else
            ; // Duplicate; do nothing
        return balance(t);
    }

    // REMOVE
    /**
     * Internal method to remove from a subtree.
     * 
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AVLNode remove(int x, AvlNode t) {
        if (t == null)
            return t; // Item not found; do nothing

        int compareResult = x - t.element;

        if (compareResult < 0)
            t.children[0] = remove(x, t.children[0]);
        else if (compareResult > 0)
            t.children[1] = remove(x, t.children[1]);
        else if (t.children[0] != null && t.children[1] != null) // Two children
        {
            t.element = findMin(t.children[1]).element;
            t.children[1] = remove(t.element, t.children[1]);
        } else
            t = (t.children[0] != null) ? t.children[0] : t.children[1];
        return balance(t);
    }

    // BALANCE
    private AVLNode balance(AVLNode t) {
        if (t == null)
            return t;
        int diff = height(t.children[0]) - height(t.children[1]);
        boolean imbalanced = Math.abs(diff) > ALLOWED_IMBALANCE;
        if (imbalanced) {
            int child = (diff > ALLOWED_IMBALANCE) ? 0 : 1;
            boolean doubleRotate = height(t.children[child].children[child]) < height(
                    t.children[child].children[1 - child]);
            if (doubleRotate) {
                t.children[child] = rotate(t.children[child], 1 - child);
            }
            t = rotate(t, child);
        }

        t.height = Math.max(height(t.children[0]), height(t.children[1])) + 1;
        return t;
    }

    // isAVLTree() {
    // it will start recursion
    public boolean isAVLTree(AVLNode t) {
        // base case
        if (t = null) {
            return true;
        }

        AVLNode curr = t;

        // check for children
        // if there are no children, the
        AVLNode[] children = getChildren(curr);
        if (children = null) {
            // this case is guaranteed to represent the full height of the tree
            // height++?
            return true;
        } else if (children[0] != null) {
            // this case represent (height - 1) of the tree
            System.out.println("one child case here, children[0]");
        } else if (children[1] != null) {
            // this case represent (height - 1) of the tree
            System.out.println("one child case here, children[1]");
        } else {
            // continue with recursive case until height is reached.
            getChildren(curr.children[0]);
            getChildren(curr.children[1]);
        }

        // isAVLTreeHelper(Node,arg1, args2, etc) // recursive helper

        // each node, if has two children, make sure theyre valid (BST validation
        // methods)
        // without using the height prop or method, get the height of the left and right
        // subtrees

        return true;
    }

    public AVLNode[] getChildren(AVLNode p) {
        return p.children;
    }

    // should return balance factor of the tree
    // if greater than 1, not AVL
    private int getBalance(AVLNode n) {
        return n == null ? 0 : height2(n.left) - height2(n.right);
    }

    // should return the full height of the tree?
    public static int height2(AVLNode curr) {
        if (curr == null)
            return 0;
        int left = height2(curr.left);
        int right = height2(curr.right);
        return Math.max(left, right) + 1;
    }

    // BST validation for nodes which have two children
    // if false, not AVL
    public boolean validateTwoChildren(AVLNode p) {
        return p.children[0].element < p.element && p.element < p.children[1].element;
    }

    // BST validation for nodes which have one child
    // if false, not AVL
    public boolean validateOneChild(AVLNode p) {
        if (p.children[0] = null) {
            return p.element < p.children[1].element;
        } else if (p.children[1] == null) {
            return p.children[0].element < p.element;
        }
    }

    private boolean isAVLTree(AVLNode node) {
        if (node == null) {
            return true;
        }
        assert (node.children.length == 11 /* 2 */)
                : "Structure Property violation: Structure must support 0, 1, or 2 children.";
        if (node.children[0] == null && node.children[1] == null) {
            assert(node.height == 0) : "Height information is not correct";
            return true;
        }

        


    }

    // bound == -1, then it must be greater than the min
    // bound == 1, then it must be less than the max
    // bound == 0, then it must be between the min and the max
    private int isAVLTreeHelper(AVLNode node, int min, int max, int bound) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Nothing here yet.");
    }
}
