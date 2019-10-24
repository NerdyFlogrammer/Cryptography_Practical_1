import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

public class Cipher {
    private List<Character> key;
    private int keyLength;



    public Cipher() {
        this.key = new ArrayList<>();
        this.keyLength = key.size();
    }

    private static String keyToString(List<Character> key) {
        return key.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public String decrypt(String decryptionKey, String message) {
        return encrypt(decryptionKey, message);
    }

    public String encrypt(String key, String message) {
        this.key = key.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        this.keyLength = key.length();
        List<Column> grid = createGrid(message);
        sort(grid);
        return gridToMessage(grid);
    }

    private static String gridToMessage(List<Column> grid) {
        StringBuilder stringBuilder = new StringBuilder();
        int maxRows = grid.stream()
                .mapToInt(v -> v.getSize())
                .max()
                .getAsInt();
        for (int i = 0; i < maxRows; i++) {
            for (Column col:
                    grid) {
                if(i < col.getSize()) {
                    stringBuilder.append(col.getValue(i));
                }
            }
        }
        return stringBuilder.toString();
    }

    private List<Column> createGrid(String message) {
        List<String> splittedText = Arrays.asList(message.split("(?<=\\G.{" + keyLength + "})"));
        List<Column> grid = new ArrayList<>();

        for (int i = 0; i < key.size(); i++) {
            Column tempCol = new Column(key.get(i));
            int finalI = i;
            splittedText.forEach((String line) -> {
                if(finalI < line.length())
                    tempCol.addValue(line.charAt(finalI));
            });
            grid.add(tempCol);
        }
        return grid;
    }

    public static String generateKey(String message, String keyPattern) {
        List<Character> encryptionKey = new ArrayList();
        for (Character c:
                keyPattern.toCharArray()) {
            if(Character.getNumericValue(c) < message.length())
                encryptionKey.add(message.charAt((Character.getNumericValue(c)-1)));
            else
                encryptionKey.add('x');
        }
        return keyToString(encryptionKey);
    }

    public static String generateDecryptionKey(String encryptionKey) {
        StringBuilder decryptionKey = new StringBuilder();
        List<Column> grid = new ArrayList<>();
        for (int i = 0; i < encryptionKey.length(); i++) {
            Column tmpCol = new Column(encryptionKey.charAt(i));
            tmpCol.addValue((char)('a'+i));
            grid.add(tmpCol);
        }
        sort(grid);
        return gridToMessage(grid);
    }
}
