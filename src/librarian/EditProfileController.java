package librarian;
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
import main.LoginInfo;
import main.Main;

public class EditProfileController
{
    @FXML
    private Pane mainpane;

    @FXML
    private Text options;

    @FXML
    private Button muserbutton;

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
    private TextField sfirstname;

    @FXML
    private Button savebutton;

    @FXML
    private Text invalid;

    @FXML
    private TextField slastname;

    @FXML
    private TextField ssecuritypassword;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) dbconnect.setText("● Database not connected");
        else dbconnect.setText("● Database connected");
        changeColor(con.getColor(-1));

        DBConnectionClass conn = new DBConnectionClass();
        sfirstname.setText(conn.getFirstName(LoginInfo.getID()));
        slastname.setText(conn.getLastName(LoginInfo.getID()));
        ssecuritypassword.setText(conn.getSecurityPassword(LoginInfo.getID()));
    }

    @FXML
    void enterAddKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            savebutton.fire();
        }
    }

    @FXML
    void rbookButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/RetrieveBook.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/ChangePassword.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/EditProfile.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/Home.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/ManageBook.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/ManageBorrower.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/ManageUser.fxml"));
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
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/librarian/ManageShelf.fxml"));
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
            muserbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mbookbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mshelfbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            mborrowerbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            rbookbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            eprofilebutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            cpasswordbutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            savebutton.setStyle("-fx-background-color: "+ color.replace("0x", "#"));
            invalid.setFill(Paint.valueOf(color));
        }
    }

    @FXML
    void SaveButtonAction(ActionEvent event) throws SQLException {
        if(!sfirstname.getText().isEmpty() && !slastname.getText().isEmpty() && !ssecuritypassword.getText().isEmpty()) {
            DBConnectionClass conn = new DBConnectionClass();
            invalid.setText("Info: Profile saved successfully.");
            conn.setFirstName(LoginInfo.getID(), sfirstname.getText());
            conn.setLastName(LoginInfo.getID(), slastname.getText());
            conn.setSecurityPassword(LoginInfo.getID(), ssecuritypassword.getText());
        } else invalid.setText("Error: You cannot leave the boxes blank.");
        invalid.setVisible(true);
    }
}
