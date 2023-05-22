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

    // Manager signing in Department
    public void Action(ArrayList<Document> documents) {
        for (Document document : documents) {
            Boolean status = document.verifyAllFields();
            if (status) {
                manager.signingByManager(document);
                if (document.isSignedByManager()) {
                    document.Notify();
                }
            }
        }
    }
}
//Manager Class
class Manager {
    private final String name;

    public Manager(String name) {
        this.name = name;
    }
    // Signing by manager
    public void signingByManager(Document document) {
        Scanner scan = new Scanner(System.in);
        int sign;
        System.out.println(document.getName() + " is now reviewing by " + name + ".\n");
        System.out.println("************************************");
        System.out.println("Switching to " + name);
        System.out.println("************************************");
        System.out.print("Press (1) to sign or press (0) to reject: ");
        sign = scan.nextInt();
        while (true) {
            if (sign == 1) {
                document.setSignedByManager(true);
                System.out.println("***************************");
                System.out.println("Switching back to user.");
                System.out.println("***************************");
                break;
            }
            if (sign == 0) {
                document.setSignedByManager(false);
                System.out.println("***************************");
                System.out.println("Switching back to user.");
                System.out.println("***************************");
                break;
            } else {
                System.out.println("Invalid choice. Try again\n");
                System.out.print("Press (1) to sign or press (0) to reject: ");
                sign = scan.nextInt();
            }
        }
    }
}
// DEPARTMENTS
class EngineeringDepartment extends Department {
    public EngineeringDepartment(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

class EngineeringDeanery extends Department {
    public EngineeringDeanery(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
class EYTFirstDep extends Department {
    public EYTFirstDep(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
class EYTThirdDep extends Department {
    public EYTThirdDep(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

class Rectorate extends Department {
    public Rectorate(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

class HumanResources extends Department {

    public HumanResources(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

