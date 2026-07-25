import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public ArrayList<String> text = new ArrayList<>();
    ArrayList<Character> alphabet = new ArrayList<>();
    ArrayList<String> encryptedText = new ArrayList<>();

    static void main(String[] args){
        Main main = new Main();
        main.populateArray();
        main.readFromFile();
      //  main.encrypt();

    }

    void populateArray() {
        for (int i = 0; i < 25; i++) {
            alphabet.add((char) ('a' + i));                                                                //Loops through the array of the alphabet and adds the letters to each
        }
    }

    void readFromFile() {
        try(Scanner scFile = new Scanner(new FileInputStream("Text.txt"))){
            while (scFile.hasNext()) {
                text.add(scFile.next());
            }
        }
        catch(IOException e){
            System.out.println("Error with file: "+e.getMessage());
        }
    }


    void encrypt(){
        for (int i =0; i<text.size();i++){
            char Letter;
            String wordFromText;
            int indexLetter;
            String encryptedWord= "";
            StringBuilder stringBuilder = new StringBuilder();
            wordFromText = text.get(i);
            String wholeWord="";

           for (int l=0; l<wordFromText.length();l++) {
               Letter = wordFromText.charAt(l);

               if (!Character.isLetterOrDigit(Letter)){
                   //Somehow separate each word into its own variable to encrypt the letters
                   System.out.println("special character: "+Letter +" at index: "+l);
                   if (Character.isWhitespace(Letter)){
                       int j=l;
                       char blankLetter;

                       while (true){
                           blankLetter = wordFromText.charAt(j-1);
                           if (Character.isWhitespace(blankLetter)){
                               wholeWord = wordFromText.substring(j,i+1);
                               break;
                           } else{
                               j--;
                           }
                       }
                   }
               }
               for (int y=0; y< wholeWord.length();y++){
                   Letter = wholeWord.charAt(y);
                   indexLetter = alphabet.indexOf(Letter);
                   Letter = alphabet.get(indexOverZ(indexLetter));
                   encryptedWord = String.valueOf(stringBuilder.append(Letter));
               }

           }
            //System.out.println("encryptedWord: "+encryptedWord);
            encryptedText.add(encryptedWord);
        }
        System.out.println(encryptedText);
    }

    int indexOverZ(int indexLetter){
        if (indexLetter >=24){
            indexLetter =25-indexLetter-1;
        }
        return indexLetter+2;
    }
}

