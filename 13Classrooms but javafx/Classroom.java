 import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.io.ObjectInputStream;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

class Classroom {
    private SimpleListProperty<Student> students;
    private String sourcefilename;
    private int filelength;

    Classroom() {
        
        //try loading classroom.jobj
        //if fail, load classlist.txt
        if (!load("classroom.jobj")) {
            load("classlist.txt");
        }

    }

    public boolean load(String filename) {
        if (filename.endsWith(".jobj")) {
            return loadJobj(filename);
        } else if (filename.endsWith(".txt")) {
            loadText(filename);
        } else { //Try loading strings regardless
            loadText(filename);
        }
        return false;
    }

    private boolean loadJobj(String filename) {
        try {
            ObjectInputStream filereader = new ObjectInputStream(new FileInputStream(filename));

            this.students = (SimpleListProperty<Student>) filereader.readObject();
            // Reads the array of students from a file
            filereader.close();

            return true;
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            // Make sure to catch these and do nothing
        } catch (ClassCastException e) {
        
        }
        return false;

    }

    private void loadText(String filename) {
        this.sourcefilename = filename;
        int filelength = getfilelength();
        this.filelength = filelength;
        this.students = new SimpleListProperty<Student>(javafx.collections.FXCollections.observableList(new ArrayList<Student>()));;

        Scanner filescanner = generatescanner();

        for (int i = 0; i < filelength; i++) {
            this.students.add(new Student(filescanner.nextLine().split(" ")));
        }
        savefile();
    }

    public Student search(String name) {
        // Searches first by last name, than first name, than returns the generic
        try {
            return search(2, name);
        } catch (NameNotFoundException e0) {
            try {
                return search(3, name);
            } catch (NameNotFoundException e1) {
                return new Student();
            }
        }
    }

    public Student search(int id) {
        try {
            return search(0, Integer.toString(id));
        } catch (NameNotFoundException e) {
            return new Student();
        }
    }

    private Student search(int field, String data) throws NameNotFoundException{
        Student result = null;
        
        for (int i = 0; i < this.filelength; i++) {
            if (this.students.get(i).getData()[field].equals(data)) {
                result = this.students.get(i);
            }
        }

        if (result == null) {
            throw new NameNotFoundException();
        }

        return result;
    }

    public void delete(String[] student) {
        delete(new Student(student));
    }

    public void delete(Student student) {
        this.students.remove(student);
        savefile();
    }

    public void add(String[] student) {
        add(new Student(student));
    }

    public void add(Student student) {
        this.students.add(student);
        savefile();
    }

    public void sortbyid() {
        StudentIDComparator comparator = new StudentIDComparator();

        sort(comparator);
    }

    public void sortbyname() {
        int maxim = 0;

        for (int i = 0; i < this.filelength; i++) {
            maxim = Math.max(this.students.get(i).getLastName().length(), maxim);
        }

        LastNameComparator comparator = new LastNameComparator(maxim);

        sort(comparator);
    }

    private void sort(Comparator<Student> comparator) {
        Student swap;
        boolean sorted;

        do {
            sorted = true;
            for (int i = 0; i < this.filelength - 1; i++) {

                if (inttobool(comparator.compare(this.students.get(i), this.students.get(i + 1)))) {
                    swap = this.students.get(i);
                    this.students.set(i, this.students.get(i + 1));
                    this.students.set(i + 1, swap);
                    sorted = false;
                }

            }
        } while (!sorted);
        savefile();
    }

    public int[] gradestatistics() {
        String[] lineparts;
        int[] grades = new int[4];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students.get(i).getData();
            grades[Integer.parseInt(lineparts[1]) - 9]++; // Adds one to the appropriate array

        }

        return grades;
    }

    public int[] genderstatistics() {
        String[] lineparts;
        int[] genders = new int[2];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students.get(i).getData();
            genders[((int) lineparts[4].toCharArray()[0] - (int) 'F') / ((int) 'M' - (int) 'F')]++;
        }

        return genders;
    }

    public int[] namestatistics() {
        String[] lineparts;
        int[] lastnames = new int[26];

        for (int i = 0; i < this.filelength; i++) {
            lineparts = this.students.get(i).getData();
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

    private void savefile() {
        try {
            ObjectOutputStream filewriter = new ObjectOutputStream(new FileOutputStream("classroom.jobj"));
            filewriter.writeObject(students);
            // Writes the array of primes to a file
            filewriter.close();
        } catch (IOException e) {

        } finally {
            // Make sure to catch these and do nothing
        }
    }


    public String generatestring() {
        String str = "";
        for (int i = 0; i < this.students.getSize(); i++) {
            for (int j = 0; j < 5; j++) {
                str += this.students.get(i).getData()[j] + " ";
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
            this.sourcefilename = "classlist.txt";
            return generatescanner();
        }
    }

    public ObservableList<Student> getStudents() {
        return this.students;
    }

}

