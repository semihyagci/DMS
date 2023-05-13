import java.util.ArrayList;

public class DMS {

  //FACADE
    private Department department;
    private User user;
    private WorkOrder workOrder;
    ArrayList<Document> FirstDocument;

    public DMS(User user, String UserName) {
        user = new User(UserName);
        workOrder = new WorkOrder("FirstWorkOrder", null, department);
    }

    public void createWorkOrder() {
        // ilk workorderımızı oluşturaccağımız yer nere? 
    }

    //composite
    public void addSubWorkOrder(WorkOrderComponent d ) {
        WorkOrderComponent.Add(d);
    }

}
