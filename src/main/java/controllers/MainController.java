package controllers;

import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

import javafx.event.ActionEvent;


public class MainController {
    @FXML
    private Hyperlink signin_createaccount;

    @FXML
    private Button signin_loginbtn;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Hyperlink signup_alreadyhaveaccount;

    @FXML
    private Button signup_btn;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_username;

    private Connection connection;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet resultSet;

    public void signin() throws SQLException {
        String sql="SELECT * FROM admin WHERE username = ? and password = ?";
        connection = DatabaseConnection.getConnection();
        prepare = connection.prepareStatement(sql);
        prepare.setString(1, signin_username.getText());
        prepare.setString(2, signin_password.getText());
        resultSet = prepare.executeQuery();

        Alert alert;

        //check if username or password is Empty
        if(signin_username.getText().isEmpty()||signin_password.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username and password are required");
            alert.showAndWait();
        }
        else {
            if(resultSet.next()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully logged in");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Wrong username or password");
                alert.showAndWait();
            }
        }
    }
    //fenetre close + minimise
    public void SignIn_close(){
        System.exit(0);
    }
    public void SignIn_Minimise(){
        Stage stage=(Stage)signin_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void SignUp_close(){
        System.exit(0);
    }
    public void SignUp_Minimise(){
        Stage stage=(Stage)signup_form.getScene().getWindow();
        stage.setIconified(true);
    }

    //switch form from sign in to sign up et verse versa
    public void switchForm(ActionEvent event){
        if(event.getSource()==signin_createaccount){
            signin_form.setVisible(false);
            signup_form.setVisible(true);
        }else if(event.getSource()==signup_alreadyhaveaccount){
            signin_form.setVisible(true);
            signup_form.setVisible(false);
        }

    }

}
