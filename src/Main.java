import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public ArrayList<String> text = new ArrayList<>();
    ArrayList<Character> alphabet = new ArrayList<>();

    static void main(String[] args) {
        Main main = new Main();

        main.populateArray();
        main.readFromFile();
        main.encrypt();

    }

    void populateArray() {
        for (int i = 0; i < 26; i++) {
            alphabet.add((char) ('a' + i));                                                 //Loops through the array of the alphabet and adds the letters to each
        }
    }

    void readFromFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Text.txt"));                                       //Reads the text into a buffered reader (essentially line by line)
            String line;
            while ((line = reader.readLine()) != null) {                                                   //Makes sure that it's looped through the entire file
                text.add(line);                                                                            //Adds the new line to the string and then passes to the encryption metho
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void encrypt(){
        //Loop though every char of the string of each index of the array to get each char/letter
        //Then shift the letters using the letters[] array from earlier
        char Letter;
        String word ="";
        int indexLetter;
        for (int i =0; i<text.size();i++){
           word = text.get(i);
           for (int l=0; l<word.length();l++){

               Letter = word.charAt(l);
               System.out.println("letter: "+Letter);

               indexLetter = alphabet.indexOf(Letter);
               System.out.println("indexLetter: "+indexLetter);

               Letter = alphabet.get(indexLetter+2);

                //text.set(i,l);
               System.out.println("new letter: "+Letter);

            }
        }

    }
}

