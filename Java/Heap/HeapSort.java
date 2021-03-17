public class HeapSort {

    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    void heapify(int data[], int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && data[leftChild] > data[largest])
            largest = leftChild;

        if (rightChild < n && data[rightChild] > data[largest])
            largest = rightChild;

        if (largest != i) {
            int temp = data[i];
            data[i] = data[largest];
            data[largest] = temp;

            heapify(data, n, largest);
        }
    }
}
