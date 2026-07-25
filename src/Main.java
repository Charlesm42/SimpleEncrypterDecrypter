import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public ArrayList<String> text = new ArrayList<>();
    ArrayList<Character> alphabet = new ArrayList<>();
    int shiftEncryptionAmount =2;

    static void main(String[] args){
        Main main = new Main();
        main.populateArray();
        main.readFromFile();
        main.encrypt();
    }

    void populateArray() {
        for (int i = 0; i < 25; i++) {
            alphabet.add((char) ('a' + i));                                                                //Loops through the array of the alphabet and adds the letters to each
        }
    }

    void readFromFile() {
        try(Scanner scFile = new Scanner(new FileInputStream("Text.txt"))){
            while (scFile.hasNext()) {
                text.add(scFile.next().toLowerCase());                                                                    //Uses .next instead of .nextLine because we want each individual word from the index
            }
        }
        catch(IOException e){
            System.out.println("Error with file: "+e.getMessage());
        }
    }


    void encrypt(){
        ArrayList<String> encryptedText = new ArrayList<>();
        for (int i=0;i<text.size();i++){
            //each increment of i add a finder for the array that allows for each different line to be distinguished
            String WholeWord = text.get(i);
            int indexLetter;
            String encryptedWord= "";
            StringBuilder stringBuilder = new StringBuilder();
            for (int k=0;k <WholeWord.length();k++){
                char Letter = WholeWord.charAt(k);
                if (!Character.isLetter(Letter)){
                    System.out.println("Special character at index: "+i);
                } else {
                    indexLetter = alphabet.indexOf(Letter);
                    Letter = alphabet.get(indexOverZ(indexLetter));
                }
                encryptedWord = String.valueOf(stringBuilder.append(Letter));
            }

            encryptedText.add(encryptedWord);
        }
        System.out.println(encryptedText);
        System.out.println(writeToFile(encryptedText));
    }

    int indexOverZ(int indexLetter){
        if (indexLetter >=(26-shiftEncryptionAmount)){
            indexLetter =24-indexLetter;
            return indexLetter;
        }
        return indexLetter+shiftEncryptionAmount;
    }

    String writeToFile(ArrayList<String> text){
        String totalString="";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<text.size();i++){
            totalString = (stringBuilder.append(text.get(i)))+(stringBuilder.append(" ")).toString();
        }

        try (FileWriter fileWriter = new FileWriter("Text.txt")){
            fileWriter.write(totalString);
        } catch(IOException e) {
            System.out.println("Error with file: " + e.getMessage());
        }
        return totalString;
    }
}

