import java.util.ArrayList;

public class Column implements Comparable<Column> {
    private Character key;
    private ArrayList<Character> values;

    public Column(Character key) {
        this.key = key;
        this.values = new ArrayList<>();
    }

    public Character getKey() {
        return key;
    }

    public Character getValue(int i) {
        return values.get(i);
    }

    public void addValue(Character value) {
        this.values.add(value);
    }
    public int getSize() {
        return values.size();
    }

    @Override
    public int compareTo(Column o) {
        return this.key.compareTo(o.key);
    }
}
