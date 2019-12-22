package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

public class DBConnectionClass {

    private static Connection myCon = null;
    private HashMap<Integer, String> employees;
    private static String url = "";
    private static String username = "";
    private static String password = "";

    public Connection getInstance() {
        try {
            if(myCon != null && myCon.isClosed()) {
                myCon = null;
            }

            File file = new File("resources/mysql.properties");
            if(file.exists()) {
                try (InputStream input = new FileInputStream("resources/mysql.properties")) {

                    Properties prop = new Properties();

                    prop.load(input);

                    url = prop.getProperty("db.url");
                    username = prop.getProperty("db.user");
                    password = prop.getProperty("db.password");

                    input.close();

                    if(url == null || url.equals("") || username == null || username.equals("") || password == null) {
                        myCon = null;
                    } else {
                        try {
                            myCon = DriverManager.getConnection(url, username, password);
                        } catch (SQLException e) {
                            myCon = null;
                            e.printStackTrace();
                        }
                    }

                } catch (IOException ex) {
                    myCon = null;
                    ex.printStackTrace();
                }
            }
        } catch(Exception e) {
            myCon = null;
            e.printStackTrace();
        }

        return myCon;
    }

    public boolean isConnected() throws SQLException {
        if(getInstance() != null && getInstance().isValid(0)) {
            return true;
        }
        return false;
    }

    /*public boolean changeServer(String url, String username, String password, String softPass) {
        boolean ret=false;
        if(softPass.equals("willPower")) {
            this.url=url.trim();
            this.username= username.trim();
            this.password= password.trim();
            try {
                myCon = DriverManager.getConnection( this.url, this.username, this.password);
                ObservableList list = FXCollections.observableArrayList();
                Statement stat = myCon.createStatement();
                employees=new HashMap<Integer,String>();
                ResultSet resultSet= stat.executeQuery("select `ID`,`First Name`,`Last Name` FROM librarystuff");
                while (resultSet.next()) {
                    employees.put(resultSet.getInt("ID"), resultSet.getString("First Name")+ " "+resultSet.getString("Last name"));
                }
                ret=true;
            } catch (SQLException e) {
                this.url="jdbc:mysql://localhost:3306/library?serverTimezone=UTC";
                this.username= "root";
                this.password= "";
                e.printStackTrace();
                ret= false;
            }
        }
        return ret;
    }*/

    public int getID(String username) throws SQLException {
        String s = "SELECT * FROM users WHERE username=\"" + username + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        int ID = -1;
        while (rs.next()) {
            ID = rs.getInt("ID");
        }
        return ID;
    }

    public String getUsername(int id) throws SQLException {
        String s = "SELECT * FROM users WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        String uusername = "";
        while (rs.next()) {
            uusername = rs.getString("username");
        }
        return uusername;
    }

    public String getFirstName(int id) throws SQLException {
        String s = "SELECT * FROM users WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        String ufirstname = "";
        while (rs.next()) {
            ufirstname = rs.getString("firstname");
        }
        return ufirstname;
    }

    public String getLastName(int id) throws SQLException {
        String s = "SELECT * FROM users WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        String ulastname = "";
        while (rs.next()) {
            ulastname = rs.getString("lastname");
        }
        return ulastname;
    }

    public String getPassword(int id) throws SQLException {
        String s = "SELECT * FROM users WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        String upassword = "";
        while (rs.next()) {
            upassword = rs.getString("password");
        }
        return upassword;
    }

    public String getSecurityPassword(int id) throws SQLException {
        String s = "SELECT * FROM users WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        String uspassword = "";
        while (rs.next()) {
            uspassword = rs.getString("securitypassword");
        }
        return uspassword;
    }

    public String getColor(int id) throws SQLException {
        String s = "SELECT * FROM users WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        String ucolor = "";
        while (rs.next()) {
            ucolor = rs.getString("color");
        }
        return ucolor;
    }

    public int getAbilityLevel(int id) throws SQLException {
        String s = "SELECT * FROM users WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        int uabilityLevel = 0;
        while (rs.next()) {
            uabilityLevel = rs.getInt("abilitylevel");
        }
        return uabilityLevel;
    }

