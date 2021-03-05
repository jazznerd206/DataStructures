class ArrayList {
    String[] table;
    int size;

    ArrayList() {
        table = new String[8];
        size = 0;
    }

    // insert
    public void add(int index, String element) {
        System.out.printf("%s!%n", element);
    }

    // remove

    // get
    public String get(int index) {
        return table[index];
    }

    // adjust index

    // grow()
    // goes both ways

    // MAIN()
    public static void main(String[] args) {
        System.out.println("===================");
        System.out.println("Init ArrayList");
        System.out.println("size -- 0/7 filled");
        ArrayList list = new ArrayList();
        System.out.println("===================");
        System.out.println("print list");
        System.out.println(list.get(0));
        System.out.println("===================");

    }
}