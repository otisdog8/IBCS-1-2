import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class RootJLab5b {
   static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
   static Random rand = new Random(); //Makes it so everyone can use this Random


  public static void main(String[] args) {
    int response;
    String[] items = {"End Program", "Get Character", "Start and End character", "Nextint", "Sum Integers"}; //Menu code
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
        for(int i = 0; i < linenum; i++) {
            tab(linenum - i,i); // Using recursion to avoid nested loops
            System.out.println();
        }
    }

    private static void tab(int printnum, int tabnum) {
        System.out.print(printnum);
        System.out.print((char) 9);
        if (tabnum > 0) {
            tab(printnum,tabnum-1);
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
        for (long i = 0; i < size; i++) {
            result = i + 1;
            System.out.print((i+1) + "!= " + (i + 1));
            for (long j = i; j > 0; j--) {
                System.out.print(" x " + j);
                result *= j;
            }
            System.out.println(" = " + result);
        }
    }

    private static void thousandprime() {
        System.out.print("Enter the nth prime that you want to find:  ");
        int prime = ensureint();
        ArrayList<Integer> primes = primefinder(1000000,prime,0);
        System.out.println("Your prime is:  " + primes.get(primes.size() - 1));
    }

    private static ArrayList<Integer> primefinder(int frame_size, int num_to_find, int max_frames) { //Frame_size for sieve, num_to_find is how many primes to find, max_frames is the meximum amount of frames to find
        ArrayList<Integer> primes = new ArrayList<Integer>();
        Queue<Integer> primequeue = new LinkedList<Integer>();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int frames_calculated = 0;
        int number_of_primes = 1;

        primes.add(2);
        primequeue.add(2);

        do {
            nums.clear();

            for (int i = frames_calculated*frame_size+3; i < (frames_calculated+1)*frame_size; i++) {
                nums.add(i);
            }

            do {
                final int testednum = primequeue.poll();
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
}