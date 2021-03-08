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

    HashTable(int length) {
        this.table = (Node<K, V>[]) new Node[length];
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
                // collision occurred, use linear probing
                index = (index + 1) % table.length;
            }
        }
        if (oldValue == null) {
            size++;
        }
        // if size > loadFactor * table.length then grow the hash table
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
                // System.out.println("value found");
                returnValue = (V) table[index].value;
                break;
            } else {
                // System.out.println("increment index +1");
                index = (index + 1) % table.length;
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
        // get new length
        // initialize table holder so we can access data from the old table of nodes
        // set current table to a new table of nodes of the new length
        int nextTableLength = nextTableLength(table.length);
        Node[] oldTable = table;
        table = new Node[nextTableLength];
        System.out.println("============= 1: old table: " + table.length);
        System.out.println("============= 2: new table: " + table.length);
        // loop through the old table
        for (int i = 0; i < oldTable.length; i++) {
            // if there is a key at index i
            if (oldTable[i] != null) {
                // grab the key
                K oldKey = (K) oldTable[i].key;
                // rehash the key to get the proper index in the new table
                int newIndex = Math.abs(oldKey.hashCode() % table.length);
                System.out.printf("Old index: %s, Value: %s, new index: %s, new length: %s\n", i, oldTable[i].value,
                        newIndex, table.length);
                // if there is already a value at the new index
                // and the key stored there is not equal to the key from the old table
                if (table[newIndex] != null && table[newIndex].key != oldKey) {
                    System.out.println("matching keys");
                } else {
                    System.out.println("non matching keys");
                }
                // set the table at the new index with the k/v pair from the old table
                table[newIndex] = new Node(oldTable[i].key, oldTable[i].value);
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
        HashTable<StringValue, Integer> ht1 = new HashTable<>(7);
        StringValue sv1 = new StringValue("Hello World");
        StringValue sv2 = new StringValue("Hello Worl");
        StringValue sv3 = new StringValue("Hello Wor");
        StringValue sv4 = new StringValue("Hello Wo");
        StringValue sv5 = new StringValue("Hello W");
        StringValue sv6 = new StringValue("Hello");
        StringValue sv7 = new StringValue("Hell");
        StringValue sv8 = new StringValue("Hel");
        StringValue sv9 = new StringValue("He");
        ht1.put(sv1, 1);
        // System.out.println(ht1.printString());
        ht1.put(sv2, 2);
        // System.out.println(ht1.printString());
        ht1.put(sv3, 3);
        // System.out.println(ht1.printString());
        ht1.put(sv4, 4);
        // System.out.println(ht1.printString());
        ht1.put(sv5, 5);
        // System.out.println(ht1.printString());
        ht1.put(sv6, 6);
        // System.out.println(ht1.printString());
        ht1.put(sv7, 7);
        // System.out.println(ht1.printString());
        ht1.put(sv8, 8);
        // System.out.println(ht1.printString());
        ht1.put(sv9, 9);
        System.out.println(ht1.printString());
        System.out.println(ht1.get(sv1));
        System.out.println(ht1.get(sv2));
        System.out.println(ht1.get(sv3));
        System.out.println(ht1.get(sv4));
        System.out.println(ht1.get(sv5));
        System.out.println(ht1.get(sv6));
        System.out.println(ht1.get(sv7));
        System.out.println(ht1.get(sv8));
        System.out.println(ht1.get(sv9));

    }
}