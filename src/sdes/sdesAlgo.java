
package sdes;

import java.util.Arrays;

/**
 *
 * @author alouvoul
 */
public class sdesAlgo {

    public sdesAlgo(boolean encrypt, char[] plaintext) {
        char[] key1 = new char[10];
        char[] key2 = new char[10];
        
        char[] ip = IP(plaintext);
        function(plaintext, key1);
    }
    
    private char[] IP(char[] plaintext){
        char[] permutation = new char[8];
        
        permutation[0] = plaintext[1];
        permutation[1] = plaintext[5];
        permutation[2] = plaintext[2];
        permutation[3] = plaintext[0];
        permutation[4] = plaintext[3];
        permutation[5] = plaintext[7];
        permutation[6] = plaintext[4];
        permutation[7] = plaintext[6];
               
        return permutation;
    }
    
    /**
     * 
     * @param plaintext 4 digit code
     * @return 
     */
    private char[] EP(char[] plaintext){
        char[] permutation = new char[8];
        
        permutation[0] = plaintext[3];
        permutation[1] = plaintext[0];
        permutation[2] = plaintext[1];
        permutation[3] = plaintext[2];
        permutation[4] = plaintext[1];
        permutation[5] = plaintext[2];
        permutation[6] = plaintext[3];
        permutation[7] = plaintext[0];
    
        return permutation;
    }
    
    private char[] function(char[] plaintext, char[] key){
        int length = plaintext.length;
        char[] left = Arrays.copyOfRange(plaintext, 0, length/2);
        char[] right = Arrays.copyOfRange(plaintext, length/2, length);
    
        char[] FText;
        FText = F(right, key);
        
        char[] leftConvertion = XOR(left,FText);
        
        
        return FText;
    }
    
    private char[] F(char[] right, char[] key){
        
        char[] ep = EP(right);
        char[] temp = XOR(ep,key);
        
        
        return ;
    }
    
    private char[] XOR(char[] left, char[] right){
        
        char[] convertedLeft = new char[left.length];
        
        for (int i = 0; i < left.length; i++) {
            convertedLeft[i] = (char) (left[i] ^ right[i]);
        }
        return convertedLeft;
    }
}
