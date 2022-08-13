package maria.memorygame.model;

import java.util.regex.Pattern;

public class Coordinates {

    private static final Pattern pattern = Pattern.compile("^[A-Z]\\d$");
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates parse(String value) {
        if (!pattern.matcher(value).matches()) {
            return null;
        }
        return new Coordinates(Character.getNumericValue(value.charAt(1)) -1, (int) value.charAt(0) - 'A');
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
