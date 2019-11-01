//********************************
//*     Made By Jacob Root       *
//*                              *
//********************************

import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;


public class RootJLab7b {
    static Scanner input = new Scanner(System.in); // Makes it so that everyone can use this Scanner
    static Classroom classroom = new Classroom("classlist.txt");


    public static void main(String[] args) {
        int response;
        String[] items = { "End Program", "Search", "Delete", "Add", "Name Sort", "ID Sort", "Print", "Save to File", "Read in new class", "Statistics"}; // Menu
                                                                   // code
        String menu = makemenu(items);

        do {
            System.out.println(menu);
            response = ensureint();
            switch (response) {
                case 1:
                    search();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    namesort();
                    break;
                case 5:
                    IDsort();
                    break;
                case 6:
                    print();
                    break;
                case 7:
                    save();
                    break;
                case 8:
                    readinnew();
                    break;
                case 9:
                    statistics();
                    break;
            }

        } while (response != 0);

        System.out.println("Bye!");
    }

    private static Student search() {
        System.out.print("Enter a student ID or name");;
        if (input.hasNextInt()) {
            return classroom.search(input.nextInt());
        }
        else {
            return classroom.search(input.nextLine());
        }
    }

    private static void delete() {
        Student studenttodelete = search();
        
        System.out.print("Do you really want to delete " + studenttodelete.firstname + " " + studenttodelete.lastname + " " + studenttodelete.studentnum + "(y/n)?   ");
        String result = input.nextLine();

        if (result == "y") {
            classroom.delete(studenttodelete);
        }
    }

    private static void add() {
        String[] data = new String[5];
        String[] queries = {"student ID", "grade", "last name", "first name", "gender"};
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter the " + queries[i] + ":   ");
            data[i] = input.nextLine();
        }
        classroom.add(data);
    }

    private static void namesort() {
        classroom.sortbyname();
        System.out.println("done");
    }

    private static void IDsort() {
        classroom.sortbyid();
        System.out.println("done");
    }

    private static void print() {
        System.out.println(classroom.generatestring());
    }

    private static void save() {
        System.out.print("Enter the filename you wish to save as:   ");
        String filename = input.nextLine();
        classroom.generatefile(filename);
    }

    private static void readinnew() {
        System.out.print("Enter the filename you wish to read as:   ");
        String filename = input.nextLine();
        File existtest = new File(filename);
        if (existtest.exists()) {
            classroom = new Classroom(filename);
        }
        else {
            readinnew();
        }

    }
    
    private static void statistics() {

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
