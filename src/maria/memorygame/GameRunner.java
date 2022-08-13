package maria.memorygame;

import maria.memorygame.data.WordsProvider;
import maria.memorygame.model.Board;
import maria.memorygame.model.Coordinates;
import maria.memorygame.model.DifficultyLevel;

import java.util.Scanner;

public class GameRunner {
    private final WordsProvider wordsProvider = new WordsProvider();
    private Board board;

    public static void main(String[] args) {
        new GameRunner().run();
    }

    public void run() {
        runGame();
    }

    public void runGame() {
        DifficultyLevel level = readDifficultyLevel();
        board = new Board(level, wordsProvider.getWords(level.getNumberOfWords()));
        board.display();
        while (board.isActive()) {
            runRound();
        }
    }

    public void runRound() {
        Coordinates coordinates = readCoordinates();
        board.select(coordinates);
        coordinates = readCoordinates();
        board.select(coordinates);
        board.checkMatch();
    }

    public Coordinates readCoordinates() {
        Coordinates coordinates = null;
        Scanner in = new Scanner(System.in);
        while (coordinates == null) {
            System.out.println("Type coordinates to show:");
            coordinates = Coordinates.parse(in.nextLine());
            if (!isValidAndHidden(coordinates)) {
                coordinates = null;
            }
        }
        return coordinates;
    }

    private boolean isValidAndHidden(Coordinates coordinates) {
        if (coordinates == null) {
            System.out.println("Invalid coordinates. Please type uppercase letter and single digit (eg. B2)");
            return false;
        } else if (!board.isValid(coordinates)) {
            System.out.println("Invalid coordinates.");
            return false;
        } else if (!board.isHidden(coordinates)) {
            System.out.println("Selected world is already visible.");
            return false;
        } else {
            return true;
        }
    }

    public DifficultyLevel readDifficultyLevel() {
        DifficultyLevel level = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Select difficulty level.");
        while (level == null) {
            System.out.print("Type 'easy' or 'hard':");
            String line = in.nextLine();
            level = DifficultyLevel.byDisplayName(line);
            if (level == null) {
                System.out.println("Unknown difficulty level:" + line);
            }
        }
        return level;
    }
}
