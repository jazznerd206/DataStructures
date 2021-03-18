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
        // System.out.println(data[index] + " " + data[pidx]);
        while (index >= 1 && lastEl < data[pidx]) {
            data[index] = data[pidx];
            index = pidx;
            pidx = (pidx - 1) / 2;
        }
        data[index] = lastEl;
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

    public void print() {
        for (int i = 0; i < this.data.length; i++) {
            System.out.printf("%d, ", this.data[i]);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        FourHeap fh = new FourHeap();
        fh.push(5);
        fh.push(1);
        fh.push(2);
        fh.push(8);
        fh.print();
        fh.push(35);
        fh.push(15);
        fh.push(85);
        fh.push(14);
        fh.print();
        fh.push(15);
        fh.push(17);
        fh.push(86);
        fh.push(12);
        fh.print();
        // Random rand = new Random();
        // int[] arr = new int[rand.nextInt(21) + 5];
        // for (int i = 0; i < arr.length; i++) {
        // Integer n = rand.nextInt(Integer.MAX_VALUE);
        // fh.push(n);
        // arr[i] = n.intValue();
        // }
        // Arrays.sort(arr);
        // StringBuilder sb = new StringBuilder();
        // sb.append('[');
        // sb.append(fh.pop());
        // for (int i = 1; i < arr.length; i++) {
        // sb.append(" -||- ");
        // sb.append(fh.pop());
        // }
        // sb.append(']');
        // System.out.println("heap: " + sb.toString());
        // System.out.println("array: " + Arrays.toString(arr));
    }
}