public class AllArrayTree {
    private Node root;
    private Node[] data;

    public AllArrayTree() {
        root = null;
        data = new Node[8];
    }

    private class Node {
        public int value;
        public Node[] children;

        // CONSTRUCTOR
        public Node(int v) {
            value = v;
            children = new Node[2];
        }
    }

    // INSERT
    public boolean insert(int v) {
        Node newNode = new Node(v);
        if (data[0] == null) {
            data[0] = newNode;
        }
        int index = 0;
        boolean found = false;
        while (found == false) {
            // if (data[index * 2 + 1] == null || data[index * 2 + 2] == null)
            if (data[index] == null) {
                index = index - 1;
                found = true;
            } else {
                index++;
            }
        }
        System.out.println("index " + index);
        // left child, smaller, 2i+1
        if (v < data[index].value) {
            int insertAt = 2 * index + 1;
            data[insertAt] = newNode;
        }
        // right child, larger, 2i+2
        else if (v > data[index].value) {
            int insertAt = 2 * index + 2;
            data[insertAt] = newNode;
        }
        return true;
    }

    public Node[] findChild(int index) {
        Node[] children = new Node[2];
        children[0] = data[2 * index + 1];
        children[1] = data[2 * index + 2];
        return children;
    }

    // PRINT
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("AllArrayTree --> \n");
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                if (i == data.length - 1) {
                    sb.append(data[i].value);
                    sb.append(" <-- End of AllArrayTree.\n");
                } else {
                    sb.append(data[i].value + ", \n");
                }
            } else {
                sb.append("-->NULL @ ");
                sb.append(i);
                sb.append(", \n");
            }
        }
        return sb.toString();
    }

    // MAIN
    public static void main(String[] args) {
        AllArrayTree bt = new AllArrayTree();
        bt.insert(5);
        bt.insert(1);
        bt.insert(10);
        System.out.println(bt.data);
        System.out.println(bt.print());
        Node[] arr = bt.findChild(0);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].value);
            }
        }
    }
}
