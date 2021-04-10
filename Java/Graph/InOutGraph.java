import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class InOutGraph {
    private int vCount;
    private LinkedList<Integer> in[];
    private LinkedList<Integer> out[];

    public InOutGraph(int vCount) {
        this.vCount = vCount;
        in = new LinkedList[vCount];
        out = new LinkedList[vCount];
        for (int i = 0; i < vCount; i++) {
            in[i] = new LinkedList<Integer>();
            out[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int start, int end) {
        out[start].add(end);
        in[end].add(start);
    }

    public void dfs(int v) {
        HashSet<Integer> visited = new HashSet<>();
        dfsHelper(v, visited);

    }

    private void dfsHelper(int v, HashSet<Integer> visited) {
        System.out.printf("%d ", v);
        visited.add(v);

        Iterator<Integer> i = out[v].listIterator();
        while (i.hasNext()) {
            v = i.next();
            if (!visited.contains(v)) {
                dfsHelper(v, visited);
            }
        }
    }

    public void printOuts() {
        for (int i = 0; i < vCount; i++) {
            System.out.printf("Vertex %d is connected to:", i);
            for (int j = 0; j < out[i].size(); j++) {
                System.out.printf(" %d", out[i].get(j));
            }
            System.out.println();
        }
    }

    public void printIns() {
        for (int i = 0; i < vCount; i++) {
            System.out.printf("Vertex %d in edges:", i);
            for (int j = 0; j < in[i].size(); j++) {
                System.out.printf(" %d", in[i].get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        InOutGraph g = new InOutGraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(2, 0);
        g.addEdge(4, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 4);

        g.dfs(0);
    }
}
