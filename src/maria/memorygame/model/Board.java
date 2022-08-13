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
    private final List<Box> selected = new ArrayList<>(2);
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

    public boolean isHidden(Coordinates coordinates) {
        Box box = boxes[coordinates.getX()][coordinates.getY()];
        return box.isHidden();
    }

    public boolean isValid(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < xSize && coordinates.getY() >= 0 && coordinates.getY() < ySize;
    }

    public void select(Coordinates coordinates) {
        Box box = boxes[coordinates.getX()][coordinates.getY()];
        box.setSelected(true);
        selected.add(box);
        display();
    }

    public void checkMatch() {
        Box selected1 = selected.get(0);
        Box selected2 = selected.get(1);
        if (selected1.getWord().equals(selected2.getWord())) {
            selected1.setMatched(true);
            selected2.setMatched(true);
        }
        selected1.setSelected(false);
        selected2.setSelected(false);
        selected.clear();
        guessChances--;
    }

    public boolean isActive() {
        if (guessChances < 1) return false;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (!boxes[x][y].isMatched()) return true;
            }
        }
        return false;
    }

    public void display() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("       Level:%s%n", level);
        System.out.printf("       Guess chances:%d%n", guessChances);
        System.out.printf("   %15d %15d %15d %15d%n", 1, 2, 3, 4);
        for (int y = 0; y < ySize; y++) {
            System.out.printf("%3c", (char) ('A' + y));
            for (int x = 0; x < xSize; x++) {
                Box box = boxes[x][y];
                System.out.printf("%15s", box.isHidden() ? "X" : box.getWord());
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
