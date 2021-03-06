class ArrayList {
    static String[] table;
    static int size;

    ArrayList() {
        table = new String[8];
        size = 0;
    }

    // insert
    public void add(int index, String element) {
        indexCheck(index);
        growCheck();

        // if we are inserting at the size index
        if (index == size) {
            table[index] = element;
        } else /* (0 <= index && index < size) */ {
            // inserting between 0 and size, so we must shift elements to the right
            shiftRight(index);
            table[index] = element;
        }
        size++;
    }

    // remove
    public String remove(int index) {
        indexCheck(index);
        String element = table[index];
        table[index] = null;
        shiftLeft(index);

        // shrink function
        if (size < table.length / 4 && size > 15) {
            String[] newTable = new String[table.length / 2];
            for (int i = 0; i < size; i++) {
                newTable[i] = table[i];
            }
            table = newTable;
        }
        table[size] = null;
        size--;
        return element;
    }

    // get
    public String get(int index) {
        return table[index];
    }

    // grow
    public void growCheck() {
        if (size == table.length) {
            String[] newTable = new String[table.length << 1];
            for (int i = 0; i < size; i++) {
                newTable[i] = table[i];
            }
            table = newTable;
        }
    }

    // shift elements right
    public void shiftRight(int index) {
        for (int i = size; i > index; i--) {
            table[i] = table[i - 1];
        }
    }

    // shift elements left
    public void shiftLeft(int index) {
        for (int j = index; j < size; j++) {
            table[j] = table[j + 1];
        }
    }

    public void indexCheck(int index) {
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
        System.out.printf("size -- %s/%s filled \n", size, table.length);
        System.out.println("===================");
        list.add(0, "string");
        list.add(1, "string2");
        list.add(2, "string3");
        list.add(3, "string4");
        list.add(4, "string5");
        list.add(5, "string6");
        System.out.println("2. add 6 items to list");
        System.out.printf("size -- %s/%s filled \n", size, table.length);
        System.out.println("===================");
        list.add(6, "string7");
        list.add(7, "string8");
        list.add(8, "string9");
        System.out.println("3. add 3 items to list, call grow(), length 8 -> 12");
        System.out.printf("size -- %s/%s filled \n", size, table.length);
        System.out.println("===================");
        list.add(6, "non matching string");
        System.out.println("4. add non matching string to list at existing index");
        System.out.printf("size -- %s/%s filled \n", size, table.length);
        System.out.println("===================");
        list.remove(0);
        list.remove(0);
        list.remove(0);
        System.out.println("5. remove 3 items from list");
        System.out.printf("size -- %s/%s filled \n", size, table.length);
        System.out.println("===================");
        list.remove(0);
        list.remove(0);
        list.remove(0);
        System.out.println("6. remove 3 more items from list, activate shrink");
        System.out.printf("size -- %s/%s filled \n", size, table.length);
        System.out.println("===================");
        System.out.println("clear ArrayList");
        list.clear();
        System.out.println("===================");
        System.out.println("print list");
        System.out.println(list.printList());
        System.out.println("===================");
    }
}