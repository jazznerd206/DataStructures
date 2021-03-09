public class MergeSort {
    // TWO FUNCTIONS
    // -- first function
    // recursively split the array down into blocks of one or zero length sub arrays
    // -- second function
    // merge arrays back together in sorted order

    // FIRST FUNCTION
    private void Sort(T[] a, int l, int r) {
        if (left < right) {
            int p = l + (r - l) / 2;
            Sort(a, l, p);
            Sort(a, p + 1, r);
            // Merge(a, l, p, r)
        }
    }

    private static void Merge(T[] a, int l, int p, int r) {
        int length = r - l + 1;
        T[] temp = (T) new Comparable(length);
        int i = l;
        int j = p + 1;
        int k = 0;

        while (i <= p && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k] = arr[j];
                k++;
                j++;
            }
        }

        while (i < p) {
            temp[k] = arr[i];
            k++;
            i++;
        }

        while (j < r) {
            temp[k] = arr[j];
            k++;
            j++;
        }
    }
}
