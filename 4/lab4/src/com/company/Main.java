package com.company;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner

    public static void main(String[] args) {
        int response;
        String[] items = {"End Program", "Add it up", "Grading", "Reciprocal", "LCM and GCD0"}; //Menu code
        String menu = makemenu(items);
        do {
            System.out.println(menu);
            response = input.nextInt();
            if (response == 1) {
                additup(); //Runs the specified program
            } else if (response == 2) {
                grading();
            } else if (response == 3) {
                additup();
            } else if (response == 4) {
                additup();
            } else {
            }

        } while (response != 0);
        System.out.println("Bye!");
    }

    public static String makemenu(String[] items) { //Function generates a menu string so that I don't have to type it out.
        String result = "";
        int length = items.length; //*very* slight optimization
        for (int i = 0; i < length; i++) { //Iterates through the array items and generates a menu entry for each item on items
            result += i + ":  " + items[i] + "\n";
        }
        return result;
    }

    public static void additup() {
        System.out.println("Enter an integer");
        int lastnum = input.nextInt();
        int result = 0;
        for (int i = 0; i < lastnum + 1; i++) { //Runs each time with a different i value
            result += i;
        }
        System.out.println("Your result is " + result);
    }

    public static void grading() {
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
}
