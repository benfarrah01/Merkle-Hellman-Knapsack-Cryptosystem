// Benjamin Farrah
// 95-771 Data Structure and Algorithms for Information Processing
// Project 1 Part 2

package MHK;

import java.math.BigInteger;
import java.util.Random;

public class SLLKeyGen {
    int blockSize;
    SinglyLinkedList superIncreasing;
    SinglyLinkedList pub;
    BigInteger r;
    BigInteger q;

    SinglyLinkedList priv;

    // control center, calls the other functions
    public SLLKeyGen(int blockSize){
        this.blockSize = blockSize;
        priv = new SinglyLinkedList();
        genSuperIncreasing();
        findQ();
        findR();
        publicKey();
        priv.addAtEndNode(superIncreasing);
        priv.addAtEndNode(q);
        priv.addAtEndNode(r);
    }

    // generate the super increasing linked list
    public void genSuperIncreasing(){
        // Initialize SinglyLinkedList to store the random superincreasing integers
        superIncreasing = new SinglyLinkedList();

        // populate with powers of 7
        for (int i = 0; i < blockSize; i++){
            superIncreasing.addAtEndNode(BigInteger.valueOf(7).pow(i));
        }
    }

    // Calculate q based on the sum of the superincreasing sequence
    public void findQ(){
        superIncreasing.reset();
        BigInteger count = BigInteger.valueOf(0);

        // calculate total of all numbers in super increasing list
        for (int i = 0; i < blockSize; i++){
            count = count.add((BigInteger) superIncreasing.next());
        }
        Random rand = new Random();
        BigInteger upperLimit = count;

        // generate a random value that is bigger than the sum of all values in superincreasing list
        // The following do-while loop is from
        // https://stackoverflow.com/questions/2290057/how-to-generate-a-random-biginteger-value-in-java
        BigInteger randomNumber;
        do {
            randomNumber = new BigInteger(upperLimit.bitLength(), rand);
        } while (randomNumber.compareTo(upperLimit) >= 0);
        randomNumber = randomNumber.add(count);
        this.q = randomNumber;
    }

    public void findR(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(blockSize);
        superIncreasing.reset();

        // find r, which must be coprime with q
        BigInteger greatestValue = (BigInteger) superIncreasing.getLast();
        boolean rFound = false;
        while(!rFound){
            BigInteger tempR = BigInteger.valueOf(rand.nextInt(1000000000)).add(greatestValue);
            if (gcdByEuclidsAlgorithm(tempR,q).equals(BigInteger.valueOf(1))){
                this.r = tempR;
                rFound = true;
            }
        }
    }

    public void publicKey(){
        // initialize SinglyLinkedList to store the public key
        SinglyLinkedList publicKeyList = new SinglyLinkedList();

        // iterate through the superincreasing sequence and calculate public key
        superIncreasing.reset();
        for (int w = 0; w < blockSize; w++){
            BigInteger currentNode = (BigInteger) superIncreasing.next();
            // multiply r by the current element of superincreasing
            BigInteger currentNodeR = currentNode.multiply(r);
            BigInteger b = currentNodeR.mod(q);
            publicKeyList.addAtEndNode(b);
        }
        publicKeyList.reset();
        this.pub = publicKeyList;
    }

    // This function is taken from https://www.baeldung.com/java-greatest-common-divisor
    private BigInteger gcdByEuclidsAlgorithm(BigInteger n1, BigInteger n2) {
        if (n2.equals(BigInteger.valueOf(0))) {
            return n1;
        }
        return gcdByEuclidsAlgorithm(n2, n1.mod(n2));
    }
}
