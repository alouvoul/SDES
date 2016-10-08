
package sdes;

import java.util.Arrays;


/**
 *
 * @author alouvoul
 */
public class SDES{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] plaintext = {'1','0','1','0','1','0','1','0'};
        char[] key = {'1','1','1','0','0','0','1','1','0','0'};
        sdesAlgo sdes = new sdesAlgo(true, plaintext, key);
    }
    
}
