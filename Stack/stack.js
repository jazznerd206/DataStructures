// CREATING A STACK WITH AN ARRAY
// easiest way
// use the array and make it abide by LIFO rules
// using push and pop exclusively on an array creates a stack
// using unshift and shift exclusively on an array creates a queue


// BULDING FROM SCRATCH
class Node {
    constructor(val) {
        this.val = val;
        this.next = null;
    }
}

class Stack {
    constructor() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    push(val) {
        let newNode = new Node(val)
        if (!this.first) {
            this.first = newNode;
            this.last = this.first
        } else {
            newNode.next = this.first;
            this.first = newNode;
        }
        this.size++;
        return this;
    }
    pop() {
        if (!this.first) return null;
        let current = this.first;
        if (this.first === this.last) {
            this.last = null;
        }
        this.first = this.first.next;
        this.size--;
        return current.value;
    }
}