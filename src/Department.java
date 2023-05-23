import java.util.ArrayList;
import java.util.Scanner;

// Department class (Receiver of the WorkOrder(Command))
//This class is responisble with checking documents fields and signing documents regarding to some procedures which is mentioned on Project Document.
abstract public class Department {
    // Attributes of Department
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

    //The effect of the WorkOrder(ConcreteCommand) in the Receiver side. In that method it's checked the fields of the documents and if they are OK we send it to manager to sign it.
    //This method is Action method of Receiver in Command Pattern
    public void deparmentCheckOperation(ArrayList<Document> documents) {
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
//This class is responsible with signing document if the document is passed through the Department's Action method.
class Manager {
    //Attributes for Manager
    private String name;
    // Manager constructor
    public Manager(String name) {
        this.name = name;
    }
    // Signing Method for Manager
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
// Concrete Departments for Vacation Application
class EngineeringDepartment extends Department {
    public EngineeringDepartment(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments for Vacation Application
class EngineeringDeanery extends Department {
    public EngineeringDeanery(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

// Concrete Departments for Vacation Application
class Rectorate extends Department {
    public Rectorate(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments for Vacation Application
class HumanResources extends Department {
    public HumanResources(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

// Concrete Departments for Retirement Application
class SSAPublicRelation extends Department {
    public SSAPublicRelation(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments for Retirement Application
class SSARetirementPremiumPaymentControl extends Department {
    public SSARetirementPremiumPaymentControl(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}
// Concrete Departments for Retirement Application
class SSARetirementInsuredDayControl extends Department {
    public SSARetirementInsuredDayControl(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

