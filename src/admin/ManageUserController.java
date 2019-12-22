package admin;
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

public class ManageUserController
{
    @FXML
    private Pane mainpane;

    @FXML
    private Text options;

    @FXML
    private Button madminbutton;

    @FXML
    private Button mbookbutton;

    @FXML
    private Button mshelfbutton;

    @FXML
    private Button mborrowerbutton;

    @FXML
    private Button rbookbutton;

    @FXML
    private Button eprofilebutton;

    @FXML
    private Button cpasswordbutton;

    @FXML
    private Button muserbutton;

    @FXML
    private Button mlibrarianbutton;

    @FXML
    private Button mysqlsettingbutton;

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
    private Button musearchbutton;

    @FXML
    private Button murefreshbutton;

    @FXML
    private Button mudeletebutton;

    @FXML
    private ComboBox<String> mutype;

    @FXML
    private TableView<UserInfo> table;

    @FXML
    private TableColumn<UserInfo, Integer> tid;

    @FXML
    private TableColumn<UserInfo, String> tusername;

    @FXML
    private TableColumn<UserInfo, String> tfirstname;

    @FXML
    private TableColumn<UserInfo, String> tlastname;

    @FXML
    private TableColumn<UserInfo, String> tpassword;

    @FXML
    private TableColumn<UserInfo, String> tsecuritypassword;

    @FXML
    private TableColumn<UserInfo, String> tcolor;

    @FXML
    private TableColumn<UserInfo, String> tcreatedat;

    @FXML
    private TableColumn<UserInfo, String> tupdatedat;

    @FXML
    private Text totaluser;

