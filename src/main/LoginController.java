package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Pane toppane;

    @FXML
    private Button closeButton;

    @FXML
    private Pane bottompane;

    @FXML
    private Text dbconnect;

    @FXML
    private Button creditsbutton;

    @FXML
    private Button dbsettingsbutton;

    @FXML
    private TextField loginusername;

    @FXML
    private PasswordField loginpassword;

    @FXML
    private Button loginbutton;

    @FXML
    private Button forgotbutton;

    @FXML
    private Text invalid;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) dbconnect.setText("● Database not connected");
        else dbconnect.setText("● Database connected");
    }

    @FXML
    void enterUsernameKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            loginbutton.fire();
        }
    }

    @FXML
    void enterPasswordKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            loginbutton.fire();
        }
    }

    @FXML
    void ForgotButtonAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/ForgotPassword.fxml"));
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
    void LoginButtonAction(ActionEvent aevent) throws IOException, SQLException {
        if(!loginusername.getText().isEmpty() && !loginpassword.getText().isEmpty()) {
            DBConnectionClass conn = new DBConnectionClass();
            if((conn.loginCheck(loginusername.getText(), loginpassword.getText()))) {
                int alevel = conn.getAbilityLevel(-1);
                String gui = null;
                if(alevel == 0) gui = "user";
                else if(alevel == 1) gui = "librarian";
                else if(alevel == 2) gui = "admin";
                Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/" + gui + "/Home.fxml"));
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
            } else {
                invalid.setText("Error: Incorrect username or password.");
                invalid.setVisible(true);
            }
        } else {
            invalid.setText("Error: You cannot leave the username or password blank.");
            invalid.setVisible(true);
        }
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

    @FXML
    void CreditsAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/Credits.fxml"));
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
    void DBSettingsAction(ActionEvent aevent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Main.class.getResource("/gui/DatabaseSettingsController.fxml"));
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

}