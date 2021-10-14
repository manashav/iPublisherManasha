package iPublisher;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Report
{
    private final StringProperty id;
    private final StringProperty content;
    private final Date date;

    public Report(String id, String name, Date date)
    {
        this.content = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.date = date;
    }

    public void setContent(String n) {
        content.set(n);
    }
    public StringProperty contentProperty() {
        return content;
    }
    public String getContent() {
        return content.get();
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
