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

    public int getID() {
        return this.studentnum.get();
    }

    public SimpleIntegerProperty getIDProperty() {
        return this.studentnum;
    }

    public int getGrade() {
        return this.grade.get();
    }

    public SimpleIntegerProperty getGradeProperty() {
        return this.studentnum;
    }

    public String getLastName() {
        return this.lastname.get();
    }

    public SimpleStringProperty getLastNameProperty() {
        return this.studentnum;
    }

    public String getFirstName() {
        return this.firstname.get();
    }

    public SimpleStringProperty getFirstNameProperty() {
        return this.studentnum;
    }

    public String getGender() {
        return this.gender.get();
    }

    public SimpleStringProperty getGenderProperty() {
        return this.studentnum;
    }

    public String[] getData() {
        return this.data;
    }

    public boolean isGeneric() {
        return this.generic;
    }
}
    