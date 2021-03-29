public class QuickSort {

    static void print(int[] arr, int size) {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    static void swap(int[] arr, int i, int j) {
        System.out.println("swap");
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int minElement = (low - 1);
        for (int i = low; i <= high - 1; i++) {
            if (arr[i] < pivot) {
                minElement++;
                swap(arr, minElement, i);
            }
        }
        swap(arr, minElement + 1, high);
        return (minElement + 1);
    }

    static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            sort(arr, low, pivot - 1);
            sort(arr, pivot + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] array = { 5, 2, 9, 7, 4, 1, 2345, 6, 3, 69, 8 };
        sort(array, 0, array.length - 1);
        print(array, array.length);
    }
}