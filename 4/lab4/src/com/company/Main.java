package com.company;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in); //Makes it so that everyone can use this Scanner
    public static void main(String[] args) {
        int response;
        String[] items = {"End Program","Add it up","Grading","Reciprocal","LCM and GCD0"}; //Menu code
        String menu = makemenu(items);
        do {
            System.out.println(menu);
            response = input.nextInt();
            if (response == 1) {
                additup(); //Runs the specified program
            } else if (response == 2) {
                additup();
            } else if (response == 3){
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
        for(int i = 0;i < length; i++) { //Iterates through the array items and generates a menu entry for each item on items
            result += i + ":  " + items[i] + "\n";
        }
        return result;
    }
    public static void additup() {
        System.out.println("Enter an integer");
        int lastnum = input.nextInt();
        int result = 0;
        for(int i=0;i<lastnum+1;i++) {
            result += i;
        }
        System.out.println("Your result is " + result);
    }
}
