class HashTable {
    constructor(size=53) {
        this.keyMap = new Array(size)
    }
    _hash(key) {
        let total = 0;
        let WEIRD_PRIME = 31;
        for (let i = 0; i < Math.min(key.length, 100); i++) {
            let char = key[i];
            let value = char.charCodeAt(0) - 96;
            total = (total * WEIRD_PRIME + value) % this.keyMap.length
        } 
        return total;
    }
    set(key, value) {
        let i = this._hash(key);
        if (!this.keyMap[i]) {
            this.keyMap[i] = [];
        }
        this.keyMap[i].push([key, value]);

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
}

let table = new HashTable();
table.set('fork','knife');
table.set('wheel','tire');
table.set('love','hate');
table.set('peace','war');
table.set('chicken','egg');
table.set('chicken','parm');
table.set('chicken','chicken');
console.log(table);
console.log(table.keys())
console.log(table.values())