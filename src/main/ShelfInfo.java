package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShelfInfo {
    private IntegerProperty ID;
    private StringProperty name;
    private StringProperty createdate;
    private StringProperty updatedate;

    public void setID(int ID) { this.ID = new SimpleIntegerProperty(ID); }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public IntegerProperty getID() {
        return ID;
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getCreateDate() { return createdate; }

    public StringProperty getUpdateDate() { return updatedate; }

    public ShelfInfo(int ID, String name, String createdate, String updatedate){
        this.ID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.createdate = new SimpleStringProperty(createdate);
        this.updatedate = new SimpleStringProperty(updatedate);
    }
}
