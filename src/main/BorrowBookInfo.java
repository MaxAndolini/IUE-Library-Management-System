package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BorrowBookInfo {
    private IntegerProperty ID;
    private StringProperty title;
    private StringProperty author;
    private IntegerProperty borrowedbyid;
    private StringProperty borrowedbyname;
    private IntegerProperty borrowedtoid;
    private StringProperty borrowedtoname;
    private StringProperty borroweddate;
    private IntegerProperty retrievedbyid;
    private StringProperty retrievedbyname;
    private StringProperty retrieveddate;

    public void setID(int ID) { this.ID = new SimpleIntegerProperty(ID); }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public void setAuthor(String author) {
        this.author = new SimpleStringProperty(author);
    }

    public void setBorrowedByID(int borrowedbyid) { this.borrowedbyid = new SimpleIntegerProperty(borrowedbyid); }

    public void setBorrowedByName(String borrowedbyname) { this.borrowedbyname = new SimpleStringProperty(borrowedbyname); }

    public void setBorrowedToID(int borrowedtoid) { this.borrowedtoid = new SimpleIntegerProperty(borrowedtoid); }

    public void setBorrowedToName(String borrowedtoname) { this.borrowedtoname = new SimpleStringProperty(borrowedtoname); }

    public void setRetrievedByID(int retrievedbyid) { this.retrievedbyid = new SimpleIntegerProperty(retrievedbyid); }

    public void setRetrievedByName(String retrievedbyname) { this.retrievedbyname = new SimpleStringProperty(retrievedbyname); }

    public IntegerProperty getID() {
        return ID;
    }

    public StringProperty getTitle() {
        return title;
    }

    public StringProperty getAuthor() {
        return author;
    }

    public IntegerProperty getBorrowedByID() {
        return borrowedbyid;
    }

    public StringProperty getBorrowedByName() {
        return borrowedbyname;
    }

    public IntegerProperty getBorrowedToID() {
        return borrowedtoid;
    }

    public StringProperty getBorrowedToName() {
        return borrowedtoname;
    }

    public StringProperty getBorrowedDate() { return borroweddate; }

    public IntegerProperty getRetrievedByID() {
        return retrievedbyid;
    }

    public StringProperty getRetrievedByName() {
        return retrievedbyname;
    }

    public StringProperty getRetrievedDate() { return retrieveddate; }

    public BorrowBookInfo(int ID, String title, String author, int borrowedbyid, String borrowedbyname, int borrowedtoid, String borrowedtoname, String borroweddate, int retrievedbyid, String retrievedbyname, String retrieveddate){
        this.ID = new SimpleIntegerProperty(ID);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.borrowedbyid = new SimpleIntegerProperty(borrowedbyid);
        this.borrowedbyname = new SimpleStringProperty(borrowedbyname);
        this.borrowedtoid = new SimpleIntegerProperty(borrowedtoid);
        this.borrowedtoname = new SimpleStringProperty(borrowedtoname);
        this.borroweddate = new SimpleStringProperty(borroweddate);
        this.retrievedbyid = new SimpleIntegerProperty(retrievedbyid);
        this.retrievedbyname = new SimpleStringProperty(retrievedbyname);
        this.retrieveddate = new SimpleStringProperty(retrieveddate);
    }
}
