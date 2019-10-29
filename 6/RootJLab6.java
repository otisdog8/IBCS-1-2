//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************


import java.io.*;
import java.util.Scanner;
import java.util.Random;



public class RootJLab6 {
   static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
   static Random rand = new Random(); //Makes it so everyone can use this Random


  public static void main(String[] args) {
    int response;
    String[] items = {"End Program", "Horizontal Tabs", "Numbergame", "Rectangle", "Factorials","Thousand Primes"}; //Menu code
    String menu = makemenu(items);

    do {
        System.out.println(menu);
        response = ensureint();
        if (response == 1) {
            horizontaltabs(); //Runs the specified program
        } else if (response == 2) {
            numbergame();
        } else if (response == 3) {
            rectangle();
        } else if (response == 4) {
            factorials();
        } else if (response == 5) {
            thousandprime();
        } else {
        }

    } while (response != 0);

    System.out.println("Bye!");
    }

    private static String makemenu(String[] items) { //Function generates a menu string so that I don't have to type it out.
        String result = "";
        int length = items.length; //*very* slight optimization

        for (int i = 0; i < length; i++) { //Iterates through the array items and generates a menu entry for each item on items
            result += i + ":  " + items[i] + "\n";
        }

        return result;
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

    private static void numbergame() {
        int solved = 0;
        int guess;
        int numscorrect;
        int guesses = 0;
        int random = rand.nextInt(1001); //Sets it so that it can generate a value of 1000
        do {
            numscorrect = 0;
            System.out.println("Enter your a whole number between 1 and 1000");
            guess = input.nextInt();
            guesses++; //Increments guesses
            if (guess % 10 == random % 10) { //Calculates how many digits are correct
                numscorrect++;
            }
            if (guess % 100 - guess % 10 == random % 100 - random % 10) {
                numscorrect++;
            }
            if (guess % 1000 - guess % 100 == random % 1000 - random % 100) {
                numscorrect++;
            } //The only reason the 1000s number will ever be the same as the answer is when the answer, or when the person for whatever reason decides to break the rules
            if (random == guess) {
                solved++;
                System.out.println("Congratulations you did it in " + guesses + " guesses.");
            } else {
                System.out.println("You got " + numscorrect + " digits correct.");
            }
        } while (solved == 0);
    }

    private static void horizontaltabs() {
        System.out.println("Enter the number of lines to print");
        int linenum = input.nextInt();
        for(int i = 0; i < linenum + 1; i++) { 
            for(int j = 0; j < i; j++) { 
                System.out.print(linenum - i + 1);
                System.out.print((char) 9); //This is a tab
            }
            System.out.println();
        }
    }


    private static void rectangle() {
        System.out.println("Enter the height of the rectangle");
        int height = input.nextInt();
        System.out.println("Enter the width of the rectangle");
        int width = input.nextInt();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0 || j == width-1 || i == 0 || i == height-1) { //Tells it to print an asterix when it is needed
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
    private static void factorials() {
        System.out.println("Enter the size of your factorial table"); //We're using longs here to stave off the inevitable variable overflow
        long size = input.nextLong();
        long result;
        for (long i = 0; i < size; i++) { //Counts up
            result = i + 1;
            System.out.print((i+1) + "!= " + (i + 1));
            for (long j = i; j > 0; j--) { //Counts down and multiplies
                System.out.print(" x " + j);
                result *= j;
            }
            System.out.println(" = " + result);
        }
    }

    private static void thousandprime() {
        System.out.print("How many primes do you want to find:    ");
        int numtofind = ensureint();
        long[] primes = sieve(numtofind,numtofind); //tends to have somewhat efficent results
        System.out.print("Enter the nth prime you want to find (0 to quit):   ");
        int prime = ensureint();
        do {
            System.out.println("Your prime is:  " + primes[prime-1]);
            System.out.print("Enter the nth prime you want to find (0 to quit):   ");
            prime = ensureint();
            

        } while (prime != 0);

    }

    private static long[] sieve(int numofprimes,int framesize) {
        long offset = +2;
        int numfound = 0;
        boolean primefound = false;
        long[] results = new long[numofprimes];
        long firstvalue;


        do {
            boolean[] primes = new boolean[framesize]; //Boolean for marking primes
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