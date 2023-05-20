import java.util.Scanner;

//Facade
//This class as a work engine will route the
//work order from one department into another
public class DMS {
    public DMS() {
    }

    public void createApplication(User user) {
        Scanner input = new Scanner(System.in);

        System.out.println("Press 1 for Vacation Application\nPress 2 for EYT Application\n");
        int choice=input.nextInt();

        while (true){
        if (choice==1){
            WorkOrder root = new WorkOrder("Vacation Request",user);
            WorkOrder vacationHrWorkOrder = new VacationApplicationHRWorkOrder("Vacation Request Human Resources",user);
            WorkOrder vacationWorkOrder = new VacationApplicationAdministrationWorkOrder("Vacation Request Administration",user);
            root.Add(vacationHrWorkOrder);
            root.Add(vacationWorkOrder);
            root.Display(1);
        System.out.println("You choose to create Vacation Application Request. DMS is filling the required areas of the document with your informations automatically.\n");
        user.sendWorkOrder(root);
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
