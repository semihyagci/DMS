import java.util.ArrayList;
import java.util.Stack;

// This is the "Composite"
class WorkOrder implements Component, Command {
    private String name;
    private Stack<Department> departments;
    private ArrayList<Document> documents;
    private ArrayList<Component> elements = new ArrayList<Component>();

    public WorkOrder(String name, ArrayList<Document> docs, Stack<Department> departments) {
        this.name = name;
        this.documents = docs;
        this.departments = departments;
    }

    public WorkOrder createWorkOrder(String name,ArrayList<Document> docs,Stack<Department> departments){
        return new WorkOrder(name,docs,departments);
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
        // Display each child element on this node
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).Display(indent + 2);
        }
    }

    @Override
    public void Execute() {
        for (int i=0;i<departments.size();i++) {
            departments.get(i).Action(documents);
            if (departments.get(i).equals(departments.lastElement())){
                System.out.println("YOUR APPLICATION APPROVED");
                break;
            }
            System.out.println("Your application forwarded to "+departments.get(i+1).getDepartmentName()+ " for approval.");
            System.out.println();
        }
    }
}
