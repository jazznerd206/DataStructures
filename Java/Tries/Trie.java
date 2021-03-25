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
        // start at the root
        Node curr = root;
        // initialize loop through t9 array, numerical representation of the letters
        for (int i = 0; i < t9.length; i++) {
            char c = t9[i];
            // if map m of trie does not contain the current key of the t9 array
            if (curr.m.containsKey(c)) {
                // at this point, we know the map already contains the node
                // set curent to node from hashmap
                curr = curr.m.get(c);
            } else {
                // build a new node
                // WHERE DOES THIS GO??
                // is it enough just to put it in the map?
                curr = new Node(t9[i]);
                curr.m.put(t9[i], curr);
            }
        }
        return;
    }

    public List<String> lookup(int[] t9) {
        return Arrays.asList(new String[] { "bar", "cap", "car" });
    }

    // return t9 #num representation
    public static int[] toT9(String word) {
        return new int[] { 2, 2, 7 };
        // int[] t9array = new int[word.length];
        // word = word.toLowerCase();
        // // length of input string
        // int n = word.length();
        // for (int i = 0; i < n; i++) {
        // if (word.charAt(i) == ' ')
        // t9array = t9array + "0";

        // else {
        // int position = word.charAt(i) - 'A';
        // t9array[i] = position;
        // }
        // }
        // return t9array;
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

            Scanner input = new Scanner(System.in);
            String line = "";
            while (true) {
                System.out.print("Enter T9: ");
                line = input.nextLine();
                line = line.trim().toLowerCase();
                if (line.startsWith("q")) {
                    break;
                }
                String token = line.split(" ")[0];
                if (isValidT9(token)) {
                    List<String> words = t9ToWords.lookup(toT9(token));
                    System.out.println("Possible words are: " + words.toString());
                }
            }
            input.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        System.out.println("Goodbye!");
    }
}
