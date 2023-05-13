import java.util.ArrayList;

abstract public class Department {

    // TEMPLATE METHOD IS THİS
    public void Action(ArrayList<Document> documents) {
        for (int i=0;i<documents.size();i++) {
            documents.get(i).setSignedByWorker(true);

            Boolean status= documents.get(i).verifyAllFields() && documents.get(i).verifyIsSigned();
            if (status){
                sendToManager(documents.get(i));
            }
            else{
                System.out.println("Rejected workorder");
            }
        }


    }

    private void sendToManager(Document document) {
        document.setSignedByManager(true);
        document.Notify();
        System.out.println("YOUR APPLICATION IS APPROVED");
    }
}
