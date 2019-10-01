  private static int ensureint() {
    boolean isint = false;
    int result = 0;

    while (!isint) {
      if (input.hasNextInt()) {
        result = input.nextInt();
        isint = true;
      }
      else {
        input.nextLine();
        System.out.print("Please enter an int\n");
      }
    }

    return result;
  }