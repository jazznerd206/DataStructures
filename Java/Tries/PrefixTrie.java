public class PrefixTrie {

    Node root;

    public class Node {
        Node[] children;
        boolean isLeaf;

        public Node() {
            this.children = new Node[26];
            this.isLeaf = false;
        }
    }

    PrefixTrie() {
        this.root = new Node();
    }

    public void insert(String word) {
        int n = word.length();
        Node curr = root;
        for (int i = 0; i < n; i++) {
            int index = word.charAt(i) - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            curr = curr.children[index];
        }
        curr.isLeaf = true;
    }

    static int countChildren(Node node) {
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                count++;
            }
        }
        return count;
    }

    public String validatePrefix() {
        Node curr = root;
        int indexs = 0;
        String prefix = "";
        while (countChildren(curr) == 1 && curr.isLeaf == false) {
            for (int i = 0; i < 26; i++) {
                if (curr.children[i] != null) {
                    indexs = i;
                }
            }
            curr = curr.children[indexs];
            prefix += (char) ('a' + indexs);
        }
        if (prefix.length() == 0) {
            return "No common prefix";
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] bankOfWords = { "dog", "doghouse", "dont", "doughnut", "dongle", "doggone" };
        String[] bankOfWords2 = { "dog", "doghouse", "dont", "doughnut", "dongle", "doggone", "endgame" };
        PrefixTrie pt = new PrefixTrie();
        for (String word : bankOfWords) {
            pt.insert(word);
        }
        System.out.println(pt.validatePrefix());
    }
}
