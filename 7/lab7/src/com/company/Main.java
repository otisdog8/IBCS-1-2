package com.company;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
    public static void main(String[] args) {
        int response;
        String[] items = {"End Program", "Grade levels", "Male or female", "Last names", "Sort by last name", "Sort by student number"}; //Menu code
        String menu = makemenu(items);
        do {
            System.out.println(menu);
            response = input.nextInt();
            if (response == 1) {
                grade();  //Runs the specified program
            } else if (response == 2) {
                gender();
            } else if (response == 3) {
                lastnames();
            } else if (response == 4) {
                lastnamesort();
            } else if (response == 5) {
                studentnumsort();
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

    private static void grade() {

    }
    private static void gender() {

    }
    private static void lastnames() {

    }
    private static void lastnamesort() {

    }
    private static void studentnumsort() {

    }
}
