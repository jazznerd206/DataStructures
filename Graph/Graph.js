class Graph {
    constructor() {
        this.adjacencyList = {};
    }
    addVertex(vertex) {
        if(!this.adjacencyList[vertex]) this.adjacencyList[vertex] = [];   
    }
    removeVertex(vertex) {
        while(this.adjacencyList[vertex].length) {
            const adjVert = this.adjacencyList[vertex].pop();
            this.removeEdge(vertex, adjVert);
        }
        delete this.adjacencyList[vertex];
    }
    addEdge(v1,v2) {
        this.adjacencyList[v1].push(v2);
        this.adjacencyList[v2].push(v1);
    }
    removeEdge(v1,v2) {
        this.adjacencyList[v1] = this.adjacencyList[v1].filter(
            v => v!== v2
        );
        this.adjacencyList[v2] = this.adjacencyList[v2].filter(
            v => v!== v1
        );
    }
    R_DFS(vertex) {
        let result = [];
        let visited = {};
        // IIFE 
        (function dfs(vertex) {
            if(!vertex) return null;
            visited[vertex] = true;
            result.push(vertex)
            adjacencyList[vertex].forEach(neighbor => {
                if (!visited[neighbor]) {
                    return dfs(neighbor);
                }
            })
        })(start)
    }
    I_DFS(start) {
        let stack = [start];
        let result = [];
        let visited = {};
        let crntVert;

        visited[start] = true;
        while(stack.lenght) {
            crntVert = stack.pop();
            result.push(crntVert);
            this.adjacencyList[crntVert].forEach(neighbor => {
                if (!visited[neighbor]) {
                    visted[neighbor] = true;
                    stack.push(neighbor);
                }
            })
        }
        return result;
    }
    BFS() {
        let queue = [start];
        let result = [];
        let visited = {};
        let crntVert;

        while (queue.length) {
            crntVert = queue.shift();
            result.push(crntVert);
            this.adjacencyList[crntVert].forEach(neighbpr => {
                if(!visited[crntVert]) {
                    visited[crntVert] = true;
                    queue.push(crntVert)
                }
            })
            return crntVert
        }
    }
}

let g = new Graph();
g.addVertex("LA")
g.addVertex("KPT")
g.addVertex("NYC")
g.addVertex("SF")
g.addVertex("SEA")
g.addEdge("LA","KPT")
g.addEdge("KPT","SF")
g.addEdge("SEA","SF")
g.addEdge("NYC","SEA")
console.log(g)
g.removeVertex('NYC')
// g.removeEdge("LA","KPT")
console.log(g)