package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookInfo {
    private IntegerProperty ID;
    private StringProperty title;
    private StringProperty author;
    private StringProperty category;
    private StringProperty shelf;
    private StringProperty available;
    private StringProperty createdate;
    private StringProperty updatedate;

    public void setID(int ID) { this.ID = new SimpleIntegerProperty(ID); }

    public void setTitle(String title) { this.title = new SimpleStringProperty(title); }

    public void setAuthor(String author) {
        this.author = new SimpleStringProperty(author);
    }

    public void setCategory(String category) {
        this.category = new SimpleStringProperty(category);
    }

    public void setShelfID(String shelf) {
        this.shelf = new SimpleStringProperty(shelf);
    }

    public void setAvailable(String available) {
        this.available = new SimpleStringProperty(available);
    }

    public IntegerProperty getID() {
        return ID;
    }

    public StringProperty getTitle() {
        return title;
    }

    public StringProperty getAuthor() {
        return author;
    }

    public StringProperty getCategory() {
        return category;
    }

    public StringProperty getShelf() {
        return shelf;
    }

    public StringProperty getAvailable() {
        return available;
    }

    public StringProperty getCreateDate() { return createdate; }

    public StringProperty getUpdateDate() { return updatedate; }

    public BookInfo(int ID, String title, String author, String category, String shelf, String available, String createdate, String updatedate){
        this.ID = new SimpleIntegerProperty(ID);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.shelf = new SimpleStringProperty(shelf);
        this.available = new SimpleStringProperty(available);
        this.createdate = new SimpleStringProperty(createdate);
        this.updatedate = new SimpleStringProperty(updatedate);
    }
}
