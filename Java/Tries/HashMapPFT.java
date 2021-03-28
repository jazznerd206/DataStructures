import java.util.HashMap;
import java.util.LinkedList;

public class HashMapPFT {

    Node root;

    HashMapPFT() {
        this.root = new Node();
    }

    public class Node {
        LinkedList<String> words;
        HashMap<Character, Node> m;

        public Node() {
            this.words = new LinkedList();
            this.m = new HashMap();
        }
    }

    public void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char d = word.charAt(i);
            if (!curr.m.containsKey(d)) {
                curr.m.put(d, new Node());
            }
            curr = curr.m.get(d);
        }
        curr.words.add(word);
        return;
    }

    public String validatePrefix() {
        Node curr = root;
        String prefix = "";
        while (curr != null && curr.m.size() == 1) {
            prefix += curr.m.entrySet().stream().findFirst().get().getKey();
            curr = curr.m.entrySet().stream().findFirst().get().getValue();
        }
        if (prefix.length() == 0)
            return "No common prefix";
        return prefix;
    }

    public static void main(String[] args) {
        // System.out.println("poopfart");
        String[] bankOfWords = { "dog", "doghouse", "dont", "doughnut", "dongle", "doggone" };
        // String[] bankOfWords2 = { "dog", "doghouse", "dont", "doughnut", "dongle",
        // "doggone", "endgame" };
        HashMapPFT pft = new HashMapPFT();
        for (String word : bankOfWords) {
            pft.insert(word);
        }
        System.out.println(pft.validatePrefix());
    }
}