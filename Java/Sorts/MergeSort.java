public class MergeSort {

    private <T extends Comparable<T>> void sort(T[] a, int l, int r) {
        if (l < r) {
            int p = l + (r - l) / 2;
            sort(a, l, p);
            sort(a, p + 1, r);
            merge(a, l, p, r);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] a, int l, int p, int r) {
        int length = r - l + 1;
        T[] temp = (T[]) new Comparable[length];
        int i = l;
        int j = p + 1;
        int k = 0;
        printArray((Integer[]) a);

        while (i <= p && j <= r) {
            if (a[i].compareTo(a[j]) <= 0) {
                temp[k] = a[i];
                i++;
            } else {
                temp[k] = a[j];
                j++;
            }
            k++;
        }

        while (i <= p) {
            temp[k] = a[i];
            i++;
            k++;
        }

        while (j <= r) {
            temp[k] = a[j];
            j++;
            k++;
        }
        System.arraycopy(temp, 0, a, l, length);
    }

    public static void checkPointer(Integer index, int low, int high) {
        if (index > high || index < low) {
            throw new NullPointerException(index + "");
        }
    }

    public void checkIndex(int index, int low, int high) {
        if (index > high || index < low) {
            throw new IndexOutOfBoundsException(index + "");
        }
    }

    /* A utility function to print array of size n */
    public static void printArray(Integer[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " : ");
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr, 0, arr.length - 1);
        printArray(arr);
    }
}
