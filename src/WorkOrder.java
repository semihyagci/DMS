import java.util.ArrayList;

// This is the "Composite"
class WorkOrder implements Component, Command {
    private String name;
    private Department department;
    private ArrayList<Document> documents;
    private ArrayList<Component> elements = new ArrayList<Component>();

    public WorkOrder(String name, ArrayList<Document> docs, Department department) {
        this.name = name;
        this.documents = docs;
        this.department = department;
    }

    public WorkOrder createWorkOrder(String name, ArrayList<Document> docs, Department department) {
        return new WorkOrder(name, docs, department);
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
        department.Action(documents);
    }

}