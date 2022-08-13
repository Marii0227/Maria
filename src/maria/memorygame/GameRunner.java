package maria.memorygame;

import maria.memorygame.data.WordsProvider;

public class GameRunner {
    public static void main(String[] args) {
        System.out.println(new WordsProvider().getWords());
    }
}
