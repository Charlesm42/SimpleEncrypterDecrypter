import javax.swing.*;
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
        main.shiftAmount();
        main.choice();
    }

    void shiftAmount(){
       while (true){
           String input = JOptionPane.showInputDialog("Please enter the amount you want to shift the letters by: ");
           try {
               shiftEncryptionAmount = Integer.parseInt(input);
               break;
           }catch (NumberFormatException e){
               JOptionPane.showMessageDialog(null,"Please enter a valid integer to use.");
           }
       }
    }

    void choice(){
        String[] choice = {"Encrypt","Decrypt"};
        String input = (String) JOptionPane.showInputDialog(
                null,
                "Please choose between encrypting or decrypting:",
                "Choice",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]
        );

        if (!input.isBlank()&&input.equals("Encrypt")){
            encrypt(true);
        } else if (!input.isBlank()&&input.equals("Decrypt")){
            encrypt(false);
        }
    }

    int indexUnderZ(int indexLetter){
        return ((indexLetter - shiftEncryptionAmount) % 26 + 26) % 26;
    }


    void populateArray() {
        for (int i = 0; i < 26; i++) {
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


    void encrypt(boolean encrypt){
        ArrayList<String> encryptedText = new ArrayList<>();
        for (int i=0;i<text.size();i++){
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
                    if (encrypt){
                        Letter = alphabet.get(indexOverZ(indexLetter));
                    }else Letter = alphabet.get(indexUnderZ(indexLetter));
                }
                encryptedWord = String.valueOf(stringBuilder.append(Letter));
            }
            encryptedText.add(encryptedWord);
        }
        System.out.println(encryptedText);
        System.out.println(writeToFile(encryptedText));
        System.out.println("Shifted by "+shiftEncryptionAmount+" letters.");
    }

    int indexOverZ(int indexLetter){
        return (indexLetter + shiftEncryptionAmount) % 26;
    }

    String writeToFile(ArrayList<String> text){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<text.size();i++){
            stringBuilder.append(text.get(i)).append(" ");
        }
        String totalString = stringBuilder.toString();

        try (FileWriter fileWriter = new FileWriter("Text.txt")){
            fileWriter.write(totalString);
        } catch(IOException e) {
            System.out.println("Error with file: " + e.getMessage());
        }
        return totalString;
    }
}

