// CREATING A QUEUE WITH AN ARRAY
// easiest way
// use the array and make it abide by FIFO rules
// using unshift and shift exclusively on an array creates a queue

// CREATING AN QUEUE FROM SCRATCH
class Queue {
    constructor() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    queue(val) {
        let newNode = new Node(val);
        if (!this.first) {
            this.first = newNode;
            this.last = newNodel;
        }
        this.last.next = newNode;
        this.last = newNode;
        this.size++;
        return this;
    }
    dequeue() {
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