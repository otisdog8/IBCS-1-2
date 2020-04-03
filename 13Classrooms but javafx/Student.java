import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student implements Serializable {
    private transient SimpleIntegerProperty studentnum;
    private transient SimpleIntegerProperty grade;
    private transient SimpleStringProperty lastname;
    private transient SimpleStringProperty firstname;
    private transient SimpleStringProperty gender;
    private transient SimpleBooleanProperty selected;
    private String[] data;
    private boolean generic;

    static final long serialVersionUID = 214;

    public Student(String[] data) {
        this.studentnum = new SimpleIntegerProperty(Integer.parseInt(data[0]));
        this.grade = new SimpleIntegerProperty(Integer.parseInt(data[1]));
        this.lastname = new SimpleStringProperty(data[2]);
        this.firstname = new SimpleStringProperty(data[3]);
        this.gender = new SimpleStringProperty(data[4]);
        this.selected = new SimpleBooleanProperty(false);
        this.data = data;
        this.generic = false;
    }

    public Student() {
        this(new String[] {"111111","11","Smith","John","Male"});
        this.generic = true;
    }

    public Student copy() {
        return new Student(this.data);
    }

    public void setID(int ID) {
        this.studentnum.set(ID);
        this.data[0] = String.valueOf(ID);
    }

    public int getID() {
        return this.studentnum.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return this.studentnum;
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
        this.data[1] = String.valueOf(grade);
    }

    public int getGrade() {
        return this.grade.get();
    }

    public SimpleIntegerProperty gradeProperty() {
        return this.grade;
    }

    public void setLastName(String lastname) {
        this.lastname.set(lastname);
        this.data[2] = lastname;
    }

    public String getLastName() {
        return this.lastname.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return this.lastname;
    }

    public void setFirstName(String firstname) {
        this.firstname.set(firstname);
        this.data[3] = firstname;
    }

    public String getFirstName() {
        return this.firstname.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return this.firstname;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
        this.data[4] = gender;
    }

    public String getGender() {
        return this.gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return this.gender;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public boolean getSelected() {
        return this.selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return this.selected;
    }

    public String[] getData() {
        return this.data;
    }

    public boolean isGeneric() {
        return this.generic;
    }

    //Serialization stuff
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(this.studentnum.get());
        oos.writeInt(this.grade.get());
        oos.writeObject(this.lastname.get());
        oos.writeObject(this.firstname.get());
        oos.writeObject(this.gender.get());
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        this.studentnum = new SimpleIntegerProperty(ois.readInt());
        this.grade = new SimpleIntegerProperty(ois.readInt());
        this.lastname = new SimpleStringProperty((String) ois.readObject());
        this.firstname = new SimpleStringProperty((String) ois.readObject());
        this.gender = new SimpleStringProperty((String) ois.readObject());
        this.selected = new SimpleBooleanProperty(false);
    }
}