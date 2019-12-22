package user;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import main.BookInfo;
import main.DBConnectionClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.layout.Pane;
import main.Main;
import main.UserInfo;

public class SearchBookController
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
    private Button bsearchbutton;

    @FXML
    private Button brefreshbutton;

    @FXML
    private ComboBox<String> bstype;

    @FXML
    private ComboBox<String> bscategory;

    @FXML
    private TableView<BookInfo> table;

    @FXML
    private TableColumn<BookInfo, Integer> tid;

    @FXML
    private TableColumn<BookInfo, String> ttitle;

    @FXML
    private TableColumn<BookInfo, String> tauthor;

    @FXML
    private TableColumn<BookInfo, String> tcategory;

    @FXML
    private TableColumn<BookInfo, String> tshelf;

    @FXML
    private TableColumn<BookInfo, String> tavailable;

    @FXML
    private TableColumn<BookInfo, String> tcreatedat;

    @FXML
    private TableColumn<BookInfo, String> tupdatedat;

    @FXML
    private Text totalbook;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) dbconnect.setText("● Database not connected");
        else dbconnect.setText("● Database connected");
        changeColor(con.getColor(-1));

        bstype.getItems().addAll("Search by Title", "Search by Author");
        bstype.getSelectionModel().selectFirst();

        DBConnectionClass conn = new DBConnectionClass();
        try {
            bscategory.getItems().add("All");
            for (String s:conn.getBooksCategory()){
                bscategory.getItems().add(s);
            }
            bscategory.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tid.setCellValueFactory(data -> data.getValue().getID().asObject());
        ttitle.setCellValueFactory(feature -> feature.getValue().getTitle());
        tauthor.setCellValueFactory(feature -> feature.getValue().getAuthor());
        tcategory.setCellValueFactory(feature -> feature.getValue().getCategory());
        tshelf.setCellValueFactory(feature -> feature.getValue().getShelf());
        tavailable.setCellValueFactory(feature -> feature.getValue().getAvailable());
        tcreatedat.setCellValueFactory(feature -> feature.getValue().getCreateDate());
        tupdatedat.setCellValueFactory(feature -> feature.getValue().getUpdateDate());

        try {
            table.getItems().addAll(conn.getAllBooks("All"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totalbook.setText("[Total Book: " + table.getItems().size() + "]");
    }

    @FXML
    void enterSearchKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            bsearchbutton.fire();
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
            bsearchbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            brefreshbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bstype.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            bscategory.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            totalbook.setFill(Paint.valueOf(color));
        }
    }

    @FXML
    void bRefreshButtonAction(ActionEvent event) {
        DBConnectionClass conn = new DBConnectionClass();
        bstype.getSelectionModel().selectFirst();
        bscategory.getSelectionModel().selectFirst();
        sbox.clear();
        table.getItems().clear();
        try {
            table.getItems().addAll(conn.getAllBooks("All"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totalbook.setText("[Total Book: " + table.getItems().size() + "]");
    }

    @FXML
    void bSearchButtonAction(ActionEvent event) {
        DBConnectionClass conn = new DBConnectionClass();
        table.getItems().clear();
        try {
            table.getItems().addAll(conn.getSearchBooks(sbox.getText(), bstype.getValue(), bscategory.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totalbook.setText("[Total Book: " + table.getItems().size() + "]");
    }
}
