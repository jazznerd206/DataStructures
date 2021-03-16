import java.util.NoSuchElementException;

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
        if (size + 1 > data.length / 2) {
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
                System.out.println("bubble break");
                break;
            } else {
                System.out.println("swap");
                swap(pidx, index);
                percolateUp(pidx);
            }
        }
        // if (index == 1 || data[pidx] < data[index]) {
        // return;
        // }
    }

    // pop()
    public int pop() {
        if (size == 0) {
            throw new NoSuchElementException("no such element");
        }
        int value = data[0];
        int i = 0;
        while (i < size) {
            data[i] = data[i + 1];
            i++;
        }
        size--;
        System.out.println("Value: " + value);
        return value;
    }

    public int extractMin() {
        int value = data[size];
        size--;
        return value;
    }

    // percolate down
    public void percolateDown(int index) {
    }

    // swap
    public void swap(int l, int r) {
        int temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }

    // grow
    public void grow() {
        System.out.println("grow");
        Integer[] newData = new Integer[data.length * 2];
        for (int i = 0; i < size + 1; i++) {
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
        bh.push(-400);
        bh.print();
        bh.push(66);
        bh.print();
        bh.push(2);
        bh.print();
        bh.push(-8);
        bh.print();
        bh.push(1);
        bh.print();
        bh.push(90);
        bh.print();
        bh.push(31);
        bh.print();
        bh.push(17);
        bh.print();
        bh.push(5);
        bh.print();
        bh.pop();
        bh.print();
        bh.pop();
        bh.print();
        // bh.pop();
        // bh.pop();
    }
}