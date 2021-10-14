package iPublisher;


import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Author
{
    private final StringProperty name;
    private final StringProperty contractNum;
    private final StringProperty address;
    private final StringProperty email;
    private final StringProperty publishingUnitName;

    public Author(String name, String contractNum, String email, String address, String PU_Name)
    {
        this.name = new SimpleStringProperty(name);
        this.contractNum = new SimpleStringProperty(contractNum);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.publishingUnitName = new SimpleStringProperty(PU_Name);
    }

    public void setName(String n) {
        name.set(n);
    }
    public StringProperty nameProperty() {
        return name;
    }
    public String getName() {
        return name.get();
    }

    public void setAddress(String a) {
        address.set(a);
    }
    public StringProperty addressProperty() {
        return address;
    }
    public String getAddress() {
        return address.get();
    }

    public void setEmail(String e) {
        email.set(e);
    }
    public StringProperty emailProperty() {
        return email;
    }
    public String getEmail() {
        return email.get();
    }

    public void setPublishingUnitName(String p) { publishingUnitName.set(p); }
    public StringProperty PublishingUnitProperty() {
        return publishingUnitName;
    }
    public String getPublishingUnitName() {
        return publishingUnitName.get();
    }

    public void setContractNum(String c) {
        contractNum.set(c);
    }
    public StringProperty ContractNumProperty() {
        return contractNum;
    }
    public String getContractNum() {
        return contractNum.get();
    }

}
