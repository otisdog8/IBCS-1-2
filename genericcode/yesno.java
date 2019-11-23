
    private boolean yesno(String query) {
        query += "? (y/n)";

        do {
            System.out.println(query);

            if (input.nextLine() == "y") {
                return true;
            } else if (input.nextLine() == "n") {
                return false;
            } else {
                System.out.println("Please put y or n");
            }
        } while (true);
    }