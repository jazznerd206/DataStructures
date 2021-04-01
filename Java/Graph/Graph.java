/*
By definition, trees are undirected graphs that:

1. Are acyclic, i.e. we can't traverse back to any starting node we choose without back-tracking
2. A cycle is created by adding any edge into the existing tree
3. The path between any two nodes is unique
4. There is a distinct root node
5. The graph is connected (there's a path between every two nodes)
*/

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
    private int vCount;
    private Vertex root;
    private HashMap nodes;

    public Graph() {
        this.vCount = 0;
        this.root = null;
        this.nodes = new HashMap();
    }

    public class Vertex {
        private int value;
        private LinkedList<Edge> adjList;

        public Vertex(int value) {
            this.value = value;
            this.adjList = new LinkedList();
        }
    }

    public class Edge {
        private int weight;
        private Vertex next;

        public Edge(Vertex next, int w) {
            this.next = next;
            this.weight = w;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        System.out.println("this is a graph.");
        System.out.println(g);
    }

}