    public boolean idCheck(int ID) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM users WHERE ID=\"" + ID + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                int id = -1;
                while (rs.next()) {
                    id = rs.getInt("ID");
                    if(id != -1) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean usernameCheck(String username) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM users WHERE username=\"" + username + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                int id = -1;
                while (rs.next()) {
                    id = rs.getInt("ID");
                    if(id != -1) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean loginCheck(String username, String enteredPassword) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM users WHERE username=\"" + username + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                while (rs.next()) {
                    if(rs.getString("password").equals(enteredPassword)) {
                        LoginInfo.SetID(rs.getInt("ID"));
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean forgotpasswordCheck(String username, String securitypassword) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM users WHERE username=\"" + username + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                while (rs.next()) {
                    if(rs.getString("securitypassword").equals(securitypassword))
                        return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean bookidCheck(int ID) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM books WHERE ID=\"" + ID + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                int id = -1;
                while (rs.next()) {
                    id = rs.getInt("ID");
                    if(id != -1) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean bookstatusCheck(int ID) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM books WHERE ID=\"" + ID + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                int available = -1;
                while (rs.next()) {
                    available = rs.getInt("available");
                    if(available == -1) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean borrowidCheck(int ID) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM borrowedbooks WHERE ID=\"" + ID + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                int id = -1;
                while (rs.next()) {
                    id = rs.getInt("ID");
                    if(id != -1) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean borrowCheck(int ID) throws SQLException {
        if(myCon != null) {
            try {
                String s = "SELECT * FROM borrowedbooks WHERE ID=\"" + ID + "\";";
                Statement stat = myCon.createStatement();
                ResultSet rs = stat.executeQuery(s);
                Timestamp date = null;
                while (rs.next()) {
                    date = rs.getTimestamp("retrievedate");
                    if(date != null) {
                        return true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setUsername(int id, String username) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET username=\"" + username + "\" WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setFirstName(int id, String firstname) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET firstname=\"" + firstname + "\" WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setLastName(int id, String lastname) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET lastname=\"" + lastname + "\" WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setPassword(int id, String password) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET password=\"" + password + "\" WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setSecurityPassword(int id, String securitypassword) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET securitypassword=\"" + securitypassword + "\" WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setColor(int id, String newcolor) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET color=\"" + newcolor + "\" WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setAbilityLevel(int id, int abilityLevel) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET abilityLevel=\"" + abilityLevel + "\" WHERE ID=\"" + ((id == -1) ? LoginInfo.getID() : id) + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setNewPassword(String username, String newpassword) {
        if(myCon != null) {
            try {
                String s = "UPDATE users SET password=\"" + newpassword + "\" WHERE username=\"" + username + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setTitle(int id, String title) {
        if(myCon != null) {
            try {
                String s = "UPDATE books SET title=\"" + title + "\" WHERE ID=\"" + id + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setAuthor(int id, String author) {
        if(myCon != null) {
            try {
                String s = "UPDATE books SET author=\"" + author + "\" WHERE ID=\"" + id + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean setShelfName(int id, String name) {
        if(myCon != null) {
            try {
                String s = "UPDATE shelfs SET name=\"" + name + "\" WHERE ID=\"" + id + "\";";
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate(s);
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public int addUser(String username, String firstname, String lastname, String password, String securitypassword, int abilitylevel) throws SQLException {
        Statement state = myCon.createStatement();
        int rs = state.executeUpdate("INSERT INTO users(`username`, `firstname`, `lastname`, `password`, `securitypassword`, `abilitylevel`)" +
                " VALUES (\"" + username + "\",\"" + firstname + "\",\"" + lastname + "\",\"" + password + "\",\"" + securitypassword + "\",\"" + abilitylevel + "\")");

        int ret = -1;

        ResultSet rs1 = state.executeQuery("SELECT ID FROM users ORDER BY id DESC LIMIT 1");
        while (rs1.next()) {
            ret = rs1.getInt("ID");
        }

        return ret;
    }

    public void deleteUser(int id) {
        if(myCon != null) {
            try {
                String s = "DELETE FROM users WHERE ID=\"" + id + "\";";
                Statement stat = myCon.createStatement();
                stat.executeUpdate(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public int addBook(String title, String author, int categoryid, int shelfid) throws SQLException {
        Statement state = myCon.createStatement();
        int rs = state.executeUpdate("INSERT INTO books(`title`, `author`, `categoryid`, `shelfid`)" +
                " VALUES (\"" + title + "\",\"" + author + "\",\"" + categoryid + "\",\"" + shelfid + "\")");

        int ret = -1;

        ResultSet rs1 = state.executeQuery("SELECT ID FROM books ORDER BY id DESC LIMIT 1");
        while (rs1.next()) {
            ret = rs1.getInt("ID");
        }

        return ret;
    }

    public void deleteBook(int id) {
        if(myCon != null) {
            try {
                String s = "DELETE FROM books WHERE ID=\"" + id + "\";";
                Statement stat = myCon.createStatement();
                stat.executeUpdate(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public int addShelf(String name) throws SQLException {
        Statement state = myCon.createStatement();
        int rs = state.executeUpdate("INSERT INTO shelfs(`name`)" +
                " VALUES (\"" + name + "\")");

        int ret = -1;

        ResultSet rs1 = state.executeQuery("SELECT ID FROM shelfs ORDER BY id DESC LIMIT 1");
        while (rs1.next()) {
            ret = rs1.getInt("ID");
        }

        return ret;
    }

    public void deleteShelf(int id) {
        if(myCon != null) {
            try {
                String s = "DELETE FROM shelfs WHERE ID=\"" + id + "\";";
                Statement stat = myCon.createStatement();
                stat.executeUpdate(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public int borrowBook(int bookid, String username) throws SQLException {
        Statement state = myCon.createStatement();
        int rs = state.executeUpdate("INSERT INTO borrowedbooks(`bookid`, `borrowedby`, `borrowedto`)" +
                " VALUES (\"" + bookid + "\",\"" + LoginInfo.getID() + "\",\"" + getID(username) + "\")");

        int rs1 = state.executeUpdate("UPDATE books SET available=\"" + getID(username) + "\" WHERE ID=\"" + bookid + "\";");

        int ret = -1;

        ResultSet rs2 = state.executeQuery("SELECT ID FROM borrowedbooks ORDER BY id DESC LIMIT 1");
        while (rs2.next()) {
            ret = rs2.getInt("ID");
        }

        return ret;
    }

    public void deleteBorrowBook(int id) {
        if(myCon != null) {
            try {
                String s = "DELETE FROM borrowedbooks WHERE ID=\"" + id + "\";";
                Statement stat = myCon.createStatement();
                stat.executeUpdate(s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean retrieveBook(int id) {
        if(myCon != null) {
            try {
                Statement stat = myCon.createStatement();
                int rs = stat.executeUpdate("UPDATE borrowedbooks SET retrievedby=\"" + LoginInfo.getID() + "\", retrievedate=\"" + new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()) + "\" WHERE ID=\"" + id + "\";");
                int rs1 = stat.executeUpdate("UPDATE books SET available=-1 WHERE ID=\"" + getBorrowedBook(id) + "\";");
                if(rs > 0) return true;
                else return false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<String> getBooksCategory() throws SQLException {
        ArrayList<String> ret = new ArrayList<String>();
        Statement stat = myCon.createStatement();
        ResultSet rSet = stat.executeQuery("SELECT * FROM categories");
        while (rSet.next()) {
            ret.add(rSet.getString("name"));
        }
        rSet.close();
        stat.close();

        return ret;
    }

    public ArrayList<String> getBooksShelf() throws SQLException {
        ArrayList<String> ret = new ArrayList<String>();
        Statement stat = myCon.createStatement();
        ResultSet rSet = stat.executeQuery("SELECT * FROM shelfs");
        while (rSet.next()) {
            ret.add(rSet.getString("name"));
        }
        rSet.close();
        stat.close();

        return ret;
    }

    public int getBookStrToCategory(String category) throws SQLException {
        String s = "SELECT * FROM categories WHERE name=\"" + category + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        int categoryid = -1;
        while (rs.next()) {
            categoryid = rs.getInt("id");
        }
        return categoryid;
    }

    public int getBookStrToShelf(String shelf) throws SQLException {
        String s = "SELECT * FROM shelfs WHERE name=\"" + shelf + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        int shelfid = -1;
        while (rs.next()) {
            shelfid = rs.getInt("id");
        }
        return shelfid;
    }

    public int getBookNumber(int available) throws SQLException {
        String s = "SELECT COUNT(*) AS total FROM books WHERE available" + ((available == 0) ? "!=-1" : "=-1") + ";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        int total = 0;
        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public int getUserNumber(int abilitylevel) throws SQLException {
        String s = "SELECT COUNT(*) AS total FROM users WHERE abilitylevel=\"" + abilitylevel + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        int total = 0;
        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public int getBorrowedBook(int borrowid) throws SQLException {
        String s = "SELECT * FROM borrowedbooks WHERE ID=\"" + borrowid + "\";";
        Statement stat = myCon.createStatement();
        ResultSet rs = stat.executeQuery(s);
        int bookid = -1;
        while (rs.next()) {
            bookid = rs.getInt("bookid");
        }
        return bookid;
    }

    public ObservableList<UserInfo> getSearchUsers(String search, String searchBy, int abilitylevel) throws SQLException {
        ObservableList<UserInfo> list = FXCollections.observableArrayList();
        Statement stat = myCon.createStatement();
        if(searchBy.equals("All")) {
            search = "SELECT * FROM users WHERE abilitylevel=\"" + abilitylevel + "\";";
        }else if(searchBy.equals("Search by ID")) {
            search = "SELECT * FROM users WHERE abilitylevel=\"" + abilitylevel + "\" AND ID LIKE \"%" + search + "%\";";
        } else if(searchBy.equals("Search by Username")) {
            search = "SELECT * FROM users WHERE abilitylevel=\"" + abilitylevel + "\" AND username LIKE \"%" + search + "%\";";
        } else if(searchBy.equals("Search by First Name")) {
            search = "SELECT * FROM users WHERE abilitylevel=\"" + abilitylevel + "\" AND firstname LIKE \"%" + search + "%\";";
        } else if(searchBy.equals("Search by Last Name")) {
            search = "SELECT * FROM users WHERE abilitylevel=\"" + abilitylevel + "\" AND lastname LIKE \"%" + search + "%\";";
        }
        ResultSet rSet = stat.executeQuery(search);
        while (rSet.next()) {
            list.add(new UserInfo(rSet.getInt("ID"), rSet.getString("username"), rSet.getString("firstname"), rSet.getString("lastname"), rSet.getString("password"), rSet.getString("securitypassword"), (rSet.getString("color").equals("")) ? "Default" : rSet.getString("color"), rSet.getTimestamp("createdate").toString(), rSet.getTimestamp("updatedate").toString()));
        }
        rSet.close();
        stat.close();

        return list;
    }

    public ObservableList<ShelfInfo> getSearchShelfs(String search, String searchBy) throws SQLException {
        ObservableList<ShelfInfo> list = FXCollections.observableArrayList();
        Statement stat = myCon.createStatement();
        if(searchBy.equals("All")) {
            search = "SELECT * FROM shelfs;";
        }else if(searchBy.equals("Search by ID")) {
            search = "SELECT * FROM shelfs WHERE ID LIKE \"%" + search + "%\";";
        } else if(searchBy.equals("Search by Name")) {
            search = "SELECT * FROM shelfs WHERE name LIKE \"%" + search + "%\";";
        }
        ResultSet rSet = stat.executeQuery(search);
        while (rSet.next()) {
            list.add(new ShelfInfo(rSet.getInt("ID"), rSet.getString("name"), rSet.getTimestamp("createdate").toString(), rSet.getTimestamp("updatedate").toString()));
        }
        rSet.close();
        stat.close();

        return list;
    }

    public ObservableList<BookInfo> getAllBooks(String s) throws SQLException {
        ObservableList<BookInfo> list = FXCollections.observableArrayList();
        Statement stat = myCon.createStatement();
        if(s.equals("All")) {
            s = "SELECT * FROM books LEFT JOIN categories AS cat ON books.categoryid=cat.ID LEFT JOIN shelfs AS shelf ON books.shelfid=shelf.ID;";
        } else {
            s = "SELECT * FROM books LEFT JOIN categories AS cat ON books.categoryid=cat.ID LEFT JOIN shelfs AS shelf ON books.shelfid=shelf.ID WHERE cat.name=\"" + s + "\";";
        }
        ResultSet rSet = stat.executeQuery(s);
        while (rSet.next()) {
            list.add(new BookInfo(rSet.getInt("ID"), rSet.getString("title"), rSet.getString("author"), rSet.getString("cat.name"), rSet.getString("shelf.name"), ((rSet.getInt("available") == -1) ? "Yes" : "No"), rSet.getTimestamp("createdate").toString(), rSet.getTimestamp("updatedate").toString()));
        }
        rSet.close();
        stat.close();

        return list;
    }

    public ObservableList<BookInfo> getSearchBooks(String search, String searchBy, String category) throws SQLException {
        ObservableList<BookInfo> list = FXCollections.observableArrayList();
        Statement stat = myCon.createStatement();
        if(category.equals("All")) {
            if(searchBy.equals("Search by Title")) {
                search = "SELECT * FROM books LEFT JOIN categories AS cat ON books.categoryid=cat.ID LEFT JOIN shelfs AS shelf ON books.shelfid=shelf.ID WHERE title LIKE \"%" + search + "%\";";
            } else {
                search = "SELECT * FROM books LEFT JOIN categories AS cat ON books.categoryid=cat.ID LEFT JOIN shelfs AS shelf ON books.shelfid=shelf.ID WHERE author LIKE \"%" + search + "%\";";
            }
        } else {
            if(searchBy.equals("Search by Title")) {
                search = "SELECT * FROM books LEFT JOIN categories AS cat ON books.categoryid=cat.ID LEFT JOIN shelfs AS shelf ON books.shelfid=shelf.ID WHERE title LIKE \"%" + search + "%\" AND cat.name=\"" + category + "\";";
            } else {
                search = "SELECT * FROM books LEFT JOIN categories AS cat ON books.categoryid=cat.ID LEFT JOIN shelfs AS shelf ON books.shelfid=shelf.ID WHERE author LIKE \"%" + search + "%\" AND cat.name=\"" + category + "\";";
            }
        }
        ResultSet rSet = stat.executeQuery(search);
        while (rSet.next()) {
            list.add(new BookInfo(rSet.getInt("ID"), rSet.getString("title"), rSet.getString("author"), rSet.getString("cat.name"), rSet.getString("shelf.name"), ((rSet.getInt("available") == -1) ? "Yes" : "No"), rSet.getTimestamp("createdate").toString(), rSet.getTimestamp("updatedate").toString()));
        }
        rSet.close();
        stat.close();

        return list;
    }

    public ObservableList<BorrowBookInfo> getSearchBorrowedBooks(String search, String searchBy, Timestamp startDate, Timestamp endDate, int ability, int ID) throws SQLException {
        ObservableList<BorrowBookInfo> list = FXCollections.observableArrayList();
        Statement stat = myCon.createStatement();
        String s = "SELECT * FROM borrowedbooks LEFT JOIN books AS book ON borrowedbooks.bookid=book.ID LEFT JOIN users AS bby ON borrowedbooks.borrowedby=bby.ID LEFT JOIN users AS bto ON borrowedbooks.borrowedto=bto.ID LEFT JOIN users AS rby ON borrowedbooks.retrievedby=rby.ID";
        if(startDate != null && endDate == null) s += " WHERE borrowdate>=\"" + startDate + "\"";
        else if(startDate != null) s += " WHERE borrowdate>=\"" + startDate + "\" AND borrowdate<=\"" + endDate + "\"";
        if(ability == 0) {
            if(startDate == null && endDate == null) s += " WHERE borrowedto=\"" + ID + "\"";
            else s += " AND borrowedto=\"" + ID + "\"";
        }
        if(ability != 0 && (startDate == null && endDate == null)) {
            if(searchBy.equals("All")) {
                s += ";";
            } else if(searchBy.equals("Search by Title")) {
                s += " WHERE book.title LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Author")) {
                s += " WHERE book.author LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Borrowed By ID")) {
                s += " WHERE borrowedby LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Borrowed To ID")) {
                s += " WHERE borrowedto LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Retrieved By ID")) {
                s += " WHERE retrievedby LIKE \"%" + search + "%\";";
            }
        } else {
            if(searchBy.equals("All")) {
                s += ";";
            } else if(searchBy.equals("Search by Title")) {
                s += " AND book.title LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Author")) {
                s += " AND book.author LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Borrowed By ID")) {
                s += " AND borrowedby LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Borrowed To ID")) {
                s += " AND borrowedto LIKE \"%" + search + "%\";";
            } else if(searchBy.equals("Search by Retrieved By ID")) {
                s += " AND retrievedby LIKE \"%" + search + "%\";";
            }
        }
        ResultSet rSet = stat.executeQuery(s);
        while (rSet.next()) {
            list.add(new BorrowBookInfo(rSet.getInt("ID"), rSet.getString("book.title"), rSet.getString("book.author"), rSet.getInt("borrowedby"), (rSet.getInt("borrowedby") == -1) ? " " : (rSet.getString("bby.firstname") + " " + rSet.getString("bby.lastname")), rSet.getInt("borrowedto"), (rSet.getInt("borrowedto") == -1) ? " " : (rSet.getString("bto.firstname") + " " + rSet.getString("bto.lastname")), rSet.getTimestamp("borrowdate").toString(), rSet.getInt("retrievedby"), (rSet.getInt("retrievedby") == -1) ? "-" : (rSet.getString("rby.firstname") + " " + rSet.getString("rby.lastname")), (rSet.getTimestamp("retrievedate") == null) ? "-" : rSet.getTimestamp("retrievedate").toString()));
        }
        rSet.close();
        stat.close();

        return list;
    }
}