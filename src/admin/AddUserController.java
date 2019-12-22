package admin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
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

public class AddUserController
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
    private TextField ausername;

    @FXML
    private PasswordField apassword;

    @FXML
    private Button addbutton;

    @FXML
    private Text invalid;

    @FXML
    private TextField afirstname;

    @FXML
    private TextField alastname;

    @FXML
    private PasswordField asecuritypassword;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) dbconnect.setText("● Database not connected");
        else dbconnect.setText("● Database connected");
        changeColor(con.getColor(-1));
    }

    @FXML
    void enterAddKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            addbutton.fire();
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
            addbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            invalid.setFill(Paint.valueOf(color));
        }
    }

    @FXML
    void AddButtonAction(ActionEvent event) throws SQLException {
        if(!ausername.getText().isEmpty() && !afirstname.getText().isEmpty() && !alastname.getText().isEmpty() && !asecuritypassword.getText().isEmpty() && !apassword.getText().isEmpty()) {
            DBConnectionClass conn = new DBConnectionClass();
            if(!conn.usernameCheck(ausername.getText())) {
                invalid.setText("Info: User added successfully.");
                conn.addUser(ausername.getText(), afirstname.getText(), alastname.getText(), apassword.getText(), asecuritypassword.getText(), 0);
                ausername.clear();
                afirstname.clear();
                alastname.clear();
                asecuritypassword.clear();
                apassword.clear();
            } else invalid.setText("Error: This username already in use.");
        } else invalid.setText("Error: You cannot leave the boxes blank.");
        invalid.setVisible(true);
    }
}
