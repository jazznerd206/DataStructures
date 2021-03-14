public class ArrayBinaryTree {
    private Integer[] arr;

    public ArrayBinaryTree() {
        arr = new Integer[8];
    }

    public boolean insert(int v) {
        int i = 0;
        while (arr[i] != null && i < arr.length) {
            if (v < arr[i].intValue()) {
                i = 2 * i + 1;
            } else if (v > arr[i].intValue()) {
                i = 2 * i + 2;
            } else {
                return false;
            }
        }

        if (i < arr.length) { /* arr[i] == null */
            arr[i] = v;
            return true;
        }
        grow();

        return true;
    }

}