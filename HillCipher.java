import java.util.*;

/* Unique implementation of the Hill Cipher.
 * Redesigned in 2019.
 * @author: Jasper Cherry
 * @date: 23.12.2019
 */

public class HillCipher {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static Scanner inputScanner = null;
    private static String inputStr = null;
    
    public static String prompt(String msg) {
        System.out.println(msg);
        inputScanner = new Scanner(System.in);
        inputStr = inputScanner.nextLine();
        return inputStr;
    }
    
    public static void encrypt(String plainText, String keyMatrix) {
        
        int[] alphNums = new int[plainText.length()];
        int xval, yval, dxval, dyval;
        char rxval, ryval;
        
        
        /* clean up the plain text for later use */
        plainText = plainText.toLowerCase();
        plainText = plainText.replaceAll("[^a-zA-Z]+", "");
        
        if (plainText.length() % 2 != 0) plainText += "e";
        
        for(int ii=0; ii < plainText.length(); ++ii) {
            int tempPos = plainText.charAt(ii) - 'a' + 1;
            alphNums[ii] = tempPos;
        }
        
        String[] tempMatrix = keyMatrix.split(",", 0);
        int[] matrix = new int[tempMatrix.length + 1];
        
        try {
            int i=0;
            for (String s : tempMatrix ) {
                i++;
                int newKey = Integer.parseInt(s);
                matrix[i] = newKey;
            }
        } catch(Exception e) {
            System.out.println("Invalid key matrix." + e);
        }
        
        
        int[] xi = new int[alphNums.length/2];
        int[] yi = new int[alphNums.length/2];
        
        for (int nn=0; nn < alphNums.length / 2; ++nn) {
            xi[nn]=nn*2;
            yi[nn]=xi[nn]+1;
            
            xval = alphNums[xi[nn]];
            yval = alphNums[yi[nn]];
            
            dxval = (xval * matrix[0] + matrix[1] * yval) % 26;
            dyval = (xval * matrix[2] + matrix[3] * yval) % 26;
            
            rxval = alphabet.charAt(dxval);
            ryval = alphabet.charAt(dyval);
            
            System.out.print("(" + rxval + " " + ryval + ")");
        }
        System.out.println(); 
    }
    
    public static void main(String[] args) {
        String plainTextStr = prompt("Enter a plain text: ");
        String keyMatrixStr = prompt("Enter a key matrix: ");
        encrypt(plainTextStr, keyMatrixStr);
    }
}
