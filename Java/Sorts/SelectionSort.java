public class SelectionSort {

    public <T extends Comparable<T>> T[] sort(T[] a) {
        int l = a.length;
        for (int i = 0; i < l - 1; i++) {
            int mIndex = i;
            for (int j = i + 1; j < l; j++) {
                System.out.printf("%s, %s, %s\n", mIndex, a[j], a[mIndex]);
                printArray(a);
                if (a[j].compareTo(a[mIndex]) < 0)
                    mIndex = j;
            }
            if (mIndex != i) {
                T k = a[mIndex];
                a[mIndex] = a[i];
                a[i] = k;
            }
        }
        return a;
    }

    public static <T extends Comparable<T>> void printArray(T[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " : ");
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
        SelectionSort selectionSort = new SelectionSort();
        Integer[] sorted = selectionSort.sort(arr);
        printArray(sorted);
    }
}
