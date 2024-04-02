// Benjamin Farrah
// 95-771 Data Structure and Algorithms for Information Processing
// Project 1 Part 2

package MHK;

import java.math.BigInteger;

public class Decryption {

    public Decryption(SinglyLinkedList priv, Encryption encrypt){
        Decrypt(encrypt.cipherText, priv);

    }
    // Decryption
    // PRE: p and q must be positive integers
    public void Decrypt(BigInteger c, SinglyLinkedList priv){
        //private key: (W,q,r)
        priv.reset();

        // superincreasing list
        Object wObj = priv.next();
        SinglyLinkedList W = (SinglyLinkedList) wObj;

        // q
        Object qObj = (Object) priv.next();
        BigInteger q = (BigInteger) qObj;

        // r
        Object rObj = (Object) priv.next();
        BigInteger r = (BigInteger) rObj;

        // r` = r^(-1) % q
        BigInteger rPrime = r.modInverse(q);
        // c` = (c * r`) % q
        BigInteger cPrime = (c.multiply(rPrime)).mod(q);

        W.reset();
        // variable to store the bits from decryption
        String integers = "";

        // find the largest element in W which is <= c`
        // iterate through W backwards, starting with largest number
        for (int i = W.countNodes-1; i >= 0; i--){
            ObjectNode currentNode = (ObjectNode) W.getObjectAt(i);
            Object data = currentNode.getData();
            BigInteger currentNodeNum = (BigInteger)data;

            // check if the current number is <= c`
            if(currentNodeNum.compareTo(cPrime) <= 0) {
                // subtract current number from c`
                cPrime = cPrime.subtract(currentNodeNum);
                // since the number was <= c`, this is a success. 1 is concatenated to the binary string
                integers += '1';
            } else {
                // number was greater than c`, 0 is concatenated to binary string
                integers += '0';
            }
        }

        // reverse the binary string
        // https://stackoverflow.com/questions/7569335/reverse-a-string-in-java
        String result="";
        for(int i=integers.length()-1; i>=0; i--) {
            result = result + integers.charAt(i);
        }

        // string to store the final decryption
        String answer = "";

        // iterate over every 8 bits, since 8 bits are 1 character
        for (int i = 0; i < result.length()/8; i++){
            // start at i*8 because we move forward 8 bits after every iteration
            int start = i*8;

            // get substring, equal to 1 character
            String letterBinary = result.substring(start,start+8);

            // convert binary string to ascii
            int numberRepresentation = Integer.parseInt(letterBinary,2);

            // convert ascii to character
            char letter = (char)numberRepresentation;

            // concatenate letter to answer string
            answer += letter;
        }
        System.out.println("Result of decryption: "+answer);
    }
}
