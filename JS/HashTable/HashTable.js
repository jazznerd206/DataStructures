class HashTable {
    constructor(size) {
        /*
        NEW VARIABLE IN CONSTRUCTOR
        potential HashTable entry allocations, static variable constructed from ./FindPrimes.js
        */
        this.primes = [ 7,  11,  13,  17,  19,  23,  29,  31,  37, 41,  43,  47,  53,  59,  61,  67,  71,  73,  79,  83,  89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541 ]

        this.keyMap = new Array(this.primes[0]);

        /* 
        NEW VARIABLE IN CONSTRUCTOR
        this.elements is equal to the number of elements set into the map, a proportion of total spaces available. As the HashMap is a sparsely populated data structure, it will be necessary to grow the table size as this.elements nears it's upper bound of (keyMap.size / 2).
        */
        this.elements = 0;
    }
    _hash(key) {
        /*
        variable 'total' was initialized to zero and ln13 was the following:
        total = (total * PRIME + value) % length
        BECAUSE total was zero, this became value % length, a shitty hash
        Total is now initalized as a blank variable, and removed the multiply by zero in ln13
        */
        let total;
        let WEIRD_PRIME = 31;
        for (let i = 0; i < Math.min(key.length, 100); i++) {
            let char = key[i];
            let value = char.charCodeAt(0) - 'a'.charCodeAt(0);
            total = (WEIRD_PRIME * value) % this.keyMap.length
        } 
        return total;
    }
    set(key, value) {
        if (this.elements > this.keyMap.length / 2) this.grow();
        let i = this._hash(key);
        if (!this.keyMap[i]) {
            this.keyMap[i] = [];
        }
        this.keyMap[i].push([key, value]);
        this.elements++;
    }
    get(key) {
        let index = this._hash(key)
        if (this.keyMap[index]) {
            for (let i = 0; i < this.keyMap[index].length; i++) {
                if (this.keyMap[index][i][0] === key) {
                    return this.keyMap[index][i][1]
                }
            }
        }
        return undefined;
    }
    keys() {
        let arr = [];
        for (let i = 0; i < this.keyMap.length; i++) {
            if (this.keyMap[i]) {
                for (let j = 0; j < this.keyMap[i].length; j++) {
                    arr.push(this.keyMap[i][j][1])
                }
            }
        }
        return arr;
    }
    values() {
        let arr = [];
        for (let i = 0; i < this.keyMap.length; i++) {
            if (this.keyMap[i]) {
                for (let j = 0; j < this.keyMap[i].length; j++) {
                    if (!arr.includes(this.keyMap[i][j][1])) {
                        arr.push(this.keyMap[i][j][0]);
                    }
                }
            }
        }
        return arr;
    }
    /*
    NEW METHOD grow()
    does not require any parameters
    creates a new keyMap of the next size in the primes list
    copies table data over, rehashes data to current table size so get() still works
    */
    grow() {
        if (this.keyMap.length > this.elements[this.elements.length - 1]) {
            throw new Error('Map has reached maximum size');
        }
        let nextSize = () => {
            let currSizeIndex = this.primes.indexOf(this.keyMap.length);
            return this.primes[currSizeIndex + 1];
        }
        let oldLength = this.keyMap.length;
        let oldTable = this.keyMap;
        this.keyMap = new Array(nextSize(oldTable.length));
        this.elements = 0;
        for (let i = 0; i < oldLength; i++) {
            if (oldTable[i] === undefined) {
                continue;
            } else {
                let key = oldTable[i][0][0];
                let value = oldTable[i][0][1];
                this.set(key, value);
            }
        }
    }
}

let table = new HashTable();
table.set('fork','knife');
table.set('wheel','tire');
table.set('love','hate');
table.set('peace','war');
table.set('hello','world');
table.set('chicken','parm');
console.log(table.keyMap);
table.set('breakfast','sandwich');
console.log(table.keyMap);
console.log(table.get('chicken'));
console.log(table.get('fork'));