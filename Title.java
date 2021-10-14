package iPublisher;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;


public class Title
{
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty author;

    public Title(String id, String name, String author)
    {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.author = new SimpleStringProperty(author);
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
        id.set(i);
    }
    public StringProperty idProperty() {
        return id;
    }
    public String getId() {
        return id.get();
    }

    public void setAuthor(String a) {
        author.set(a);
    }
    public StringProperty authorProperty() {
        return author;
    }
    public String getAuthor() {
        return author.get();
    }
}
