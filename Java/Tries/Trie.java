import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Trie {

    Node root;

    // T9 to possible word (0-9 input, list of words output)

    public class Node {
        LinkedList<String> words; // value
        HashMap<Integer, Node> m; // next pointer

        public Node() {
            this.words = new LinkedList();
            this.m = null;
        }
    }

    public void insert(int[] t9, String word) {
        for (int i = 0; i < t9.length; i++) {
            System.out.print("T9: ");
            System.out.print(t9[i] + " ");
            System.out.println();
        }
        System.out.println("word: " + word);
        // start at the root
        Node curr = root;
        // initialize loop through t9 array, numerical representation of the letters
        for (int i = 0; i < t9.length; i++) {
            char c = word.charAt(i);
            System.out.println("current character: " + c);
            System.out.println("current: " + curr);
            // if map m of trie contains the current key of the t9 array
            if (!curr.m.containsKey(t9[i])) {
                // build a new node
                // is it enough just to put it in the map?
                curr = new Node();
                curr.words.add(word);
                curr.m.put(t9[i], curr);
                System.out.println("word not found: " + curr);
            } else {
                // at this point, we know the map contains a node corresponding to the current
                // t9 digit
                // get that node, set curr to its value
                curr = curr.m.get(c);
                System.out.println("word found: " + curr);
            }
        }
        return;
    }

    public List<String> lookup(int[] t9) {
        return Arrays.asList(new String[] { "bar", "cap", "car" });
    }

    // return t9 #num representation
    public static int[] toT9(String word) {
        System.out.println("length: " + word.toCharArray().length);
        int length = word.toCharArray().length;
        int[] T9 = new int[length];
        HashMap<Character, Integer> map = new HashMap();
        map.put('a', 2);
        map.put('b', 2);
        map.put('c', 2);
        map.put('d', 3);
        map.put('e', 3);
        map.put('f', 3);
        map.put('g', 4);
        map.put('h', 4);
        map.put('i', 4);
        map.put('j', 5);
        map.put('k', 5);
        map.put('l', 5);
        map.put('m', 6);
        map.put('n', 6);
        map.put('o', 6);
        map.put('p', 7);
        map.put('q', 7);
        map.put('r', 7);
        map.put('s', 7);
        map.put('t', 8);
        map.put('u', 8);
        map.put('v', 8);
        map.put('w', 9);
        map.put('x', 9);
        map.put('y', 9);
        map.put('z', 9);
        System.out.println(map);
        for (int i = 0; i < length; i++) {
            char key = word.charAt(i);
            System.out.println("Key: " + key + ", from map: " + map.get(key));
            T9[i] = map.get(key);
        }
        return T9;
    }

    // http://github.com/dwyl/english-words/blob/master/words.txt

    private static boolean containsLettersOnly(String word) {
        for (char ch : word.toCharArray()) {
            if (!Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidT9(String token) {
        for (char ch : token.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            File dictionary = new File("/Users/miller/Downloads/words.txt");
            Scanner scan = new Scanner(dictionary);

            // Build our t9ToWord index
            Trie t9ToWords = new Trie();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String word = line.trim().toLowerCase();
                if (containsLettersOnly(word)) {
                    t9ToWords.insert(toT9(word), word);
                }
            }
            scan.close();

            // Scanner input = new Scanner(System.in);
            // String line = "";
            // while (true) {
            // System.out.print("Enter T9: ");
            // line = input.nextLine();
            // line = line.trim().toLowerCase();
            // if (line.startsWith("q")) {
            // break;
            // }
            // String token = line.split(" ")[0];
            // if (isValidT9(token)) {
            // List<String> words = t9ToWords.lookup(toT9(token));
            // System.out.println("Possible words are: " + words.toString());
            // }
            // }
            // input.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        System.out.println("Goodbye!");
    }
}
