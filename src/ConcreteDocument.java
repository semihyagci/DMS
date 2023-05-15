class ConcreteDocument extends Document {
    public ConcreteDocument(String type,String name,String address) {
        super(type,name,address);
    }

    @Override
    protected Document createDocument(String type,String name,String address) {
        return new ConcreteDocument(type,name,address);
    }

    @Override
    protected Boolean verifyAllFields() {
        return null;
    }

    @Override
    protected void sendToManager() {

    }

}