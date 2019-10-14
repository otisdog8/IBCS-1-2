import java.io.*;
import java.util.Scanner;


public class test {
   static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

  public static void main(String[] args) {
    int response;
    String[] items = {"End Program", "Get Character", "Start and End character", "Nextint", "Sum Integers"}; //Menu code
    String menu = makemenu(items);

    long[] helpme = new long[800000000];

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
}