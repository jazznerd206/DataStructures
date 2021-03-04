class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    // inner class
    private class Node {
        String data; // element
        Node next; // next pointer
        // Node prev; // previous pointer
    }

    LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // public boolean add(String element) {
    //     add(size, element);
    //     return true;
    // }

    public void addAt(int index, String element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        
        // create new node, append passed element to data param
        Node newNode = new Node();
        newNode.data = element;

        // if the list is empty, set the head to the new node
        // set the tail to the head since there is only 1 element
        // increment size
        if (size() == 0) {
            head = newNode;
            tail = head;
            size += 1;
        }

        // get index of node previous of index
        int counter = 0;
        int previous = index - 1;
        Node curr = head;
        // loop until you reach the previous element
        while (counter < index) {
            curr = curr.next;
            counter += 1;
        }
        
        // set newNode.next to previous.next
        newNode.next = curr.next;
        // set previous.next to newNode
        curr.next = newNode;
        // increment size
        size += 1;

        // System.out.println("----------");
        // System.out.println(curr.data);
        // System.out.println(curr.next);
        // System.out.println("----------");
        
        return;
    }

    public String remove(int index) {
        return "";
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Node curr = null;
        // add the first fence post
        if (head != null) {
            sb.append(head.data);
            curr = head.next;
        }
        while (curr != null) {
            sb.append(", ");
            sb.append(curr.data);
            curr = curr.next;
        }
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        // test our your linked list here for now
        LinkedList list = new LinkedList();
        System.out.println("Hello World!");
        list.addAt(0,"string");
        list.addAt(1,"string");
        list.addAt(2,"string");
        System.out.println(list.toString());
    }
}