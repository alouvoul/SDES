
package sdes;

import java.util.Arrays;

/**
 *
 * @author alouvoul
 */
public class sdesAlgo {

    public sdesAlgo(boolean encrypt, char[] plaintext, char[] key) {
        char[] key1 = new char[8];
        char[] key2 = new char[8];
        char[] encryptedMessage;
        char[] decryptedMessage;
        System.out.println("main-->"+ Arrays.toString(plaintext));
        key1 = keyManagement(key, true);    //first key declared with true
        key2 = keyManagement(key, false);
        
        //if(encrypt){
            char[] ip = IP(plaintext);
            char[] f = function(ip, key1);
            char[] sw = SW(f);
            f = function(sw, key2);
            encryptedMessage = IIP(f);
//==========================================================
            ip = IP(encryptedMessage);
            f = function(ip, key2);
            sw = SW(f);
            f = function(sw, key1);
            decryptedMessage = IIP(f);
            System.out.println("Array is: "+ Arrays.toString(decryptedMessage));
        
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
    
    private char[] IIP(char[] plaintext){
        char[] permutation = new char[8];
        
        permutation[0] = plaintext[3];
        permutation[1] = plaintext[0];
        permutation[2] = plaintext[2];
        permutation[3] = plaintext[4];
        permutation[4] = plaintext[6];
        permutation[5] = plaintext[1];
        permutation[6] = plaintext[7];
        permutation[7] = plaintext[5];
               
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
        char[] left = Arrays.copyOfRange(plaintext, 0, length/2-1);
        char[] right = Arrays.copyOfRange(plaintext, length/2-1, length);
        //System.arraycopy(left, 0, plaintext, 0, left.length/2-1);
        //System.out.println("function debug-->"+ Arrays.toString(plaintext));
        char[] FText;
        FText = F(right, key);
        
        char[] leftConvertion = XOR(left,FText);
        
        for (int i = 0; i < leftConvertion.length; i++) {
            plaintext[i] = leftConvertion[i];
        }
        
        return plaintext;
    }
    
    private char[] F(char[] right, char[] key){
        final int[][] S0 = { {1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2} } ;
        final int[][] S1 = { {0,1,2,3}, {2,0,1,3}, {3,0,1,0}, {2,1,0,3}} ;
        
        char[] ep = EP(right);
        char[] temp = XOR(ep,key);
        
        char[] p0 = new char[2];
        char[] p1 = new char[2];
        
        p0[0] =  temp[0];
        p0[1] =  temp[3];
        
        p1[0] =  temp[1];
        p1[1] =  temp[2];
System.out.println("p1-->"+ Arrays.toString(right));//=====================
        int row, column;
        String text = new String(p0);
System.out.println("text-->"+text);
        String text1 = new String(p1);
        row = Integer.parseInt(text, 2);
        column = Integer.parseInt(text1, 2);
        
        char[] finalLeftPart = new char[4];
        
        char[] temp1 = convertToBinary(row, column, S0);
        char[] temp2 = convertToBinary(row, column, S1);
        
        finalLeftPart[0] = temp1[0];
        finalLeftPart[1] = temp1[1];
        finalLeftPart[2] = temp2[0];
        finalLeftPart[3] = temp2[1];
        
               
        return finalLeftPart;
    }
    
    private char[] XOR(char[] left, char[] right){
        
        char[] convertedLeft = new char[left.length];
        
        for (int i = 0; i < left.length; i++) {
            convertedLeft[i] = (char) (left[i] ^ right[i]);
        }
        return convertedLeft;
    }
    
    private char[] convertToBinary(int row, int column, int[][] s){

        char[] leftArray = new char[4];
        
        int temp;
        temp = s[row][column];
        
        if(temp == 0){
            leftArray[0] = '0';
            leftArray[1] = '0';
        }
        else if(temp == 1){
            leftArray[0] = '0';
            leftArray[1] = '1';
        }
        else if(temp == 2){
            leftArray[0] = '1';
            leftArray[1] = '0';
        }
        else{
            leftArray[0] = '1';
            leftArray[1] = '1';
        }
        return leftArray;
        
    }
    
    private char[] SW(char[] plaintext){
        char[] temp = new char[8];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = plaintext[i+temp.length];
            temp[i+temp.length] = plaintext[i];
        }
        
        return temp;
    }
    
    private char[] keyManagement(char[] key, boolean first){
        char[] keyTemp;
        
        if(first){
            char[] temp = P10(key);
            char[] temp1 = Shift(temp);
            keyTemp = P8(temp1);
        }
        else{
            char[] temp = P10(key);
            char[] temp1 = Shift(temp);
            temp1 = Shift(temp1);
            keyTemp = P8(temp1);            
        }
        
        return keyTemp;
    }
    
    private char[] P10(char[] key){
        char[] permutation = new char[10];
        
        permutation[0] = key[2];
        permutation[1] = key[4];
        permutation[2] = key[1];
        permutation[3] = key[6];
        permutation[4] = key[3];
        permutation[5] = key[9];
        permutation[6] = key[0];
        permutation[7] = key[8];
        permutation[8] = key[7];
        permutation[9] = key[5];
    
        return permutation;
    }
    
    private char[] P8(char[] key){
        char[] permutation = new char[8];
        
        permutation[0] = key[5];
        permutation[1] = key[2];
        permutation[2] = key[6];
        permutation[3] = key[3];
        permutation[4] = key[7];
        permutation[5] = key[4];
        permutation[6] = key[9];
        permutation[7] = key[8];
        
        return permutation;
    }
        
    private char[] Shift(char[] key){
        char[] shifted = new char[key.length];
        
        for (int i = 0; i < key.length-1; i++) {
            shifted[i] = key[i+1];
        }
        shifted[shifted.length-1] = key[0];
        
        return shifted;
    }
        
}
