import java.util.ArrayList;
import java.util.Stack;

// Interface of Command (declares an interface for executing the operation)
interface Command {
    void Execute();
}
// Concrete Command
abstract class WorkOrder implements Command {
    // Attributes of Workorder
    protected String name;
    protected ArrayList<WorkOrder> subWorkOrders;
    protected User workorderCreator;
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;
    protected static ArrayList<Document> rejectedDocuments=new ArrayList<>();

    //Constructor
    public WorkOrder(String name, User workorderCreator) {
        this.name = name;
        this.workorderCreator = workorderCreator;
        subWorkOrders = new ArrayList<>();
    }
    // Arraylist of rejected documents by manager
    public ArrayList<Document> rejectedDocuments() {
        ArrayList<Document> tempList=new ArrayList<>();
        for (Document document : documents) {
            if (!document.isSignedByManager()) {
                rejectedDocuments.add(document);
                tempList.add(document);
            }
        }
        return tempList;
    }
    // Check for all documents are signed
    public static boolean checkingAllDocuments(WorkOrder workOrder) {
        boolean checkingAllDocuments = true;
        if (workOrder.documents!=null){
            for (int j = 0; j < workOrder.documents.size(); j++) {
                if (!workOrder.documents.get(j).isSignedByManager()) {
                    checkingAllDocuments = false;
                    break;
                }
            }
        }
        if (workOrder.subWorkOrders!=null){
            for (WorkOrder subWorkOrder: workOrder.subWorkOrders){
                for (int i=0;i<subWorkOrder.documents.size();i++){
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
    // Display the workorder structure (tree)
    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (WorkOrder subWorkOrder : subWorkOrders) {
            subWorkOrder.Display(indent + 2);
        }
    }
    // Executes the workorder and apply the scenario
    @Override
    public void Execute() {
        ArrayList<Document> rejectedDocs;
        if (documents == null) {
            for (WorkOrder subWorkOrder : subWorkOrders) {
                subWorkOrder.Execute();
            }
        } else {
            System.out.println("Your application forwarded to " + departments.get(0).getDepartmentName() + " for approval.\n");
            for (Department department : departments) {
                department.Action(documents);
                rejectedDocs = rejectedDocuments();
                if (!rejectedDocs.isEmpty()) {
                    break;
                }
            }
            if (subWorkOrders != null) {
                for (WorkOrder subWorkOrder : subWorkOrders) {
                    subWorkOrder.Execute();
                }
            }

        }

    }

}
// Concrete Subworkorders that implements command interface
class VacationApplicationWorkOrder extends WorkOrder {
    public VacationApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDocumentsForVacationApplication(workorderCreator);
    }
}
// Concrete Subworkorders that implements command interface
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
// Concrete Subworkorders that implements command interface
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
// Concrete Subworkorders that implements command interface
class EYTApplicationWorkOrder extends WorkOrder {
    public EYTApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDocumentsForEYTApplication(workorderCreator);

        departments = Database.createPublicRelationsDepartmentsForEYTApplication();
        documents = Database.dividingSpecificPartOfTheEYTDocumentsList(0, 0);

        for (Document document : documents) {
            document.Attach(workorderCreator);
        }
    }
}
// Concrete Subworkorders that implements command interface
class EYTApplicationSSAWorkOrder extends WorkOrder {

    public EYTApplicationSSAWorkOrder(String name, User workOrderCreator) {
        super(name, workOrderCreator);
        departments = Database.createSSADepartmentsForEYTApplication();
        documents = Database.dividingSpecificPartOfTheEYTDocumentsList(1, 2);
        for (Document document : documents) {
            document.Attach(workOrderCreator);
        }
    }
}

