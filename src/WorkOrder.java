import java.util.ArrayList;
import java.util.Stack;

interface Command {
    void Execute();
}

// This is the "Composite"
abstract class WorkOrder implements Command {
    protected String name;
    protected ArrayList<WorkOrder> subWorkOrders;
    protected User workorderCreator;
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;

    protected static ArrayList<Document> rejectedDocs = new ArrayList<>();

    public WorkOrder(String name, User workorderCreator) {
        this.name = name;
        this.workorderCreator = workorderCreator;
        subWorkOrders = new ArrayList<>();
    }

    public ArrayList<Document> rejectedDocuments() {
        for (Document document : documents) {
            if (!document.isSignedByManager()) {
                if (rejectedDocs.contains(document)) {
                    continue;
                }
                rejectedDocs.add(document);
            }
        }
        return rejectedDocs;
    }
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

    public String getName() {
        return name;
    }

    public void Add(WorkOrder d) {
        subWorkOrders.add(d);
    }

    public void Remove(WorkOrder d) {
        for (int i = 0; i < subWorkOrders.size(); i++) {
            if (subWorkOrders.get(i).getName().equals(d.getName())) {
                subWorkOrders.remove(i);
                return;
            }
        }
    }

    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (WorkOrder subWorkOrder : subWorkOrders) {
            subWorkOrder.Display(indent + 2);
        }
    }

    @Override
    public void Execute() {
        if (documents == null) {
            for (WorkOrder subWorkOrder : subWorkOrders) {
                subWorkOrder.Execute();
            }
        } else {
            System.out.println("Your application forwarded to " + departments.get(0).getDepartmentName() + " for approval.\n");
            for (int i = 0; i < departments.size(); i++) {
                departments.get(i).Action(documents);
                rejectedDocs = rejectedDocuments();
                if (rejectedDocs.size()==i) {
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

// Also composite or Leaf
class VacationApplicationWorkOrder extends WorkOrder {
    public VacationApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDocumentsForVacationApplication(workorderCreator);
    }
}

// Also composite or Leaf
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

// Also composite or Leaf
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

// Also composite or Leaf
class EYTApplicationWorkOrder extends WorkOrder {
    public EYTApplicationWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        Database.createDepartmentsForEYTApplication();
        Database.createDocumentsForEYTApplication(workorderCreator);

        departments = Database.EYTDepartments;
        documents = Database.EYTDocuments;

        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).Attach(workorderCreator);
        }
    }
}

// Also composite or Leaf
class EYTApplicationSecondWorkOrder extends WorkOrder {
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;

    public EYTApplicationSecondWorkOrder(String name, User workOrderCreator) {
        super(name, workOrderCreator);
        departments = Database.createSecondSubDepartmentsForEYTApplication();
        documents = Database.dividingSpecificPartOfTheEYTDocumentsList(1, 1);
        for (Document document : documents) {
            document.Attach(workOrderCreator);
        }
    }
}

// Also composite or Leaf
class EYTApplicationFirstWorkOrder extends WorkOrder {
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;

    public EYTApplicationFirstWorkOrder(String name, User workorderCreator) {
        super(name, workorderCreator);
        //departments = Database.createFirstSubDepartmentsForEYTApplication();
        documents = Database.dividingSpecificPartOfTheVacationDocumentsList(0, 0);
        for (Document document : documents) {
            document.Attach(workorderCreator);
        }
    }
}
