import java.util.HashMap;

public class HashTable<K, V> {
    Node[] table;
    private double loadFactor;
    private static int[] primes = { 7, 17, 37, 79, 163, 331, 673, 1361, 2729, 5471, 10949, 21911, 43853, 87719, 175447,
            350899, 701819, 1403641, 2807303, 5614657, 11229331, 22458671, 44917381, 89834777, 179669557, 359339171,
            718678369, 1437356741 };
    int size;

    public class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    HashTable() {
        this.table = (Node<K, V>[]) new Node[primes[0]];
        this.size = 0;
        this.loadFactor = 0.5;
    }

    public V put(K key, V value) {
        int index = Math.abs(key.hashCode() % table.length);
        V oldValue = null;
        while (table[index] != null) {
            K oldKey = (K) table[index].key;
            if (oldKey != null && oldKey.equals(key)) {
                oldValue = (V) table[index].value;
                break;
            } else {
                // collision occurred

                // LINEAR PROBING
                // index = (index + 1) % table.length;

                // QUADRATIC PROBING
                for (int j = 0; j < table.length; j++) {
                    index = (index + j * j) % table.length;
                    if (table[index] == null) {
                        break;
                    }
                }
            }
        }
        if (oldValue == null) {
            size++;
        }
        if (size > loadFactor * table.length) {
            grow();
        }
        table[index] = new Node(key, value);
        return oldValue;
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return null;
        }
        V returnValue = null;
        while (table[index] != null) {
            if (table[index].key != null && table[index].key.equals(key)) {
                returnValue = (V) table[index].value;
                break;
            } else {
                // linear probing
                // index = (index + 1) % table.length;

                // quadratic probing
                for (int j = 0; j < table.length; j++) {
                    index = (index + j * j) % table.length;
                    if (table[index] == null) {
                        break;
                    }
                }
            }
        }
        return returnValue;
    }

    private static int nextTableLength(int capacity) {
        for (int i = 0; i < primes.length; i++) {
            if (capacity < primes[i]) {
                return primes[i];
            }
        }
        return Integer.MAX_VALUE;
    }

    private void grow() {
        int nextTableLength = nextTableLength(table.length);
        Node[] oldTable = table;
        table = (Node<K, V>[]) new Node[nextTableLength];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                K key = (K) oldTable[i].key;
                V value = (V) oldTable[i].value;
                put(key, value);
            }
        }
    }

    public String printString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                sb.append("{");
                sb.append("INDEX: ");
                sb.append(i + ", ");
                sb.append("KEY: ");
                sb.append(table[i].key + ", ");
                sb.append("VALUE: ");
                sb.append(table[i].value);
                sb.append("}\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        HashTable<String, Integer> ht1 = new HashTable<>();
        String keyPrefix = "key";
        for (int i = 0; i < 9; i++) {
            Integer value = i;
            String key = keyPrefix + i;
            ht1.put(key, value);
        }
        for (int i = 0; i < 9; i++) {
            String key = keyPrefix + i;
            System.out.println(ht1.get(key));
        }
        System.out.println(ht1.printString());
    }
}