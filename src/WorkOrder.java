import java.util.ArrayList;
import java.util.Stack;

// This is the "Composite"
class WorkOrder implements Component, Command {
    protected String name;
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;
    protected ArrayList<Component> elements;

    public WorkOrder(String name, ArrayList<Document> docs) {
        this.name = name;
        this.documents = docs;
        elements = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void Add(Component d) {
        elements.add(d);
    }


    public void Remove(Component d) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getName() == d.getName()) {
                elements.remove(i);
                return;
            }
        }
    }

    public void Display(int indent) {
        for (int i = 1; i <= indent; i++) System.out.print("-");
        System.out.println("+ " + getName());
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).Display(indent + 2);
        }
    }

    @Override
    public void Execute() {
        System.out.println("Your application forwarded to " + departments.get(0).getDepartmentName() + " for approval.\n");
        for (int i = 0; i < departments.size(); i++) {
            departments.get(i).Action(documents);
            if (departments.get(i).equals(departments.lastElement())) {
                System.out.println();
                checkingAllDocuments(i);
                break;
            }
            checkingAllDocuments(i);

            System.out.println("Your application forwarded to " + departments.get(i + 1).getDepartmentName() + " for approval.\n");
        }
    }

    private void checkingAllDocuments(int i) {
        boolean checkingAllDocuments = true;
        for (int j = 0; j < documents.size(); j++) {
            if (!documents.get(j).isSignedByManager()) {
                checkingAllDocuments = false;
            }
        }
        if (checkingAllDocuments) {
            System.out.printf("All the documents are okay to moving forward to upper departments to confirm. \n");
        } else {
            System.out.println("@@@@@@@@@@@@@ REJECTED WORKORDER @@@@@@@@@@@@@");
            System.out.println("We are sorry for inform you that your workorder has been rejected because some of the documents are not suitable for our procedures. Please check your documents and make the corrections according to our rules and apply again. ");
            System.exit(0);
        }
    }
}
