import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

public class RootJLab8 {
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
        String[] lineparts;
        int[] grades = new int[4];
        int[] genders = new int[2];
        int[] lastnames = new int[26];

        while (scanner.hasNextLine()) {
            lineparts = scanner.nextLine().split(" ");
            grades[Integer.parseInt(lineparts[1]) - 9]++; // Adds one to the appropriate array by subtracting 9 from the grade level
            genders[((int) lineparts[4].toCharArray()[0] - (int) 'F') / ((int) 'M' - (int) 'F')]++; // Uses char arithmetic to assign an 'F' to 1 and an 'M' to 0
            lastnames[(int) lineparts[2].toCharArray()[0] - (int) 'A']++; //Subtracts 'A' to index lastnames.
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
        int maxim = 0; //Maximum length of a last name
        String[][] strings = new String[length][5];
        Student[] students;
        Student[] studentsbystudentid;
        Student[] studentsbylastname;

        Scanner scanner = generatescanner("classlist.txt");

        for (int i = 0; i < length; i++) {
            strings[i] = scanner.nextLine().split(" ");
        }

        students = tostudentarray(strings);

        for (int i = 0; i < students.length; i++) {
            maxim = Math.max(students[i].lastname.length(), maxim);
        }


        // Instead of making two bubblesorts, we use comparators
        LastNameComparator lastnametester = new LastNameComparator(maxim);
        StudentIDComparator studentIDcheck = new StudentIDComparator();

        studentsbystudentid = bubblesort(students, studentIDcheck);
        studentsbylastname = bubblesort(students, lastnametester);

        generatefile(studentsbylastname, "studentLastNamED.txt");
        generatefile(studentsbystudentid, "studentIDed.txt");
    }

    private static Student[] bubblesort(Student[] array, Comparator comparator) {
        Student[] newarray = copyarray(array);
        Student swap; // Swap Assistant
        boolean sorted;

        do {
            sorted = true;
            for (int i = 0; i < newarray.length - 1; i++) {

                if (comparatortoboolean(comparator.compare(newarray[i], newarray[i + 1]))) {
                    swap = newarray[i];
                    newarray[i] = newarray[i + 1];
                    newarray[i + 1] = swap;
                    sorted = false;
                }
            }
        } while (!sorted);

        return newarray;
    }

    private static Student[] tostudentarray(String[][] array) {
        Student[] newarray = new Student[array.length];

        for (int i = 0; i < array.length; i++) {
            newarray[i] = new Student(array[i]);
        }

        return newarray;
    }

    private static void generatefile(Student[] array, String filename) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 5; j++) {
                str += array[i].data[j] + " ";
            }
            str += "\n";
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            System.out.print("Caught an error\n");
        }

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

    private static boolean comparatortoboolean(int result) {
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static Student[] copyarray(Student[] array) {
        Student[] newarray = new Student[array.length];
        for (int i = 0; i < array.length; i++) {
            newarray[i] = array[i];
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

class Student {
    int studentnum;
    int grade;
    String lastname;
    String firstname;
    String gender;
    String[] data;

    public Student(String[] data) {
        this.studentnum = Integer.parseInt(data[0]);
        this.grade = Integer.parseInt(data[1]);
        this.lastname = data[2];
        this.firstname = data[3];
        this.gender = data[4];
        this.data = data;
    }
}

class LastNameComparator implements Comparator<Student> {
    int padding;

    LastNameComparator(int padding) {
        this.padding = padding;
    }

    public int compare(Student o1, Student o2) {
        return comparestrings(o1.lastname, o2.lastname, this.padding);
    }

    private static String padstring(String string, int padding, char padchar) {
        if (string.length() < padding) {
            for (int i = 0; i < padding - string.length() + 1; i++) {
                string += padchar;
            }
        }
        return string;
    }

    private static int comparestrings(String check1, String check2, int padding) {
        // We need padding because otherwise the checker will fail
        check1 = padstring(check1, padding, 'a').toLowerCase();
        check2 = padstring(check2, padding, 'a').toLowerCase();

        // recursively calculate which string is bigger
        return checkstring(check1, check2, 0);
    }

    private static int checkstring(String check1, String check2, int index) {
        if ((int) check1.charAt(index) > (int) check2.charAt(index)) {
            return 1;
        } else if ((int) check1.charAt(index) < (int) check2.charAt(index)) {
            return -1;
        } else {
            index++;
            if (index > check1.length() - 1) {
                return 0;
            }
            return checkstring(check1, check2, index);
        }

    }
}

class StudentIDComparator implements Comparator<Student> {
    public int compare(Student o1, Student o2) {
        return (int) Math.signum(o1.studentnum - o2.studentnum);
    }

}