import java.util.ArrayList;
import java.util.Scanner;

abstract public class Department {
    protected String departmentName;
    protected Manager manager;

    public Department(String departmentName, Manager manager) {
        this.departmentName = departmentName;
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public String getDepartmentName() {
        return departmentName;
    }


    public void Action(ArrayList<Document> documents) {
        for (int i = 0; i < documents.size(); i++) {
            Boolean status = documents.get(i).verifyAllFields();
            if (status) {
                manager.signingByManager(documents.get(i));
                if (documents.get(i).isSignedByManager()) {
                    documents.get(i).Notify();
                }
            }
        }
    }
}

class Manager{
    private String name;


    private int documentIncrement = 1;

    public Manager(String name) {
        this.name=name;
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

class EngineeringDepartment extends Department {
    public EngineeringDepartment(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

class EngineeringDeanery extends Department {
    public EngineeringDeanery(String departmanName, Manager manager) {
        super(departmanName, manager);
    }
}

class Rectorate extends Department {
    public Rectorate(String departmanName, Manager manager) {
        super(departmanName, manager);
    }
}

class HumanResources extends Department {

    public HumanResources(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

