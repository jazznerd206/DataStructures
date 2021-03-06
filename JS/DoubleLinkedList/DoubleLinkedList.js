class Node {
    constructor(data, previous, next) {
        this.data = data;
        this.previous = previous;
        this.next = null;
    }
}

class DoubleLinkedList {
    constructor() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // INSERT AT TAIL
    push(val) {
        // create new node
        const node = new Node(val);
        // if list has no members
        if(this.size === 0) {
            this.head  = node;
            this.tail  = node;
            this.size++;
        } else {
            // INSERT AT TAIL
            this.tail.next = node;
            node.previous  = this.tail;
            this.tail = node;   
        }
        this.size++;
        return node;
    }

    // INSERT AT HEAD
    unshift(val) {
        // create new node
        const node = new Node(val);
        // if list has no members, make new node head and tail
        if(this.size === 0) {
            this.head  = node;
            this.tail  = node;
            this.size++;
        } else {
            // INSERT AT HEAD
            this.head.previous = node;
            node.next   = this.head;
            this.head = node;   
        }
        this.size++;
        return node;
    }

    // INSERT AT INDEX
    insert(val, index) {
        // if the index is less than 0 or greater than the size of the list
        if(index < 0 || index > this.size) {
            return null;
        }
        // else if the index is 0, insert at the head
        else if (index === 0) {
            return this.unshift(val);
        }
        // else if the index equals the size of the list, insert at tail
        else if (index = this.size) {
            return this.push(val);
        }
        // INSERT AT INDEX
        const node = new Node(val);
        const newPreviousNode = this.get(index - 1);
        const newNextNode = newPreviousNode.next;

        // connect the new node to the previous node
        node.prev = newPreviousNode;
        newPreviousNode.next = node;

        // connect the new node to the next node
        node.next = newNextNode;
        newNextNode.prev = node;

        this.length += 1;
        return node;
    }

    // GET AT INDEX
    get(index) {
        // if size is 0
        if(this.size === 0) {
            console.log('the size of the list is 0');
            return;
        }
        // if index does not exist
        if(index > 0 && index > this.size) {
            console.log('index provided is greater than the list length')
            return;
        }
        // RETURN NODE
        let currentNode;
        if(index < this.size/2) {
            currentNode = this.head;
            let counter = 0;
            while (counter < index) {
                currentNode = currentNode.next;
                counter += 1;
            }
        } else {
            let counter = this.size - 1;
            // start from the tail
            currentNode = this.tail;
            // go to the previous node until we found our desired node
            while (counter > index) {
            currentNode = currentNode.prev;
            counter -= 1;
            }
        }
        return currentNode;
    }

    // REMOVE FROM HEAD
    shift() {
        // if list is empty return
        if (this.size === 0) {
            console.log('the size of the list is 0');
            return;
        }
        const nodeToRemove = this.head;
        // if the list has only one member, remove that member
        if(this.size === 1) {
            this.head = null;
            this.tail = null,
            this.size = 0;
        } else {
            this.head = nodeToRemove.next;

            this.head.previous = null;
            nodeToRemove.next = null;
        }
    }

    // REMOVE FROM TAIL
    pop() {
        // if list is empty return
        if (this.size === 0) {
            console.log('the size of the list is 0');
            return;
        }
        const nodeToRemove = this.tail;
        // if the list has only one member, remove that member
        if(this.size === 1) {
            this.head = null;
            this.tail = null,
            this.size = 0;
        } else {
            this.tail = nodeToRemove.previous;

            this.tail.next = null;
            nodeToRemove.previous = null;
        }
    }

    // REMOVE AT INDEX
    remove(index) {
        // if list has no members
        if(this.size === 0) {
            console.log('the size of the list is 0');
            return null
        }
        // else if the provided index is 0
        else if(index === 0 ) {
            return this.shift()
        }
        else if (index = this.size) {
            return this.pop();
        }
        // REMOVE AT INDEX
        const nodeToRemove = this.get(index);
        const prevNodeToRemove = nodeToRemove.previous;
        const nextNodeToRemove = nodeToRemove.next; 

        // remove old connections from nodes
        nodeToRemove.prev = null;
        nodeToRemove.next = null;
        
        // update connections before removal
        prevNodeToRemove.next = nextNodeToRemove;
        nextNodeToRemove.previous = prevNodeToRemove;

        this.length -= 1;
        return nodeToRemove;
    }

    // CLEAR LIST DATA
    clearList() {
        this.head = null;
        this.tail = null;
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

const doubleLinkedList = new DoubleLinkedList();
console.log('====================================')
doubleLinkedList.unshift(1);
doubleLinkedList.unshift(2);
doubleLinkedList.unshift(3);
doubleLinkedList.unshift(4);
console.log('====================================')
console.log('print first full list data')
doubleLinkedList.printListData();
console.log('====================================')
console.log('END OF LIST')
console.log('====================================')
console.log('====================================')
doubleLinkedList.insert(5, 5);
doubleLinkedList.insert(6, 6);
console.log('====================================')
console.log('add to tail list data')
doubleLinkedList.printListData();
console.log('====================================')
console.log('END OF LIST')
console.log('====================================')
console.log('====================================')
console.log('get at index 0 list data')
console.log(doubleLinkedList.get(0));
console.log('====================================')
console.log('END OF LIST')
console.log('====================================')
console.log('====================================')
doubleLinkedList.shift();
doubleLinkedList.pop();
console.log('====================================')
console.log('remove head and tail list data')
doubleLinkedList.printListData();
console.log('====================================')
console.log('END OF LIST')
console.log('====================================')
console.log('====================================')
doubleLinkedList.remove(0);
console.log('====================================')
console.log('remove at index 0 list data')
doubleLinkedList.printListData();
console.log('====================================')
console.log('END OF LIST')
console.log('====================================')
console.log('====================================')
doubleLinkedList.clearList();
console.log('====================================')
console.log('clear list data')
doubleLinkedList.printListData();
console.log('====================================')
console.log('END OF LIST')
console.log('====================================')
