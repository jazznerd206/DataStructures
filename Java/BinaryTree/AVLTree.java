public class AVLTree {
    public class AVLNode {
        private int element;
        private AVLNode left;
        private AVLNode right;
        private int height;

        AVLNode(int element) {
            this.element = element;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    private static final int ALLOWED_IMBALANCE = 1;

    private int height(AVLNode t) {
        return t == null ? -1 : t.height;
    }

    /**
     * 2 * Rotate binary tree node with left child. 3 * For AVL trees, this is a
     * single rotation for case 1. 4 * Update heights, then return new root. 5
     */
    private AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * 2 * Double rotate binary tree node: first left child 3 * with its right
     * child; then node k3 with new left child. 4 * For AVL trees, this is a double
     * rotation for case 2. 5 * Update heights, then return new root. 6
     */
    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * 2 * Rotate binary tree node with right child. 3 * For AVL trees, this is a
     * single rotation for case 1. 4 * Update heights, then return new root. 5
     */
    private AVLNode rotateWithrightChild(AVLNode k2) {
        AVLNode k1 = k2.right;
        k2.right = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    /**
     * 2 * Double rotate binary tree node: first right child 3 * with its right
     * child; then node k3 with new right child. 4 * For AVL trees, this is a double
     * rotation for case 2. 5 * Update heights, then return new root. 6
     */
    private AVLNode doubleWithrightChild(AVLNode k3) {
        k3.right = rotateWithRightChild(k3.right);
        return rotateWithrightChild(k3);
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
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
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
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return balance(t);
    }

    // BALANCE
    private AVLNode balance(AVLNode t) {
        if (t == null)
            return t;
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    public static void main(String[] args) {
        System.out.println("Nothing here yet.");
    }
}
