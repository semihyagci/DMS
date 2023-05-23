import java.util.ArrayList;
import java.util.Scanner;
// Departmen class 
abstract public class Department {
    // Attributes of departments
    protected String departmentName;
    protected Manager manager;
    // Constructor
    public Department(String departmentName, Manager manager) {
        this.departmentName = departmentName;
        this.manager = manager;
    }
    // Return the manager
    public Manager getManager() {
        return manager;
    }
    // Returns the departments name
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
//Manager class
class Manager {
    //Attributes for manager
    private final String name;
    // Manager constructor
    public Manager(String name) {
        this.name = name;
    }
    // SIGNING BY MANAGER
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
            //Signed
            if (sign == 1) {
                document.setSignedByManager(true);
                System.out.println("***************************");
                System.out.println("Switching back to user.");
                System.out.println("***************************");
                break;
            }
            //Not signed
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
// Concrete Departments
class EngineeringDepartment extends Department {
    public EngineeringDepartment(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments 
class EngineeringDeanery extends Department {
    public EngineeringDeanery(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments 
class EYTFirstDep extends Department {
    public EYTFirstDep(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments 
class EYTThirdDep extends Department {
    public EYTThirdDep(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments 
class Rectorate extends Department {
    public Rectorate(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments 
class HumanResources extends Department {
    public HumanResources(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

