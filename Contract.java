package iPublisher;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Contract
{
    private final StringProperty id;
    private final StringProperty conditions;
    private final Date startDate;
    private final Date endDate;

    public Contract(String id, String conditions, Date start, Date end)
    {
        this.conditions = new SimpleStringProperty(conditions);
        this.id = new SimpleStringProperty(id);
        this.startDate = start;
        this.endDate = end;
    }

    public void setConditions(String i) { conditions.set(i); }
    public StringProperty conditionsProperty() {
        return conditions;
    }
    public String getName() {
        return conditions.get();
    }

    public void setId(String i) {
        id.set(i);
    }
    public StringProperty idProperty() {
        return id;
    }
    public String getId() {
        return id.get();
    }

}
