package maria.memorygame.model;

public class Box {
    private final String word;
    private boolean matched = false;
    private boolean selected = false;

    public Box(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public boolean isMatched() {
        return matched;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
