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

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseSettingsController {

    @FXML
    private Pane toppane;

    @FXML
    private Button closeButton;

    @FXML
    private Pane bottompane;

    @FXML
    private Text dbconnect;

    @FXML
    private TextField dburl;

    @FXML
    private TextField dbusername;

    @FXML
    private Button savebutton;

    @FXML
    private Button mainmenu;

    @FXML
    private Text invalid;

    @FXML
    private PasswordField dbpassword;

    private double x, y;

    @FXML
    public void initialize() throws SQLException {
        DBConnectionClass con = new DBConnectionClass();
        if(!con.isConnected()) {
            mainmenu.setVisible(false);
            dbconnect.setText("● Database not connected");
        }
        else dbconnect.setText("● Database connected");

        File file = new File("resources/mysql.properties");
        if(file.exists()) {
            try (InputStream input = new FileInputStream("resources/mysql.properties")) {

                Properties prop = new Properties();

                prop.load(input);

                dburl.setText(prop.getProperty("db.url"));
                dbusername.setText(prop.getProperty("db.user"));
                dbpassword.setText(prop.getProperty("db.password"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    void SaveButtonAction(ActionEvent event) {
        File file = new File("resources/mysql.properties");
        if(file.exists()) {
            try (OutputStream output = new FileOutputStream("resources/mysql.properties")) {

                Properties prop = new Properties();

                // set the properties value
                prop.setProperty("db.url", dburl.getText());
                prop.setProperty("db.user", dbusername.getText());
                prop.setProperty("db.password", dbpassword.getText());

                // save properties to project root folder
                prop.store(output, null);

                DBConnectionClass con = new DBConnectionClass();
                if(!con.isConnected()) {
                    invalid.setText("Error: MySQL settings saved but unable to connect.");
                    mainmenu.setVisible(false);
                    dbconnect.setText("● Database not connected");
                } else {
                    invalid.setText("Info: MySQL settings saved and successfully connected.");
                    mainmenu.setVisible(true);
                    dbconnect.setText("● Database connected");
                }
                invalid.setVisible(true);
            } catch (IOException | SQLException io) {
                io.printStackTrace();
            }
        }
    }

    @FXML
    void enterPasswordKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            savebutton.fire();
        }
    }

    @FXML
    void enterURLKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            savebutton.fire();
        }
    }

    @FXML
    void enterUsernameKey(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            savebutton.fire();
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
