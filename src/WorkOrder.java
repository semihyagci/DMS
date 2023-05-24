import java.util.ArrayList;
import java.util.Stack;

// Interface of Command (declares an interface for executing the operation)
interface Command {
    void startOperationOfWorkOrder();
}

//WorkOrder Class(Command of Command Pattern)
//This class is responisble with forcing to apply operations on Documents at Departments.
abstract class WorkOrder implements Command {
    // Attributes of Workorder
    protected String name;
    protected ArrayList<WorkOrder> subWorkOrders;
    protected User workorderCreator;
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;
    protected static ArrayList<Document> rejectedDocuments = new ArrayList<>();

    //Constructor
    public WorkOrder(String name, User workorderCreator) {
        //The system initialize the departments and the documents of the WorkOrder according to WorkOrder Type. For example: VacationApplicationWorkOrder has related documents with VacationApplication.
        this.name = name;
        this.workorderCreator = workorderCreator;
        subWorkOrders = new ArrayList<>();
    }

    // Method for creating and returning an Arraylist of rejected documents by managers
    public ArrayList<Document> rejectedDocuments() {
        ArrayList<Document> tempList = new ArrayList<>();
        for (Document document : documents) {
            if (!document.isSignedByManager()) {
                rejectedDocuments.add(document);
                tempList.add(document);
            }
        }
        return tempList;
    }

    //Method for checking all documents are signed
    public static boolean checkingAllDocuments(WorkOrder workOrder) {
        boolean checkingAllDocuments = true;
        if (workOrder.documents != null) {
            for (int j = 0; j < workOrder.documents.size(); j++) {
                if (!workOrder.documents.get(j).isSignedByManager()) {
                    checkingAllDocuments = false;
                    break;
                }
            }
        }
        if (workOrder.subWorkOrders != null) {
            for (WorkOrder subWorkOrder : workOrder.subWorkOrders) {
                for (int i = 0; i < subWorkOrder.documents.size(); i++) {
                    if (!subWorkOrder.documents.get(i).isSignedByManager()) {
                        checkingAllDocuments = false;
                        break;
                    }

                }

            }
        }
        return checkingAllDocuments;
    }

    // Get name of workorder
    public String getName() {
        return name;
    }

    // Add subworkorder to workorder
    public void Add(WorkOrder d) {
        subWorkOrders.add(d);
    }

    // Remove subworkorder from workorder
    public void Remove(WorkOrder d) {
        for (int i = 0; i < subWorkOrders.size(); i++) {
            if (subWorkOrders.get(i).getName().equals(d.getName())) {
                subWorkOrders.remove(i);
                return;
            }
        }
    }

    // Display the workorder structure like a tree structure
    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (WorkOrder subWorkOrder : subWorkOrders) {
            subWorkOrder.Display(indent + 2);
        }
    }

    //Method for starting the related operations on the related WorkOrder
    //This method is Execute method of Command Pattern
    @Override
    public void startOperationOfWorkOrder() {
        ArrayList<Document> rejectedDocs;
        if (documents == null) {
            for (WorkOrder subWorkOrder : subWorkOrders) {
                subWorkOrder.startOperationOfWorkOrder();
            }
        } else {
            System.out.println("Your application forwarded to " + departments.get(0).getDepartmentName() + " for approval.\n");
            for (Department department : departments) {
                department.deparmentCheckOperation(documents);
                rejectedDocs = rejectedDocuments();
                if (!rejectedDocs.isEmpty()) {
                    break;
                }
            }
            if (subWorkOrders != null) {
                for (WorkOrder subWorkOrder : subWorkOrders) {
                    subWorkOrder.startOperationOfWorkOrder();
                }
            }

        }

    }

}

// ConcreteWorkOrder1 Class
//This class is responsible with starting Academician Vacation Application WorkOrder scenario in a university.
class VacationApplicationWorkOrder extends WorkOrder {
    public VacationApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDocumentsForVacationApplication(workorderCreator);
    }
}

// ConcreteWorkOrder2 Class
//This class is a SubWorkOrder of VacationApplicationWorkOrder
class VacationApplicationHRWorkOrder extends WorkOrder {
    public VacationApplicationHRWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        departments = Database.createHrDepartmentsForVacationApplication();
        documents = Database.dividingSpecificPartOfTheVacationDocumentsList(1, 2);
        for (Document document : documents) {
            document.Attach(workorderCreator);
        }
    }

}

// ConcreteWorkOrder3 Class
//This class is a SubWorkOrder of VacationApplicationWorkOrder
class VacationApplicationAdministrationWorkOrder extends WorkOrder {
    public VacationApplicationAdministrationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        departments = Database.createAdministrationDepartmentsForVacationApplication();
        documents = Database.dividingSpecificPartOfTheVacationDocumentsList(0, 0);
        for (Document document : documents) {
            document.Attach(workorderCreator);
        }
    }

}

// ConcreteWorkOrder4 Class
//This class is responsible with starting Retirement Application WorkOrder scenario in a Social Security Agency.
class RetirementApplicationWorkOrder extends WorkOrder {
    public RetirementApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDocumentsForRetirementApplication(workorderCreator);

        departments = Database.createPublicRelationsDepartmentsForRetirementApplication();
        documents = Database.dividingSpecificPartOfTheRetirementDocumentsList(0, 0);

        for (Document document : documents) {
            document.Attach(workorderCreator);
        }
    }
}

// ConcreteWorkOrder5 Class
//This class is a SubWorkOrder of RetirementApplicationWorkOrder
class RetirementApplicationSSAWorkOrder extends WorkOrder {

    public RetirementApplicationSSAWorkOrder(String name, User workOrderCreator) {
        super(name, workOrderCreator);
        departments = Database.createSSADepartmentsForRetirementApplication();
        documents = Database.dividingSpecificPartOfTheRetirementDocumentsList(1, 2);
        for (Document document : documents) {
            document.Attach(workOrderCreator);
        }
    }
}

