// Benjamin Farrah
// 95-771 Data Structure and Algorithms for Information Processing
// Project 1 Part 2

package MHK;

import java.math.BigInteger;

public class Encryption {
    SinglyLinkedList pub;
    SinglyLinkedList encryption;
    BigInteger cipherText;

    public Encryption(String input, SinglyLinkedList pub, SinglyLinkedList priv){
        this.pub = pub;
        encryption = new SinglyLinkedList();
        getBinary(input, priv);

    }

    public void getBinary(String input, SinglyLinkedList priv){
        // convert each byte to binary, store in SLL of Strings
        pub.reset();
        SinglyLinkedList B = new SinglyLinkedList();

        for (int i = 0; i < pub.countNodes(); i++){
            Object number = pub.next();
            B.addAtEndNode((BigInteger)number);
        }

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

        // variable for storing ciphertext
        BigInteger total = BigInteger.valueOf(0);

        // convert string input to char array
        char[] chars = input.toCharArray();

        // variable for holding the binary representation of each letter
        String binaryStr = "";
        for (char aChar : chars) {
            binaryStr += String.format("%8s", Integer.toBinaryString(aChar));
        }

        // ensure each binary string has a leading 0 to be 8 characters long
        binaryStr = binaryStr.replaceAll(" ", "0");

        // iterate over every bit in the binary string
        pub.reset();
        for (int i = 0; i < binaryStr.length(); i++){
            // ensure each bit is treated as an int rather than a char
            int bit = 0;
            if (binaryStr.charAt(i)=='1') {
                bit = 1;
            }
            // multiply each bit (0 or 1) by the corresponding element of the public key
            Object bObj = (Object) pub.next();
            BigInteger b = (BigInteger) bObj;

            // add the result of the multiplication from each iteration
            total = total.add(b.multiply(BigInteger.valueOf(bit)));
        }

        // final cipher text
        this.cipherText = total;
    }
}
