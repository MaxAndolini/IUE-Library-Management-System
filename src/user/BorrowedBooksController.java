package user;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import main.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javafx.scene.layout.Pane;

public class BorrowedBooksController
{
    @FXML
    private Pane mainpane;

    @FXML
    private Text options;

    @FXML
    private Button profilebutton;

    @FXML
    private Button searchbookbutton;

    @FXML
    private Button bbooksbutton;

    @FXML
    private Button eprofilebutton;

    @FXML
    private Button cpasswordbutton;

    @FXML
    private Pane toppane;

    @FXML
    private Button closeButton;

    @FXML
    private Pane bottompane;

    @FXML
    private ColorPicker colorpicker;

    @FXML
    private Text dbconnect;

    @FXML
    private TextField sbox;

    @FXML
    private Button bbsearchbutton;

    @FXML
    private Button bbrefreshbutton;

    @FXML
    private ComboBox<String> bbstype;

    @FXML
    private TableView<BorrowBookInfo> table;

    @FXML
    private TableColumn<BorrowBookInfo, Integer> tid;

    @FXML
    private TableColumn<BorrowBookInfo, String> ttitle;

    @FXML
    private TableColumn<BorrowBookInfo, String> tauthor;

    @FXML
    private TableColumn<BorrowBookInfo, String> tborrowedby;

    @FXML
    private TableColumn<BorrowBookInfo, Integer> tborrowedbyid;

    @FXML
    private TableColumn<BorrowBookInfo, String> tborrowedbyname;

    @FXML
    private TableColumn<BorrowBookInfo, String> tborrowedto;

    @FXML
    private TableColumn<BorrowBookInfo, Integer> tborrowedtoid;

    @FXML
    private TableColumn<BorrowBookInfo, String> tborrowedtoname;

    @FXML
    private TableColumn<BorrowBookInfo, String> tborrowedat;

    @FXML
    private TableColumn<BorrowBookInfo, String> tretrievedby;

    @FXML
    private TableColumn<BorrowBookInfo, Integer> tretrievedbyid;

    @FXML
    private TableColumn<BorrowBookInfo, String> tretrievedbyname;

    @FXML
    private TableColumn<BorrowBookInfo, String> tretrievedat;

    @FXML
    private Text totalbbooks;

    @FXML
    private DatePicker bbsfirstdate;