    @FXML
    private Button madduserbutton;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) dbconnect.setText("● Database not connected");
        else dbconnect.setText("● Database connected");
        changeColor(con.getColor(-1));

        mutype.getItems().addAll("Search by ID", "Search by Username", "Search by First Name", "Search by Last Name");
        mutype.getSelectionModel().selectFirst();
        mudeletebutton.setVisible(false);

        DBConnectionClass conn = new DBConnectionClass();

        tid.setCellValueFactory(data -> data.getValue().getID().asObject());
        tusername.setCellValueFactory(feature -> feature.getValue().getUsername());
        tfirstname.setCellValueFactory(feature -> feature.getValue().getFirstName());
        tlastname.setCellValueFactory(feature -> feature.getValue().getLastName());
        tpassword.setCellValueFactory(feature -> feature.getValue().getPassword());
        tsecuritypassword.setCellValueFactory(feature -> feature.getValue().getSecurityPassword());
        tcolor.setCellValueFactory(feature -> feature.getValue().getColor());
        tcreatedat.setCellValueFactory(feature -> feature.getValue().getCreateDate());
        tupdatedat.setCellValueFactory(feature -> feature.getValue().getUpdateDate());

        try {
            table.getItems().addAll(conn.getSearchUsers("", "All", 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totaluser.setText("[Total User: " + table.getItems().size() + "]");

        tusername.setCellFactory(TextFieldTableCell.forTableColumn());
        tfirstname.setCellFactory(TextFieldTableCell.forTableColumn());
        tlastname.setCellFactory(TextFieldTableCell.forTableColumn());
        tpassword.setCellFactory(TextFieldTableCell.forTableColumn());
        tsecuritypassword.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    void onTableClicked(MouseEvent event) {
        mudeletebutton.setVisible(true);
    }

    @FXML
    void onUsernameCommit(TableColumn.CellEditEvent<UserInfo, String> userInfoStringCellEditEvent) {
        DBConnectionClass conn = new DBConnectionClass();
        UserInfo username = table.getSelectionModel().getSelectedItem();
        username.setUsername(userInfoStringCellEditEvent.getNewValue());
        conn.setUsername(username.getID().intValue(), userInfoStringCellEditEvent.getNewValue());
    }

    @FXML
    void onFirstNameCommit(TableColumn.CellEditEvent<UserInfo, String> userInfoStringCellEditEvent) {
        DBConnectionClass conn = new DBConnectionClass();
        UserInfo firstname = table.getSelectionModel().getSelectedItem();
        firstname.setFirstName(userInfoStringCellEditEvent.getNewValue());
        conn.setFirstName(firstname.getID().intValue(), userInfoStringCellEditEvent.getNewValue());
    }

    @FXML
    void onLastNameCommit(TableColumn.CellEditEvent<UserInfo, String> userInfoStringCellEditEvent) {
        DBConnectionClass conn = new DBConnectionClass();
        UserInfo lastname = table.getSelectionModel().getSelectedItem();
        lastname.setLastName(userInfoStringCellEditEvent.getNewValue());
        conn.setLastName(lastname.getID().intValue(), userInfoStringCellEditEvent.getNewValue());
    }

    @FXML
    void onPasswordCommit(TableColumn.CellEditEvent<UserInfo, String> userInfoStringCellEditEvent) {
        DBConnectionClass conn = new DBConnectionClass();
        UserInfo password = table.getSelectionModel().getSelectedItem();
        password.setPassword(userInfoStringCellEditEvent.getNewValue());
        conn.setPassword(password.getID().intValue(), userInfoStringCellEditEvent.getNewValue());
    }

    @FXML
    void onSecurityPasswordCommit(TableColumn.CellEditEvent<UserInfo, String> userInfoStringCellEditEvent) {
        DBConnectionClass conn = new DBConnectionClass();
        UserInfo securitypassword = table.getSelectionModel().getSelectedItem();
        securitypassword.setSecurityPassword(userInfoStringCellEditEvent.getNewValue());
        conn.setSecurityPassword(securitypassword.getID().intValue(), userInfoStringCellEditEvent.getNewValue());
    }

    @FXML
    void enterSearchKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            musearchbutton.fire();
        }
    }

    @FXML
    void rbookButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/RetrieveBook.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/ChangePassword.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/EditProfile.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/Home.fxml"));
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
    void mbookButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/ManageBook.fxml"));
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
    void mborrowerButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/ManageBorrower.fxml"));
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
    void madminButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/ManageAdmin.fxml"));
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
    void mlibrarianButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/ManageLibrarian.fxml"));
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
    void muserButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/ManageUser.fxml"));
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
    void mysqlsettingsButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/MySQLSettings.fxml"));
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
    void mshelfButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/ManageShelf.fxml"));
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
    void madduserButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/admin/AddUser.fxml"));
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
            madminbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mlibrarianbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            muserbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mbookbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mshelfbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mborrowerbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            rbookbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mysqlsettingbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            eprofilebutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            cpasswordbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            musearchbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            murefreshbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mutype.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mudeletebutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            madduserbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            totaluser.setFill(Paint.valueOf(color));
        }
    }

    @FXML
    void muRefreshButtonAction(ActionEvent event) {
        DBConnectionClass conn = new DBConnectionClass();
        mutype.getSelectionModel().selectFirst();
        sbox.clear();
        mudeletebutton.setVisible(false);
        table.getItems().clear();
        try {
            table.getItems().addAll(conn.getSearchUsers("", "All", 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totaluser.setText("[Total User: " + table.getItems().size() + "]");
    }

    @FXML
    void muSearchButtonAction(ActionEvent event) {
        DBConnectionClass conn = new DBConnectionClass();
        table.getItems().clear();
        try {
            table.getItems().addAll(conn.getSearchUsers(sbox.getText(), mutype.getValue(), 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        totaluser.setText("[Total User: " + table.getItems().size() + "]");
    }

    @FXML
    void muDeleteButtonAction(ActionEvent event) {
        if(table.getSelectionModel().getSelectedItem() != null) {
            DBConnectionClass conn = new DBConnectionClass();
            UserInfo user = table.getSelectionModel().getSelectedItem();
            conn.deleteUser(user.getID().intValue());
            murefreshbutton.fire();
        } else mudeletebutton.setVisible(false);
    }
}
