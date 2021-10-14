package iPublisher;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Date;

public class Review
{
    private final StringProperty id;
    private final StringProperty conditions;
    private final Date date;
    private final StringProperty publishingUnit;
    private final BooleanProperty pass;

    public Review(String id, String condition, Date date,String pub, boolean pass)
    {
        this.conditions = new SimpleStringProperty(condition);
        this.id = new SimpleStringProperty(id);
        this.date = date;
        this.publishingUnit = new SimpleStringProperty(pub);
        this.pass = new SimpleBooleanProperty(pass);
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

    public void setPass(boolean y) {
        pass.set(y);
    }
    public BooleanProperty passProperty() {
        return pass;
    }
    public boolean getPass() {
        return pass.get();
    }

    public void setConditions(String i) { conditions.set(i); }
    public StringProperty conditionsProperty() {
        return conditions;
    }
    public String getName() {
        return conditions.get();
    }

    public void setPublish(String i) { publishingUnit.set(i); }
    public StringProperty publishingProperty() {
        return publishingUnit;
    }
    public String getPublisher() {
        return publishingUnit.get();
    }

}
