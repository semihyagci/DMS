public class DMS {

    //Factory Method
    public Document createWordDocument(){
        return new WordDocument();
    }

    public Document createPDFDocument(){
        return new PDFDocument();
    }
}
