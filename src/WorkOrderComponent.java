//Composite Pattern
// This is the "Component". (i.e tree node.)
interface WorkOrderComponent {
    void Add(WorkOrderComponent d);
    void Remove(WorkOrderComponent d);
    void Display(int indent);
    public String getName();
}