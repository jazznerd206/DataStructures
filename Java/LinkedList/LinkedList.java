class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    // inner class
    class Node {
        String data;
        Node next;
        // Node prev;

        // CONSTRUCTOR
        Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

    };

    // initialize linked list with null head, null tail and size zero
    LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // HELPER FUNCTION
    // throws index out of bounds exception, otherwise continues
    public void checkIndex(int index, int low, int high) {
        if (index > high || index < low) {
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
        // loop until the index you want to insert at
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }
        return current;
    }

    public void addFirst(String element) {
        addAt(0, element);
        return;
    }

    // ADD AT METHOD
    // add an element to a list at the index from the params
    // returns nothing
    public void addAt(int index, String element) {

        // HELPER CHECK INDEX FUNCTION (lns 31-38)
        checkIndex(index, 0, size);

        // create new node, append passed element to data param
        Node newNode = new Node(element, null);

        // if the list is empty, set the head to the new node
        // set the tail to the head since there is only 1 element
        // increment size
        if (head == null) {
            head = newNode;
            tail = head;
            size++;
            return;
            // if index is zero, we are creating a new head
            // grab the value of the head and set it to newNode.next
            // Appoggiatura
            // increment size
        } else if (head != null && index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }
        ;
        // HELPER TRAVERSE FUNCTION (lns 42-52)
        // set newNode.next to "previous".next
        // Appoggiatura
        // increment size
        Node current = traverse(index - 1);
        newNode.next = current.next;
        current.next = newNode;
        size++;
        return;
    }

    // REMOVE METHOD
    // remove element from list at index from params
    // returns the removed element as a string
    public String remove(int index) {

        // Helper checkIndex function (lns 31-38)
        checkIndex(index, 0, size - 1);

        // Initialize return variable of type Node
        // even though this is type node, we will return the data prop which is type
        // string
        Node response;

        // If index from params is zero (HEAD)
        // set return variable to the current head
        // Appoggiatura
        // decrement the size
        if (index == 0) {
            response = head;
            head = head.next;
            size--;
            return response.data;
        }
        // call helper traverse function to get to right list position
        // set the response to the current (response is the node from which we will send
        // the data)
        // Appoggiatura
        // decrement size
        Node current = traverse(index - 1);
        response = current.next;
        current.next = current.next.next;
        size--;
        return response.data;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Node curr = null;
        // FENCE POST
        // /-|-|-|-|-|-/
        // /🐑🐑🐑🐑🐑🐑 /
        // /🐑🐑🐑🐑🐑🐑 /
        // /-|-|-|-|-|-/
        if (head != null) {
            sb.append(head.data);
            curr = head.next;
        }
        // END FIRST POST
        // ===================
        // comma is place first with a space (type string because it's more than 1
        // character)
        // avoids the trailing comma!!
        while (curr != null) {
            sb.append(", ");
            sb.append(curr.data);
            curr = curr.next;
        }
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("1. Init list");
        System.out.println("==============================");
        LinkedList list = new LinkedList();
        System.out.println("==============================");
        System.out.println("list should have length of 0");
        System.out.println("PRINT LIST SIZE");
        System.out.println(list.size());
        System.out.println("PRINT LIST");
        System.out.println(list.toString());
        System.out.println("==============================");
        System.out.println("==============================");
        System.out.println("2. add three strings");
        System.out.println("==============================");
        list.addFirst("string");
        list.addAt(1, "string2");
        list.addAt(2, "string3");
        System.out.println("==============================");
        System.out.println("list should have length of 3");
        System.out.println("PRINT LIST SIZE");
        System.out.println(list.size());
        System.out.println("PRINT LIST");
        System.out.println(list.toString());
        System.out.println("==============================");
        System.out.println("==============================");
        System.out.println("3. remove from list at index 1");
        System.out.println("==============================");
        list.remove(1);
        System.out.println("==============================");
        System.out.println("list should have length of 2");
        System.out.println("PRINT LIST SIZE");
        System.out.println(list.size());
        System.out.println("PRINT LIST");
        System.out.println(list.toString());
        System.out.println("==============================");
        System.out.println("==============================");
        System.out.println("4. add to list at index 1, add new head at index 0");
        System.out.println("==============================");
        list.addAt(1, "string2 addback");
        list.addAt(0, "string addback at index 0");
        System.out.println("==============================");
        System.out.println("list length should be 4");
        System.out.println("PRINT LIST SIZE");
        System.out.println(list.size());
        System.out.println("PRINT LIST");
        System.out.println(list.toString());
        System.out.println("==============================");
        System.out.println("==============================");
        System.out.println("5. remove all from list");
        System.out.println("==============================");
        list.remove(0);
        list.remove(0);
        list.remove(0);
        list.remove(0);
        System.out.println("==============================");
        System.out.println("list length should be 0");
        System.out.println("PRINT LIST SIZE");
        System.out.println(list.size());
        System.out.println("PRINT LIST");
        System.out.println(list.toString());
        System.out.println("==============================");
    }
}