import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;;

public class RootJLab7 {
    static Scanner input = new Scanner(System.in); // Makes it so that everyone can use this Scanner

    public static void main(String[] args) {
        int response;
        String[] items = { "End Program", "Counting", "Sorting" }; // Menu
                                                                   // code
        String menu = makemenu(items);

        do {
            System.out.println(menu);
            response = ensureint();
            if (response == 1) {
                counting(); // Runs the specified program
            } else if (response == 2) {
                sort();
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

    private static void counting() {
        Scanner scanner = generatescanner("classlist.txt");
        String line;
        String[] lineparts;
        int[] grades = new int[4];
        int[] genders = new int[2];
        int[] lastnames = new int[26];

        while (scanner.hasNextLine()) {
            lineparts = scanner.nextLine().split(" ");
            grades[Integer.parseInt(lineparts[1]) - 9]++; // Adds one to the appropriate array
            genders[((int) lineparts[4].toCharArray()[0] - (int) 'F') / ((int) 'M' - (int) 'F')]++;
            lastnames[(int) lineparts[2].toCharArray()[0] - (int) 'A']++;
        }

        System.out.println("Freshmen:  " + grades[0]);
        System.out.println("Sophomores:  " + grades[1]);
        System.out.println("Juniors:  " + grades[2]);
        System.out.println("Seniors:  " + grades[3]);
        System.out.println("Males:  " + genders[0]);
        System.out.println("Females:  " + genders[1]);
        for (int i = 0; i < 26; i++) {
            System.out.println((char) (i + (int) 'A') + ":  " + lastnames[i]);
        }
    }

    private static void sort() {
        int length = getfilelength("classlist.txt");
        String[][] students = new String[length][5];
        String[][] studentsbystudentid;
        String[][] studentsbylastname;

        Scanner scanner = generatescanner("classlist.txt");

        for (int i = 0; i < length; i++) {
            students[i] = scanner.nextLine().split(" ");
        }

        studentsbystudentid = bubblesortstudentid(students);
        studentsbylastname = bubblesortlastname(students);

        generatefile(studentsbylastname, "studentLastNamED.txt");

        generatefile(studentsbystudentid, "studentIDed.txt");

    }

    private static String[][] bubblesortlastname(String[][] array) {
        String[][] newarray = copy2darray(array);
        String[] swap = new String[5]; //Swap Assistant
        boolean sorted;
        long check1; //Converts array aspects into ints
        long check2;
        int maxim = 0;

        for (int i = 0; i < newarray.length; i++) {
            maxim = Math.max(newarray[i][2].length(),maxim);
        }

        do {
            sorted = true;
            for (int i = 0; i < newarray.length - 1; i++) {

                if (comparestrings(newarray[i][2],newarray[i+1][2],maxim)) {
                    swap = newarray[i];
                    newarray[i] = newarray[i+1];
                    newarray[i+1] = swap;
                    sorted = false;
                }
            }
        } while (!sorted);
        return newarray;
    }

    private static String[][] bubblesortstudentid(String[][] array) {
        String[][] newarray = copy2darray(array);
        String[] swap = new String[5]; //Swap Assistant
        boolean sorted;
        int check1; //Converts array aspects into ints
        int check2;

        do {
            sorted = true;
            for (int i = 0; i < newarray.length - 1; i++) {
                check1 = Integer.parseInt(newarray[i][0]);
                check2 = Integer.parseInt(newarray[i+1][0]); 
                if (check1 > check2) {
                    swap = newarray[i];
                    newarray[i] = newarray[i+1];
                    newarray[i+1] = swap;
                    sorted = false;
                }
            }
        } while (!sorted);
        return newarray;
    }

    private static void generatefile(String[][] array, String filename) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 5; j++) {
                str += array[i][j] + " ";
            }
            str += "\n";
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(str);
            writer.close();
        }
        catch (IOException e) {
            System.out.print("Caught an error\n");
        }

    }

    private static boolean comparestrings(String check1, String check2, int padding) {
        check1 = padstring(check1, padding, 'a').toLowerCase();
        check2 = padstring(check2, padding, 'a').toLowerCase();

        // recursively calculate which string is bigger
        return checkstring(check1, check2, 0);
    }

    private static boolean checkstring(String check1, String check2, int index) { 
        if ((int) check1.charAt(index) > (int) check2.charAt(index)) {
            return true;
        }
        else if ((int) check1.charAt(index) < (int) check2.charAt(index)) {
            return false;
        }
        else {
            index++;
            if (index > check1.length() - 1) {
                return false;
            }
            return checkstring(check1,check2,index);
        }

    }

    private static String padstring(String string, int padding, char padchar) {
        if (string.length() < padding) {
            for (int i = 0; i < padding - string.length() + 1; i++) {
                string += padchar;
            }
        }
        return string;
    }

    private static Scanner generatescanner(String filename) {
        File file = new File(filename);

        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("File not found\n");
            return new Scanner(System.in);
        }

    }

    private static String[][] copy2darray(String[][] array) {
        int length = array[0].length;
        String[][] newarray = new String[array.length][length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < length; j++) {
                newarray[i][j] = array[i][j];
            }
        }
        return newarray;
    }

    private static int getfilelength(String filename) {
        Scanner scanner = generatescanner(filename);
        int result = 0;

        while (scanner.hasNextLine()) {
            result++;
            scanner.nextLine();
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