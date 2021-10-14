package iPublisher;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class PublishingUnit
{
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty region;

    public PublishingUnit(String name, String address, String region)
    {
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.region = new SimpleStringProperty(region);
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

    public void setRegion(String r) {
        region.set(r);
    }
    public StringProperty regionProperty() {
        return region;
    }
    public String getRegion() {
        return region.get();
    }
}
