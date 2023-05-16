import java.util.ArrayList;

abstract public class Department {
    //protected DMS dms;
    protected String departmentName;
    protected Manager manager;

    public Department(String departmentName, Manager manager) {
        this.departmentName = departmentName;
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void Action(ArrayList<Document> documents) {
        for (int i=0;i<documents.size();i++) {

            Boolean status= documents.get(i).verifyAllFields();
            if (status){
                manager.signingByManager(documents.get(i));
                if (DMS.IsTransmitted(documents.get(i)))
                    documents.get(i).Notify();
                else{
                    System.out.println("Rejected work order. Apply again by editing your document. ");
                    System.exit(0);
                }
            }

        }
    }
}

class EngineeringDepartment extends Department{
    public EngineeringDepartment(String departmentName, Manager manager,DMS dms) {
        super(departmentName, manager);
    }
}
class EngineeringDeanery extends Department{
    public EngineeringDeanery(String departmanName, Manager manager,DMS dms){super(departmanName,manager);}
}
class Rectorate extends Department{
    public Rectorate(String departmanName, Manager manager,DMS dms){super(departmanName,manager);}
}

