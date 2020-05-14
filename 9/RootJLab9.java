//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RootJLab9 {
    static Scanner input = new Scanner(System.in); // Makes it so that everyone can use this Scanner
    static Random rand = new Random(); // Makes it so everyone can use this Random

    public static void main(String[] args) throws IOException {
        int response;
        String[] items = { "End Program", "Sum", "Multiplication table", "Fibbonacci numbers", "Interest rate",
                "Square picture", "Diamiond picture", "X xpicture", "Pot shots at pi" }; // Menu code
        String menu = makemenu(items);
        do {
            System.out.println(menu);
            response = ensureint();
            if (response == 1) {
                sum(); // Runs the specified program
            } else if (response == 2) {
                multiplicationtable();
            } else if (response == 3) {
                fibonacci();
            } else if (response == 4) {
                interestcalc();
            } else if (response == 5) {
                squarepic();
            } else if (response == 6) {
                diamond();
            } else if (response == 7) {
                xshape();
            } else if (response == 8) {
                pihole();
            } else {

            }

        } while (response != 0);
        System.out.println("Bye!");
    }

    private static String makemenu(String[] items) { // Function generates a menu string so that I don't have to type it
                                                     // out.
        String result = "";
        int length = items.length; // *very* slight optimization
        for (int i = 0; i < length; i++) { // Iterates through the array items and generates a menu entry for each item
                                           // on items
            result += i + ":  " + items[i] + "\n";
        }
        return result;
    }

    private static void sum() {
        long total = 0;
        for (int i = 7; i < 70001; i++) {
            total += i;
        }
        System.out.println("The sum is " + total);
    }

    private static void multiplicationtable() {
        System.out.println("Enter the width of your multiplcation table");
        int width = ensureint();
        System.out.println("Enter the height of your multiplcation table");
        int height = ensureint();
        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < width + 1; j++) {
                System.out.print("" + (j * i) + (char) 9);
            }
            System.out.println();

        }
    }

    private static void fibonacci() {
        ArrayList<Long> nums = new ArrayList<>();
        nums.add(0L);
        nums.add(1L);
        nums.add(1L);
        System.out.println("How many fibonacci numbers do you want to calculate?");
        int num = ensureint();
        for (int i = 0; i < num - 3; i++) {
            nums.add(nums.get(nums.size() - 1) + nums.get(nums.size() - 2)); // Adds next term. If using ArrayLists is an issue here I can code my own implementation
        }
        System.out.println("All fibonacci numbers found are " + nums);
        System.out.println("Your number is: " + nums.get(num - 1));
    }

    private static void interestcalc() {
        System.out.print("Enter your balance:   ");
        double bal = input.nextFloat();
        double balifnosave = bal;
        double balinfinite = bal;
        double balifnosaveinfinite = bal;
        System.out.print("Enter how much you want to save each month:   ");
        double save = input.nextFloat();
        System.out.print("Enter your annual interest rate (%):  ");
        double interest = input.nextFloat();
        for (int i = 0; i < 360; i++) {
            bal += save; // Adds monthly deposit
            balinfinite += save;

            bal *= interest / 1200 + 1; // Adds interest
            balifnosave *= interest / 1200 + 1; // Caclulates the interest if they didn't save

            balinfinite *= Math.pow(Math.E, interest / 1200); // Calculates indefinitely compunding interest
            balifnosaveinfinite *= Math.pow(Math.E, interest / 1200);

        }
        bal = Math.round(bal * 100.0) / 100.0; // Rounds numbers
        balifnosave = Math.round(balifnosave * 100.0) / 100.0;
        balinfinite = Math.round(balinfinite * 100.0) / 100.0;
        balifnosaveinfinite = Math.round(balifnosaveinfinite * 100.0) / 100.0;

        System.out.println("You have " + bal + " dollars in your account. If you didn't save, you would've had "
                + balifnosave + " dollars.");
        System.out.println("If it compounded indefinitely and you didn't save you would have " + balifnosaveinfinite
                + ". If you did save and it compounded indefinitely, you would have " + balinfinite + " dollars.");
    }

    private static void squarepic() {
        System.out.println("Enter a height");
        int height = ensureint();
        System.out.println("Enter a width");
        int width = ensureint();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 0 || j % 2 == 0) {// Determines if it wants to print a 0 or an asterix
                    System.out.print("*");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    private static void diamond() {
        System.out.println("Enter the height");
        int height = ensureint();
        int print;
        for (int i = -((height - 1) / 2); i < ((height - 1) / 2) + 1; i++) { // "Centers" the numbers
            for (int l = 0; l < Math.abs(i); l++) {// Edge spacing
                System.out.print(" ");
            }
            System.out.print("*");
            print = 0;
            for (int l = Math.abs(i) * 2 + 2; l < height; l++) { // Center spacing
                print = 1;
                System.out.print(" ");
            }
            if (print == 1) {
                System.out.print("*"); // If a second asterisk is needed, it goes there
            }
            for (int l = 0; l < Math.abs(i); l++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void xshape() {
        System.out.println("Enter the height");
        int height = ensureint();
        int print;
        for (int i = -((height - 1) / 2); i < ((height - 1) / 2) + 1; i++) {
            for (int l = 0; l < ((height - 1) / 2) - Math.abs(i); l++) { // Edge spacing
                System.out.print(" ");
            }
            System.out.print("*");
            print = 0;
            for (int l = 0; l < Math.abs(i) * 2 - 1; l++) { // Center spacing
                print = 1;
                System.out.print(" ");
            }
            if (print == 1) {
                System.out.print("*"); // Again, if it needs another asterisk
            }
            for (int l = 0; l < ((height - 1) / 2) - Math.abs(i); l++) { // More edge spacing
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void pihole() {
        System.out.print("How many darts do you want to throw?   ");
        int numofdarts = ensureint();

        double pi = calculatepi(numofdarts);
        System.out.print("\n Pi was found to be " + pi + "\n");
    }

    private static double calculatepi(int numofdarts) {
        double pi;
        double x;
        double y;
        double counter = 0;

        for (int i = 0; i < numofdarts; i++) {
            x = rand.nextDouble();
            y = rand.nextDouble();
            for (double x2 = 0; x2 < 2; x2++) {
                for (double y2 = 0; y2 < 2; y2++) {
                    if (distanceto(x, y, x2, y2) <= 1) {
                        counter++; //Slightly higher accuracy
                    }
                }
            }
        }
        pi = counter / numofdarts; // Calculates the area. Because r=1, we don't need to divide by r^2
        return pi;
    }

    private static double distanceto(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int ensureint() {
        boolean isint = false;
        int result = 0;

        while (!isint) {
            if (input.hasNextInt()) {
                result = input.nextInt();
                isint = true;
            } else {
                input.nextLine();
                System.out.print("Please enter an int\n");
            }
        }

        return result;
    }
}