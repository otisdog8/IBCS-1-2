class shapes {
  public static void diamond() {
    System.out.println("\nDiamond:");
    System.out.println("\n   *   \n  ***  \n ** ** \n**   **\n ** ** \n  ***  \n   *   ");
  }

  public static void box() {
    System.out.println("\nBox:");
    System.out.println("\n******* \n*     *\n*     *\n*     *\n*******");
  }
  public static void oval() {
    System.out.println("\nOval:");
    System.out.println("     *");
    System.out.println("    ***");
    System.out.println("    * *");
    System.out.println("   ** **");
    System.out.println("   *   *");
    System.out.println("   *   *");
    System.out.println("   ** **");
    System.out.println("    * *");
    System.out.println("    ***");
    System.out.println("     *");
  }
  public static void uparrow() {
    System.out.println("\nArrow: ");
    System.out.println("\n   *   \n  ***  \n ***** \n*******\n   *   \n   *   \n   *   ");
  }
  public static void main(String[] args) {
    box();
    diamond();
    oval();
    uparrow();
  }
}
