package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserInfo {
    private IntegerProperty ID;
    private StringProperty username;
    private StringProperty firstname;
    private StringProperty lastname;
    private StringProperty password;
    private StringProperty securitypassword;
    private StringProperty color;
    private StringProperty abilitylevel;
    private StringProperty createdate;
    private StringProperty updatedate;

    public void setID(int ID) { this.ID = new SimpleIntegerProperty(ID); }

    public void setUsername(String username) { this.username = new SimpleStringProperty(username); }

    public void setFirstName(String firstname) {
        this.firstname = new SimpleStringProperty(firstname);
    }

    public void setLastName(String lastname) {
        this.lastname = new SimpleStringProperty(lastname);
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public void setSecurityPassword(String securitypassword) { this.securitypassword = new SimpleStringProperty(securitypassword); }

    public void setColor(String color) { this.color = new SimpleStringProperty(color); }

    public void setAbilityLevel(String abilitylevel) { this.abilitylevel = new SimpleStringProperty(abilitylevel); }

    public IntegerProperty getID() {
        return ID;
    }

    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getFirstName() {
        return firstname;
    }

    public StringProperty getLastName() {
        return lastname;
    }

    public StringProperty getPassword() {
        return password;
    }

    public StringProperty getSecurityPassword() {
        return securitypassword;
    }

    public StringProperty getColor() {
        return color;
    }

    public StringProperty getAbilityLevel() {
        return abilitylevel;
    }

    public StringProperty getCreateDate() { return createdate; }

    public StringProperty getUpdateDate() { return updatedate; }

    public UserInfo(int ID, String username, String firstname, String lastname, String password, String securitypassword, String color, String createdate, String updatedate){
        this.ID = new SimpleIntegerProperty(ID);
        this.username = new SimpleStringProperty(username);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.password = new SimpleStringProperty(password);
        this.securitypassword = new SimpleStringProperty(securitypassword);
        this.color = new SimpleStringProperty(color);
        this.createdate = new SimpleStringProperty(createdate);
        this.updatedate = new SimpleStringProperty(updatedate);
    }
}
