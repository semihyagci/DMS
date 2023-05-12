import javax.print.Doc;
import java.util.ArrayList;


//Composite Pattern
// This is the "Component". (i.e tree node.)
interface WorkOrderComponent {
    void Add(WorkOrderComponent d);
    void Remove(WorkOrderComponent d);
    void Display(int indent);
    public String getName();
}
//This is the "Leaf".
class SubWorkOrder implements WorkOrderComponent {
    private String name;
    private ArrayList<Document> documents;

    public String getName() { return name;}
    public SubWorkOrder(String name,ArrayList<Document> docs) {
        this.name = name;
        this.documents=docs;
    }
    public void Add(WorkOrderComponent d) {elements.add(d);};
    public void Remove(WorkOrderComponent d) {
        for (int i = 0; i< elements.size(); i++) {
            if (elements.get(i).getName() == d.getName()) {
                elements.remove(i);
                return;
            }
        }
    }
    public	void Display(int indent) {
        for(int i = 1;i <= indent;i++) System.out.print("-");
        System.out.println( "+ " + getName());
        // Display each child element on this node
        for (int i= 0; i< elements.size(); i++) {
            elements.get(i).Display(indent+2);
        }
    }

    private ArrayList<WorkOrderComponent> elements = new ArrayList<WorkOrderComponent>();
}

// This is the "Composite"
class WorkOrder implements WorkOrderComponent {
    private String name;
    private ArrayList<Document> documents;
    public String getName() { return name;}
    public WorkOrder(String name) {this.name = name;}
    public void Add(WorkOrderComponent d) {elements.add(d);};
    public void Remove(WorkOrderComponent d) {
        for (int i = 0; i< elements.size(); i++) {
            if (elements.get(i).getName() == d.getName()) {
                elements.remove(i);
                return;
            }
        }
    }
    public	void Display(int indent) {
        for(int i = 1;i <= indent;i++) System.out.print("-");
        System.out.println( "+ " + getName());
        // Display each child element on this node
        for (int i= 0; i< elements.size(); i++) {
            elements.get(i).Display(indent+2);
        }
    }
    private ArrayList<WorkOrderComponent> elements = new ArrayList<WorkOrderComponent>();
}
//This is the "client"