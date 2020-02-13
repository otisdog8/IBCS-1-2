import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

class Student {
    private SimpleIntegerProperty studentnum;
    private SimpleIntegerProperty grade;
    private SimpleStringProperty lastname;
    private SimpleStringProperty firstname;
    private SimpleStringProperty gender;
    private String[] data;
    private boolean generic;

    public Student(String[] data) {
        this.studentnum = SimpleIntegerProperty(Integer.parseInt(data[0]));
        this.grade = SimpleIntegerProperty(Integer.parseInt(data[1]));
        this.lastname = SimpleStringProperty(data[2]);
        this.firstname = SimpleStringProperty(data[3]);
        this.gender = SimpleStringProperty(data[4]);
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
        this.studentnum = SimpleIntegerProperty(ID);
        this.data[0] = String.valueOf(ID);
    }

    public int getID() {
        return this.studentnum.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return this.studentnum;
    }

    public void setGrade(int grade) {
        this.grade = SimpleIntegerProperty(grade);
        this.data[1] = String.valueOf(grade);
    }

    public int getGrade() {
        return this.grade.get();
    }

    public SimpleIntegerProperty gradeProperty() {
        return this.grade;
    }

    public void setLastName(String lastname) {
        this.lastname = SimpleStringProperty(lastname);
        this.data[2] = lastname;
    }

    public String getLastName() {
        return this.lastname.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return this.lastname;
    }

    public void setFirstName(String firstname) {
        this.firstname = SimpleStringProperty(firstname);
        this.data[3] = firstname;
    }

    public String getFirstName() {
        return this.firstname.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return this.firstname;
    }

    public void setGender(String gender) {
        this.gender = SimpleStringProperty(gender);
        this.data[4] = gender;
    }

    public String getGender() {
        return this.gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return this.gender;
    }

    public String[] getData() {
        return this.data;
    }

    public boolean isGeneric() {
        return this.generic;
    }
}
    