package com.company;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
    static Random rand = new Random(); //Makes it so everyone can use this Random

    public static void main(String[] args) throws IOException {
        int response;
        String[] items = {"End Program", "Sum", "Multiplication table", "Fibbonacci numbers", "Interest rate", "Square picture", "Diamiond picture", "X xpicture", "PI hole","Test pi accuracy"}; //Menu code
        String menu = makemenu(items);
        do {
            System.out.println(menu);
            response = input.nextInt();
            if (response == 1) {
                sum();  //Runs the specified program
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
            } else if (response == 9) {
                pitest();
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

    private static void sum() {
        long total = 0;
        for (int i = 7; i < 70001; i++) {
            total += i;
        }
        System.out.println("The sum is " + total); //One line
    }

    private static void multiplicationtable() {
        System.out.println("Enter the width of your numtiplication table");
        int width = input.nextInt();
        System.out.println("Enter the height of your multiplcation table");
        int height = input.nextInt();
        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < width + 1; j++) {
                System.out.print("" + (j * i) + (char) 9);
            }
            System.out.println();

        }
    }

    private static void fibonacci() {
        ArrayList<BigInteger> nums = new ArrayList<>();
        nums.add(new BigInteger("0")); //We want large numbers that don't overflow
        nums.add(new BigInteger("1")); //Sets up the fibonacci number list
        System.out.println("How many fibonacci numbers do you want to calculate?");
        int num = input.nextInt();
        for (int i = 0; i < num; i++) {
            nums.add(nums.get(nums.size() - 1).add(nums.get(nums.size() - 2)));
        }
        System.out.println("All fibonacci numbers found are " + nums);
        System.out.println("Your number is: " + nums.get(num - 1));
    }

    private static void interestcalc() {
        System.out.println("Enter your balance");
        double bal = input.nextFloat();
        double balifnosave = bal;
        System.out.println("Enter how much you want to save each month");
        double save = input.nextFloat();
        System.out.println("Enter your monthly interest rate (%)");
        double interest = input.nextFloat();
        for (int i = 0; i < 360; i++) {
            bal += save; //Adds monthly deposit
            bal *= interest / 100 + 1; //Adds interest
            balifnosave *= interest / 100 + 1; //Caclulates the interest if they didn't save

        }
        bal = Math.round(bal * 100.0) / 100.0; //Rounds numbers
        balifnosave = Math.round(balifnosave * 100.0) / 100.0;
        System.out.println("You have " + bal + " dollars in your account. If you didn't save, you would've had " + balifnosave + " dollars.");
    }

    private static void squarepic() {
        System.out.println("Enter a height");
        int height = input.nextInt();
        System.out.println("Enter a width");
        int width = input.nextInt();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 0 || j % 2 == 0) {//Determines if it wants to print a 0 or an asterix
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
        int height = input.nextInt();
        int print;
        for (int i = -((height - 1) / 2); i < ((height - 1) / 2) + 1; i++) { //"Centers" the numbers
            for (int l = 0; l < Math.abs(i); l++) {//Edge spacing
                System.out.print(" ");
            }
            System.out.print("*");
            print = 0;
            for (int l = Math.abs(i) * 2 + 2; l < height; l++) { //Center spacing
                print = 1;
                System.out.print(" ");
            }
            if (print == 1) {
                System.out.print("*"); //If a second asterisk is needed, it goes there
            }
            for (int l = 0; l < Math.abs(i); l++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void xshape() {
        System.out.println("Enter the height");
        int height = input.nextInt();
        int print;
        for (int i = -((height - 1) / 2); i < ((height - 1) / 2) + 1; i++) {
            for (int l = 0; l < ((height - 1) / 2)-Math.abs(i); l++) { //Edge spacing
                System.out.print(" ");
            }
            System.out.print("*");
            print = 0;
            for (int l = 0; l < Math.abs(i)*2-1; l++) { //Center s[acing
                print = 1;
                System.out.print(" ");
            }
            if (print == 1) {
                System.out.print("*"); //Again, if it needs another asterisk
            }
            for (int l = 0; l < ((height - 1) / 2)-Math.abs(i); l++) { //More edge spacing
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void pihole() {
        System.out.println("How many times do you want the machine to throw darts?");
        int numofdarts = input.nextInt();
        System.out.println("Do you want to use higher precision calculations (at the cost of speed)? Yes is 1, no is any other integer"); //Calculates with four circles, but the same amount of darts. Increases the precision 4x? (idk)
        int highpres = input.nextInt();
        double x;
        double y;
        double pi1;
        int counter1 = 0;
            double pi2;
            double pi3;
            double pi4;
            int counter2 = 0;
            int counter3 = 0;
            int counter4 = 0; //There was an if statement enclosing pi2 through counter4, but that messed up variable scope
        for (int i =0; i < numofdarts; i++) {
            x = rand.nextDouble();
            y = rand.nextDouble();
            if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
                counter1++;
            }
            if (highpres == 1) {
                if (Math.pow(x - 1, 2) + Math.pow(y, 2) <= 1) {
                    counter2++;
                }
                if (Math.pow(x, 2) + Math.pow(y - 1, 2) <= 1) {
                    counter3++;
                }
                if (Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1) {
                    counter4++;
                }
            }

        }
        pi1 = counter1 / (double) numofdarts * 4;
        if (highpres == 1) {
            pi2 = counter2/(double) numofdarts * 4;
            pi3 = counter3/(double) numofdarts * 4;
            pi4 = counter4/(double) numofdarts * 4;
            pi1 = (pi1+pi2+pi3+pi4)/4;
        }
        System.out.println("We found that pi was " + pi1);
    }
    private static void pitest() {//Tests accuracy. This was just a side project of mine I decided to include.
        System.out.println("How many times do you want the machine to throw darts?");
        int numofdarts = input.nextInt();
        System.out.println("How many trials do you want to use?"); //Calculates with four circles, but the same amount of darts. Increases the precision 4x? (idk)
        int trials = input.nextInt();
        int highpres = 1;
        double pinohighpres;
        double pihighpres;
        double x;
        double y;
        double pi1;
        int counter1 = 0;
        double pi2;
        double pi3;
        double pi4;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0; //There was an if statement enclosing pi2 through counter4, but that messed up variable scope
        double diffhighpres = 0;
        double diffnohighpres = 0;
        for (int n = 0; n < trials; n++) {
            counter1 = 0;
            counter2 = 0;
            counter3 = 0;
            counter4 = 0;
            for (int i = 0; i < numofdarts; i++) {
                x = rand.nextDouble();
                y = rand.nextDouble();
                if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
                    counter1++;
                }
                if (highpres == 1) {
                    if (Math.pow(x - 1, 2) + Math.pow(y, 2) <= 1) {
                        counter2++;
                    }
                    if (Math.pow(x, 2) + Math.pow(y - 1, 2) <= 1) {
                        counter3++;
                    }
                    if (Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1) {
                        counter4++;
                    }
                }

            }
            pi1 = counter1 / (double) numofdarts * 4;
            pinohighpres = pi1;
            if (highpres == 1) {
                pi2 = counter2 / (double) numofdarts * 4;
                pi3 = counter3 / (double) numofdarts * 4;
                pi4 = counter4 / (double) numofdarts * 4;
                pi1 = (pi1 + pi2 + pi3 + pi4) / 4;
            }
            diffhighpres += Math.abs(Math.PI-pi1);
            diffnohighpres += Math.abs(Math.PI-pinohighpres);
        }
        System.out.println("High prescision difference was: " + diffhighpres);
        System.out.println("Low prescision difference was: " + diffnohighpres);
    }
    private static void fillprimearray() {

    }
}