import java.util.ArrayList;

// This is the "Composite"
abstract class WorkOrder implements Command {
    protected String name;
    protected ArrayList<WorkOrder> subWorkOrders;
    protected User workorderCreator;

    public WorkOrder(String name, User workorderCreator) {
        this.name = name;
        this.workorderCreator = workorderCreator;
        subWorkOrders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void Add(WorkOrder d) {
        subWorkOrders.add(d);
    }

    public void Remove(WorkOrder d) {
        for (int i = 0; i < subWorkOrders.size(); i++) {
            if (subWorkOrders.get(i).getName() == d.getName()) {
                subWorkOrders.remove(i);
                return;
            }
        }
    }

    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (int i = 0; i < subWorkOrders.size(); i++) {
            subWorkOrders.get(i).Display(indent + 2);
        }
    }

    @Override
    public void Execute() {
        for (int i = 0; i < subWorkOrders.size(); i++) {
            subWorkOrders.get(i).Execute();
        }
    }

    public boolean checkingAllDocuments(int i, ArrayList<Document> documents) {
        boolean checkingAllDocuments = true;
        for (int j = 0; j < documents.size(); j++) {
            if (!documents.get(j).isSignedByManager()) {
                checkingAllDocuments = false;
            }
        }
        return checkingAllDocuments;
    }
}
