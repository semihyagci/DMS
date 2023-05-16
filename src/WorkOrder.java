import java.util.ArrayList;
import java.util.Stack;

// This is the "Composite"
class WorkOrder implements Component, Command {
    protected String name;
    protected Stack<Department> departments;
    protected ArrayList<Document> documents;
    protected ArrayList<Component> elements = new ArrayList<Component>();

    public WorkOrder(String name, ArrayList<Document> docs) {
        this.name = name;
        this.documents = docs;
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
                break;
            }
            System.out.println("Your application forwarded to "+departments.get(i+1).getDepartmentName()+ " for approval.");
            System.out.println();
        }
    }
}
