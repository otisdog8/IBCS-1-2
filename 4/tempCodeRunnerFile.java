  private static int gcd(int firstnum, int secondnum) {
    int remainder = 0;

    do { //Euclidean GCD
      remainder = firstnum % secondnum;
      firstnum = secondnum;
      secondnum = remainder;
    } while (remainder != 0);

    return firstnum;
  }