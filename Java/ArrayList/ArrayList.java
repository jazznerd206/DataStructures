import java.lang.Math;
import java.util.Arrays;

class ArrayList {
    static String[] table;
    int size;

    ArrayList() {
        table = new String[8];
        size = 0;
    }

    // insert
    public void add(int index, String element) {
        checkIndex(index);
        growCheck();
        String currEl = table[index];
        if (currEl == null) {
            table[index] = element;
            size++;
        } else if (currEl != element) {
            System.out.printf("current element @ index %s does not equal input element %s", index, element);
            System.out.println("this is where the index shifting will go");
        }
        return;
    }

    // remove

    // get
    public String get(int index) {
        return table[index];
    }

    // adjust index

    // grow()
    public void growCheck() {
        int maxCap = table.length;
        if (size == maxCap) {
            int newSize = (int) Math.floor(maxCap * 1.61);
            // System.out.println("===================");
            // System.out.println(newSize);
            // System.out.println("===================");
            table = Arrays.copyOf(table, newSize);
        }
    }

    public void checkIndex(int index) {
        if (index > table.length || index < 0) {
            throw new IndexOutOfBoundsException(index + "");
        }
    }

    public void clear() {
        table = new String[8];
        size = 0;
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        sb.append("list: ");
        for (String i : table) {
            if (counter < size) {
                sb.append(i);
                sb.append(" - ");
                counter++;
            } else {
                sb.append(i);
                counter = 0;
            }
        }
        sb.append(".");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        System.out.println("1. Init ArrayList");
        System.out.printf("size -- 0/%s filled \n", table.length);
        System.out.println("===================");
        list.add(0, "string");
        list.add(1, "string2");
        list.add(2, "string3");
        list.add(3, "string4");
        list.add(4, "string5");
        list.add(5, "string6");
        System.out.println("2. add 6 items to list");
        System.out.printf("size -- 6/%s filled \n", table.length);
        System.out.println("===================");
        list.add(6, "string7");
        list.add(7, "string8");
        list.add(8, "string9");
        System.out.println("3. add 3 items to list, call grow(), length 8 -> 12");
        System.out.printf("size -- 9/%s filled \n", table.length);
        System.out.println("===================");
        list.add(6, "non matching string");
        System.out.println("4. add non matching string to list at existing index");
        System.out.printf("size -- 9/%s filled \n", table.length);
        System.out.println("===================");
        System.out.println("print list");
        System.out.println(list.printList());
        System.out.println("===================");
        System.out.println("clear ArrayList");
        list.clear();
        System.out.println("===================");
        System.out.println("print list");
        System.out.println(list.printList());
        System.out.println("===================");
    }
}