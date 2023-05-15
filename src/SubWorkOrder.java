import java.util.ArrayList;

class SubWorkOrder implements WorkOrderComponent, Command {
    private String name;
    private Department department;
    private ArrayList<Document> documents;

    public String getName() {
        return name;
    }

    public SubWorkOrder(String name, ArrayList<Document> docs, Department department) {
        this.name = name;
        this.documents = docs;
        this.department=department;
    }

    public void Add(WorkOrderComponent d) {
        elements.add(d);
    }

    public void Remove(WorkOrderComponent d) {
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

    private ArrayList<WorkOrderComponent> elements = new ArrayList<WorkOrderComponent>();

    @Override
    public void Execute() {
        //Action will be replaced with Template Method in here
        department.Action(documents);
    }

    @Override
    public void UnExecute() {
        // Can be filled
    }
}