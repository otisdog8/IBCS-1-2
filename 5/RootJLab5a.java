import java.io.*;
import java.util.Scanner;


public class RootJLab5a {
   static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

  public static void main(String[] args) {
    int response;
    String[] items = {"End Program", "Get Character", "Start and End character", "Nextint"}; //Menu code
    String menu = makemenu(items);

    do {
        System.out.println(menu);
        response = ensureint();
        if (response == 1) {
            charread(); //Runs the specified program
        } else if (response == 2) {
            intervalcharread();
        } else if (response == 3) {
            charread();
        } else if (response == 4) {
            charread();
        } else if (response == 5) {
            charread();
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

    private static void charread() {
        System.out.print("Enter any character twice to exit\n");
        int prev = -3; // -1 corresponds to end of stream, -2 corresponds to IOException
        int newval = -3;
        char character = 'a'; //randomly chosen

        do {
            prev = newval;
            newval = readonechar();
            character = (char) newval;
            printval(newval);
        } while (prev != newval);
    }

    private static void intervalcharread() {
        System.out.print("Enter the same character twice to exit\n");
        int firstchar;
        int secondchar;

        do {
            System.out.print("Enter a start character:  ");
            firstchar = readonechar();
            System.out.print("\nEnter an end character:  ");
            secondchar = readonechar();
            for (int i = Math.min(firstchar,secondchar); i <= Math.max(firstchar,secondchar); i++) {
                printval(i);
            }
        } while (firstchar != secondchar);
    }

    private static void printval(int value) {
        System.out.println("Letter value was " + value + " and character was " + (char) value);
    }

    private static int readonechar() {
        int[] result = new int[1];
        result = readchars(1);
        return result[0];
    }

    private static int[] readchars(int howmany) {
        int[] result = new int[howmany];
        int i = 0;
        int newval;
        do {
            newval = getchar();
            if (newval == 10 || newval == 13) {
                continue;
            }
            result[i] = newval;
            i++;
        } while(i < howmany);

        return result;
    }

    private static int getchar() {
        try {
            return System.in.read();
        }
        catch (IOException e) {
            System.out.print("IOException thrown\n");
            return -2;
        }
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