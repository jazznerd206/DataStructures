class Node {
    constructor(data, next = null) {
        this.data = data;
        this.next = next;
    }
}

class LinkedList {
    constructor() {
        this.head = null;
        this.size = 0;
    }

    // INSERT AT FIRST NODE
    insertFirst(data) {
        this.head  = new Node(data, this.head);
        this.size++;
    }

    // INSERT AT LAST NODE
    insertLast(data) {
        let node = new Node(data);
        let current;

        // IF LIST IS EMPTY (HEAD IS NULL) INSERT AT HEAD
        if(this.head === null) {
            this.head = node;
        } else {
            current = this.head;
            
            while(current.next) {
                current = current.next;
            }
            current.next = node;
        }
        this.size++;
    }

    // INSERT AT INDEX
    insertAt(data, index) {
        // if the index to insert is larger than the size of the list
        if(index > 0 && index > this.size) {
            return;
        }
        // if index is first member of list
        if (index === 0) {
            this.head = new Node(data, this.head);
            return;
        }
        // INSERT AT
        const node = new Node(data);
        let current, previous;
        current = this.head // set current to first
        let count = 0 // initialize counter

        while(count < index) {
            previous = current;
            count++;
            current = current.next;
    
        }
        node.next = current;
        previous.next = node;
        this.size++;
    }

    // GET AT INDEX
    getAt(index) {
        let current = this.head;
        let count = 0;

        while(current) {
            if(count === index) {
                console.log(current.data);
            }
            count++;
            current = current.next;
        }
        return null;
    }

    // REMOVE AT INDEX
    removeAt(index) {
        // counters and variables
        let current = this.head;
        let previous;
        let count = 0;
        // if there are no members of the list
        if(index > 0 && index > this.size) {
            return;
        }
        // if the list has only one member
        if(index === 0) {
            this.head = current.next
        } else {
            while(count < index) {
                count++;
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
        }
        this.size--;
    }

    // CLEAR LIST
    clearList() {
        this.head = null;
        this.size = 0;
    }

    // PRINT LIST DATA
    printListData() {
        let current = this.head;

        while(current) {
            console.log(current.data);
            current = current.next;
        }
    }
}

const linkedList = new LinkedList();

linkedList.insertFirst(100);
linkedList.insertLast(300);
linkedList.insertAt(200, 1)
linkedList.getAt(0);
linkedList.getAt(1);
linkedList.getAt(2);

linkedList.printListData();