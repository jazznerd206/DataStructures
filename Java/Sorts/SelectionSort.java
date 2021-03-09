public class SelectionSort {

    public <T extends Comparable<T>> void sort(T[] a) {
        int l = a.length - 1;
        for (int i = 0; i < l - 1; l++) {
            int mIndex = i;
            for (int j = i + 1; j < l; j++) {
                System.out.printf("%s, %s, %s\n", mIndex, a[j], a[mIndex]);
                printArray(a);
                if (a[j].compareTo(a[mIndex]) <= 0)
                    mIndex = j;
            }
            T k = a[mIndex];
            a[mIndex] = a[i];
            a[i] = k;
        }
    }

    public static <T extends Comparable<T>> void printArray(T[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " : ");
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        printArray(arr);
    }
}
