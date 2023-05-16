//Composite Pattern
// This is the "Component". (i.e tree node.)
interface Component {
    void Add(Component d);

    void Remove(Component d);

    void Display(int indent);

    String getName();
}