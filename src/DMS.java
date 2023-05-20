import java.util.Scanner;

//Singleton-Facade
//This class as a work engine will route the
//work order from one department into another
public class DMS {
    private static DMS uniqueDMSInstance=null;
    private DMS() {
    }

    //Singleton getInstance method.
    public static DMS getInstance(){
        if (uniqueDMSInstance==null)
            uniqueDMSInstance=new DMS();
        return uniqueDMSInstance;
    }

    public void createApplication(User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("Press 1 for Vacation Application\nPress 2 for EYT Application\n");
        int choice=input.nextInt();

        while (true){
        if (choice==1){
            WorkOrder vacationApplicationWorkOrder = new VacationApplicationWorkOrder("Vacation Request",user);
            WorkOrder vacationHrCheckSubWorkOrder = new VacationApplicationHRWorkOrder("Vacation Check Request Human Resources",user);
            WorkOrder vacationAdministrationCheckSubWorkOrder = new VacationApplicationAdministrationWorkOrder("Vacation Check Request Administration",user);
            vacationApplicationWorkOrder.Add(vacationHrCheckSubWorkOrder);
            vacationApplicationWorkOrder.Add(vacationAdministrationCheckSubWorkOrder);
            System.out.println();
            vacationApplicationWorkOrder.Display(1);
            System.out.println();
        System.out.println("You choose to create Vacation Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
        user.sendWorkOrder(vacationApplicationWorkOrder);
        break;
        }else if (choice==2){
            System.out.println("You choose to create EYT Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
            user.sendWorkOrder(new EYTApplicationWorkOrder("EYT Request",user));
            break;
        }else {
            System.out.println("Invalid choice.\n Press 1 for Vacation Application\nPress 2 for EYT Application\n");
            choice=input.nextInt();
        }
        }
    }
}
