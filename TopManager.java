package iPublisher;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class TopManager
{
    private final StringProperty employeeId;
    private final StringProperty name;

    public TopManager(String id, String name)
    {
        this.name = new SimpleStringProperty(name);
        this.employeeId = new SimpleStringProperty(id);
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

    public void setId(String i) {
        employeeId.set(i);
    }
    public StringProperty idProperty() {
        return employeeId;
    }
    public String getId() {
        return employeeId.get();
    }
}
