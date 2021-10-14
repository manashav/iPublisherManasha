package iPublisher;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class PublicUser
{
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty accountName;
    private final StringProperty accountPassword;
    private final BooleanProperty isRegistered;


    public PublicUser(String id, String name, String account, String pass, boolean register)
    {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.accountName = new SimpleStringProperty(account);
        this.accountPassword = new SimpleStringProperty(pass);
        this.isRegistered = new SimpleBooleanProperty(register);
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

    public void setAccount(String n) {
        accountName.set(n);
    }
    public StringProperty accountProperty() {
        return accountName;
    }
    public String getAccount() {
        return accountName.get();
    }

    public void setPass(String n) {
        accountPassword.set(n);
    }
    public StringProperty PasswordProperty() {
        return accountPassword;
    }
    public String getPass() {
        return accountPassword.get();
    }

    public void setRegis(boolean y) {
        isRegistered.set(y);
    }
    public BooleanProperty regisProperty() {
        return isRegistered;
    }
    public boolean getRegistered() {
        return isRegistered.get();
    }
}

