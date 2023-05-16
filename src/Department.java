import java.util.ArrayList;

abstract public class Department {
    private String departmentName;
    private Manager manager;

    public Department(String departmentName, Manager manager) {
        this.departmentName = departmentName;
        this.manager = manager;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void Action(ArrayList<Document> documents) {
        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).setSignedByWorker(true);
            Boolean status = documents.get(i).verifyAllFields() && documents.get(i).verifyIsSigned();
            if (status) {
                manager.signingByManager(documents.get(i));
                documents.get(i).Notify();
            } else {
                System.out.println("Rejected application");
            }
        }
    }
}


