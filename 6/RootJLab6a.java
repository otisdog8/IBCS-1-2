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
        

        //fillprimearray(primearray,num_to_find);
        testprimes();
        //do {
        //    System.out.print("\n Enter the prime number you would like to find:   ");
        //    response = ensureint();
        //    System.out.print("\nFound prime number:  " + primearray[response-1]);
        //} while (response != -1);
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

    private static void fillprimearray(long[] primearray, int num_to_find) {
        ArrayList<Long> primes = primefinder(1000000,num_to_find,0);
        for (int i = 0; i < num_to_find; i++) {
            primearray[i] = primes.get(i);
        }
    }
    
    private static ArrayList<Long> primefinder(int frame_size, int num_to_find, int max_frames) {
        // Frame_size for sieve, num_to_find is how many primes to find, max_frames is the meximum amount of frames to find
        // sieve of eratosthenes
        ArrayList<Long> primes = new ArrayList<Long>();
        Queue<Long> primequeue = new LinkedList<Long>();
        ArrayList<Long> nums = new ArrayList<Long>();
        long frames_calculated = 0;
        long number_of_primes = 1;

        primes.add((long) 2);
        primequeue.add((long) 2);

        do {
            nums.clear();

            for (long i = frames_calculated*frame_size+3; i < (frames_calculated+1)*frame_size; i++) {
                nums.add(i);
            }

            do {
                final long testednum = primequeue.poll();
                nums.removeIf(n -> (n % testednum == 0));
                if (primequeue.size() == 0) {
                primequeue.add(nums.get(0));
                primes.add(nums.get(0));
                number_of_primes++;
            }
            } while (primequeue.size() != 0 && nums.size() != 1 && number_of_primes < num_to_find);

            primequeue.addAll(primes);
            frames_calculated++;
        } while (frames_calculated != max_frames && number_of_primes < num_to_find);

        return primes;
    }

    private static void testprimes() {
        int numtofind = 1000;
        ArrayList<Long> primes = sieve(numtofind,1000000);
        for (int i = 0; i < numtofind; i++) {
            System.out.println(primes.get(i));
        }
    }

    private static ArrayList<Long> sieve(int numofprimes,int framesize) {
        int offset = +2;
        int numfound = 0;
        boolean primefound = false;
        ArrayList<Long> results = new ArrayList<Long>();

        do {
            boolean[] primes = new boolean[framesize];
            for (int i = 0; i < framesize; i++) {
                primes[i] = true;
            }
            
            for (int i = 0; i < results.size(); i++) {
                prime = results.get(i);
                
            }

            for (int index = 0; Math.pow(index + offset, 2) < framesize + offset; index++) {
                if (primes[index] == true) {
                    int prime = index + offset;

                    for (int i = prime; i < framesize + offset; i += prime) {
                        primes[i-offset] = false;
                    }
                    
                    results.add((long) prime);
                    numfound++;
                }
            }

            if (numfound > numofprimes) {
                primefound = true;
            }
            
            
            offset += framesize;

            
        } while (!primefound);

        return results;
    }
}