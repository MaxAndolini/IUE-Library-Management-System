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

public class ForgotPasswordController {

    @FXML
    private Pane toppane;

    @FXML
    private Button closeButton;

    @FXML
    private Pane bottompane;

    @FXML
    private Text dbconnect;

    @FXML
    private TextField fspassword;

    @FXML
    private TextField fusername;

    @FXML
    private Button changepasswordbutton;

    @FXML
    private Button mainmenu;

    @FXML
    private Text invalid;

    @FXML
    private PasswordField fnewpassword;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) dbconnect.setText("● Database not connected");
        else dbconnect.setText("● Database connected");
    }

    @FXML
    void ChangePasswordButtonAction(ActionEvent aevent) throws SQLException {
        if(!fusername.getText().isEmpty() && !fnewpassword.getText().isEmpty() && !fspassword.getText().isEmpty()) {
            DBConnectionClass conn = new DBConnectionClass();
            if(conn.forgotpasswordCheck(fusername.getText(), fspassword.getText())) {
                if(conn.setNewPassword(fusername.getText(), fnewpassword.getText())) invalid.setText("Info: Your password has been successfully changed.");
                else invalid.setText("Error: Your password could not be changed.");
            } else invalid.setText("Error: Incorrect username or security password.");
        } else invalid.setText("Error: You cannot leave the username, new password, or security password blank.");
        invalid.setVisible(true);
    }

    @FXML
    void MainMenuButtonAction(ActionEvent aevent) throws IOException {
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
    void enterUsernameKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            changepasswordbutton.fire();
        }
    }

    @FXML
    void enterNewPasswordKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            changepasswordbutton.fire();
        }
    }

    @FXML
    void enterSecurityPasswordKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            changepasswordbutton.fire();
        }
    }

    @FXML
    void handleMinimizeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void handleCloseButtonAction(javafx.event.ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
