class Node {
    constructor(value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree {
    constructor() {
        this.root = null;
    }
    insert(val) {
        let newNode = new Node(val)
        let root = this.root
        if (!root) {
            root = newNode;
            return this;
        }
        let current = this.root;
        while(true) {
            if (value < current.value) {
                if(current.left === null) {
                    current.left = newNode;
                    return this;
                }
                current = current.left
            } else if (value > current.value) {
                if(current.right === null) {
                    current.right = newNode;
                    return this;
                }
                current = current.right
            } 
        }
    }
    find(val) {
        if (!this.root) return false;
        let found = false;
        let root = this.root;
        while (root && !found) {
            if (value < root.value) {
                root = root.left;
            } else if (value > root.value) {
                root = root.right;
            } else {
                found = true;
            }
        }
        if (!found) return undefined;
        return root;
    }
    contains(val) {
        if (!this.root) return false;
        let found = false;
        let root = this.root;
        while (root && !found) {
            if (value < root.value) {
                root = root.left;
            } else if (value > root.value) {
                root = root.right;
            } else {
                found = true;
            }
        }
        return false;
    }
    BFS() {
        let data = [];
        let queue = [];
        let node = this.root;
        queue.push(node);
        while(queue.length) {
            node = queue.shift();
            data.push(node);
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        return data;
    }
    DFS() { // preorder, recursively
        let data = [];
        function traverse(node) {
            data.push(node);
            if (node.left) traverse(node.left);
            if (node.right) traverse(node.right);
        }   
        traverse(this.root)
        return data;
    }
    DFS() { // post order
        let data = [];
        function traverse(node) {
            data.push(node);
            if (node.left) traverse(node.left);
            if (node.right) traverse(node.right);
        }   
        traverse(this.root)
        return data;
    }
    FS() { // in order
        let data = [];
        function traverse(node) {
            if (node.left) traverse(node.left);
            data.push(node);
            if (node.right) traverse(node.right);
        }   
        traverse(this.root)
        return data;
    }
}