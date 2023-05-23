import java.util.ArrayList;
import java.util.Stack;

// INTERFACE OF COMMAND (declares an interface for executing the operation)
interface Command {
    void Execute();
}
// CONCRETE COMMAND 
abstract class WorkOrder implements Command {
    protected String name;
    protected ArrayList<WorkOrder> subWorkOrders;
    protected User workorderCreator;
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;
    protected static ArrayList<Document> rejectedDocuments=new ArrayList<>();

    //CONSTRUCTOR
    public WorkOrder(String name, User workorderCreator) {
        this.name = name;
        this.workorderCreator = workorderCreator;
        subWorkOrders = new ArrayList<>();
    }
    // ARRAYLIST OF REJECTED DOCUMENTS BY MANAGERS
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
    // CHECKS FOR ALL DOCUMENTS ARE SIGNED
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
    // GET NAME OF WORKORDER
    public String getName() {
        return name;
    }
    // ADD SUBWORKORDER TO WORKORDER
    public void Add(WorkOrder d) {
        subWorkOrders.add(d);
    }
    // REMOVE SUBWORKORDER FROM WORKORDER
    public void Remove(WorkOrder d) {
        for (int i = 0; i < subWorkOrders.size(); i++) {
            if (subWorkOrders.get(i).getName().equals(d.getName())) {
                subWorkOrders.remove(i);
                return;
            }
        }
    }
    // DISPLAY THE WORKORDER TREE (STRUCTURE)
    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (WorkOrder subWorkOrder : subWorkOrders) {
            subWorkOrder.Display(indent + 2);
        }
    }
    // EXECUTES THE WORKORDER AND APPLY THE SCENARIO
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

//CONCRETE SUBWORKORDERS THAT IMPLEMENTS COMMAND INTERFACE
class VacationApplicationWorkOrder extends WorkOrder {
    public VacationApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDocumentsForVacationApplication(workorderCreator);
    }
}
//CONCRETE SUBWORKORDERS THAT IMPLEMENTS COMMAND INTERFACE
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
//CONCRETE SUBWORKORDERS THAT IMPLEMENTS COMMAND INTERFACE
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
//CONCRETE SUBWORKORDERS THAT IMPLEMENTS COMMAND INTERFACE
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
//CONCRETE SUBWORKORDERS THAT IMPLEMENTS COMMAND INTERFACE
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

