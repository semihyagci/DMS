import java.util.ArrayList;

abstract public class Department {
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
        for (int i = 0; i < documents.size(); i++) {
            Boolean status = documents.get(i).verifyAllFields();
            if (status) {
                manager.signingByManager(documents.get(i));
                if (documents.get(i).isSignedByManager()) {
                    documents.get(i).Notify();
                }
            }
        }
    }
}

class EngineeringDepartment extends Department {
    public EngineeringDepartment(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

class EngineeringDeanery extends Department {
    public EngineeringDeanery(String departmanName, Manager manager) {
        super(departmanName, manager);
    }
}

class Rectorate extends Department {
    public Rectorate(String departmanName, Manager manager) {
        super(departmanName, manager);
    }
}

class HumanResources extends Department {

    public HumanResources(String departmentName, Manager manager) {
        super(departmentName, manager);
    }
}

