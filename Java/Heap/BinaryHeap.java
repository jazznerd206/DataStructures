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
    public boolean push(int value) {
        size++;
        data[size] = value;
        percolateUp(size);
        grow();
        return true;
    }

    // pop()
    public int pop() {
        if (size == 0) {
            throw new NoSuchElementException("no such element");
        }
        int value = data[size];
        data[size] = null;
        size--;
        return value;
    }

    // percolate (bubble) down

    // percolate (bubble) up
    public void percolateUp(int index) {
        // System.out.println("Michael Buble");
        int pidx = index / 2;
        if (index == 1 || data[pidx] < data[index]) {
            return;
        }
        System.out.println("swap");
        swap(index, pidx);
        percolateUp(pidx);
    }

    // swap
    public void swap(int l, int r) {
        int temp = data[l];
        data[l] = data[r];
        data[r] = temp;
    }

    // grow
    public void grow() {
        if (size > data.length / 2) {
            System.out.println("grow");
            Integer[] newData = new Integer[data.length * 2];
            for (int i = 0; i < size + 1; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    // print
    public void print() {
        for (int i = 0; i < this.data.length; i++) {
            System.out.printf("%d, ", this.data[i]);
        }
        System.out.println("");
    }

    // return the index of the left child
    public static int leftChild(int i) {
        return 2 * i + 1;
    }

    // return the index of the right child
    public static int rightChild(int i) {
        return 2 * i + 2;
    }

    // main
    public static void main(String[] args) {
        BinaryHeap bh = new BinaryHeap();
        bh.push(4);
        bh.print();
        bh.push(6);
        bh.print();
        bh.push(2);
        bh.print();
        bh.push(8);
        bh.print();
        bh.push(1);
        bh.print();
        bh.push(9);
        bh.print();
        bh.push(3);
        bh.print();
        bh.push(7);
        bh.print();
        bh.push(5);
        bh.print();
        // bh.pop();
        // bh.pop();
        // bh.pop();
        // bh.pop();
    }

}