    @FXML
    private DatePicker bbsseconddate;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) dbconnect.setText("● Database not connected");
        else dbconnect.setText("● Database connected");
        changeColor(con.getColor(-1));

        bbstype.getItems().addAll("Search by Title", "Search by Author", "Search by Borrowed By ID", "Search by Borrowed To ID", "Search by Retrieved By ID");
        bbstype.getSelectionModel().selectFirst();

        DBConnectionClass conn = new DBConnectionClass();

        tid.setCellValueFactory(data -> data.getValue().getID().asObject());
        ttitle.setCellValueFactory(feature -> feature.getValue().getTitle());
        tauthor.setCellValueFactory(feature -> feature.getValue().getAuthor());
        tborrowedbyid.setCellValueFactory(data -> data.getValue().getBorrowedByID().asObject());
        tborrowedbyname.setCellValueFactory(feature -> feature.getValue().getBorrowedByName());
        tborrowedtoid.setCellValueFactory(data -> data.getValue().getBorrowedToID().asObject());
        tborrowedtoname.setCellValueFactory(feature -> feature.getValue().getBorrowedToName());
        tborrowedat.setCellValueFactory(feature -> feature.getValue().getBorrowedDate());
        tretrievedbyid.setCellValueFactory(data -> data.getValue().getRetrievedByID().asObject());
        tretrievedbyname.setCellValueFactory(feature -> feature.getValue().getRetrievedByName());
        tretrievedat.setCellValueFactory(feature -> feature.getValue().getRetrievedDate());

        try {
            table.getItems().addAll(conn.getSearchBorrowedBooks(null, "All", null, null,0, LoginInfo.getID()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totalbbooks.setText("[Total Borrowed Book: " + table.getItems().size() + "]");
    }

    @FXML
    void enterSearchKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            bbsearchbutton.fire();
        }
    }

    @FXML
    void profileButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/user/Profile.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) aevent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        //we gonna drag the frame
        home_page_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        home_page_parent.setOnMouseDragged(event -> {
            app_stage.setX(event.getScreenX() - x);
            app_stage.setY(event.getScreenY() - y);
        });
        app_stage.show();
    }

    @FXML
    void changeColor(ActionEvent event) {
        DBConnectionClass con = new DBConnectionClass();
        changeColor(colorpicker.getValue().toString());
        con.setColor(-1, colorpicker.getValue().toString());
    }

    @FXML
    void cpasswordButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/user/ChangePassword.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) aevent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        //we gonna drag the frame
        home_page_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        home_page_parent.setOnMouseDragged(event -> {
            app_stage.setX(event.getScreenX() - x);
            app_stage.setY(event.getScreenY() - y);
        });
        app_stage.show();
    }

    @FXML
    void eprofileButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/user/EditProfile.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) aevent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        //we gonna drag the frame
        home_page_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        home_page_parent.setOnMouseDragged(event -> {
            app_stage.setX(event.getScreenX() - x);
            app_stage.setY(event.getScreenY() - y);
        });
        app_stage.show();
    }

    @FXML
    void homeButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/user/Home.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) aevent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        //we gonna drag the frame
        home_page_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        home_page_parent.setOnMouseDragged(event -> {
            app_stage.setX(event.getScreenX() - x);
            app_stage.setY(event.getScreenY() - y);
        });
        app_stage.show();
    }

    @FXML
    void logoutButtonAction(ActionEvent aevent) throws IOException {
        main.LoginInfo.SetID(-1);
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) aevent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        //we gonna drag the frame
        home_page_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        home_page_parent.setOnMouseDragged(event -> {
            app_stage.setX(event.getScreenX() - x);
            app_stage.setY(event.getScreenY() - y);
        });
        app_stage.show();
    }

    @FXML
    void sbookButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/user/SearchBook.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) aevent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        //we gonna drag the frame
        home_page_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        home_page_parent.setOnMouseDragged(event -> {
            app_stage.setX(event.getScreenX() - x);
            app_stage.setY(event.getScreenY() - y);
        });
        app_stage.show();
    }

    @FXML
    void bbooksButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/user/BorrowedBooks.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) aevent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        //we gonna drag the frame
        home_page_parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        home_page_parent.setOnMouseDragged(event -> {
            app_stage.setX(event.getScreenX() - x);
            app_stage.setY(event.getScreenY() - y);
        });
        app_stage.show();
    }

    @FXML
    void handleMinimizeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void changeColor(String color) {
        if(!color.equals("")) {
            toppane.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bottompane.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            options.setFill(Paint.valueOf(color));
            profilebutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            searchbookbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bbooksbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            eprofilebutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            cpasswordbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bbsearchbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bbrefreshbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bbstype.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bbsfirstdate.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bbsseconddate.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            totalbbooks.setFill(Paint.valueOf(color));
        }
    }

    @FXML
    void bbRefreshButtonAction(ActionEvent event) {
        DBConnectionClass conn = new DBConnectionClass();
        bbstype.getSelectionModel().selectFirst();
        bbsfirstdate.setValue(null);
        bbsseconddate.setValue(null);
        sbox.clear();
        table.getItems().clear();
        try {
            table.getItems().addAll(conn.getSearchBorrowedBooks("", "All", null, null, 0, LoginInfo.getID()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totalbbooks.setText("[Total Borrowed Book: " + table.getItems().size() + "]");
    }

    @FXML
    void bbSearchButtonAction(ActionEvent event) {
        DBConnectionClass conn = new DBConnectionClass();
        table.getItems().clear();
        try {
            table.getItems().addAll(conn.getSearchBorrowedBooks(sbox.getText(), bbstype.getValue(), (bbsfirstdate.getValue() == null) ? null : Timestamp.valueOf(bbsfirstdate.getValue().atTime(0, 0)), (bbsseconddate.getValue() == null) ? null : Timestamp.valueOf(bbsseconddate.getValue().atTime(0, 0)), 0, LoginInfo.getID()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totalbbooks.setText("[Total Borrowed Book: " + table.getItems().size() + "]");
    }
}
