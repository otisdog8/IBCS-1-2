import java.util.Scanner;
import java.util.ArrayList;

//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

public class lab4 {

    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

    public static void main(String[] args) {
        int response;
        String[] items = {"End Program", "Add it up", "Grading", "Reciprocal", "LCM and GCD"}; //Menu code
        String menu = makemenu(items);

        do {
            System.out.println(menu);
            response = input.nextInt();
            if (response == 1) {
                additup(); //Runs the specified program
            } else if (response == 2) {
                grading();
            } else if (response == 3) {
                reciprocal();
            } else if (response == 4) {
                LCMGCD();
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

    private static void additup() {
        System.out.println("Enter an integer");
        int lastnum = input.nextInt();
        int result = 0;

        for (int i = 0; i < lastnum + 1; i++) { //Runs each time with a different i value
            result += i;
        }

        System.out.println("Your result is " + result);
    }

    private static void grading() {
    int grade;
    long[] letters = new long[] {0,0,0,0,0};
    double average = 1;
    long counter = 0; //We're using longs so that the teacher could have a very big class
    do {
        System.out.println("Enter a grade (-1 to stop)");
        grade = input.nextInt();
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
            num = input.nextInt();
            if (top == 0 && bottom == 0) {
              top = 1;
              bottom = num;
              continue;
            }
            if (num == 0) {
                System.out.println("Do you want to quit? 0 to quit");
                num = input.nextInt();
                times--;
                continue;
            }
            commonfactor = gcd((bottom + top * num), num * bottom);
            top = bottom + top * num;
            bottom = num * bottom;
            top /= commonfactor;
            bottom /= commonfactor;
            System.out.println("Your sum is: " + top + " / " + bottom);
        } while (num != 0 && times != 10); //The limit of 10 is 100% artificial; this program can do it until the integers overflow
    }

    private static void  LCMGCD() {
        System.out.println("Enter your first number: ");
        int firstnum = input.nextInt();
        System.out.println("Enter your second number: ");
        int secondnum = input.nextInt();
        int gcd = gcd(firstnum,secondnum);
        int lcm = Math.abs(firstnum*secondnum)/gcd;
        System.out.println("The GCD of the two numbers is: " + gcd);
        System.out.println("The LCM of the two numbers is: " + lcm);
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
