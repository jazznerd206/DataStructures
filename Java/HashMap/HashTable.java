import java.util.HashMap;

public class HashTable<K, V> {
    Node[] table;
    int size;

    public class Node<K, V> {
        K key;
        V value;

        Node(K Key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    HashTable() {
        this.table = (Node<K, V>[]) new Node[8];
        this.size = 0;
    }

    V put(K key, V value) {
        int index = Math.abs(key.hashCode() % table.length);
        V oldValue = null;
        while (table[index] != null) {
            K oldKey = (K) table[index].key;
            if (oldKey != null && oldKey.equals(key)) {
                oldValue = (V) table[index].value;
                break;
            } else {
                System.out.println("detectd a collision");
                index = (index + 1) % table.length;
            }
        }
        table[index] = new Node(key, value);
        return oldValue;
    }

    V get(K key) {
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return null;
        }
        return (V) table[index].value;
    }

    public static void main(String[] args) {
        HashTable<StringValue, Integer> ht1 = new HashTable<>();
        StringValue sv1 = new StringValue("Hello World");
        StringValue sv2 = new StringValue("World Hello");
        ht1.put(sv1, 13);
        ht1.put(sv2, 14);
        System.out.println("HashTable<StringValue, Integer>:");
        System.out.println(sv1 + ":" + ht1.get(sv1));
        System.out.println(sv2 + ":" + ht1.get(sv2));

        HashTable<String, Integer> ht2 = new HashTable<>();
        String s1 = "Hello World";
        String s2 = "World Hello";
        ht2.put(s1, 13);
        ht2.put(s2, 14);
        System.out.println("HashTable<String, Integer>:");
        System.out.println(s1 + ":" + ht2.get(s1));
        System.out.println(s2 + ":" + ht2.get(s2));

        HashMap<StringValue, Integer> hm1 = new HashMap();
        hm1.put(sv1, 13);
        hm1.put(sv2, 14);
        System.out.println("HashMap<StringValue, Integer>:");
        System.out.println(sv1 + ":" + hm1.get(sv1));
        System.out.println(sv2 + ":" + hm1.get(sv2));

        HashMap<String, Integer> hm2 = new HashMap<>();
        hm2.put(s1, 13);
        hm2.put(s2, 14);
        System.out.println("HashMap<String, Integer>:");
        System.out.println(s1 + ":" + hm2.get(s1));
        System.out.println(s2 + ":" + hm2.get(s2));
    }
}