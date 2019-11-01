import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;


class Classroom {
    Student[] students;
    String sourcefilename;
    int filelength;

    Classroom(String filename) {
        this.sourcefilename = filename;
        int filelength = getfilelength();
        this.filelength = filelength;
        this.students = new Student[filelength];

        Scanner filescanner = generatescanner();

        for (int i = 0; i < filelength; i++) {
            this.students[i] = new Student(filescanner.nextLine().split(" "));
        }
    }

    public Student search(String name) {
        return search(2, name);
    }

    public Student search(int id) {
        return search(1, Integer.toString(id));
    }

    private Student search(int field, String data) {
        for (int i = 0; i < this.filelength; i++) {
            if (this.students[i].data[field] == data) {
                return this.students[i];
            }
        }
        String[] genericstudent = {"1","1","","",""};
        return new Student(genericstudent);
    }

    public void delete(String[] student) {
        delete(new Student(student));
    }

    public void delete(Student student) {
        Student[] newstudents;
        int offset = 0;
        for (int i = 0; i < this.filelength; i++) {
            if (student.data == this.students[i].data) {
                this.filelength--;
                newstudents = new Student[this.filelength];
                for (int j = 0; j < this.filelength; j++) {
                    if (j == i) {
                        offset++;
                    }
                    newstudents[j] = students[j + offset];
                }
                this.students = newstudents;
            }
        }
    }

    public void add(String[] student) {
        add(new Student(student));
    }

    public void add(Student student) {
        this.filelength++;
        Student[] newstudents = new Student[this.filelength];

        for (int i = 0; i < this.filelength - 1; i++) {
            newstudents[i] = students[i];
        }

        newstudents[this.filelength - 1] = student;
        this.students = newstudents;
    }

    public void sortbyid() {
        Comparator comparator = new StudentIDComparator();

        sort(comparator);
    }

    public void sortbyname() {
        int maxim = 0;

        for (int i = 0; i < this.filelength; i++) {
            maxim = Math.max(this.students[i].lastname.length(), maxim);
        }

        LastNameComparator comparator = new LastNameComparator(maxim);

        sort(comparator);
    }

    private void sort(Comparator comparator) {
        Student swap;
        boolean sorted;

        do {
            sorted = true;
            for (int i = 0; i < this.filelength - 1; i++) {

                if (inttobool(comparator.compare(this.students[i], this.students[i + 1]))) {
                    swap = this.students[i];
                    this.students[i] = this.students[i + 1];
                    this.students[i + 1] = swap;
                    sorted = false;
                }

            }
        } while (!sorted);
    }

    public int[] gradestatistics() {
        String[] lineparts;
        int[] grades = new int[4];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students[i].data;
            grades[Integer.parseInt(lineparts[1]) - 9]++; // Adds one to the appropriate array

        }

        return grades;
    }

    public int[] genderstatistics() {
        String[] lineparts;
        int[] genders = new int[2];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students[i].data;
            genders[((int) lineparts[4].toCharArray()[0] - (int) 'F') / ((int) 'M' - (int) 'F')]++;
        }

        return genders;
    }

    public int[] namestatistics() {
        String[] lineparts;
        int[] lastnames = new int[26];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students[i].data;
            lastnames[(int) lineparts[2].toCharArray()[0] - (int) 'A']++;
        }

        return lastnames;

    }

    private boolean inttobool(int result) {
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void generatefile(String filename) {
        String str = generatestring();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            System.out.print("Caught an error\n");
        }

    }

    public String generatestring() {
        String str = "";
        for (int i = 0; i < this.students.length; i++) {
            for (int j = 0; j < 5; j++) {
                str += this.students[i].data[j] + " ";
            }
            str += "\n";
        }
        return str;
    }

    private int getfilelength() {
        Scanner scanner = generatescanner();
        int result = 0;

        while (scanner.hasNextLine()) {
            result++;
            scanner.nextLine();
        }

        return result;
    }

    private Scanner generatescanner() {
        File file = new File(this.sourcefilename);

        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("File not found\n");
            return new Scanner(System.in);
        }
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