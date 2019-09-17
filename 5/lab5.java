import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

public class lab5 {
  static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
  static Random rand = new Random(); //Makes it so everyone can use this Random
  public static void main(String[] args) {
    int response;
    String[] items = {"End Program", "Character code", "From start to end", "Next int", "Two numbers","Numbergame","Horizontal tabs","Rectangle drawer","Factorial table","The 1000th prime"}; //Menu code
    String menu = makemenu(items);
    do {
      System.out.println(menu);
      response = input.nextInt();
      if (response == 1) {
        charcode(); //Runs the specified program
      } else if (response == 2) {
        startend();
      } else if (response == 3) {
        nextint();
      } else if (response == 4) {
        twonums();
      } else if (response == 5) {
        numbergame();
      } else if (response == 6) {
        horizontaltabs();
      } else if (response == 7) {
        rectangle();
      } else if (response == 8) {
        factorials();
      } else if (response == 9) {
        testprimegenerator();
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
  private static int getchar() {
    try {
      return System.in.read(); //Little hack that makes it a tiny bit easier for me
    }
    catch(IOException e) {
      System.out.println("Error reading character, returned 0");
      return 0;
    }
  }
  private static void charcode() {
    int[] history = new int[]{1,1,1};
    System.out.println("Type 0 in three times in a row to exit");
    int charval;
    do {
      history[0] = history[1];
      history[1] = history[2];
      System.out.println("Enter a number");
      history[2] = getchar();
      getchar(); //Little hack
      System.out.println("Your letter's value was " + history[2] + ". The character was " + (char) history[2] +".");
    } while (history[0] != 48 || history[1] != 48 || history[2] != 48);
  }
  private static void startend() {
    System.out.println("Enter 0 6 times in a row to exit");
    int[] history = new int[]{1,1,1,1,1,1};
    do {
      history[0] = history[1];
      history[1] = history[2];
      history[3] = history[4];
      history[4] = history[5];
      System.out.println("Enter a character");
      int first = getchar();
      history[2] = first;
      getchar(); //So apparently this removes the newline character. Cool.
      System.out.println("Enter another one");
      int second = getchar();
      history[5] = second;
      getchar();
      for (int i = first; i < second + 1; i++) { //Loops through to get all the characters
        System.out.println("ASCII code for " + (char) i + " is " + i + ".");
      }

    } while (history[0] != 48 || history[1] != 48 || history[2] != 48 || history[3] != 48 || history[4] != 48 || history[5] != 48); //For some reason history != new int[]{48,48,48,48,48,48} didn't work, so you get this abomination
  }
private static int getuptosixnums() {
    int character;
    String num = "";
    do {
      character = getchar();
      num += (char) character;
    } while (character != 32);
    getchar();
    num = num.substring(0, num.length()-1);
    character = Integer.parseInt(num); //I just reused this veriable cause I don't need it
    return character;
  }
  private static void nextint() {
    System.out.println("Enter a number followed by a space");
    System.out.println("Your number was " + getuptosixnums());
  }
  private static void twonums() {
    System.out.println("Enter two numbers, seperated by a space");
    int first;
    int second;
    String num = ""; //Basically a copy of the getuptosixnums code (that can get more than six nums) that is adapted to my this project
    do {
      first = getchar();
      num += (char) first;
    } while (first != 32);
    num = num.substring(0, num.length()-1);
    first = Integer.parseInt(num);

    do {
      second = getchar();
      num += (char) second;
    } while (second != 10);
    num = num.substring(Integer.toString(first).length(), num.length()-1); //For some reason puts the first number at the front; need to remove that
    second = Integer.parseInt(num);
    int sum = first + second;
    System.out.println("The sum is " + sum);
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
        System.out.print((char) 9);
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

  private static void thousandprimes() {
      System.out.println("Enter how many primes you want to find");
      int numofprimes = input.nextInt() - 2; //This is to account for the offset of already generated primes
      ArrayList<Integer> primes = new ArrayList<>();
      primes.add(2);
      primes.add(3);
      int found = 0;
      while (found < numofprimes) {
          found++;
          primes = primefinder(1,1,1);
      }
      System.out.println("Prime number " + (numofprimes + 2) + " is " + primes.get(primes.size()-1));
  }

  private static void testprimegenerator() {
    ArrayList<Integer> primes = primefinder(1000000,100000,10);
    System.out.println(primes.get(primes.size()-1));
  }

}
