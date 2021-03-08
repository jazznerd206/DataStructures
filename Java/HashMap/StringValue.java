public class StringValue {
    private String str;
    private int hash;

    StringValue(String str) {
        this.str = str;
        this.hash = 0;
    }

    public String toString() {
        return this.str;
    }

    public int hashCode() {
        if (hash == 0 && str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                hash += 31 * hash + (int) str.charAt(i);
            }
        }
        return hash;
    }

    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check
        if (this.getClass() != o.getClass()) {
            return false;
        }
        StringValue other = (StringValue) o;
        return this.str.equals(other.str);
    }
}
