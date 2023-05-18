import java.util.Scanner;

public class Manager extends People {
    public Manager(String name, int age, String address) {
        super(name, age, address);
    }

    public void signingByManager(Document document) {
        Scanner scan = new Scanner(System.in);
        int sign;
        System.out.println("Your application is reviewing by " + name + " right now.");
        System.out.println("Switching to manager...");
        System.out.print("Press (1) to sign or press (0) to reject: ");
        sign = scan.nextInt();
        while (true) {
            if (sign == 1) {
                System.out.println("Signed for " + document.getType());
                document.setSignedByManager(true);
                break;
            }
            if (sign == 0) {
                document.setSignedByManager(false);
                break;
            } else {
                System.out.println("Invalid choice. Try again");
                System.out.print("Press (1) to sign or press (0) to reject: ");
                sign = scan.nextInt();
            }
        }

    }
}
