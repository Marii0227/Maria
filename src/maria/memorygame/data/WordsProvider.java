package maria.memorygame.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WordsProvider {
    private static final String INPUT_FILE_PATH = "Words.txt";
    private List<String> words;

    public WordsProvider() {
        try {
            this.words = Files.readAllLines(Path.of("Words.txt"));
        } catch (IOException e) {
            System.out.println("Invalid input file path:" + INPUT_FILE_PATH);
            System.exit(1);
        }
    }

    public List<String> getWords() {
        return words;
    }
}
