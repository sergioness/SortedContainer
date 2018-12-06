public class StringLen implements Comparable<StringLen> {

    private final String str;

    public StringLen(String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object obj) {
        StringLen that = (StringLen) obj;
        return that.str.equals(this.str);
    }

    @Override
    public int hashCode() {
        return str.hashCode();
    }

    @Override
    public int compareTo(StringLen that) {
        return Integer.compare(this.str.length(), that.str.length());
    }

    @Override
    public String toString() {
        return str;
    }
}
