import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.NoSuchElementException;
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

    public ObservableList<Student> getStudents() {
        return this.students;
    }

    public Student search(String name) {
        // Searches first by last name, than first name, than returns the generic if both fail
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

    public void deleteSelected() {
        this.students.removeIf(x -> x.getSelected());
        save();
    }

    public void delete(String[] student) {
        delete(new Student(student));
    }

    public void delete(Student student) {
        this.students.remove(student);
        save();
    }

    public void add(String[] student) {
        add(new Student(student));
    }

    public void add(Student student) {
        this.students.add(student);
        save();
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
        save();
    }

    private boolean inttobool(int result) {
        if (result > 0) {
            return true;
        } else {
            return false;
        }
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

    Classroom(String filename) throws FileNotFoundException{
        load(filename);
    }

    Classroom() {
        try {
            load("classlist.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: main file not found");
        }
    }

    public void load(String filename) throws FileNotFoundException {
        try {
            loadJava(filename);
        }
        catch (Exception e) {
            loadText(filename);
        }
    }

    private void loadText(String filename) {
        this.sourcefilename = filename;
        int filelength = getfilelength();
        this.filelength = filelength;
        this.students = new SimpleListProperty<Student>(javafx.collections.FXCollections.observableList(new ArrayList<Student>()));

        Scanner filescanner = generatescanner();

        for (int i = 0; i < filelength; i++) {
            this.students.add(new Student(filescanner.nextLine().split(" ")));
        }
        save();
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

    private void loadJava(String filename) throws FileNotFoundException {
        try {
            this.sourcefilename = filename;
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(filename));
            this.students = new SimpleListProperty<Student>(javafx.collections.FXCollections.observableList((ArrayList<Student>) oin.readObject()));
            this.filelength = this.students.size();
        }
        catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    public void save() {
        save(this.sourcefilename, false);
    }

    public void save(String filename, boolean text) {
        if (text) {
            saveText(filename);
        }
        else {
            saveJava(filename);
        }
    }

    private void saveText(String filename) {
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
        for (int i = 0; i < this.students.getSize(); i++) {
            str += Integer.toString(this.students.get(i).getID()) + " ";
            str += Integer.toString(this.students.get(i).getGrade()) + " ";
            str += this.students.get(i).getLastName() + " ";
            str += this.students.get(i).getFirstName() + " ";
            str += this.students.get(i).getGender();

            str += "\n";
        }
        return str;
    }

    private void saveJava(String filename) {
        try {
            ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filename));
            oout.writeObject(new ArrayList<Student>(this.students));
            oout.close();
        }
        catch (Exception e) {
            System.out.println("Caught an error! " + e.toString());
        }
    }

}

