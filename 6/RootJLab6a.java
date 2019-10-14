//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class RootJLab6a {
   static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

    public static void main(String[] args) {
        System.out.print("How many primes do you want to find:   ");
        int num_to_find = ensureint();
        
        long[] primearray = new long[num_to_find];
        int response;
        

        primearray = fillprimearray(primearray,num_to_find);

        do {
            System.out.print("\n Enter the prime number you would like to find:   ");
            response = ensureint();
            System.out.print("\nFound prime number:  " + primearray[response-1]);
        } while (response != -1);
    }

    private static int ensureint() {
        boolean isint = false;
        int result = 0;

        while (!isint) {
            if (input.hasNextInt()) {
                result = input.nextInt();
                isint = true;
            }
            else {
                input.nextLine();
                System.out.print("Please enter an int\n");
            }
        }

        return result;
    }

    private static long[] fillprimearray(long[] primearray) {// Put here to fufill lab requirements
        return fillprimearray(primearray, 1000); 
    }

    private static long[] fillprimearray(long[] primearray, int num_to_find) {
        primearray = sieve(num_to_find, 2147483645);
        return primearray;
    }
    


    private static long[] sieve(int numofprimes,int framesize) {
        long offset = +2;
        int numfound = 0;
        boolean primefound = false;
        long[] results = new long[numofprimes];
        long firstvalue;


        do {
            boolean[] primes = new boolean[framesize];
            for (int i = 0; i < framesize; i++) {
                primes[i] = true;
            }
            
            for (int index = 0; index < numfound; index++) {
                long prime = results[index];
                firstvalue = (offset / prime) * prime - offset;
                if (firstvalue < 0L) {
                    firstvalue += prime;
                }
                firstvalue += offset;

                for (long i = firstvalue; i < framesize + offset; i += prime) {
                        primes[(int) (i-offset)] = false;
                }

                
            }

            for (int index = 0; index < framesize; index++) {
                if (primes[index] == true) {
                    long prime = index + offset;

                    for (long i = prime; i < framesize + offset; i += prime) {
                        primes[(int) (i-offset)] = false;
                    }
                    
                    results[numfound] = (long) prime;
                    numfound++;
                    if (numfound == numofprimes) {
                        primefound = true;
                        break;

                    }
                }
            }


            offset += framesize;

            
        } while (!primefound);

        return results;
    }
}