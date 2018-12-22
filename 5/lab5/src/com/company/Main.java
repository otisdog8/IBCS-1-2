package com.company;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Scanner;
public class Main {
    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
    public static void main(String[] args) throws IOException {
        int response;
        String[] items = {"End Program", "Character code", "From start to end", "Next int", "Two numbers"}; //Menu code
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
    private static int getchar() throws IOException{
        return System.in.read(); //Little hack that makes it a tiny bit easier for me
    }
    public static void charcode() throws IOException {
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
    public static void startend() throws IOException {
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
    public static int getuptosixnums() throws IOException{
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
    public static void nextint() throws IOException{
        System.out.println("Enter a number followed by a space");
        System.out.println("Your number was " + getuptosixnums());
    }
    public static void twonums() throws IOException {
        System.out.println("Enter your first number");
        int first;
        int second;
        String num = ""; //Basically a copy of the getuptosixnums code (that can get more than six nums) that is adapted to my this project
        do {
            first = getchar();
            num += (char) first;
        } while (first != 32);
        num = num.substring(0, num.length()-1);
        first = Integer.parseInt(num);
        String numtwo = "";
        do {
            second = getchar();
            num += (char) second;
        } while (second != 10);
        num = num.substring(Integer.toString(first).length(), num.length()-1); //For some reason puts the first number at the front; need to remove that
        second = Integer.parseInt(num);
        int sum = first + second;
        System.out.println("The sum is " + sum);
    }

}