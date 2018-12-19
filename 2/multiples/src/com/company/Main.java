package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first number");
        int firstnum = input.nextInt();
        System.out.println("Enter your second number");
        int secondnum = input.nextInt();
        if (secondnum % firstnum == 0) {
            System.out.println("The first number is a multiple of the second.");
        } else {
            System.out.println("The first number isn't a multiple of the second.");
        }
    }
}
