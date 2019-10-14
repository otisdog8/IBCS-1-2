import java.util.ArrayList;

class crash {
  public static void main( String args[] ) {
    ArrayList<Integer> nums = new ArrayList<Integer>();
    int i = 0;
    while (true) {
      nums.add(i);
      i++;
    }

  }
}
