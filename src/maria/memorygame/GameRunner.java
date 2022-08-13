package maria.memorygame;

import maria.memorygame.model.DifficultyLevel;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
            DifficultyLevel level = readDifficultyLevel();
    }

    public static DifficultyLevel readDifficultyLevel() {
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
