import java.util.Scanner;

public class Manager extends People {
    private int documentIncrement = 1;

    public Manager(String name, int age, String address) {
        super(name, age, address);
    }

    public void signingByManager(Document document) {
        Scanner scan = new Scanner(System.in);
        int sign;
        System.out.println("Your application's " + (documentIncrement) + ". document is  reviewing by " + name + " right now.\n");
        System.out.println("Switching to manager...\n");
        System.out.print("Press (1) to sign or press (0) to reject: ");
        sign = scan.nextInt();
        while (true) {
            if (sign == 1) {
                document.setSignedByManager(true);
                break;
            }
            if (sign == 0) {
                document.setSignedByManager(false);
                break;
            } else {
                System.out.println("Invalid choice. Try again\n");
                System.out.print("Press (1) to sign or press (0) to reject: ");
                sign = scan.nextInt();
            }
        }
        documentIncrement++;
    }
}
