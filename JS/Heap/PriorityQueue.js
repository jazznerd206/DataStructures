class Node {
    constructor(val, priority) {
        this.value = val;
        this.priority = priority;
    }
}

class PriorityQueue {
    constructor() {
        this.values = []
    }
    enqueue(val, priority) {
        let newNode = new Node(val, priority);
        this.values.push(newNode)
        this.bubbleUp()
    }
    bubbleUp() {
        let i = this.values.length - 1;
        const element = this.values[i];
        while(true) {
            let parentIdx = Math.floor((i-1)/2);
            let parent = this.values[parentIdx];
            if (element.priority <=  parent.priority) break;
            this.values[parentIdx] = element;
            this.values[i] = parent;
            i = parentIdx;
        }
    }
    extractMax() {
        let max = this.values[0];
        let end = this.values.pop();
        this.values[0] = end;
        this.sinkDown();
        return max;
    }
    sinkDown() {
        let i = 0;
        let length = this.values.length;
        let element = this.values[0]
        while(true){
            let L_C_I = (2 * i) + 1;
            let R_C_I = (2 * i) + 2;
            let leftChild, rightChild;
            let swap = null;
            if(L_C_I < length) {
                leftChild = this.values[L_C_I];
                if (leftChild.priority > element.priority) {
                    swap = L_C_I;
                }
            }
            if(R_C_I < length) {
                rightChild = this.values[R_C_I];
                if ((swap === null && rightChild.priority > element.priority) || (swap !== null && rightChild.priority > leftChild.priority)) {
                    swap = R_C_I;
                }
            }
            if (swap === null) break;
            this.values[i] = this.values[swap];
            this.values[swap] = element;
            i = swap;
        }
    }
}