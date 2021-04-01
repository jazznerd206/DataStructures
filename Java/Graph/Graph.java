/*
By definition, trees are undirected graphs that:

1. Are acyclic, i.e. we can't traverse back to any starting node we choose without back-tracking
2. A cycle is created by adding any edge into the existing tree
3. The path between any two nodes is unique
4. There is a distinct root node
5. The graph is connected (there's a path between every two nodes)
*/

import java.util.HashMap;

public class Graph {
    private int vCount;
    private Vertex root;
    private HashMap<Integer, LinkedList> nodes;

    public Graph() {
        this.vCount = 0;
        this.root = null;
        this.nodes = new HashMap();
    }

    public class Vertex {
        private int value;
        private LinkedList<Vertex> adjList;

        public Vertex(int value) {
            this.value = value;
            this.adjList = new LinkedList();
        }

        private boolean listHasValue(Vertex v) {
            for (int i = 0; i < this.adjList.length; i++) {
                if (this.adjList[i].equals(v)) {
                    return true;
                }
            }
            return false;
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

    /**
     * 
     * ADD EDGE this methods adds next vertex v2 to the adjacency list of vertex v1
     * this.weight is the weight of vertex v2, the cost of traversing to that node
     * 
     */
    public void addEdge(Vertex v1, Vertex v2) {
        LinkedList adjList1 = v1.adjList;
        int weight = v2.weight;
        Vertex hasV2 = adjList1.listHasValue(v2);
        if (hasV2 == false) {
            // insert vertex v2 into list
            // key = value
            // value = weight
            adjList1.add(v2);
        }
    }

    public void addVertex(Vertex v) {
        if (this.root == null) {
            this.root = v;
        }
        this.nodes.put(v.value, new LinkedList());
        this.vCount++;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        System.out.println("this is a graph.");
        System.out.println(g);
    }

}