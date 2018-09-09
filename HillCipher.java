import java.util.*;

/*
 * Unique implementation of the Hill cipher.
 * - 
 * @author: jedwardmc
 * @date: 9.09.2018
 */

public class HillCipher {
    
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args) {
    
        
        int[] keyMatrix = {6, 14, 3, 0};
        encrypt("love does not boast", keyMatrix);
        
    }
    
    public static void encrypt(String plainText, int[] keyMatrix) {
        
        // Clean up the plain text for later use
        
        plainText = plainText.toLowerCase();
        plainText = plainText.replaceAll("[^a-zA-Z]+", "");
        
        // Add an additional character if the length is odd
        // This will assist in creating a digraph later on
        
        if(plainText.length() % 2 != 0) {
            plainText += "e";
        }
        
        // Shorthand variable for storing the length of plainText
        int length = plainText.length();
        
        // Stores numerical equivalents of each letter
        int[] numbers = new int[length];
        
        // Get the numerical equivalent of all letters in the plaintext
        
        for (int i=0; i < length; ++i) {
            
            // Get the numerical equivalent of each letter in the alphabet
            int charValue = ALPHABET.indexOf(plainText.charAt(i));
            
            // Add them the numbers array list
            numbers[i] = charValue;

        }
        
        // Digraphs need X and Y values in the format (0 1) (2 3) (.. ..)
        // Declare two integer arrays containing the indices for finding
        // numbers in the numbers array
        
        int[] xi = new int[numbers.length/2];
        int[] yi = new int[numbers.length/2];
        
        // Generate the ciphertext
        
        System.out.println();
        System.out.println("HILL CIPHER ::::::::::::::");
        System.out.println("\t KEY: " + Arrays.toString(keyMatrix));
        System.out.println("\t ORIGINAL TEXT: " + plainText);
        System.out.print("\t CIPHER TEXT: ");
        
      
        for(int n=0; n < numbers.length / 2; n++) {
            xi[n] = n * 2;
            yi[n] = xi[n] + 1;
            
            // Get the numbers using the created indices
            
            int xVal = numbers[ xi[n] ];
            int yVal = numbers[ yi[n] ];
            
            // Generate the digraphs using matrix multiplication
            
            int digraphX = (xVal * keyMatrix[0] + keyMatrix[1] * yVal) % 26;
            int digraphY = (xVal * keyMatrix[2] + keyMatrix[3] * yVal) % 26;
            
            // Transform them into textual equivalents
            
            char resultX = ALPHABET.charAt(digraphX);
            char resultY = ALPHABET.charAt(digraphY);
            
            // Print the final result in digraph form
            
            System.out.print("(" + resultX + " " + resultY + ")");
            

        }

        // Print a new line 
        System.out.println("\n");

    }
}
