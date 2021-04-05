import java.util.HashMap;
import java.util.LinkedList;

public class GraphTwo {
    private int vCount;
    private HashMap<Integer, LinkedList> nodes;

    public GraphTwo() {
        this.vCount = 0;
        this.nodes = new HashMap();
    }

    public void addVertex(Integer v) {
        if (!nodes.containsKey(v)) {
            nodes.put(v, new LinkedList());
            vCount++;
        }
    }

    public void addEdge(Integer start, Integer end, boolean bidirectional) {
        if (!nodes.containsKey(start))
            addVertex(start);
        if (!nodes.containsKey(end))
            addVertex(end);
        nodes.get(start).add(end);
        if (bidirectional == true) {
            nodes.get(end).add(start);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer v : nodes.keySet()) {
            sb.append(v.toString() + ": ");
            // for (int w : nodes.get(v)) {
            // sb.append(String.valueOf(w) + " ");
            // }
            LinkedList l = nodes.get(v);
            sb.append(l.toString());
            sb.append("\n");

        }

        return (sb.toString());
    }

    public static void main(String[] args) {
        GraphTwo g = new GraphTwo();
        System.out.println("this is a graph.");
        g.addEdge(1, 2, false);
        g.addEdge(1, 3, false);
        g.addEdge(3, 2, false);
        g.addEdge(4, 2, false);
        g.addEdge(4, 2, false);
        g.addEdge(1, 1, false);
        g.addEdge(5, 2, false);
        g.addEdge(5, 3, false);
        g.addEdge(5, 4, false);
        g.addEdge(5, 5, false);
        System.out.println(g.toString());
    }

}
