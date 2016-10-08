
package sdes;


/**
 *
 * @author alouvoul
 */
public class SDES{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] plaintext = {1,0,1,1,0,0,1,1};
        new sdesAlgo(true, plaintext);
    }
    
}
