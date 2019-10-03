import java.io.*;
import java.util.Scanner;


public class RootJLab5a {
   static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

  public static void main(String[] args) {
    int response;
    String[] items = {"End Program", "Get Character", "Start and End character", "Nextint", "Sum Integers"}; //Menu code
    String menu = makemenu(items);

    do {
        System.out.println(menu);
        response = ensureint();
        if (response == 1) {
            charread(); //Runs the specified program
        } else if (response == 2) {
            intervalcharread();
        } else if (response == 3) {
            displayint();
        } else if (response == 4) {
            sumints();
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
}