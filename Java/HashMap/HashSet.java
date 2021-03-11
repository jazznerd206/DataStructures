import java.util.Hashtable;

public class HashSet<K> {
    private HashTable<K, Boolean> hashTable;

    public HashSet() {
        this.hashTable = new HashTable<K, Boolean>();
    }

    public boolean contains(K key) {
        Boolean exists = hashTable.get(key);
        return exists != null && exists.booleanValue();
    }

    public void insert(K key) {
        hashTable.put(key, true);
    }

    public void remove(K key) {
        hashTable.put(key, false);
    }

    public int size() {
        return hashTable.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        HashTable.Node[] table = hashTable.table;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && (Boolean) table[i].value) {
                sb.append(table[i].key.toString());
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        int n = 9;
        String keyPrefix = "key";
        for (int i = 0; i < n; i++) {
            String key = keyPrefix + i;
            set.insert(key);
        }
        String key = keyPrefix + 2;

        System.out.println(set);
    }
}
