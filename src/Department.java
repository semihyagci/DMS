import java.util.ArrayList;

abstract public class Department {
    protected String departmentName;
    protected Manager manager;

    public Department(String departmentName, Manager manager) {
        this.departmentName = departmentName;
        this.manager = manager;
    }

    public void Action(ArrayList<Document> documents) {
        for (int i=0;i<documents.size();i++) {
            documents.get(i).setSignedByWorker(true);
            Boolean status= documents.get(i).verifyAllFields() && documents.get(i).verifyIsSigned();
            if (status){
                manager.signingByManager(documents.get(i));
                documents.get(i).Notify();
                System.out.println("YOUR APPLICATION IS APPROVED");
            }
            else{
                System.out.println("Rejected workorder");
            }
        }
    }

}
