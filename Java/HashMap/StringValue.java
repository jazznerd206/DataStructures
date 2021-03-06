public class StringValue {
    private String str;

    StringValue(String str) {
        this.str = str;
    }

    public String toString() {
        return this.str;
    }

    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < str.length(); i++) {
            hashCode += (int) str.charAt(i);
        }
        return hashCode;
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
