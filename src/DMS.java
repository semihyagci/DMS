import java.util.ArrayList;

public class DMS {


 public void createApplication(User user, Document document,Department department){
  ArrayList a= new ArrayList<Document>();
  a.add(document);
  user.sendWorkOrder(new WorkOrder("Vacation Request",a,new EngineeringDepartment("Software Engineering Department",new Manager("Senem Kumova Metin",29,"Balcova"))));
 }
}
