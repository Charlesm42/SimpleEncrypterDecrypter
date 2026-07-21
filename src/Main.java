import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public ArrayList<String> text = new ArrayList<>();

    static void main(String[] args) {
        Main main = new Main();

        main.populateArray();
        main.readFromFile();
    }

    void populateArray() {
        String[] letters = new String[26];
        for (int i = 0; i < 26; i++) {
            letters[i] = String.valueOf((char) ('a' + i));
        }
    }

    void readFromFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Text.txt"));                                     //Reads the text into a buffered reader (essentially line by line)
            String line;
            while ((line = reader.readLine()) != null) {                                                  //Makes sure that it's looped through the entire file
                text.add(line);                                                                           //Adds the new line to the string and then passes to the encryption metho
            }

            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

