import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class BinaryHeap {

    // ========================
    // IMPLEMENTATION One:
    int size;
    Integer[] data;
    // ========================

    // ========================
    // IMPLEMENTATION Two:
    // private class Node {
    // Integer value;
    // Node[] children;

    // public Node() {
    // this.value = null;
    // this.children = new Node[2];
    // }
    // }
    // ========================

    public BinaryHeap() {
        this.size = 0;
        this.data = new Integer[8];
    }

    // push()
    public void push(int value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        percolateUp(size);
        size++;
    }

    // percolate up
    public void percolateUp(int index) {
        int pidx = index / 2;
        while (index >= 1) {
            if (data[index] > data[pidx]) {
                // System.out.println("bubble break");
                break;
            } else {
                // System.out.println("swap");
                swap(pidx, index);
                percolateUp(pidx);
            }
        }
        // if (index == 1 || data[pidx] < data[index]) {
        // return;
        // }
    }

    // pop()

    // pop() returns the top element from the heap and removes it from the heap
    // heap maintains heap property after pop
    // the first element in the heap must be the min element
    // to do this, you store the top element of the heap in a return variable
    // next, you replace the top element with the last element in the heap
    // then you percolate down
    // ret = arr[0]
    // arr[0] = arr[last element position]
    // decrement size
    // percolate down moves the top element down to where it belongs

    public int pop() {
        if (size == 0) {
            throw new NoSuchElementException("no such element");
        }
        int value = data[0];
        size--;
        data[0] = data[size];
        percolateDown(0);
        return value;
    }

    public int extractMin() {
        int value = data[size];
        size--;
        return value;
    }

    // percolate down
    public void percolateDown(int index) {
        int temp = index;
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;

        if (leftChild <= size && data[leftChild] < data[temp]) {
            temp = leftChild;
        }

        if (rightChild <= size && data[rightChild] < data[temp]) {
            temp = rightChild;
        }

        if (temp != index) {
            swap(index, temp);
            percolateDown(temp);
        }
    }

    // swap
    public void swap(int l, int r) {
        int temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }

    // grow
    public void grow() {
        // System.out.println("grow");
        Integer[] newData = new Integer[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // public static int leftChild(int i) {
    // return 2 * i + 1;
    // }

    // public static int rightChild(int i) {
    // return 2 * i + 2;
    // }

    // private int parent(int pos) {
    // return pos / 2;
    // }

    public void sort() {
        int n = data.length;

        // Build heap (rearrange array)
        // for (int i = n / 2 - 1; i >= 0; i--)
        // heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;

            // call max heapify on the reduced heap
            // heapify(sort, i, 0);
        }
    }

    // print
    public void print() {
        for (int i = 0; i < this.data.length; i++) {
            System.out.printf("%d, ", this.data[i]);
        }
        System.out.println("");
    }

    // main
    public static void main(String[] args) {
        BinaryHeap bh = new BinaryHeap();
        Random rand = new Random();
        int[] arr = new int[rand.nextInt(21) + 5];
        for (int i = 0; i < arr.length; i++) {
            Integer n = rand.nextInt(Integer.MAX_VALUE);
            bh.push(n);
            arr[i] = n.intValue();
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(bh.pop());
        for (int i = 1; i < arr.length; i++) {
            sb.append(' ');
            sb.append(bh.pop());
        }
        sb.append(']');
        System.out.println("heap: " + sb.toString());
        System.out.println("array: " + Arrays.toString(arr));
    }
}