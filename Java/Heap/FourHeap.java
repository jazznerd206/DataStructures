import java.util.Arrays;
import java.util.Random;

public class FourHeap {
    int size;
    Integer[] data;
    int chCnt;

    public FourHeap() {
        this.size = 0;
        this.data = new Integer[8];
        this.chCnt = 4;
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
        // System.out.println("bitch y tho");
        int pidx = (index - 1) / chCnt;
        int lastEl = data[index];
        while (index >= 1 && lastEl < data[pidx]) {
            data[index] = data[pidx];
            index = pidx;
            pidx = (pidx - 1) / chCnt;
        }
        data[index] = lastEl;
    }

    public int pop() {
        System.out.println("bang bang");
        if (isEmpty()) {
            throw new IllegalStateException("No crayons in this box.");
        }
        int value = data[0].intValue();
        size--;
        data[0] = data[size];
        percolateDown(0);
        return value;
    }

    public void percolateDown(int idx) {
        int temp = data[idx];
        int minChildIndex = getMinChild(idx * chCnt + 1, idx * chCnt + chCnt);
        System.out.println(minChildIndex);
        while (minChildIndex < size && data[minChildIndex] < data[idx]) {
            data[idx] = data[minChildIndex];
            idx = minChildIndex;
            minChildIndex = getMinChild(minChildIndex * chCnt + 1, minChildIndex * chCnt + chCnt);
        }
        data[idx] = temp;
    }

    public int getMinChild(int from, int to) {
        int minChild = from;
        for (int i = from + 1; (i <= to && i < chCnt); i++) {
            if (data[minChild] > data[i]) {
                minChild = i;
            }
        }
        return minChild;
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
        FourHeap fh = new FourHeap();
        Random rand = new Random();
        int[] arr = new int[rand.nextInt(21) + 5];
        for (int i = 0; i < arr.length; i++) {
            Integer n = rand.nextInt(Integer.MAX_VALUE);
            fh.push(n);
            arr[i] = n.intValue();
        }
        for (int i = 0; i < fh.data.length; i++) {
            System.out.println(" : " + fh.data[i]);
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(fh.pop());
        for (int i = 1; i < arr.length; i++) {
            sb.append(" : ");
            sb.append(fh.pop());
        }
        sb.append(']');
        System.out.println("heap: " + sb.toString());
        System.out.println("array: " + Arrays.toString(arr));
    }
}