package maria.memorygame.model;

public enum DifficultyLevel {
    EASY("easy",4,10),
    HARD("hard",8,15);
    private final String displayName;
    private final int numberOfWords;
    private final int numberOfChances;

    DifficultyLevel(String displayName, int numberOfWords, int numberOfChances) {
        this.displayName = displayName;
        this.numberOfWords = numberOfWords;
        this.numberOfChances = numberOfChances;
    }

    public static DifficultyLevel byDisplayName(String displayName){
        for (DifficultyLevel value : DifficultyLevel.values()) {
            if(value.displayName.equals(displayName)){
                return value;
            }
        }
        return null;
    }
    public int getNumberOfWords() {
        return numberOfWords;
    }

    public int getNumberOfChances() {
        return numberOfChances;
    }

    public String getDisplayName() {
        return displayName;
    }
}
