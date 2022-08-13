package maria.memorygame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Board {
    private final String level;
    private final int xSize = 4;
    private final int ySize;
    private final Box[][] boxes;
    private int guessChances;

    public Board(DifficultyLevel level, List<String> words) {
        ySize = (level.getNumberOfWords() * 2) / xSize;
        this.level = level.getDisplayName();
        this.guessChances = level.getNumberOfChances();
        boxes = getBoxes(words);
    }

    private static List<String> getWordsPairs(List<String> words) {
        List<String> wordsPairs = new ArrayList<>(words.size() * 2);
        wordsPairs.addAll(words);
        wordsPairs.addAll(words);
        Collections.shuffle(wordsPairs);
        return wordsPairs;
    }

    public void display() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("   %15d %15d %15d %15d%n", 1, 2, 3, 4);
        for (int y = 0; y < ySize; y++) {
            System.out.printf("%3c", (char) ('A' + y));
            for (int x = 0; x < xSize; x++) {
                System.out.printf("%15s", boxes[x][y].getWord());
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private Box[][] getBoxes(List<String> words) {
        final Box[][] boxes = new Box[xSize][ySize];
        Iterator<String> wordsPairsIterator = getWordsPairs(words).iterator();
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                boxes[x][y] = new Box(wordsPairsIterator.next());
            }
        }
        return boxes;
    }
}
