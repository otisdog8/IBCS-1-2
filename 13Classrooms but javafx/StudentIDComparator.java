import java.util.Comparator;


class StudentIDComparator implements Comparator<Student> {
    public int compare(Student o1, Student o2) {
        return (int) Math.signum(o1.getID() - o2.getID());
    }

}