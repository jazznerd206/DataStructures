
class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    // inner class
    private class Node {
        String data; // element
        Node next; // next pointer
        // Node prev; // previous pointer
    };

    // initialize linked list with null head, null tail and size zero
    LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // HELPER FUNCTION
    // throws index out of bounds exception, otherwise pass
    public void checkIndex(int index, int low, int high) {
        if (index > high || index < low) {
            System.out.println("Out of bounds");
            throw new IndexOutOfBoundsException(index + "");
        } else {
            System.out.println("Index valid");
        }
    }

    // HELPER FUNCTION
    // traverse list, return Node
    public Node traverse(int index) {
        // grab the head
        Node current = head;
        // loop until the index before the one you want to insert
        for (int i = 0; i < index - 1; ++i) {
            current = current.next;
        }
        return current;
    }

    // ADD AT METHOD
    public void addAt(int index, String element) {

        // HELPER FUNCTION
        checkIndex(index, 0, size);

        // create new node, append passed element to data param
        Node newNode = new Node();
        newNode.data = element;

        // if the list is empty, set the head to the new node
        // set the tail to the head since there is only 1 element
        // increment size
        if (size() == 0) {
            head = newNode;
            tail = head;
            size++;
            // if index is zero, we are creating a new head
            // grab the value of the head and set it to newNode.next
            // set newNode to the new head
            // increment size
        } else if (index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
        }
        ;

        // HELPER FUNCTION
        Node current = traverse(index);
        // set newNode.next to "previous".next
        newNode.next = current.next;
        // set '"previous'.next to newNode
        current.next = newNode;
        // increment size
        size++;
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
        list.addAt(0, "string");
        list.addAt(1, "string");
        list.addAt(2, "string");
        System.out.println(list.toString());
    }
}