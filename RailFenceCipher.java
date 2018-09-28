import java.util.*;

/*
 * Implementation of the Rail Fence cipher.
 * -
 * @author: jedwardmc
 * @date: 9.09.2018
 *
 */


public class RailFenceCipher {
    
    public static void main(String[] args) {
        
        // Declare the plaintext and key size variables
        String plainText = "";
        int keySize = 0;
        
        // Declare the error status for the menu loop
        boolean error = true;
        
        while(error != false) {
            // Print out the application title
            System.out.println();
            System.out.println("RAIL FENCE CIPHER >>>");
            
            // Get plaintext input from the user in the console
            System.out.print("Enter plaintext: ");
            Scanner textScanner = new Scanner(System.in);
            String textInput = textScanner.nextLine();
            
            // Get the key size from the user in the console
            System.out.print("Enter key size: ");
            Scanner keyScanner = new Scanner(System.in);
            String keyInput = keyScanner.nextLine();
            
            
            // Validate the text input
            if(textInput.length() < 1) {
                System.out.println("------------------------------------");
                System.out.println("| Error: please enter plain text.  |");
                System.out.println("------------------------------------");
                error = true;
            } else {
                // Set the plaintext to the text input
                plainText = textInput;
                error = false;
            }
            
            // Validate the key input
            String keyRegex = "[0-9]+";
            
            if(keyInput.matches(keyRegex) == false || keyInput.length() == 0) {
                // Digits are permitted only in the console
                System.out.println("------------------------------------");
                System.out.println("| Error: please enter a number.    |");
                System.out.println("------------------------------------");
                error = true;
            } else {
                // Convert the key input to an integer
                keySize = Integer.parseInt(keyInput);
                error = false;
            }
        }
        
        // Encrypt the plain text and the key
        encrypt(plainText, keySize);
        
    }
    
    public static void encrypt(String plainText, int keySize) {
        
        // Declare the cipher text string
        String cipherText = "";
        int row = 0, col = 0;
    
        // Remove the spaces and unnecessary characters
        plainText = plainText.replaceAll("[^a-zA-Z]+", "");
        plainText = plainText.toLowerCase();
        
        // Create the cipher text multi-dimensional array
        String[][] cipher = new String[row]
        
        // Create the array containing all letters from plain text
        String[] letters = plainText.split("");

        System.out.print(">>> Cipher Text: ");
        // Generate the rail fence cipher text
        for(int i=0; i < letters.length; i++) {
            
            
            
            cipherText = letters[i];
            System.out.print(cipherText);
        }
        
        // Print the rail fence cipher text
        System.out.println();
        
        


        
    }
    
}
