
/**
 * THIS IS A TEMPLATE FOR A WEIGHTED, UNDIRECTED GRAPH
 */

import java.util.HashMap;

public class WeightedGraph {

    static class Graph {
        int size;
        HashMap<Integer, HashMap<Integer, Integer>> vMap;

        public Graph() {
            this.size = 0;
            this.vMap = new HashMap();
        }

        public void addVertex(Integer v) {
            if (!vMap.containsKey(v)) {
                vMap.put(v, new HashMap());
                size++;
            }
        }

        public void addEdge(Integer a, Integer b, Integer weight) {
            if (!vMap.containsKey(a))
                addVertex(a);
            if (!vMap.containsKey(b))
                addVertex(b);
            HashMap m = vMap.get(a);
            m.put(b, weight);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Integer v : vMap.keySet()) {
                sb.append(v.toString() + ": ");
                HashMap l = vMap.get(v);
                l.forEach((K, V) -> {
                    sb.append(" { destination: ");
                    sb.append(K);
                    sb.append(" :: weight: ");
                    sb.append(V);
                    sb.append(" } ");
                });
                sb.append("\n");
            }

            return (sb.toString());
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        System.out.println("this is a weighted graph.");
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(3, 2, 3);
        g.addEdge(4, 2, 4);
        g.addEdge(4, 2, 5);
        g.addEdge(1, 1, 6);
        g.addEdge(5, 2, 7);
        g.addEdge(5, 3, 8);
        g.addEdge(5, 4, 9);
        System.out.println(g.toString());
    }
}
