import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SGraph {
    private int vCount;
    private ArrayList<Integer> vertices[];

    public SGraph(int vCount) {
        this.vCount = vCount;
        this.vertices = new ArrayList[vCount];
        for (int i = 0; i < vCount; i++) {
            vertices[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int start, int end) {
        vertices[start].add(end);
        vertices[end].add(start);
    }

    public void printGraph() {
        for (int i = 0; i < vCount; i++) {
            if (vertices[i].size() > 0) {
                System.out.printf("vertex %d is connected to:", i);
                for (int j = 0; j < vertices[i].size(); j++) {
                    System.out.printf(" %d", vertices[i].get(j));
                }
                System.out.println();
            }
        }
    }

    public void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(v);
        visited.add(v);
        while (q.size() != 0) {
            v = q.poll();
            System.out.printf("%d ", v);
            // ==========================================
            // ==========================================
            /**
             * this is the droid you are looking for... 🤖 iterate through a list!!!
             * ----------------------------↴↴↴↴↴↴↴↴↴↴↴↴↴↴
             */
            Iterator<Integer> i = vertices[v].listIterator();
            // ==========================================
            // ==========================================
            while (i.hasNext()) {
                int n = i.next();
                if (!visited.contains(n)) {
                    visited.add(n);
                    q.add(n);
                }
            }
        }

    }

    public void dfs(int v) {
        HashSet<Integer> visited = new HashSet<>();
        dfsHelper(v, visited);

    }

    private void dfsHelper(int v, HashSet<Integer> visited) {
        System.out.printf("%d ", v);
        visited.add(v);

        Iterator<Integer> i = vertices[v].listIterator();
        while (i.hasNext()) {
            v = i.next();
            if (!visited.contains(v)) {
                dfsHelper(v, visited);
            }
        }
    }

    public static void main(String[] args) {
        SGraph g = new SGraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.dfs(0);
        System.out.println();
        g.bfs(0);
    }

}
