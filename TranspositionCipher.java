import java.util.*;

public class TranspositionCipher {
    
    //
    // this row transposition cipher is inspired by the algorithm described in:
    // http://crypto.interactive-maths.com/columnar-transposition-cipher.html
    //
    // date:   28.09.2018
    // author: jedwardmc
    //
    
    public static void main(String[] args) {
        
        // define the key matrix, key word and the plain text
        String plainText = "The tomato is a plant in the nightshade family";
        int[] keyMatrix = {5, 3, 2, 1, 6, 4};
        String keyWord  = "tomato";
        
        // run the encrypt method using the previous parameters
        encrypt(plainText, keyWord, keyMatrix);
        
    }
    
    public static void encrypt(String plainText, String keyWord, int[] keyMatrix) {
        
        
        // change the plaintext to lowercase and remove unnecessary characters and spaces
        plainText = plainText.toLowerCase();
        plainText = plainText.replaceAll("[^a-zA-Z+]", "");
        
    
        // check the length of the keyword and key matrix which must be the same to continue encryption
        
        if(keyMatrix.length != keyWord.length()) {
            // print an error message when the key matrix and keyword are not the same
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| Error: key matrix and key word lengths must be the same.         |");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| KEY MATRIX: " + keyMatrix.length + "                             |");
            System.out.println("| KEY WORD: " + keyWord.length() + "                               |");
            System.out.println("--------------------------------------------------------------------");
        } else {
            // definition of the tree map containing the sorted key word and key matrix
            TreeMap<Integer, Character> sortedValues       = new TreeMap<>();
            TreeMap<Integer, Character> scrambledText      = new TreeMap<>();
            
            // create the numbers and values array list
            ArrayList<Integer>   keys   = new ArrayList<>();
            ArrayList<Character> values = new ArrayList<>();
            
            // definition of the keyword as an array
            String[] keyWordArray = keyWord.split("");
            
            // definition of the columns and rows in the cipher
            int columns = keyMatrix.length;
            int rows    = plainText.length() / columns;
            
            
            // counter variables
            int sortedPos     = -1;
            int keyPos        = -1;
            int tablePos      = -1;
            

            // definition of the final cipher text string
            String cipherText = "";
            
            
            // add null values if the plain text is odd length
            while(plainText.length() % keyMatrix.length != 0) {
                plainText += "x";
            }
            
            // print the header for the entire program
            System.out.println("----------------------------");
            System.out.println("| ROW TRANSPOSITION CIPHER |");
            System.out.println("----------------------------");
            
            // print the header for the original unmodified cipher table
            System.out.println("Original cipher table:\n");

            // print the key word and key matrix
            System.out.println(Arrays.toString(keyWordArray));
            System.out.println(Arrays.toString(keyMatrix));
        
            // create the initial cipher table unsorted
            
            for(int y=0; y <= rows;  y++) {
                for(int x=0; x < columns; x++) {
                    // get the position for each letter
                    tablePos += 1;
                    // turn the plain text into array of characters
                    String[] letters = plainText.split("");
                    // find the character within plain text
                    System.out.print(" " + letters[tablePos] + " ");
                    
                }
                // print a new line for each row
                System.out.println();
            }
        
        // print a new line between the sorted and unsorted matrices
        System.out.println();
        
        // print a new header for the modified table
        System.out.println("Sorted cipher table:\n");

            
        // loop through the key matrix
        while(sortedPos < keyMatrix.length-1) {
            sortedPos++;
            // add the letters and numbers to the tree map
            sortedValues.put(keyMatrix[sortedPos], keyWord.charAt(sortedPos));
            
        }
            
        // print out the newly sorted key matrix and keyword
        System.out.println(sortedValues.values());
        System.out.println(sortedValues.keySet());

            
        // loop through the entire plaintext
        for(int yk=0; yk <= rows; yk++) {
            for(int xk=0; xk < columns; xk++) {
                    // increment the key position by one
                    keyPos += 1;
                
                    // attach the repeated old key matrix to keys
                    keys.add(keyMatrix[xk]);
                    values.add(plainText.charAt(keyPos));
                
                    // put the keys and values into the tree map
                    scrambledText.put(keys.get(keyPos), values.get(keyPos));
            }
        
            // print the scrambled text
            System.out.print(scrambledText.values());

            // print a new line at the end of each column
            System.out.println();
        }

        // print a new line
        System.out.println();
        }
    }
}
