import java.util.Arrays;
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

    public void push(int value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        percolateUp(size);
        size++;
    }

    public void percolateUp(int index) {
        int pidx = index / 2;
        while (index >= 1) {
            if (data[index] > data[pidx]) {
                break;
            } else {
                swap(pidx, index);
                percolateUp(pidx);
            }
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("You cannot pop from an empty heap");
        }
        int value = data[0];
        size--;
        data[0] = data[size];
        percolateDown(0);
        return value;
    }

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

    public void swap(int l, int r) {
        int temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }

    public void grow() {
        Integer[] newData = new Integer[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public static int leftChild(int i) {
        return 2 * i + 1;
    }

    public static int rightChild(int i) {
        return 2 * i + 2;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        for (int i = 0; i < this.data.length; i++) {
            System.out.printf("%d, ", this.data[i]);
        }
        System.out.println("");
    }

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
            sb.append(" -||- ");
            sb.append(bh.pop());
        }
        sb.append(']');
        System.out.println("heap: " + sb.toString());
        System.out.println("array: " + Arrays.toString(arr));
    }
}