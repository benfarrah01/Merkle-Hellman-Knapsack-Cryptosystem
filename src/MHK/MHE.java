// Benjamin Farrah
// 95-771 Data Structure and Algorithms for Information Processing
// Project 1 Part 2

package MHK;

import java.util.Scanner;
public class MHE {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // get input from user
        System.out.println("Enter a string and I will encrypt it as single large integer.");
        String input = sc.nextLine();
        System.out.println("Clear text: \n" + input);
        byte[] byteArray = input.getBytes();
        System.out.println("Number of clear bytes: " + byteArray.length);

        // generate a key
        SLLKeyGen myKeyGen = new SLLKeyGen(640);

        // encrypt the input
        Encryption encrypt = new Encryption(input,myKeyGen.pub,myKeyGen.priv);
        System.out.println(input+" is encrypted as \n"+ encrypt.cipherText);

        // decrypt the input
        Decryption decrypt = new Decryption(myKeyGen.priv, encrypt);

    }
}
