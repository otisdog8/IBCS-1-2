import java.util.Comparator;


class LastNameComparator implements Comparator<Student> {
    int padding;

    LastNameComparator(int padding) {
        this.padding = padding;
    }

    public int compare(Student o1, Student o2) {
        return comparestrings(o1.getLastName(), o2.getLastName(), this.padding);
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

