//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

import java.util.Scanner;
import java.util.ArrayList;

public class RootJLab4 {

  static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

  public static void main(String[] args) {
    int response;
    String[] items = {"End Program", "Add it up", "Grading", "Reciprocal", "LCM and GCD", "Square Root"}; //Menu code
    String menu = makemenu(items);

    do {
      System.out.println(menu);
      response = ensureint();
      if (response == 1) {
          additup(); //Runs the specified program
      } else if (response == 2) {
          grading();
      } else if (response == 3) {
          reciprocal();
      } else if (response == 4) {
          LCMGCD();
      } else if (response == 5) {
        sqrt();
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
    // Makes sure that an int is inputted
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

  private static void additup() {
    System.out.println("Enter an integer");
    int lastnum = ensureint();
    int result = 0;

    for (int i = 0; i < lastnum + 1; i++) { //Runs each time with a different i value
      result += i;
      if (i == 0) {
        // Doesn't run on i=0, otherwise generates and prints a string that showcases the additup equation
        continue;
      }
      else if (i < lastnum) {
        System.out.print(i + " + "); 
      } 
      else {
        System.out.print(i + " = ");
      }
    }

    System.out.println(result);
  }

  private static void grading() {
  int grade;
  long[] letters = new long[] {0,0,0,0,0};
  double average = 1;
  long counter = 0; //We're using longs so that the teacher could have a very big class
  do {
    System.out.println("Enter a grade (-1 to stop)");
    grade = ensureint();
    if (grade >= 90) {
      letters[0]++;
    } else if (grade < 90 && grade >= 80) {
      letters[1]++;
    } else if (grade < 80 && grade >= 70) { //Calculates which letter grade the student would be given
      letters[2]++;
    } else if (grade < 70 && grade >= 60) {
      letters[3]++;
    } else if (grade < 60 && grade >= 0) {
      letters[4]++;
    } else {
    }
    if (grade != -1) { //Doesn't calculate average when exiting loop
      average = (average * counter + grade) / (counter + 1); //Calculates the average
      counter++;
      System.out.println("------------------------------");
      System.out.println("A: " + letters[0]);
      System.out.println("B: " + letters[1]);
      System.out.println("C: " + letters[2]);
      System.out.println("D: " + letters[3]);
      System.out.println("F: " + letters[4]);
      System.out.println("Average: " + average);
      System.out.println("------------------------------");
    }
  } while (grade != -1);
  }

  private static void reciprocal() {
    int num;
    int top = 0;
    int bottom = 0;
    int times = 0;
    int commonfactor;
    do {
      times++;
      System.out.println("Enter a number (0 to stop)");
      num = ensureint();
      if (top == 0 && bottom == 0) {
        top = 1;
        bottom = num;
        System.out.println("Your fractional sum is: " + top + " / " + bottom);
        System.out.println("Your sum is:  " + (float) top / (float) bottom);
        continue;
      }
      if (num == 0) {
        System.out.println("Do you want to quit? 0 to quit");
        num = ensureint();
        times--;
        continue;
      }
      commonfactor = gcd((bottom + top * num), num * bottom); // This stuff calculates and reduces the fraction
      top = bottom + top * num;
      bottom = num * bottom;
      top /= commonfactor;
      bottom /= commonfactor;
      System.out.println("Your fractional sum is: " + top + " / " + bottom);
      System.out.println("Your sum is:  " + (float) top / (float) bottom);
    } while (num != 0 && times != 10); //The limit of 10 is 100% artificial; this program can do it until the integers overflow
  }

  private static void  LCMGCD() {
    System.out.println("Enter your first number: ");
    int firstnum = ensureint();
    System.out.println("Enter your second number: ");
    int secondnum = ensureint();
    int gcd = gcd(firstnum,secondnum);
    int lcm = Math.abs(firstnum*secondnum)/gcd; // Calculates LCM from GCD
    System.out.println("The GCD of the two numbers is: " + gcd);
    System.out.println("The LCM of the two numbers is: " + lcm);
  }

  private static void sqrt() { //Babylonian sqrt
    System.out.print("Enter the number to square root:  ");
    int sqrtnum = ensureint();
    System.out.print("Enter the number of iterations you want:  ");
    int iterations = ensureint();

    String isimaginary;
    if (0 > sqrtnum) { //Handles imaginary numbers
      sqrtnum = -1 * sqrtnum;
      isimaginary = "i";
    } else {
      isimaginary = "";
    }
    long magnitude = Math.round(Math.log10(sqrtnum)); // Calculates half the degree of the result
    double initialguess = Math.pow(10,magnitude/2)*(sqrtnum/Math.pow(10,magnitude));

    for (int i = 0; i < iterations; i++) {
      initialguess = (initialguess + sqrtnum / initialguess) / 2; //Babylonian equation
      System.out.print(initialguess + "\n");
    }
    System.out.println(initialguess + isimaginary);
  }

  private static int gcd(int firstnum, int secondnum) {
    int remainder = 0;

    do { //Euclidean GCD
      remainder = firstnum % secondnum;
      firstnum = secondnum;
      secondnum = remainder;
    } while (remainder != 0);

    return firstnum;
  }
}
