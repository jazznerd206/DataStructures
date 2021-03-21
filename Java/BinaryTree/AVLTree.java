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
    private AVLNode remove(int x, AvlNode<AnyType> t) {
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
    // base case
    // isAVLTreeHelper(Node,arg1, args2, etc) // recursive helper

    // each node, if has two children, make sure theyre valid
    // valid binary
    // without using the height prop or method, get the height of the left and right
    // subtrees
    // Depth first search

    public static void main(String[] args) {
        System.out.println("Nothing here yet.");
    }
}
